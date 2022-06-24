import java.util.Iterator;
// import ueb9.Punkt;

public class ZweiDBaum implements PunktBaum {
    private Knoten root;
    
    private class Knoten {
        Knoten left;
        Punkt punkt;
        Knoten right;
        boolean xOderY;

        public Knoten(Knoten left, Punkt punkt, Knoten right, boolean x) {
            this.left = left;
            this.punkt = punkt;
            this.right = right;
            this.xOderY = x;
        }

        /**
        public Knoten getLeft() {
            return left;
        }

        public void setLeft(Knoten left) {
            this.left = left;
        }

        public Punkt getPunkt() {
            return punkt;
        }

        public void setPunkt(Punkt punkt) {
            this.punkt = punkt;
        }

        public Knoten getRight() {
            return right;
        }

        public void setRight(Knoten right) {
            this.right = right;
        }

        public boolean isX() {
            return xOderY;
        }

        public void setXOderY(boolean x) {
            this.xOderY = x;
        }
        */
        
        public int getX() {
        	return punkt.getX();
        }
        
        public int getY() {
        	return punkt.getY();
        }
       
    }

    public ZweiDBaum() {
        root = null;
    }

    public ZweiDBaum(ZweiDBaum left, Punkt punkt, ZweiDBaum right, boolean x) {
        root = new Knoten(left.root, punkt, right.root, x);
    }

    /**
     * Einfügen eines Punktes. Falls schon ein Punkt mit gleichem x und y Wert
     * enthalten war, dann wird der Punkt überschrieben (Rückgabe false).
     * Ansonsten wird der Punkt neu eingefügt (Rückgabe true).
     *
     * @param p Punkt, der einzufügen ist
     * @param inserted[0] true, wenn neuer Punkt; false, wenn �berschrieben
     * @param inserted[1] true, wenn eingef�gt, sonst false
     * @return true gdw Punkt wurde neu eingefügt, false sonst
     */
    @Override
    public boolean insert(Punkt p) {
    	boolean inserted[] = new boolean[2];
    	inserted[1] = false;
    	Knoten aktKnoten = root;
    	while(!inserted[1]) {
    		aktKnoten = compareCoord(p, inserted, aktKnoten);
    	}
        return inserted[0];
    }

	private Knoten compareCoord(Punkt p, boolean[] inserted, Knoten aktKnoten) {
		if(aktKnoten.xOderY) {
			if(p.getX() <= aktKnoten.getX()) {
				//links
				aktKnoten = checkNext(p, aktKnoten, inserted, Richtung.LINKS);
			} else {
				//rechts
				aktKnoten = checkNext(p, aktKnoten, inserted, Richtung.RECHTS);
			}
		} else {
			if(p.getY() <= aktKnoten.getY()) {
				//links
				aktKnoten = checkNext(p, aktKnoten, inserted, Richtung.LINKS);
			} else {
				//rechts
				aktKnoten = checkNext(p, aktKnoten, inserted, Richtung.RECHTS);
			}
		}
        return aktKnoten;
	}

    /**
     * 
     * @param p
     * @param aktKnoten
     * @param richtung true = links
     * @return
     */
	private Knoten checkNext(Punkt p, Knoten aktKnoten, boolean[] inserted, Richtung r) {
		if(r == Richtung.LINKS) {
			if(aktKnoten.left == null) {
				aktKnoten.left = new Knoten(null, p, null, !aktKnoten.xOderY);
				inserted[0] = true;
				inserted[1] = true;
			} else if(aktKnoten.left.punkt.equals(p)) {
				aktKnoten.left.punkt = p;
				inserted[0] = false;
				inserted[1] = true;
			} else {
				aktKnoten = aktKnoten.left;
				inserted[1] = false;
			}
		} else {
			if(aktKnoten.right == null) {
				aktKnoten.right = new Knoten(null, p, null, !aktKnoten.xOderY);
				inserted[0] = true;
				inserted[1] = true;
			} else if(aktKnoten.right.punkt.equals(p)) {
				aktKnoten.right.punkt = p;
				inserted[0] = false;
				inserted[1] = true;
			} else {
				aktKnoten = aktKnoten.right;
				inserted[1] = false;
			}
		}
        return aktKnoten;
	}

    /**
     * Holen eines Punkts. Falls ein Punkt mit gleichem x und gleichem y Wert
     * vorhanden ist, dann wird dieser zurückgegeben. Ansonsten wird null
     * zurückgegeben.
     *
     * @param x int x Wert des gesuchten Punkts
     * @param y int y Wert des gesuchten Punkts
     * @return Punkt mit x und y Wert wenn vorhanden, null sonst
     */
    @Override
    public Punkt get(int x, int y) {
        return null;
    }

    /**
     * Suche den Punkt, der den geringsten Abstand zu einem Punkt p hat.
     *
     * @param p Punkt, von dem aus der nahste gesucht wird
     * @return Punkt, der am nahsten zu p ist, falls mehr als ein Punkt im Baum
     * ist, null sonst
     */
    @Override
    public Punkt nahster(Punkt p) {
        return null;
    }

