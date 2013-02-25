package adjuggler.test.fixture;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class PersonsModule extends AbstractModule {

	static public Map<Integer,Person> personMap;

	@Provides
	public Map<Integer,Person> providePersonsMap(){
		return personMap;
	}

	@Override
	protected void configure() {	
		personMap = new HashMap<Integer,Person>();
		personMap.put(3,new Person("John","Smith","Captain",23333l));
		personMap.put(4,new Person("Wahunsenacawh","Tsenacommacah","Weroance",23334l));
		personMap.put(5,new Person("Thomas","Harriot","Explorer",23335l));
		personMap.put(6,new Person("John","White","Artist",23336l));
		personMap.put(7,new Person("Elizabeth","Tudor","Queen",23337l));
		personMap.put(8,new Person("Richard","Grenville","Commander",23338l));
		personMap.put(9,new Person("Ralph","Lane","Commander",23339l));
		personMap.put(0,new Person("Humphry","Gilbert","Explorer",23330l));
	};
}
