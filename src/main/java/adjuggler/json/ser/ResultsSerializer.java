package adjuggler.json.ser;

import java.io.IOException;
import java.util.List;

import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ResultsSerializer extends StdSerializer<Results>  {

	private Logger log = LoggerFactory.getLogger(ResultsSerializer.class);

	public ResultsSerializer() {
		super(Results.class);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void serialize(Results results, JsonGenerator out, SerializerProvider serProvider) throws IOException, JsonGenerationException {
		log.debug("Serializing results object");
		out.writeStartArray();
		if(results.size() > 0) {
			final List<Result> all = results.all();

			// get result type serializer
			final Class valueClass = all.get(0).getValue().getClass();
			JsonSerializer elementSer= serProvider.findValueSerializer(valueClass, null);

			// run through all results
			for(Result result : all) {
				elementSer.serialize(result.getValue(), out, serProvider);
			}
		}
		out.writeEndArray();
	}

}
