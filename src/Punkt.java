/**
 * Punkt repräsentiert einen unveränderlichen Datensatz mit zwei
 * zu diskrimierenden Integer Werten und eine String Nutzinformation
 */
public class Punkt {
	private int x;
	private int y;
	private String text;

	public Punkt(int x, int y, String text) {
		this.x = x;
		this.y = y;
		this.text = text;
	}

	/**
	 * @return x Wert des Punkts
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return y Wert des Punkts
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return Text des Punkts
	 */
	public String getText() {
		return text;
	}

	/**
	 * Abstand des Punkts zu einem anderen Punkt p.
	 * @see #getAbstand(int, int)
	 * @param p Punkt zu dem der Abstand berechnet wird
	 * @return double Abstand
	 */
	public double getAbstand(Punkt p) {
		return getAbstand(p.x, p.y);
	}

	/**
	 * Abstand des Punkts zu einem anderen Punkt p.
	 * Die Wurzel der Summe der quadratischen Abstände
	 * der Differenzen der x und y Werte.
	 * @param x int x Wert des anderen Punkts p
	 * @param y int y Wert des anderen Punkts p
	 * @return double Abstand
	 */
	public double getAbstand(int x, int y) {
		double adx = this.x - x;
		double ady = this.y - y;
		return Math.sqrt(adx*adx + ady*ady);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punkt other = (Punkt) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public String toString() {
		return String.format("%s(%d,%d)", text, x, y);
	}

}
