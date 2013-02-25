package adjuggler.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;

public class EhCacheJsonProviderTestModule extends AbstractModule {

	private static Logger log = LoggerFactory.getLogger(EhCacheJsonProviderTestModule.class);

	@Override
	protected void configure() {
		this.bind(EhCacheJsonProviderTestEndpoint.class);
		log.info("Finished binding Endpoint");
	}
}
