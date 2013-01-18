package adjuggler.json.databind;

import adjuggler.json.ser.ResultsSerializer;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

public class AjObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -3916807945220852788L;

	public AjObjectMapper() {

		// Safe and stable:
		//final Version version = new Version(1, 0, 0, null, "adjuggler", AjObjectMapper.class.getSimpleName());
		//final SimpleModule module = new SimpleModule("AjcoJsonModule", version);

		// Fast and dangerous:
		final AfterburnerModule module = new AfterburnerModule(); 
		module.addSerializer(new ResultsSerializer());
		this.registerModule(module);
	}
}
