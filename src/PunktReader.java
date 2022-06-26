import java.util.*;

import de.medieninf.ads.*;

public class PunktReader {

    public static List<Punkt> readPunkte(String dateiname) {
        String[] lines = ADSTool.readStringArray(dateiname);
        List<Punkt> lp = new ArrayList<Punkt>();
        for (int i = 0; i < lines.length; i += 3) {
            int x = Integer.parseInt(lines[i]);
            int y = Integer.parseInt(lines[i + 1]);
            String text = lines[i + 2];
            lp.add(new Punkt(x, y, text));
        }
        return lp;
    }

    public static void main(String... args) {

        /*if (args.length == 0) {
            String[] defaultArgs = {
                    "zweidbaum/simple.dat"
            };
            args = defaultArgs;
        }
        for (String filename : args) {
            System.out.println(filename);
            List<Punkt> listPunkt = readPunkte(filename);
            int count = 0;
            for (Iterator<Punkt> itp = listPunkt.iterator(); itp.hasNext(); ) {
                Punkt p = itp.next();
                System.out.format("%20s", p);
                if (++count % 4 == 0) {
                    System.out.println();
                }
            }
        }*/

        Punkt a = new Punkt(50, 50, "A");
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
        System.out.println();

        // Test → Für den Fall, dass ein bestimmter erstellter Punkt nicht gefunden wird.
        /*Punkt gesucht = tree.get(70, 85);
        String msg = gesucht == null
                ? "\n❌ Punkt NICHT gefunden."
                : "\n✅ Punkt " + gesucht.toString() + " gefunden.";
        System.out.println(msg);*/
    }

}
