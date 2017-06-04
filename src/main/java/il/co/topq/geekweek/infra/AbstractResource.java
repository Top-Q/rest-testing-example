package il.co.topq.geekweek.infra;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;

public abstract class AbstractResource {

	protected final OkHttpClient client;

	protected final String baseUrl;

	protected final ObjectMapper mapper;

	public AbstractResource(OkHttpClient client, String baseUrl) {
		super();
		this.client = client;
		this.baseUrl = baseUrl;
		mapper = new ObjectMapper();

	}

}
