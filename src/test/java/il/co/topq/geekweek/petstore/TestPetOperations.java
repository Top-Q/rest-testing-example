package il.co.topq.geekweek.petstore;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import il.co.topq.geekweek.infra.RequestBodyRepository;
import il.co.topq.geekweek.petstore.resource.PetStore;
import okhttp3.Response;

public class TestPetOperations {
	private PetStore petStore;

	private RequestBodyRepository repo;

	@Before
	public void setup() {
		petStore = new PetStore("v2", "http://petstore.swagger.io");
		repo = new RequestBodyRepository();
	}

	@Test
	public void testPetCrud() throws Exception {
		int petId = 2;

		// @formatter:off
		String petString = repo
				.get("pet")
				.setFirst("name", "Piky")
				.setAll("id", petId)
				.asString();
		// @formatter:on

		petStore.pet().post(petString);

		String pet = petStore.pet(petId).get().asString();

		assertThat(pet, hasJsonPath("$.name", equalTo("Piky")));

		Response response = petStore.pet(2).delete();
		assertEquals(200, response.code());
		
		String getResponse = petStore.pet(petId).get().asString();
		assertThat(getResponse, hasJsonPath("$.code", equalTo(1)));
		assertThat(getResponse, hasJsonPath("$.message", equalTo("Pet not found")));

	}
}
