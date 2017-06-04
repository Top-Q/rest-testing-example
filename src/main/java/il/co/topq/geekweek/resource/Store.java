package il.co.topq.geekweek.resource;

import okhttp3.OkHttpClient;

public class Store extends AbstractResource {

	public Store(OkHttpClient client, String baseUrl) {
		super(client, baseUrl);
	}

	public Order order() {
		return new Order(client, baseUrl);
	}

}
