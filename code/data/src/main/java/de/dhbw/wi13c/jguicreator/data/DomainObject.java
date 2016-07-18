package de.dhbw.wi13c.jguicreator.data;

public class DomainObject extends UiElement
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
