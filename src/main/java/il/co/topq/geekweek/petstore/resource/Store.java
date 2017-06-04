package il.co.topq.geekweek.petstore.resource;

import il.co.topq.geekweek.infra.AbstractResource;
import okhttp3.OkHttpClient;

public class Store extends AbstractResource {

	public Store(OkHttpClient client, String baseUrl) {
		super(client, baseUrl);
	}

	public Order order() {
		return new Order(client, baseUrl);
	}
	
	public Order order(int id) {
		return new Order(client, baseUrl, id);
	}

}
