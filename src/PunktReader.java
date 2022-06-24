//package ueb9;

import java.util.*;
import de.medieninf.ads.*;

public class PunktReader {
	
	public static List<Punkt> readPunkte(String dateiname) {
		String[] lines = ADSTool.readStringArray(dateiname);
		List<Punkt> lp = new ArrayList<Punkt>();
		for (int i=0; i < lines.length; i+=3) {
			int x = Integer.parseInt(lines[i]);
			int y = Integer.parseInt(lines[i+1]);
			String text = lines[i+2];
			lp.add(new Punkt(x, y, text));
		}
		return lp;
	}
	
	public static void main(String... args) {
		/**
		if (args.length == 0) {
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
		ZweiDBaum tree = new ZweiDBaum(new ZweiDBaum(), a, new ZweiDBaum(), true);
		tree.insert(b);
		tree.insert(c);
		tree.insert(d);
		
	}
	
}
