package il.co.topq.geekweek.petstore;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import il.co.topq.geekweek.infra.RequestBodyRepository;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Test2PetStoreWithRequestRepository {

	private OkHttpClient client;
	
	private RequestBodyRepository repo;


	@Before
	public void setup() {
		client = new OkHttpClient();
		repo = new RequestBodyRepository();
	}

	@Test
	public void testAddOrder() throws IOException {
		// @formatter:off
		String petString = repo
				.get("pet")
				.setFirst("name", "Piky")
				.setAll("id", "1")
				.asString();
		// @formatter:on

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, petString);

		// @formatter:off
		Request request = new Request.Builder()
		  .url("http://petstore.swagger.io/v2/pet")
		  .post(body)
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .build();
		// @formatter:on
		
		Response response = client.newCall(request).execute();
		assertEquals(200, response.code());

		// @formatter:off
		String orderString = repo
				.get("order")
				.setFirst("id", "1")
				.setFirst("petId", "1")
				.asString();
		// @formatter:on

		
		
		mediaType = MediaType.parse("application/json");
		body = RequestBody.create(mediaType, orderString);

		// @formatter:off
		request = new Request.Builder()
		  .url("http://petstore.swagger.io/v2/store/order")
		  .post(body)
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .build();
		// @formatter:on

		response = client.newCall(request).execute();
		assertEquals(200, response.code());
		
		// @formatter:off
		request = new Request.Builder()
		  .url("http://petstore.swagger.io/v2/store/order/1")
		  .get()
		  .addHeader("cache-control", "no-cache")
		  .build();
		// @formatter:on

		response = client.newCall(request).execute();
		assertEquals(200, response.code());
		

	}

}
