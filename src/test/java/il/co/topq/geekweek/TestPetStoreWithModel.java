package il.co.topq.geekweek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import il.co.topq.geekweek.model.Category;
import il.co.topq.geekweek.model.OrderModel;
import il.co.topq.geekweek.model.PetModel;
import il.co.topq.geekweek.resource.PetStore;
import okhttp3.Response;

public class TestPetStoreWithModel {
	
	private PetStore petStore;
	
	@Before
	public void setup(){
		petStore = new PetStore("v2","http://petstore.swagger.io");
	}
	
	@Test
	public void testAddOrder() throws Exception{
		PetModel pet = new PetModel();
		pet.setId(1);
		Category category = new Category();
		category.setId(1);
		pet.setName("Piki");
		pet.setStatus("available");
		
		Response response = petStore.pet().post(pet);
		Assert.assertEquals(200, response.code());
		
		OrderModel order = new OrderModel();
		order.setId(1);
		order.setPetId(1);
		order.setQuantity(1);
		order.setShipDate("2018-06-04T07:22:38.179Z");
		order.setStatus("placed");
		order.setComplete(true);
		
		response = petStore.store().order().post(order);
		Assert.assertEquals(200, response.code());
		
		order = petStore.store().order().get(1).asObject();
		Assert.assertEquals(true, order.getComplete());
		
		response = petStore.store().order().delete(1);
		Assert.assertEquals(200, response.code());
		Assert.assertEquals("OK", response.message());
		
		
		
		
	}
	
}
