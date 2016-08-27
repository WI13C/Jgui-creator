package de.dhbw.wi13c.jguicreator.data.annotation;

public class AnnotationMessage
{
	public static final String PATTERN_VALIDATOR_MESSAGE = "Pattern nicht erfuellt.";

	public static final String NOTNULL_VALIDATOR_MESSAGE = "Eingegebener Wert darf nicht leer sein!";
		
	public static final String INTEGER_VALIDATOR_MESSAGE = "Die eingegebene Zahl darf kein Komma enthalten und muss zwischen " + Integer.MIN_VALUE + " und " + Integer.MAX_VALUE + " liegen.";

	public static final String DOUBLE_VALIDATOR_MESSAGE = "Die eingegebene Zahl muss " + Double.MIN_VALUE + " und " + Double.MAX_VALUE + " liegen.";

}
