package de.repisoft.solver.triangle.TriangleSolver.solver;

import java.util.ArrayList;
import java.util.List;

import static de.repisoft.solver.triangle.TriangleSolver.solver.Farbe.*;

public class Spielfeld {
    List<Spielstein> spielsteine = new ArrayList<>();
    List<Zeile> zeilen = new ArrayList<>();

    public void initialisieren() {
        spielsteine.add(new Spielstein(SCHWARZ, SCHWARZ, GRUEN));
        spielsteine.add(new Spielstein(SCHWARZ, GRUEN, ROT));
        spielsteine.add(new Spielstein(GRUEN, GELB, ROT));
        spielsteine.add(new Spielstein(WEISS, GELB, ROT));
        spielsteine.add(new Spielstein(WEISS, WEISS, BLAU));
        spielsteine.add(new Spielstein(BLAU, SCHWARZ, WEISS));
        spielsteine.add(new Spielstein(WEISS, GRUEN, GELB));
        spielsteine.add(new Spielstein(ROT, SCHWARZ, GRUEN));
        spielsteine.add(new Spielstein(SCHWARZ, ROT, GRUEN));
        spielsteine.add(new Spielstein(GRUEN, GELB, WEISS));
        spielsteine.add(new Spielstein(GELB, GRUEN, BLAU));
        spielsteine.add(new Spielstein(GELB, GRUEN, SCHWARZ));
        spielsteine.add(new Spielstein(BLAU, SCHWARZ, WEISS));
        spielsteine.add(new Spielstein(BLAU, BLAU, WEISS));
        spielsteine.add(new Spielstein(GRUEN, WEISS, ROT));
        spielsteine.add(new Spielstein(BLAU, GELB, SCHWARZ));

        zeilen.add(new Zeile(0, WEISS, BLAU));
        zeilen.add(new Zeile(1, ROT, ROT));
        zeilen.add(new Zeile(2, WEISS, GRUEN));
        zeilen.add(new Zeile(3, GELB, SCHWARZ));

        zeilen.add(new Zeile(4, null, null) {
            @Override
            public Farbe getFarbeOben(int position) {
                return switch (position) {
                    case 1, 3, 7 -> Farbe.GRUEN;
                    case 5 -> Farbe.WEISS;
                    default -> null;
                };
            }
        });

    }

    public Zeile getNachsteZeile() {
        for (Zeile zeile : zeilen) {
            if (zeile.hatPlatz()) return zeile;
        }
        return null;
    }

    public List<Integer> kannGesetztWerden(Spielstein spielstein) {
        Zeile nachsteZeile = getNachsteZeile();
        int naechsteFreiePosition = nachsteZeile.getNaechsteFreiePosition();

        return nachsteZeile.drehungMoeglich(spielstein, naechsteFreiePosition);
    }

    public String toString() {
        return "Es sind " + spielsteine.size() + " Spielsteine im Spiel: " + spielsteine;
    }

    public static void main(String[] args) {
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.initialisieren();
        System.out.println(spielfeld);
    }


    public void setzeNaechstenStein(Spielstein spielstein, int drehen) {
        Zeile nachsteZeile = getNachsteZeile();
        int position = nachsteZeile.getNaechsteFreiePosition();
        setzeStein(spielstein, drehen, nachsteZeile.getZeileNummer(), position);
    }

    public boolean kannSteinGesetztWerden(Spielstein spielstein, int drehen, Zeile zeile, int position) {
        Spielsteinposition spielsteinposition = new Spielsteinposition(position, spielstein, drehen);
        if (!zeile.kannGesetztWerden(spielstein, position, drehen)) return false;

        if (position % 2 == 1) {
            Zeile zeileVorher = zeilen.get(zeile.getZeileNummer() - 1);
            Farbe farbeUnten = zeileVorher.getFarbeUnten(position - 1);
            if (farbeUnten != null && spielsteinposition.getFarbeOben() != farbeUnten) {
                return false;
            }
        } else {
            Zeile zeileNachher = zeilen.get(zeile.getZeileNummer() + 1);
            Farbe farbeOben = zeileNachher.getFarbeOben(position + 1);
            if (farbeOben != null && farbeOben != spielsteinposition.getFarbeUnten()) {
                return false;
            }
        }
        return true;
    }

    public void setzeStein(Spielstein spielstein, int drehen, int zeilenNummer, int position) {
        Zeile zeile = zeilen.get(zeilenNummer);
        if (!kannSteinGesetztWerden(spielstein, drehen, zeile, position)) {
            throw new IllegalArgumentException("Spielstein kann nicht gesetzt werden");
        }
        zeile.setzeStein(spielstein, position, drehen);
    }
}
