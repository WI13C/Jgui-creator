package de.dhbw.wi13c.jguicreator.gui.helper;

public class KeyValue {
	private String key;

	private double value;
	
	public String getKey() {
		return this.key;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public KeyValue(String key, double value) {
		this.key = key;
		this.value = value;
	}

}
