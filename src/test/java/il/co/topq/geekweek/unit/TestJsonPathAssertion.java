package il.co.topq.geekweek.unit;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import il.co.topq.geekweek.infra.ResourceUtils;

public class TestJsonPathAssertion {
	
	@Test
	public void testAssertWithJsonPath() throws IOException{
		String petJson = ResourceUtils.resourceToString("pet.json");
		assertThat(petJson, hasJsonPath("$.status"));
		assertThat(petJson, hasJsonPath("$.status", equalTo("available")));	
	}
}
