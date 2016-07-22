package de.dhbw.wi13c.jguicreator.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.dhbw.wi13c.jguicreator.data.annotation.BarChart;
import de.dhbw.wi13c.jguicreator.data.annotation.PieChart;

public class Person {

	@NotNull
	@Size(min = 2)
	private String vorname;

	@NotNull
	@Size(min = 2)
	private final String nachname;

	private Integer anzahlKinder;

	@NotNull
	private Date Geburtsdatum;

	private Adresse adresse;

	private final List<Kontakt> kontaktDaten;

	@PieChart
	private Map<String, ? extends Number> einkommensZusammensetzung;

	@BarChart
	private Map<String, ? extends Number> einkommensEntwicklung;

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Integer getAnzahlKinder() {
		return anzahlKinder;
	}

	public void setAnzahlKinder(Integer anzahlKinder) {
		this.anzahlKinder = anzahlKinder;
	}

	public Date getGeburtsdatum() {
		return Geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		Geburtsdatum = geburtsdatum;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Map<String, ? extends Number> getEinkommensZusammensetzung() {
		return einkommensZusammensetzung;
	}

	public void setEinkommensZusammensetzung(
			Map<String, ? extends Number> einkommensZusammensetzung) {
		this.einkommensZusammensetzung = einkommensZusammensetzung;
	}

	public Map<String, ? extends Number> getEinkommensEntwicklung() {
		return einkommensEntwicklung;
	}

	public void setEinkommensEntwicklung(
			Map<String, ? extends Number> einkommensEntwicklung) {
		this.einkommensEntwicklung = einkommensEntwicklung;
	}

	public String getNachname() {
		return nachname;
	}

	public List<Kontakt> getKontaktDaten() {
		return kontaktDaten;
	}

	public Person(String vorname, String nachname, Integer anzahlKinder,
			Date geburtsdatum, Adresse adresse, List<Kontakt> kontaktDaten,
			Map<String, ? extends Number> einkommensZusammensetzung,
			Map<String, ? extends Number> einkommensEntwicklung) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.anzahlKinder = anzahlKinder;
		Geburtsdatum = geburtsdatum;
		this.adresse = adresse;
		this.kontaktDaten = kontaktDaten;
		this.einkommensZusammensetzung = einkommensZusammensetzung;
		this.einkommensEntwicklung = einkommensEntwicklung;
	}

	@Override
	public String toString() {
		return "Person [vorname=" + vorname + ", nachname=" + nachname
				+ ", anzahlKinder=" + anzahlKinder + ", Geburtsdatum="
				+ Geburtsdatum + ", adresse=" + adresse + ", kontaktDaten="
				+ kontaktDaten + ", einkommensZusammensetzung="
				+ einkommensZusammensetzung + ", einkommensEntwicklung="
				+ einkommensEntwicklung + "]";
	}

	// ..
}
