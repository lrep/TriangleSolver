package de.repisoft.solver.triangle.TriangleSolver.solver;

import java.util.ArrayList;
import java.util.List;

public class SpielfeldSolver {


    private final Spielfeld spielfeld;

    public SpielfeldSolver(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    public static void main(String[] args) {

        SpielfeldSolver solver = new SpielfeldSolver(new Spielfeld());

        solver.solve();
    }

    private void solve() {
        spielfeld.initialisieren();
        for (Spielstein spielstein : spielfeld.spielsteine) {
            ArrayList<Spielstein> spielsteine = new ArrayList<>(spielfeld.spielsteine);
            spielsteine.remove(spielstein);
            checkAndSetSpielStein(spielstein, spielsteine);
        }
    }

    private void checkAndSetSpielStein(Spielstein spielstein, ArrayList<Spielstein> spielsteine) {
        Zeile nachsteZeile = spielfeld.getNachsteZeile();
        int naechsteFreiePosition = nachsteZeile.getNaechsteFreiePosition();
        List<Integer> rotations = spielfeld.kannGesetztWerden(spielstein);
        ArrayList<Spielstein> spielsteinRest = new ArrayList<>(spielsteine);
        if (!rotations.isEmpty()) {
            spielsteinRest.remove(spielstein);
        }

        for (Integer rotation : rotations) {
            if (spielfeld.kannSteinGesetztWerden(spielstein, rotation, nachsteZeile, naechsteFreiePosition)) {
                spielfeld.setzeStein(spielstein, rotation, nachsteZeile.getZeileNummer(), naechsteFreiePosition);

                if (spielsteinRest.isEmpty()) {
                    System.out.println("Lösung gefunden");
                    zeigeStand();
                }
                for (Spielstein nachsterStein : spielsteinRest) {
                    checkAndSetSpielStein(nachsterStein, spielsteinRest);
                }
            }
            nachsteZeile.entferneSpielstein(naechsteFreiePosition);
        }
    }


    public void zeigeStand() {
        for (Zeile z : spielfeld.zeilen) {
            System.out.println(z.spielsteine);
        }
    }
}
