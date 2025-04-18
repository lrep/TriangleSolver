package de.repisoft.solver.triangle.TriangleSolver.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Zeile {

    private final int zeileNummer;

    Farbe links;
    Farbe rechts;

    List<Spielsteinposition> spielsteine = new ArrayList<>();

    public Zeile(int zeileNummer, Farbe links, Farbe rechts) {
        this.zeileNummer = zeileNummer;
        this.links = links;
        this.rechts = rechts;
    }

    public int getZeileNummer() {
        return zeileNummer;
    }

    int anzahlSteine() {
        return zeileNummer * 2 + 1;
    }

    public List<Integer> drehungMoeglich(Spielstein spielstein, int position) {
        List<Integer> result = new ArrayList<>();
        for (int drehung = 0; drehung < 3; drehung++) {
            if (kannGesetztWerden(spielstein, position, drehung))
                result.add(drehung);
        }
        return result;
    }

    public boolean hatPlatz() {
        return anzahlSteine() > spielsteine.size();
    }

    Spielsteinposition getSpielsteinposition(int position) {
        return spielsteine.stream().filter(stein -> stein.position == position).findFirst().orElse(null);
    }

    Farbe getNaechsteLinkeFarbe(int position) {
        if (position == 0) {
            return links;
        } else {
            Spielsteinposition steinLinks = getSpielsteinposition(position - 1);
            return steinLinks != null ? steinLinks.getFarbeRechts() : null;
        }
    }

    Farbe getNaechsteRechteFarbe(int position) {
        if (position == anzahlSteine() - 1) {
            return rechts;
        } else {
            Spielsteinposition steinRechts = getSpielsteinposition(position + 1);
            return steinRechts != null ? steinRechts.getFarbeLinks() : null;
        }
    }

    boolean kannGesetztWerden(Spielstein spielstein, int position, int drehung) {
        if (position - 1 > anzahlSteine()) throw new IllegalArgumentException();
        Spielsteinposition pos = new Spielsteinposition(position, spielstein, drehung);

        if (getNaechsteLinkeFarbe(position) != null && getNaechsteLinkeFarbe(position) != pos.getFarbeLinks())
            return false;
        if (getNaechsteRechteFarbe(position) != null && getNaechsteRechteFarbe(position) != pos.getFarbeRechts())
            return false;
        return true;
    }

    public int getNaechsteFreiePosition() {
        if (anzahlSteine() == spielsteine.size()) return -1;

        List<Integer> freiePositions = new ArrayList<>();
        for (int i = 0; i < anzahlSteine(); i++) {
            freiePositions.add(i);
        }
        spielsteine.forEach(stein -> freiePositions.remove(Integer.valueOf(stein.position)));
        return freiePositions.get(0);
    }

    public void setzeStein(Spielstein spielstein, int drehung) {
        setzeStein(spielstein, getNaechsteFreiePosition(), drehung);
    }

    public void setzeStein(Spielstein spielstein, int position, int drehung) {
        if (kannGesetztWerden(spielstein, position, drehung)) {
            spielsteine.add(new Spielsteinposition(position, spielstein, drehung));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Farbe getFarbeUnten(int position) {
        Spielsteinposition spielsteinposition = getSpielsteinposition(position);
        if (spielsteinposition == null) return null;
        return spielsteinposition.getFarbeUnten();
    }

    public Farbe getFarbeOben(int position) {
        Spielsteinposition spielsteinposition = getSpielsteinposition(position);
        if (spielsteinposition == null) return null;
        return spielsteinposition.getFarbeOben();
    }

    public void entferneSpielstein(int naechsteFreiePosition) {
        Optional<Spielsteinposition> first = spielsteine.stream().filter(s -> s.position == naechsteFreiePosition).findFirst();
        first.ifPresent(spielsteinposition -> spielsteine.remove(spielsteinposition));

    }
}
