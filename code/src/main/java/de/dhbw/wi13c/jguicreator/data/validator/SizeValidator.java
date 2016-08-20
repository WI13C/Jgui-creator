package de.dhbw.wi13c.jguicreator.data.validator;

import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;

public class SizeValidator extends Validator<Object>
{

	UiElementData element;

	int min;

	int max;

	public SizeValidator(UiElementData element, int min, int max)
	{
		this.element = element;
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean validate()
	{
		String elementValue = element.getValue().toString();
		//System.out.println(elementValue);
		//System.out.println(min + "/" + max);
		if(min > 0)
		{
			if(elementValue.length() < min)
				return false;
		}
		if(max < Integer.MAX_VALUE)
		{
			if(elementValue.length() > max)
				return false;
		}
		return true;
	}

	@Override
	public String getMessage()
	{
		String returnString = "";
		if(min > 0)
		{
			if(max == Integer.MAX_VALUE)
			{
				returnString = "Die Eingabe muss mindestens " + min + " Zeichen lang sein";
			}
			else
			{
				returnString = "Die Eingabe muss zwischen " + min + " und " + max + " Zeichen lang sein";
			}
		}
		else if(max < Integer.MAX_VALUE)
		{
			returnString = "Die Eingabe darf maximal " + max + " Zeichen lang sein";
		}
		return returnString;
	}

	@Override
	public int compareTo(Validator<Object> o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
