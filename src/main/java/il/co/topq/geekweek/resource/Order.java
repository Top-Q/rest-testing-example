package il.co.topq.geekweek.resource;

import java.io.IOException;

import il.co.topq.geekweek.model.OrderModel;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Order extends AbstractResource {

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

	public ResourceResponse<OrderModel> get(int id) throws IOException {
		// @formatter:off
		Request request = new Request.Builder()
		   .url(baseUrl + "store/order/" + id)
		   .get()
		   .addHeader("cache-control", "no-cache")
		   .build();
		// @formatter:on

		Response response = client.newCall(request).execute();
		return new ResourceResponse<OrderModel>(response, OrderModel.class);
	}

	public Response delete(int id) throws IOException {
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
