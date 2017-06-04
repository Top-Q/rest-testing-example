package il.co.topq.geekweek.infra;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Response;

public class EntityResponse<T> {

	private final Response response;

	private final Class<T> clazz;

	public EntityResponse(Response response, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		this.response = response;
		this.clazz = clazz;
	}

	public T asObject() throws IOException {
		return new ObjectMapper().readValue(response.body().string(), clazz);
	}

	public String asString() throws IOException {
		return response.body().string();
	}

	@Override
	public String toString() {
		try {
			return asString();
		} catch (IOException e) {
			return super.toString();
		}
	}

}
