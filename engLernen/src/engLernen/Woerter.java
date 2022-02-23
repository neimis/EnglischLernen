package engLernen;

import java.io.Serializable;

/**
 * this class is for creation an word-object, which implements serializable interface
 * for reading Woerter Objects from the file
 * 
 * @author Julius Neimantas
 */

public class Woerter implements Serializable {
	/**
	 * german translation
	 */
	private String de;
	/**
	 * english translation
	 */
	private String en;
	/**
	 * example sentence in english
	 */
	private String detB;

	/**
	 * getter
	 * 
	 * @return - german translation
	 */
	public String getDe() {
		return de;
	}

	/**
	 * setter
	 * 
	 * @param en - german translation
	 */
	private void setDe(String de) {
		this.de = de;
	}

	/**
	 * getter
	 * 
	 * @return - english translation
	 */
	public String getEn() {
		return en;
	}

	/**
	 * setter
	 * 
	 * @param en - english translation
	 */
	private void setEn(String en) {
		this.en = en;
	}

	/**
	 * getter
	 * 
	 * @return - example sentence in english
	 */
	public String getDetB() {
		return detB;
	}

	/**
	 * setter
	 * 
	 * @param detB - example sentence in english
	 */
	private void setDetB(String detB) {
		this.detB = detB;
	}

	/**
	 * constructor for creating an Woerter object, the following parameters are
	 * instantiated:
	 * 
	 * @param de - german translation
	 * @param en - english translation
	 */
	public Woerter(String de, String en) {
		this.de = de;
		this.en = en;
		this.detB = "Kein Beispiel vorhanden";
	}

	/**
	 * constructor for creating an Woerter object, the following parameters are
	 * instantiated:
	 * 
	 * @param de       - german translation
	 * @param en       - english translation
	 * @param detBesch - example sentence in english
	 */

	public Woerter(String de, String en, String detBesch) {
		this.de = de;
		this.en = en;
		this.detB = detBesch;
	}

	public String toString() {
		return "deutsch:" + de + " en: " + en + ", bsp.: " + detB;
	}

}
