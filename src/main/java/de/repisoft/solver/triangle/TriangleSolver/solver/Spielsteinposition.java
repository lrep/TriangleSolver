package de.repisoft.solver.triangle.TriangleSolver.solver;

public class Spielsteinposition {

    Spielstein spielstein;

    int position;

    int drehung;

    Spielsteinposition(int position, Spielstein spielstein, int drehung) {
        this.spielstein = spielstein;
        this.position = position;
        this.drehung = drehung;
    }

    Farbe getFarbeLinks() {
        return spielstein.farben.get((6 - drehung) % 3);
    }

    Farbe getFarbeRechts() {
        if (position % 2 == 0) {
            return spielstein.farben.get((4 - drehung) % 3);
        } else
            return spielstein.farben.get((5 - drehung) % 3);
    }

    Farbe getFarbeUnten() {
        if (position % 2 == 0) {
            return spielstein.farben.get((5 - drehung) % 3);
        } else {
            return null;
        }
    }

    Farbe getFarbeOben() {
        if (position % 2 == 0) {
            return null;
        } else {
            return spielstein.farben.get((4 - drehung) % 3);
        }
    }

    @Override
    public String toString() {

        if (getFarbeUnten() == null) {
            return getFarbeLinks() + " " + getFarbeOben() + " " + getFarbeRechts();
        } else {
            return getFarbeLinks() + " " + getFarbeRechts() + " " + getFarbeUnten();
        }
    }
}
