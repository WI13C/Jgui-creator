package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.Dataset;
import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class DomainObject extends UiElementData
{
	private Dataset dataset;

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

	public Dataset getDataset()
	{
		return dataset;
	}

	public void setDataset(Dataset dataset)
	{
		this.dataset = dataset;
	}

}
