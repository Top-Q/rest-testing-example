package il.co.topq.geekweek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import il.co.topq.geekweek.infra.RequestBodyRepository;
import il.co.topq.geekweek.model.Category;
import il.co.topq.geekweek.model.OrderModel;
import il.co.topq.geekweek.model.PetModel;
import il.co.topq.geekweek.resource.PetStore;
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
		Assert.assertEquals(200, response.code());

		// @formatter:off
		String orderString = repo
				.get("order")
				.setFirst("id", "1")
				.setFirst("petId", "1")
				.asString();
		// @formatter:on

		response = petStore.store().order().post(orderString);
		Assert.assertEquals(200, response.code());

		OrderModel order = petStore.store().order().get(1).asObject();
		Assert.assertEquals(true, order.getComplete());

		response = petStore.store().order().delete(1);
		Assert.assertEquals(200, response.code());
		Assert.assertEquals("OK", response.message());

	}
}
