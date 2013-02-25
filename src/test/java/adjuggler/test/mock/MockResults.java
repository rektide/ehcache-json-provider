package adjuggler.test.mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

@SuppressWarnings("rawtypes")
public class MockResults {
	protected Map map;

	public MockResults(Map map){
		this.map= map;
	}

	public Results getMockResults(){
		Results results = mock(Results.class);
		when(results.size()).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return map.size();
			}
		});
		when(results.all()).thenAnswer(new Answer<List<Result>>() {
			public List<Result> answer(InvocationOnMock invocation) throws Throwable {
				return MockResult.getResults(map);
			}
		});
		return results;
	}
}
