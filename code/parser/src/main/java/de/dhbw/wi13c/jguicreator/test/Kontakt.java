package de.dhbw.wi13c.jguicreator.test;

import javax.validation.constraints.Pattern;
import de.dhbw.wi13c.jguicreator.data.annotation.Id;

public class Kontakt {

	@Id
	private String typ;

	private String telefon;

	@Pattern(regexp = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b")
	private String email;

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Kontakt(String typ, String telefon, String email) {
		super();
		this.typ = typ;
		this.telefon = telefon;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Kontakt [typ=" + typ + ", telefon=" + telefon + ", email="
				+ email + "]";
	}

}
