import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZweiDBaum implements PunktBaum {
    private final Knoten root;

    private static class Knoten {
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

        public int getX() {
            return punkt.getX();
        }

        public int getY() {
            return punkt.getY();
        }

        /**
         * Der Baum wird anhand von Bedingungen durchlaufen, die die Suchrichtung festlegen.
         * Die Bedingungen vergleichen die Werte von x und y des aktuellen Punktes des Knotens
         * mit den gesuchten x und y.
         *
         * @param aktKnoten  Knoten der gerade nach x und y Werte durchsucht wird
         * @param gesuchte_X gesuchte x-Koordinate der Punkt
         * @param gesuchte_Y gesuchte y-Koordinate der Punkt
         * @return Punkt als ergebnis, null wenn nicht gefunden
         */
        private Punkt suchePunkt(Knoten aktKnoten, int gesuchte_X, int gesuchte_Y) {
            if (aktKnoten == null) return null;

            Punkt ergebnis;
            int akt_X = aktKnoten.getX();
            int akt_Y = aktKnoten.getY();

            if (akt_X == gesuchte_X && akt_Y == gesuchte_Y) {
                return aktKnoten.punkt;
            } else if (gesuchte_X >= akt_X && gesuchte_Y >= akt_Y
                    || gesuchte_X <= akt_X && gesuchte_Y >= akt_Y) {
                ergebnis = suchePunkt(aktKnoten.right, gesuchte_X, gesuchte_Y);
                if (ergebnis == null) return suchePunkt(aktKnoten.left, gesuchte_X, gesuchte_Y);
            } else {
                ergebnis = suchePunkt(aktKnoten.left, gesuchte_X, gesuchte_Y);
                if (ergebnis == null) return suchePunkt(aktKnoten.right, gesuchte_X, gesuchte_Y);
            }
            return ergebnis;
        }

        static List<Knoten> traverseLevelOrder(Knoten root) {
            List<Knoten> knotenListe = new ArrayList<>();
            int height = hoehe(root);
            for (int j = 1; j <= height; j++) {
                traverseCurrentLevel(root, j, knotenListe);
            }
            return knotenListe;
        }

        static void traverseCurrentLevel(Knoten root, int level, List<Knoten> knotenListe) {
            if (root == null) return;

            // l == 1 means only one node is present in the binary tree
            if (level == 1) knotenListe.add(root);

                /* l > 1 means either there are nodes present in the left side of the current node or in the
                 * right side of the current node or in both sides therefore, we have to look in the left as well as in
                 * the right side of the current node */
            else if (level > 1) {
                traverseCurrentLevel(root.left, level - 1, knotenListe);
                traverseCurrentLevel(root.right, level - 1, knotenListe);
            }
        }

        /**
         * Traversiert der ZweiDBaum in levelOrder
         */
        void printLevelOrder() {
            int hoehe = hoehe(this);
            for (int i = 1; i <= hoehe; i++) printAktuelleEbene(this, i);
        }

        /**
         * Berechnet die "Höhe" des ZweiDBaums - die Anzahl der Knoten entlang des längsten Pfads
         * vom Wurzelknoten bis hinunter zum am weitesten entfernten Blattknoten.
         *
         * @param root
         * @return
         */
        static int hoehe(Knoten root) {
            if (root == null) return 0;
            else {
                int linkeHoehe = hoehe(root.left);
                int rechteHoehe = hoehe(root.right);

                // die größere Variante verwenden
                if (linkeHoehe > rechteHoehe) return (linkeHoehe + 1);
                else return (rechteHoehe + 1);
            }
        }

        /**
         * Gibt die Knoten auf der aktuellen Ebene aus
         *
         * @param root
         * @param ebene
         */
        void printAktuelleEbene(Knoten root, int ebene) {
            if (root == null) return;
            if (ebene == 1) System.out.print(root.punkt + "\n");
            else if (ebene > 1) {
                printAktuelleEbene(root.left, ebene - 1);
                printAktuelleEbene(root.right, ebene - 1);
            }
        }

        /**
         * Funktion zur Ermittlung der linken Höhe des ZweiDBaums
         *
         * @param knoten
         * @return
         */
        static int linkeHoehe(Knoten knoten) {
            int hoeheLinks = 0;
            while (knoten != null) {
                hoeheLinks++;
                knoten = knoten.left;
            }
            return hoeheLinks;
        }

        /**
         * Funktion zur Ermittlung der rechten Höhe des ZweiDBaums
         *
         * @param hoehe
         * @return
         */
        static int rechteHoehe(Knoten hoehe) {
            int hoeheRechts = 0;
            while (hoehe != null) {
                hoeheRechts++;
                hoehe = hoehe.right;
            }
            return hoeheRechts;
        }

        /**
         * Funktion zur Ermittlung der Anzahl von Knoten in dem vollständigen ZweiDBaum
         *
         * @param root
         * @return
         */
        static int anzahlKnoten(Knoten root) {
            if (root == null) return 0;

            int linkeHoehe = linkeHoehe(root);
            int rechteHoehe = rechteHoehe(root);

            // Wenn die linke und rechte Höhe gleich sind, wird 2^height * (1 << height) -1
            if (linkeHoehe == rechteHoehe) return (1 << linkeHoehe) - 1;

            return 1 + anzahlKnoten(root.left) + anzahlKnoten(root.right);
        }
    }

    static class ZweiDBaumIterator {
        List<Knoten> knotenListe;
        int index = 0;

        public ZweiDBaumIterator(Knoten root) {
            knotenListe = Knoten.traverseLevelOrder(root);
        }

        public Knoten next() {
            return knotenListe.get(index++);
        }

        public boolean hasNext() {
            return index < knotenListe.size();
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
     * <p>
     * inserted[0] = true, wenn neuer Punkt; false, wenn überschrieben
     * inserted[1] = true, wenn eingefügt, sonst false
     *
     * @param p Punkt, der einzufügen ist
     * @return true gdw Punkt wurde neu eingefügt, false sonst
     */
    @Override
    public boolean insert(Punkt p) {
        boolean[] inserted = new boolean[2];
        Knoten aktKnoten = root;
        while (!inserted[1]) {
            assert aktKnoten != null;
            aktKnoten = compareCoord(p, inserted, aktKnoten);
        }
        return inserted[0];
    }

    public void printZweiDBaum() {
        assert root != null;
        root.printLevelOrder();
        System.out.print("\nAnzahl der Punkten = " + Knoten.anzahlKnoten(root) + "\n");
    }

    private Knoten compareCoord(Punkt p, boolean[] inserted, Knoten aktKnoten) {
        if (aktKnoten.xOderY) {
            if (p.getX() <= aktKnoten.getX()) {
                //links
                aktKnoten = checkNext(p, aktKnoten, inserted, Richtung.LINKS);
            } else {
                //rechts
                aktKnoten = checkNext(p, aktKnoten, inserted, Richtung.RECHTS);
            }
        } else {
            if (p.getY() <= aktKnoten.getY()) {
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
     * @param p
     * @param aktKnoten
     * @param "richtung" true = links
     * @return
     */
    private Knoten checkNext(Punkt p, Knoten aktKnoten, boolean[] inserted, Richtung r) {
        if (r == Richtung.LINKS) {
            if (aktKnoten.left == null) {
                aktKnoten.left = new Knoten(null, p, null, !aktKnoten.xOderY);
                inserted[0] = true;
                inserted[1] = true;
            } else if (aktKnoten.left.punkt.equals(p)) {
                aktKnoten.left.punkt = p;
                inserted[0] = false;
                inserted[1] = true;
            } else {
                aktKnoten = aktKnoten.left;
                inserted[1] = false;
            }
        } else {
            if (aktKnoten.right == null) {
                aktKnoten.right = new Knoten(null, p, null, !aktKnoten.xOderY);
                inserted[0] = true;
                inserted[1] = true;
            } else if (aktKnoten.right.punkt.equals(p)) {
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
        assert root != null;

        // Wenn es schon die Wurzel ist, gibt sie zurück → Baum muss nicht durchlaufen werden.
        if (root.getX() == x && root.getY() == y) return root.punkt;

        /* 1. Knoten wird übersprungen, muss nicht mehr überprüft werden. Durch den Vergleich
              des x-Wertes kann bereits entschieden werden, in welche Richtung gesucht werden soll.
              Es muss nur die Hälfte des Baumes durchsucht werden → nach links ∨ rechts. */

        if (x <= 50) return root.suchePunkt(root.left, x, y);
        else return root.suchePunkt(root.right, x, y);
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
        ZweiDBaumIterator it_Baum = new ZweiDBaumIterator(root);
        List<Punkt> punktListe = new ArrayList<>();
        System.out.println(" mit x,y Werten, so dass " + xmin + " <= x <= " + xmax + " und " + ymin + " <= y <= " + ymax);

        while (it_Baum.hasNext()) {
            Knoten k = it_Baum.next();
            int x = k.getX();
            int y = k.getY();
            if (x >= xmin && x <= xmax && y >= ymin && y <= ymax) {
                punktListe.add(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
                System.out.println(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
            }
        }
        return punktListe.iterator();
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
        ZweiDBaumIterator it_Baum = new ZweiDBaumIterator(root);
        List<Punkt> punktListe = new ArrayList<>();
        System.out.println("\nAlle Punkte mit x zwischen " + xmin + " und " + xmax);
        while (it_Baum.hasNext()) {
            int x = it_Baum.next().getX();
            if (x >= xmin && x <= xmax) {
                punktListe.add(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
                System.out.println(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
            }
        }
        return punktListe.iterator();
    }

    /**
     * Iterator über alle Punkte, deren x Wert festliegt
     *
     * @param x int alle x Werte müssen gleich x sein
     * @return Iterator über alle Punkte mit x Wert gleich x
     */
    @Override
    public Iterator<Punkt> iteratorx(int x) {
        ZweiDBaumIterator it_Baum = new ZweiDBaumIterator(root);
        List<Punkt> punktListe = new ArrayList<>();
        System.out.println("\nAlle Punkte mit x=" + x);
        while (it_Baum.hasNext()) {
            if (it_Baum.next().getX() == x) {
                punktListe.add(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
                System.out.println(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
            }
        }
        return punktListe.iterator();
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
        ZweiDBaumIterator it_Baum = new ZweiDBaumIterator(root);
        List<Punkt> punktListe = new ArrayList<>();
        System.out.println("\nAlle Punkte mit y zwischen " + ymin + " und " + ymax);
        while (it_Baum.hasNext()) {
            int y = it_Baum.next().getY();
            if (y >= ymin && y <= ymax) {
                punktListe.add(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
                System.out.println(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
            }
        }
        return punktListe.iterator();
    }

    /**
     * Iterator über alle Punkte, deren y Wert festliegt
     *
     * @param y int alle y Werte müssen gleich y sein
     * @return Iterator über alle Punkte mit y Werten gleich y
     */
    @Override
    public Iterator<Punkt> iteratory(int y) {
        ZweiDBaumIterator it_Baum = new ZweiDBaumIterator(root);
        List<Punkt> punktListe = new ArrayList<>();
        System.out.println("\nAlle Punkte mit y=" + y);
        while (it_Baum.hasNext()) {
            if (it_Baum.next().getY() == y) {
                punktListe.add(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
                System.out.println(it_Baum.knotenListe.get(it_Baum.index - 1).punkt);
            }
        }
        return punktListe.iterator();
    }

    public void suchePunkte() {
        ZweiDBaumIterator it_Baum = new ZweiDBaumIterator(root);
        List<Punkt> gefundenePunkte = new ArrayList<>();
        Knoten k;
        if (root != null) {
            while (it_Baum.hasNext()) {
                gefundenePunkte.clear();
                k = it_Baum.next();
                System.out.println("\nPunkte die sich im Rechteck " + k.punkt + " befinden:");
                if (k.left != null) suchePunkteLinks(k, gefundenePunkte);
                System.out.println(gefundenePunkte.size() + " Punkte gefunden.\n________________________________________________");
                if (it_Baum.hasNext()) it_Baum.next();
            }
        }
    }

    private void suchePunkteLinks(Knoten knoten, List<Punkt> gefundenePunkte) {
        if (knoten.left != null) {
            System.out.println(knoten.left.punkt);
            gefundenePunkte.add(knoten.left.punkt);
        }
        if (knoten.left != null && knoten.left.right != null) {
            System.out.println(knoten.left.right.punkt);
            gefundenePunkte.add(knoten.left.right.punkt);
            if (knoten.left.right.left != null) suchePunkteLinks(knoten.left.right, gefundenePunkte);
        }
        if (knoten.left != null && knoten.left.left == null && knoten.left.right == null) return;
        suchePunkteLinks(knoten.left, gefundenePunkte);
    }
}
