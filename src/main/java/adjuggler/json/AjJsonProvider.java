package adjuggler.json;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import adjuggler.json.databind.AjObjectMapper;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AjJsonProvider extends JacksonJsonProvider {

	private Logger log = LoggerFactory.getLogger(AjJsonProvider.class);

	public AjJsonProvider() {
		super(new AjObjectMapper());
		log.debug("constructing");
	}
}
