package adjuggler.test;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.ehcache.search.Results;
import adjuggler.test.fixture.Person;
import adjuggler.test.mock.MockResults;

import com.google.inject.Inject;

@Path("/test")
public class EhCacheJsonProviderTestEndpoint {

	@Inject Map<Integer,Person> personMap;

	@GET
	@Path("/person/queen")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(){
		return personMap.get(7);
	}

	@GET
	@Path("/persons")
	@Produces(MediaType.APPLICATION_JSON)
	public Results getPersonResult(){
		return new MockResults(personMap).getMockResults();
	}
}
