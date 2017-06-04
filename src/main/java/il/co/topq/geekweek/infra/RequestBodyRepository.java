package il.co.topq.geekweek.infra;

import java.io.IOException;

public class RequestBodyRepository {
	
	public Body get(String name) throws IOException{
		String body = ResourceUtils.resourceToString(name + ".json");
		return new Body(body);
	}
	
}
