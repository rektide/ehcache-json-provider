package adjuggler.test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.ehcache.search.Results;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import adjuggler.test.fixture.Person;
import adjuggler.test.fixture.PersonsModule;

import com.google.inject.Inject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

@RunWith(Arquillian.class)
public class EhCacheJsonProviderTest {

	private static Logger log = LoggerFactory.getLogger(EhCacheJsonProviderTest.class);
	private static GenericType<Person> personType = new GenericType<Person>(){};
	private static GenericType<Results> resultsType = new GenericType<Results>(){};

	@Deployment(testable = false)
	public static Archive<?> createDeployment(){
		return ShrinkWrap.create(WebArchive.class)
				.addPackage("adjuggler.json")
				.addPackage("adjuggler.json.databind")
				.addPackage("adjuggler.json.ser")
				.addPackage("adjuggler.test")
				.addPackage("adjuggler.test.fixture")
				.addPackage("adjuggler.test.mock")
				.setWebXML(new File("src/test/webapp/WEB-INF/web.xml"));
	}

	@Test @GET @Path("test/person/queen") @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public void testPerson(ClientResponse<Person> results){
		Assert.assertNotNull(results);
		Person person = results.getEntity(personType);
		Assert.assertNotNull(person);
		Assert.assertEquals("Queen", person.getRank());
		Assert.assertEquals("Elizabeth", person.getFirstName());
	}

	@Test
	public void testPersonResults(@ArquillianResource URL baseUrl){
		log.info("base: "+baseUrl);
		final String json = RestAssured.get(baseUrl.toString()+"test/persons").asString();
		final JsonPath jsonPath = JsonPath.from(json);
		final List ranks = jsonPath.get("rank");

		final List<String> expectedRanks = getRanks();
		for(int i = 0; i < ranks.size(); ++i){
			final boolean hadRank = expectedRanks.remove(ranks.get(i));
			Assert.assertTrue("Had rank: "+ranks.get(i),hadRank);
		}
		Assert.assertEquals("Unclaimed ranks", 0, expectedRanks.size());
	}

	private List<String> getRanks(){
		List<String> expectedRanks = new ArrayList<String>();
		// option #1: Arrays.asList(new String[] {"Explorer", "Captain", "Weroance", "Explorer", "Artist", "Queen", "Commander", "Commander" });
		// option #2: find some path for sharing the Guice Injector with RestEasy.
		// option #3: load modules afresh in the Test.
		// option #3: hack, static on Person:
		for(Person person : PersonsModule.personMap.values()){
			expectedRanks.add(person.getRank());
		}
		return expectedRanks;
	}
}
