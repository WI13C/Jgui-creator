package de.dhbw.vs.jprakt.exam.testclasses;

public class Adresse {

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
