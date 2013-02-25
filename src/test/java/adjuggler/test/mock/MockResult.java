package adjuggler.test.mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.search.Result;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

@SuppressWarnings("rawtypes")
public class MockResult {
	Map.Entry entry;

	public MockResult(Map.Entry entry) {
		this.entry= entry;
	}

	public Result getMockResult(){
		Result result = mock(Result.class);
		when(result.getKey()).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return entry.getKey();
			}
		});
		when(result.getValue()).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return entry.getValue();
			}
		});
		return result;
	}

	static List<Result> getResults(Map map){
		List<Result> results = new ArrayList<Result>(map.size());
		for(Object entryObject : map.entrySet()){
			Map.Entry entry = (Map.Entry) entryObject;
			results.add(new MockResult(entry).getMockResult());
		}
		return results;
	}
}
