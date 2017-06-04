package il.co.topq.geekweek.petstore;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import il.co.topq.geekweek.infra.RequestBodyRepository;
import il.co.topq.geekweek.petstore.model.OrderModel;
import il.co.topq.geekweek.petstore.resource.PetStore;
import okhttp3.Response;

public class TestPetStoreWithRequestRepository {
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

		OrderModel order = petStore.store().order(1).get().asObject();
		assertEquals(true, order.getComplete());

		response = petStore.store().order(1).delete();
		assertEquals(200, response.code());
		assertEquals("OK", response.message());

	}
}
