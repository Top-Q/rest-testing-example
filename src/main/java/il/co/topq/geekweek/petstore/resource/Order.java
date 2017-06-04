package il.co.topq.geekweek.petstore.resource;

import java.io.IOException;

import il.co.topq.geekweek.infra.AbstractResource;
import il.co.topq.geekweek.infra.EntityResponse;
import il.co.topq.geekweek.petstore.model.OrderModel;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Order extends AbstractResource {

	private Integer id;
	
	public Order(OkHttpClient client, String baseUrl, int id) {
		super(client, baseUrl);
		this.id = id;
	}

	public Order(OkHttpClient client, String baseUrl) {
		super(client, baseUrl);
	}

	
	public Response post(String orderJson) throws IOException {
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, orderJson);

		// @formatter:off
		Request request = new Request.Builder()
		  .url(baseUrl + "store/order")
		  .post(body)
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .build();
		// @formatter:on

		return client.newCall(request).execute();

	}

	public Response post(OrderModel order) throws IOException {
		return post(mapper.writeValueAsString(order));
	}

	public EntityResponse<OrderModel> get() throws IOException {
		if (null == id){
			throw new IllegalStateException("Id was not set");
		}
		// @formatter:off
		Request request = new Request.Builder()
		   .url(baseUrl + "store/order/" + id)
		   .get()
		   .addHeader("cache-control", "no-cache")
		   .build();
		// @formatter:on

		Response response = client.newCall(request).execute();
		return new EntityResponse<OrderModel>(response, OrderModel.class);
	}

	public Response delete() throws IOException {
		if (null == id){
			throw new IllegalStateException("Id was not set");
		}

		// @formatter:off
		Request request = new Request.Builder()
		   .url(baseUrl + "store/order/" + id)
		   .delete()
		   .addHeader("cache-control", "no-cache")
		   .build();
		// @formatter:on

		return client.newCall(request).execute();
	}

}
