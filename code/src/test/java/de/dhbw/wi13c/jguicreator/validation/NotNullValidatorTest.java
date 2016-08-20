package de.dhbw.wi13c.jguicreator.validation;

import org.junit.Assert;
import org.junit.Test;

import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
import de.dhbw.wi13c.jguicreator.data.validator.NotNullValidator;

public class NotNullValidatorTest {

	@Test
	public void valueNotNullTest() {
		// arrange
		UiElementData<String> data = new TextfieldData();
		data.setValue("NotNull");
		NotNullValidator notNullValidator = new NotNullValidator(data);

		// act, assert
		Assert.assertTrue(notNullValidator.validate());

	}

	@Test
	public void valueIsNullTest() {
		// arrange
		UiElementData<String> data = new TextfieldData();
		data.setValue(null);
		NotNullValidator notNullValidator = new NotNullValidator(data);

		// act, assert
		Assert.assertFalse(notNullValidator.validate());

	}

}
