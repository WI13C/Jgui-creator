package de.dhbw.wi13c.jguicreator.test;

import de.dhbw.wi13c.jguicreator.data.annotation.FieldLabel;

public class Adresse {

	@FieldLabel(name="Straße")
	private String strasse;
	
	private Integer Hausnummer;

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public Integer getHausnummer() {
		return Hausnummer;
	}

	public void setHausnummer(Integer hausnummer) {
		Hausnummer = hausnummer;
	}

	public Adresse(String strasse, Integer hausnummer) {
		super();
		this.strasse = strasse;
		Hausnummer = hausnummer;
	}

	@Override
	public String toString() {
		return "Adresse [strasse=" + strasse + ", Hausnummer=" + Hausnummer
				+ "]";
	}
}
