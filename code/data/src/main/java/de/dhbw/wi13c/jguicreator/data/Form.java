package de.dhbw.wi13c.jguicreator.data;

/**
 * Framework neutral class representing a Form.
 * 
 * !No Swing Components here
 * 
 * @author Robin Sadlo
 * @deprecated Datastructure is now represented by a root-DomainObject which serves as entry point to 
 * 	get its UiElements by calling getDataset()
 */
public abstract class Form
{
	String title = "";
}
