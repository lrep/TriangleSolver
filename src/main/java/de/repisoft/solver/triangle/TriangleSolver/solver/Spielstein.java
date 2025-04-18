package de.repisoft.solver.triangle.TriangleSolver.solver;

import java.util.ArrayList;
import java.util.List;

public class Spielstein {
    final List<Farbe> farben = new ArrayList<>();

    public Spielstein(Farbe position1, Farbe position2, Farbe position3) {
        farben.add(position1);
        farben.add(position2);
        farben.add(position3);
    }

    @Override
    public String toString() {
        return "Spielstein: >" + farben.get(0).toString() + " " + farben.get(1).toString() + " " + farben.get(2).toString() + "<";
    }
}
