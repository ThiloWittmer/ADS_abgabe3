import java.util.*;

import de.medieninf.ads.*;

public class Aufgabe2 {

    public static void main(String... args) {
        filterArguments(args);
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

    // TODO Handle wrong arguments correctly
    private static void filterArguments(String[] args) {
        if (args.length > 0) {
            String filename = args[0];

            /* Mit Dateiname x0 y0 x1 y1 werden alle Punkte innerhalb des Bildbereichs
               von links oben (x0, y0) bis rechts unten (x1, y1) ausgegeben. */
            if (args.length == 5){
                try {
                    if (!args[0].equals("simple.dat")) throw new IllegalArgumentException("Bitte simple.dat als Datei verwenden");
                }
                catch (IllegalArgumentException i){
                    System.out.println("Bitte simple.dat als Datei verwenden");
                }
                try {
                    if (!args[1].equals("x0")) throw new IllegalArgumentException("Bitte x0 als zweites Argument eingeben.");
                }
                catch (IllegalArgumentException i){
                    System.out.println("Bitte x0 als zweites Argument eingeben.");
                }
                try {
                    if (!args[2].equals("y0")) throw new IllegalArgumentException("Bitte y0 als zweites Argument eingeben.");
                }
                catch (IllegalArgumentException i){
                    System.out.println("Bitte y0 als zweites Argument eingeben.");
                }
                try {
                    if (!args[3].equals("x1")) throw new IllegalArgumentException("Bitte x1 als zweites Argument eingeben.");
                }
                catch (IllegalArgumentException i){
                    System.out.println("Bitte x1 als zweites Argument eingeben.");
                }
                try {
                    if (!args[4].equals("y1")) throw new IllegalArgumentException("Bitte y1 als zweites Argument eingeben.");
                }
                catch (IllegalArgumentException i){
                    System.out.println("Bitte y1 als zweites Argument eingeben.");
                }
                ausgabe(filename);
            }
            else if (args.length == 1) findPoints(filename);
        }
    }

    private static void ausgabe(String[] args) {
        List<Punkt> listPunkte;
        for (String dateiName : args) {
            listPunkte = readPunkte(dateiName);
            ZweiDBaum zweiDBaum = createZweiDBaum(listPunkte);
            zweiDBaum.printZweiDBaum();

            // Iterator tests
            /*zweiDBaum.iteratory(85);
            zweiDBaum.iteratorx(10);
            zweiDBaum.iteratory(60, 70);
            zweiDBaum.iteratorx(25, 70);
            zweiDBaum.iterator(25,70, 10, 70);*/
        }
    }

    private static void ausgabe(String filename) {
        List<Punkt> listPunkte = readPunkte("zweidbaum/" + filename);
        ZweiDBaum zweiDBaum = createZweiDBaum(listPunkte);
        zweiDBaum.printZweiDBaum();
        System.out.print("\nAlle Punkte die im Bildausschnitt liegen");
        zweiDBaum.iterator(35, 90, 45, 90);
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

    public static void findPoints(String filename){
        List<Punkt> listPunkte = readPunkte("zweidbaum/" + filename);
        ZweiDBaum zweiDBaum = createZweiDBaum(listPunkte);
        zweiDBaum.suchePunkte();
    }
}
