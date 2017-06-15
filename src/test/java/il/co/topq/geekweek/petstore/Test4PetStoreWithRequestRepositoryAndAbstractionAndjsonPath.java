package il.co.topq.geekweek.petstore;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import il.co.topq.geekweek.infra.RequestBodyRepository;
import il.co.topq.geekweek.petstore.resource.PetStore;
import okhttp3.Response;

public class Test4PetStoreWithRequestRepositoryAndAbstractionAndjsonPath {
	private PetStore petStore;

	private RequestBodyRepository repo;

	@Before
	public void setup() {
		petStore = new PetStore("v2", "http://petstore.swagger.io");
		repo = new RequestBodyRepository();
	}

	@Test
	public void testAddOrder() throws Exception {

		// @formatter:off
		String petString = repo
				.get("pet")
				.setFirst("name", "Piky")
				.setAll("id", "1")
				.asString();
		// @formatter:on

		Response response = petStore.pet().post(petString);
		assertEquals(200, response.code());

		// @formatter:off
		String orderString = repo
				.get("order")
				.setFirst("id", "1")
				.setFirst("petId", "1")
				.asString();
		// @formatter:on

		response = petStore.store().order().post(orderString);
		assertEquals(200, response.code());

		String order = petStore.store().order(1).get().asString();

		assertThat(order, hasJsonPath("$.complete", equalTo(true)));

		response = petStore.store().order(1).delete();
		assertEquals(200, response.code());
		assertEquals("OK", response.message());

	}
}
