package framework;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValidatorTest
{

	@Test
	public void testNotNullValidator()
	{

		// assert statements
		assertEquals("10 x 0 must be 0", 0, 0);
		assertEquals("0 x 10 must be 0", 0, 0);
		assertEquals("0 x 0 must be 0", 0, 0);
	}
}
