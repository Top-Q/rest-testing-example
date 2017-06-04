package il.co.topq.geekweek.infra;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Body {

	private final String bodyString;

	public Body(String bodyString) {
		this.bodyString = bodyString;
	}

	public Body setFirst(String key, String value) {
		String newBodyString = bodyString.replace("${" + key + "}", value);
		return new Body(newBodyString);
	}

	public Body setAll(String key, String value) {
		String newBodyString = bodyString.replaceAll("\\$\\{" + key + "\\}", value);
		return new Body(newBodyString);
	}

	public RequestBody asRequest() {
		MediaType mediaType = MediaType.parse("application/json");
		return RequestBody.create(mediaType, bodyString);
	}

	public String asString() {
		if (bodyString.contains("${")) {
			throw new IllegalStateException("Request in not ready");
		}
		return bodyString;
	}

	@Override
	public String toString() {
		return bodyString;
	}

}