    /**
     * Suche den Punkt, der den geringsten Abstand zu einem (nicht
     * notwendigerweise existierenden) Punkt p mit x Wert x und y Wert y
     * hat.
     *
     * @param x int x Wert des (hypothetischen) Punkts, von dem der nahste gesucht wird
     * @param y int y Wert des (hypothetischen) Punkts, von dem ner nahste gesucht wird
     * @return Punkt, der am nahsten zu p ist, falls mehr als ein Punkt im Baum
     * ist, null sonst
     */
    @Override
    public Punkt nahster(int x, int y) {
        return null;
    }

    /**
     * Iterator über alle Punkte, deren x Wert zwischen
     * xmin und xmax liegt (xmin <= x <= xmax) sowie y Wert zwischen
     * ymin und ymax liegt (xmax <= y <= ymax).
     *
     * @param xmin int alle x Werte müssen größer gleich xmin sein
     * @param xmax int alle x Werte müssen kleiner gleich xmax sein
     * @param ymin int alle y Werte müssen größer gleich ymin sein
     * @param ymax int alle y Werte müssen kleiner gleich ymax sein
     * @return Iterator über alle Punkte mit x,y Werten, so dass xmin <= x <= xmax und ymin <= y <= ymax
     */
    @Override
    public Iterator<Punkt> iterator(int xmin, int xmax, int ymin, int ymax) {
        return null;
    }

    /**
     * Iterator über alle Punkte, deren x Wert zwischen
     * xmin und xmax liegt (xmin <= x <= xmax).
     *
     * @param xmin int alle x Werte müssen größer gleich xmin sein
     * @param xmax int alle x Werte müssen kleiner gleich xmax sein
     * @return Iterator über alle Punkte mit x Werten, so dass xmin <= x <= xmax
     */
    @Override
    public Iterator<Punkt> iteratorx(int xmin, int xmax) {
        return null;
    }

    /**
     * Iterator über alle Punkte, deren x Wert festliegt
     *
     * @param x int alle x Werte müssen gleich x sein
     * @return Iterator über alle Punkte mit x Wert gleich x
     */
    @Override
    public Iterator<Punkt> iteratorx(int x) {
        return null;
    }

    /**
     * Iterator über alle Punkte, deren y Wert zwischen
     * ymin und ymax liegt (xmax <= y <= ymax).
     *
     * @param ymin int alle y Werte müssen größer gleich ymin sein
     * @param ymax int alle y Werte müssen kleiner gleich ymax sein
     * @return Iterator über alle Punkte mit y Werten, so dass ymin <= y <= ymax
     */
    @Override
    public Iterator<Punkt> iteratory(int ymin, int ymax) {
        return null;
    }

    /**
     * Iterator über alle Punkte, deren y Wert festliegt
     *
     * @param y int alle y Werte müssen gleich y sein
     * @return Iterator über alle Punkte mit y Werten gleich y
     */
    @Override
    public Iterator<Punkt> iteratory(int y) {
        return null;
    }
}

/*class ZweiDBaum implements PunktBaum {
    private Knot  wurzeln;
    private boolean akt;

    @Override
    public boolean insert(Punkt p) {
            Knot aktPunkt = wurzeln;
            Knot vorfahr = null;
            this.akt = false;

            if(!this.akt){
                if(aktPunkt.x > p.getX()){
                    vorfahr = aktPunkt;
                    aktPunkt = aktPunkt.links;
                }else if (aktPunkt.x < p.getX()){
                    vorfahr = aktPunkt;
                    aktPunkt = aktPunkt.recht;
             }
            }else {
                if (aktPunkt.y > p.getY()){
                    vorfahr= aktPunkt;
                    aktPunkt = aktPunkt.links;
                }else if(aktPunkt.y < p.getY()){
                    vorfahr = aktPunkt;
                    aktPunkt = aktPunkt.recht;
                }
            }this.akt = !this.akt;


        return true;
    }

    @Override
    public Punkt get(int x, int y) {
        Knot aktPunkt = wurzeln;
        boolean gefunden = false;
        this.akt = false;

        if(!this.akt){
            if(aktPunkt.x > x){
                aktPunkt = aktPunkt.links;
            }else if(aktPunkt.x < x){
                aktPunkt = aktPunkt.recht;
            }else {
                if(aktPunkt.y == y){
                    gefunden = true;
                    return aktPunkt.point;
                }
            }
        }else{
            if(aktPunkt.y > y){
                aktPunkt = aktPunkt.links;
            }else if(aktPunkt.y < y){
                aktPunkt = aktPunkt.recht;
            }else {
                if(aktPunkt.x == x){
                    gefunden = true;
                    return aktPunkt.point;
                }
            }

        }
        return null;
    }





}*/
