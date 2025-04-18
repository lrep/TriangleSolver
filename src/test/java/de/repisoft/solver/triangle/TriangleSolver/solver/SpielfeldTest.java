package de.repisoft.solver.triangle.TriangleSolver.solver;

import org.junit.jupiter.api.Test;

import static de.repisoft.solver.triangle.TriangleSolver.solver.Farbe.*;
import static org.assertj.core.api.Assertions.*;

class SpielfeldTest {

    @Test
    void testGetNachsteZeile() {
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.initialisieren();
        Zeile nachsteZeile = spielfeld.getNachsteZeile();

        assertThat(nachsteZeile.getZeileNummer()).isEqualTo(0);
    }

    @Test
    void testGetNachsteZeileNachSetzen() {
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.initialisieren();
        Zeile nachsteZeile = spielfeld.getNachsteZeile();
        nachsteZeile.setzeStein(new Spielstein(BLAU, BLAU, WEISS), 1);

        nachsteZeile = spielfeld.getNachsteZeile();
        assertThat(nachsteZeile.getZeileNummer()).isEqualTo(1);
    }

    @Test
    void testGetNachsteZeileSetzen() {
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.initialisieren();
        Zeile nachsteZeile = spielfeld.getNachsteZeile();
        nachsteZeile.setzeStein(new Spielstein(BLAU, BLAU, WEISS), 1);

        nachsteZeile = spielfeld.getNachsteZeile();
        nachsteZeile.setzeStein(new Spielstein(GRUEN, GELB, ROT), 1);

        assertThatIllegalArgumentException().isThrownBy(() ->
                spielfeld.setzeNaechstenStein(new Spielstein(GRUEN, ROT, SCHWARZ), 0));

        spielfeld.setzeNaechstenStein(new Spielstein(GRUEN, BLAU, GELB), 0);
        spielfeld.setzeNaechstenStein(new Spielstein(ROT, WEISS, GELB), 1);
    }

    @Test
    void testGetNachsteZeileSetzenPruefungUnten() {
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.initialisieren();

        Zeile nachsteZeile = spielfeld.zeilen.get(1);
        nachsteZeile.setzeStein(new Spielstein(GELB, GELB, GELB),1, 0);


        nachsteZeile = spielfeld.getNachsteZeile();
        assertThat(nachsteZeile.getZeileNummer()).isEqualTo(0);

        assertThatIllegalArgumentException().isThrownBy(() ->
                spielfeld.setzeNaechstenStein(new Spielstein(WEISS, BLAU, BLAU), 0));

        assertThatIllegalArgumentException().isThrownBy(() ->
                spielfeld.setzeNaechstenStein(new Spielstein(GRUEN, ROT, SCHWARZ), 0));

        spielfeld.setzeNaechstenStein(new Spielstein(WEISS,BLAU, GELB), 0);
        spielfeld.setzeNaechstenStein(new Spielstein(ROT, GELB, WEISS), 0);
    }

    @Test
    void testLetzteZeile(){
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.initialisieren();
        Spielstein spielstein = new Spielstein(GRUEN, GELB, BLAU);
        assertThat(spielfeld.kannSteinGesetztWerden(spielstein,0, spielfeld.zeilen.get(3),0 )).isFalse();
        assertThat(spielfeld.kannSteinGesetztWerden(spielstein,1, spielfeld.zeilen.get(3),0 )).isFalse();
        assertThat(spielfeld.kannSteinGesetztWerden(spielstein,2, spielfeld.zeilen.get(3),0 )).isTrue();
        assertThatIllegalArgumentException().isThrownBy(() ->
        spielfeld.setzeStein(spielstein, 0, 3, 0));
                spielfeld.setzeStein(spielstein, 2, 3, 0);
    }

}