import java.util.*;
import ueb9.Punkt;

public interface PunktBaum {
	/**
	 * Einfügen eines Punktes. Falls schon ein Punkt mit gleichem x und y Wert
	 * enthalten war, dann wird der Punkt überschrieben (Rückgabe false).
	 * Ansonsten wird der Punkt neu eingefügt (Rückgabe true).
	 * @param p Punkt, der einzufügen ist
	 * @return true gdw Punkt wurde neu eingefügt, false sonst
	 */
	public boolean insert(Punkt p);

	/**
	 * Holen eines Punkts. Falls ein Punkt mit gleichem x und gleichem y Wert
	 * vorhanden ist, dann wird dieser zurückgegeben. Ansonsten wird null
	 * zurückgegeben.
	 * @param x int x Wert des gesuchten Punkts
	 * @param y int y Wert des gesuchten Punkts
	 * @return Punkt mit x und y Wert wenn vorhanden, null sonst
	 */
	public Punkt get(int x, int y);

	/**
	 * Suche den Punkt, der den geringsten Abstand zu einem Punkt p hat.
	 * @param p Punkt, von dem aus der nahste gesucht wird
	 * @return Punkt, der am nahsten zu p ist, falls mehr als ein Punkt im Baum
	 *         ist, null sonst
	 */
	public Punkt nahster(Punkt p);

	/**
	 * Suche den Punkt, der den geringsten Abstand zu einem (nicht
	 * notwendigerweise existierenden) Punkt p mit x Wert x und y Wert y
	 * hat.
	 * @param x int x Wert des (hypothetischen) Punkts, von dem der nahste gesucht wird
	 * @param y int y Wert des (hypothetischen) Punkts, von dem ner nahste gesucht wird
	 * @return Punkt, der am nahsten zu p ist, falls mehr als ein Punkt im Baum
	 *         ist, null sonst
	 */
	public Punkt nahster(int x, int y);

	/**
	 * Iterator über alle Punkte, deren x Wert zwischen
	 * xmin und xmax liegt (xmin <= x <= xmax) sowie y Wert zwischen
	 * ymin und ymax liegt (xmax <= y <= ymax).
	 * @param xmin int alle x Werte müssen größer gleich xmin sein
	 * @param xmax int alle x Werte müssen kleiner gleich xmax sein
	 * @param ymin int alle y Werte müssen größer gleich ymin sein
	 * @param ymax int alle y Werte müssen kleiner gleich ymax sein
	 * @return Iterator über alle Punkte mit x,y Werten, so dass xmin <= x <= xmax und ymin <= y <= ymax
	 */
	public Iterator<Punkt> iterator(int xmin, int  xmax, int ymin, int ymax);

	/**
	 * Iterator über alle Punkte, deren x Wert zwischen
	 * xmin und xmax liegt (xmin <= x <= xmax).
	 * @param xmin int alle x Werte müssen größer gleich xmin sein
	 * @param xmax int alle x Werte müssen kleiner gleich xmax sein
	 * @return Iterator über alle Punkte mit x Werten, so dass xmin <= x <= xmax
	 */
	public Iterator<Punkt> iteratorx(int xmin, int xmax);

	/**
	 * Iterator über alle Punkte, deren x Wert festliegt
	 * @param x int alle x Werte müssen gleich x sein
	 * @return Iterator über alle Punkte mit x Wert gleich x
	 */
	public Iterator<Punkt> iteratorx(int x);

	/**
	 * Iterator über alle Punkte, deren y Wert zwischen
	 * ymin und ymax liegt (xmax <= y <= ymax).
	 * @param ymin int alle y Werte müssen größer gleich ymin sein
	 * @param ymax int alle y Werte müssen kleiner gleich ymax sein
	 * @return Iterator über alle Punkte mit y Werten, so dass ymin <= y <= ymax
	 */
	public Iterator<Punkt> iteratory(int ymin, int ymax);

	/**
	 * Iterator über alle Punkte, deren y Wert festliegt
	 * @param y int alle y Werte müssen gleich y sein
	 * @return Iterator über alle Punkte mit y Werten gleich y
	 */
	public Iterator<Punkt> iteratory(int y);

}
