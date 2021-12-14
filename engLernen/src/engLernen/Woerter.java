package engLernen;

import java.io.Serializable;

/* this class is for creating an word-object */

public class Woerter implements Serializable {

	private String de;
	private String en;
	private String detB;

	public String getDe() {
		return de;
	}

	private void setDe(String de) {
		this.de = de;
	}

	public String getEn() {
		return en;
	}

	private void setEn(String en) {
		this.en = en;
	}

	public String getDetB() {
		return detB;
	}

	private void setDetB(String detB) {
		this.detB = detB;
	}

	public Woerter(String de, String en) {
		this.de = de;
		this.en = en;
		this.detB = "Kein Beispiel vorhanden";
	}

	public Woerter(String de, String en, String detBeschreibung) {
		this.de = de;
		this.en = en;
		this.detB = detBeschreibung;
	}

	public String toString() {
		return "deutsch:" + de + " en: " + en + ", bsp.: " + detB;
	}

}
