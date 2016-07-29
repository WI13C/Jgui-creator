package de.dhbw.wi13c.jguicreator.listener;

import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;

public interface GuiListener
{
	public void guiSaved(DomainObject domainObject);
	public void guiCanceled(DomainObject domainObject);

	
}
