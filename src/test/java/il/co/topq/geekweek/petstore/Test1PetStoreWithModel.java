package il.co.topq.geekweek.petstore;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import il.co.topq.geekweek.petstore.model.Category;
import il.co.topq.geekweek.petstore.model.OrderModel;
import il.co.topq.geekweek.petstore.model.PetModel;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Test1PetStoreWithModel {

	private OkHttpClient client;
	
	private ObjectMapper mapper;


	@Before
	public void setup() {
		client = new OkHttpClient();
		mapper = new ObjectMapper();
	}

	@Test
	public void testAddOrder() throws IOException {
		PetModel pet = new PetModel();
		pet.setId(1);
		Category category = new Category();
		category.setId(1);
		pet.setName("Piki");
		pet.setStatus("available");
		
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, mapper.writeValueAsString(pet));

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

		OrderModel order = new OrderModel();
		order.setId(1);
		order.setPetId(1);
		order.setQuantity(1);
		order.setShipDate("2018-06-04T07:22:38.179Z");
		order.setStatus("placed");
		order.setComplete(true);
		
		
		mediaType = MediaType.parse("application/json");
		body = RequestBody.create(mediaType, mapper.writeValueAsString(order));

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
