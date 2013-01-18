package adjuggler.json;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import adjuggler.json.databind.AjObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class AjContextResolver {

	private Logger log = LoggerFactory.getLogger(AjContextResolver.class);

    private ObjectMapper objectMapper;

    public AjContextResolver() throws Exception {
        log.debug("constructing");
    	this.objectMapper = new AjObjectMapper();
    }
    public ObjectMapper getContext(Class<?> objectType) {
    	log.debug("looking up "+objectType.getCanonicalName());
    	return objectMapper;
    }
}