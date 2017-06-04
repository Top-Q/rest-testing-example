package il.co.topq.geekweek.petstore.resource;

import java.io.IOException;

import il.co.topq.geekweek.infra.AbstractResource;
import il.co.topq.geekweek.infra.EntityResponse;
import il.co.topq.geekweek.petstore.model.PetModel;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Pet extends AbstractResource {

	private Integer id;
	
	public Pet(OkHttpClient client, String baseUrl) {
		super(client, baseUrl);
	}
	
	public Pet(OkHttpClient client, String baseUrl, int id) {
		super(client, baseUrl);
		this.id = id;
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
	
	public EntityResponse<PetModel> get() throws IOException {
		if (null == id){
			throw new IllegalStateException("Id was not set");
		}
		// @formatter:off
		Request request = new Request.Builder()
		   .url(baseUrl + "pet/" + id)
		   .get()
		   .addHeader("cache-control", "no-cache")
		   .build();
		// @formatter:on

		Response response = client.newCall(request).execute();
		return new EntityResponse<PetModel>(response, PetModel.class);
	}
	
	public Response delete() throws IOException {
		if (null == id){
			throw new IllegalStateException("Id was not set");
		}
		// @formatter:off
		Request request = new Request.Builder()
		   .url(baseUrl + "pet/" + id)
		   .delete()
		   .addHeader("cache-control", "no-cache")
		   .build();
		// @formatter:on

		return client.newCall(request).execute();
	}
	

}
