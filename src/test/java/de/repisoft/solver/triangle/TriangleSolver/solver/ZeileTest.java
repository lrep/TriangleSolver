package de.repisoft.solver.triangle.TriangleSolver.solver;

import org.junit.jupiter.api.Test;

import static de.repisoft.solver.triangle.TriangleSolver.solver.Farbe.*;

import static org.assertj.core.api.Assertions.assertThat;

class ZeileTest {

    @Test
    void testKannGesetztWerden() {
        Zeile zeile = new Zeile(0, WEISS, BLAU);
        Spielstein spielstein = new Spielstein(WEISS, WEISS, BLAU);

        assertThat(zeile.kannGesetztWerden(spielstein, 0, 0)).isFalse();
        assertThat(zeile.kannGesetztWerden(spielstein, 0, 1)).isFalse();
        assertThat(zeile.kannGesetztWerden(spielstein, 0, 2)).isTrue();
    }


    @Test
    void testKannNieGesetztWerden() {
        Zeile zeile = new Zeile(0, WEISS, BLAU);
        Spielstein spielstein = new Spielstein(WEISS, WEISS, WEISS);

        assertThat(zeile.kannGesetztWerden(spielstein, 0, 0)).isFalse();
        assertThat(zeile.kannGesetztWerden(spielstein, 0, 1)).isFalse();
        assertThat(zeile.kannGesetztWerden(spielstein, 0, 2)).isFalse();
    }

    @Test
    void testKannGesetztWerdenNachEinemStein() {
        Zeile zeile = new Zeile(0, WEISS, BLAU);
        Spielstein spielstein = new Spielstein(WEISS, WEISS, BLAU);

        assertThat(zeile.kannGesetztWerden(spielstein, 0, 0)).isFalse();
        assertThat(zeile.kannGesetztWerden(spielstein, 0, 1)).isFalse();
        assertThat(zeile.kannGesetztWerden(spielstein, 0, 2)).isTrue();
    }

    @Test
    void testKannGesetztWerdenZweiteZeile() {
        Zeile zeile = new Zeile(1, ROT, ROT);
        Spielstein spielstein = new Spielstein(ROT, BLAU, GRUEN);

        assertThat(zeile.kannGesetztWerden(spielstein, 0, 0)).isTrue();
        assertThat(zeile.kannGesetztWerden(spielstein, 0, 1)).isFalse();
        assertThat(zeile.kannGesetztWerden(spielstein, 0, 2)).isFalse();

        assertThat(zeile.kannGesetztWerden(spielstein, 1, 0)).isTrue();
        assertThat(zeile.kannGesetztWerden(spielstein, 1, 1)).isTrue();
        assertThat(zeile.kannGesetztWerden(spielstein, 1, 2)).isTrue();

        assertThat(zeile.kannGesetztWerden(spielstein, 2, 0)).isFalse();
        assertThat(zeile.kannGesetztWerden(spielstein, 2, 1)).isTrue();
        assertThat(zeile.kannGesetztWerden(spielstein, 2, 2)).isFalse();
    }

    @Test
    void naechsteFreiePosition() {

        Zeile zeile = new Zeile(15, WEISS, BLAU);
        assertThat(zeile.getNaechsteFreiePosition()).isEqualTo(0);

    }
}