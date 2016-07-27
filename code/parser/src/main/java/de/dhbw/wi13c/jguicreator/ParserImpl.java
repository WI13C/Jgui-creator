package de.dhbw.wi13c.jguicreator;

import de.dhbw.wi13c.jguicreator.data.Datafield;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;

public class ParserImpl implements Parser
{

	@Override
	public DomainObject parseObject(Object object)
	{
		// TODO Auto-generated method stub
		
		DomainObject domainObject = new DomainObject();
		Datafield<String> textDatafield = new Datafield<>();
		domainObject.setDatafield(textDatafield);
		
		return domainObject;
	}

}
