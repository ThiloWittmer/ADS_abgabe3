import java.util.*;
import de.medieninf.ads.*;

public class Aufgabe1 {

    public static void main(String... args) {
        checkParameter(args);
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
                ausgabe(args);
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
            ausgabe(args);
        }
    }

    private static void ausgabe(String[] args) {
        List<Punkt> listPunkte;
        for (String dateiName : args) {
            listPunkte = readPunkte(dateiName);
            ZweiDBaum zweiDBaum = createZweiDBaum(listPunkte);
            zweiDBaum.printZweiDBaum();
        }
    }

    private static void ausgabe(String filename, int gesuchte_X, int gesuchte_Y){
        List<Punkt> listPunkte = readPunkte("zweidbaum/" + filename);
        ZweiDBaum zweiDBaum = createZweiDBaum(listPunkte);
        zweiDBaum.printZweiDBaum();
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
        System.out.println("\n2D-Baum erstellt:\n");
        return zweiDBaum;
    }
}
