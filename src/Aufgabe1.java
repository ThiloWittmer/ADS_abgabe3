import java.util.*;
import de.medieninf.ads.*;

public class Aufgabe1 {

    public static void main(String... args) {
        checkParameter(args);

        // Wurde für Starttests verwendet
        /*Punkt a = new Punkt(50, 50, "A");
        Punkt b = new Punkt(10, 70, "B");
        Punkt c = new Punkt(80, 85, "C");
        Punkt d = new Punkt(20, 25, "D");
        Punkt e = new Punkt(0, 85, "E");
        Punkt f = new Punkt(70, 85, "F");
        Punkt[] punkte = {a, b, c, d, e, f};

        ZweiDBaum tree = new ZweiDBaum(new ZweiDBaum(), a, new ZweiDBaum(), true);
        tree.insert(b);
        tree.insert(c);
        tree.insert(d);
        tree.insert(e);
        tree.insert(f);

        // Test → Versuchen alle erstellten Punkte zu durchsuchen.
        for (Punkt punkt : punkte){
            Punkt gesucht = tree.get(punkt.getX(), punkt.getY());
            String msg = gesucht == null
                    ? "\n❌ Punkt " + punkt.getText() + "(" + punkt.getX() + "," + punkt.getY() + ") NICHT gefunden."
                    : "\n✅ Punkt " + gesucht + " gefunden.";
            System.out.print(msg);
        }
        System.out.println();*/

        // Test → Für den Fall, dass ein bestimmter erstellter Punkt nicht gefunden wird.
        /*Punkt gesucht = tree.get(70, 85);
        String msg = gesucht == null
                ? "\n❌ Punkt NICHT gefunden."
                : "\n✅ Punkt " + gesucht.toString() + " gefunden.";
        System.out.println(msg);*/
    }

    public static List<Punkt> readPunkte(String dateiname) {
        String[] lines = ADSTool.readStringArray(dateiname);
        List<Punkt> lp = new ArrayList<>();
        for (int i = 0; i < lines.length; i += 3) {
            int x = Integer.parseInt(lines[i]);
            int y = Integer.parseInt(lines[i + 1]);
            String text = lines[i + 2];
            lp.add(new Punkt(x, y, text));
        }
        return lp;
    }

    private static void checkParameter(String[] args) {
        if(args.length > 0){
            String filename = args[0];

            // Mit Dateiname und ohne Parameter x und y wird jeder Punkt ausgegeben und schließlich die Anzahl der Punkte
            if (args.length == 1){
                args = new String[]{"zweidbaum/" + filename};
                ausgabe(args, filename);
            }

            // Mit Dateiname und x und y Eingabe wird der Punkt an der Koordinate x × y ausgeben, wenn vorhanden
            else if (args.length == 3){
                int gesuchte_X = Integer.parseInt(args[1]);
                int gesuchte_Y = Integer.parseInt(args[2]);
                ausgabe(filename, gesuchte_X, gesuchte_Y);
            }
        }

        // Standard-Ausgabe
        else {
            String filename = "zweidbaum/simple.dat";
            args = new String[]{filename};
            ausgabe(args, filename);
        }
    }

    private static void ausgabe(String[] args, String filename) {
        int anzahlPunkte = 0;
        List<Punkt> listPunkte;

        for (String dateiName : args) {
            System.out.println("\n\nPunkte in " + filename + ":\n");
            listPunkte = readPunkte(dateiName);
            int count = 0;
            for (Punkt punkt : listPunkte) {
                anzahlPunkte += 1;
                System.out.format("%20s", punkt);
                if (++count % 4 == 0) {
                    System.out.println();
                }
            }
        }
        System.out.println("\n\nAnzahl der Punkte = " + anzahlPunkte);
    }

    private static void ausgabe(String filename, int gesuchte_X, int gesuchte_Y){
        List<Punkt> listPunkte = readPunkte("zweidbaum/" + filename);
        ZweiDBaum zweiDBaum = createZweiDBaum(listPunkte);
        Punkt gesucht = zweiDBaum.get(gesuchte_X, gesuchte_Y);
        String msg = gesucht == null
                ? "\nPunkt mit x=" + gesuchte_X + " und y=" + gesuchte_Y + " NICHT gefunden."
                : "\nPunkt " + gesucht + " gefunden.";
        System.out.println(msg);
    }

    public static ZweiDBaum createZweiDBaum(List<Punkt> listPunkte) {
        // ZweiDBaum und root erstellen
        ZweiDBaum zweiDBaum = new ZweiDBaum(new ZweiDBaum(), listPunkte.get(0), new ZweiDBaum(), true);

        // Alle Punkte ab der 2. erstellen
        for (Punkt punkt : listPunkte.subList(1, listPunkte.size())) {
            zweiDBaum.insert(punkt);
        }
        System.out.println("2D-Baum erstellt.");
        return zweiDBaum;
    }
}
