package il.co.topq.geekweek.resource;

import okhttp3.OkHttpClient;

public class PetStore {

	private final OkHttpClient client;

	private final String baseUrl;

	public PetStore(String version, String URL) {
		client = new OkHttpClient();
		this.baseUrl = URL + "/" + version + "/";
	}

	public Pet pet() {
		return new Pet(client, baseUrl);
	}

	public Store store() {
		return new Store(client, baseUrl);
	}

}
