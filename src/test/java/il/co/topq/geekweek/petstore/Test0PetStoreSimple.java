package il.co.topq.geekweek.petstore;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Test0PetStoreSimple {

	private OkHttpClient client;

	private final static String ADD_PET_REQ = "{\"id\": 1,\"category\": {\"id\": 0,\"name\": \"string\"},\"name\": \"doggie\",\"photoUrls\": [\"string\"],\"tags\": [{ \"id\": 0,\"name\": \"string\"}], \"status\": \"available\"}";

	private final static String ADD_ORDER_REQ = "{   \"id\": 1,   \"petId\": 1,   \"quantity\": 1,   \"shipDate\": \"2017-05-23T07:15:51.126Z\",   \"status\": \"placed\",   \"complete\": true }";

	@Before
	public void setup() {
		client = new OkHttpClient();
	}

	@Test
	public void testAddOrder() throws IOException {
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, ADD_PET_REQ);

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

		mediaType = MediaType.parse("application/json");
		body = RequestBody.create(mediaType, ADD_ORDER_REQ);

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
