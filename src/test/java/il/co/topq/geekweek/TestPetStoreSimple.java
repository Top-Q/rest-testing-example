package il.co.topq.geekweek;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestPetStoreSimple {

	private OkHttpClient client;

	private final static String ADD_PET_REQ = "{\r\n  \"id\": 1,\r\n  \"category\": {\r\n    \"id\": 0,\r\n    \"name\": \"string\"\r\n  },\r\n  \"name\": \"doggie\",\r\n  \"photoUrls\": [\r\n    \"string\"\r\n  ],\r\n  \"tags\": [\r\n    {\r\n      \"id\": 0,\r\n      \"name\": \"string\"\r\n    }\r\n  ],\r\n  \"status\": \"available\"\r\n}";

	private final static String ADD_ORDER_REQ = "{\r\n  \"id\": 1,\r\n  \"petId\": 1,\r\n  \"quantity\": 1,\r\n  \"shipDate\": \"2017-05-23T07:15:51.126Z\",\r\n  \"status\": \"placed\",\r\n  \"complete\": true\r\n}";

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
		Assert.assertEquals(200, response.code());

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
		Assert.assertEquals(200, response.code());
		
		// @formatter:off
		request = new Request.Builder()
		  .url("http://petstore.swagger.io/v2/store/order/1")
		  .get()
		  .addHeader("cache-control", "no-cache")
		  .build();
		// @formatter:on

		response = client.newCall(request).execute();
		Assert.assertEquals(200, response.code());
		

	}

}
