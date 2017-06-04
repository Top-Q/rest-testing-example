package il.co.topq.geekweek.infra;

import java.io.IOException;
import java.util.Scanner;

public class ResourceUtils {

	private ResourceUtils() {
		// Static class
	}

	public static String resourceToString(final String resourceName) throws IOException {
		try (Scanner s = new Scanner(ResourceUtils.class.getClassLoader().getResourceAsStream(resourceName))) {
			s.useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
	}

}
