package il.co.topq.geekweek.resource;

import java.io.IOException;

import il.co.topq.geekweek.model.PetModel;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Pet extends AbstractResource {

	public Pet(OkHttpClient client, String baseUrl) {
		super(client, baseUrl);
	}

	public Response post(String petJson) throws IOException {
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, petJson);

		// @formatter:off
		Request request = new Request.Builder()
		  .url(baseUrl + "pet")
		  .post(body)
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .build();
		// @formatter:on

		return client.newCall(request).execute();

	}

	public Response post(PetModel pet) throws IOException {
		return post(mapper.writeValueAsString(pet));
	}

}
