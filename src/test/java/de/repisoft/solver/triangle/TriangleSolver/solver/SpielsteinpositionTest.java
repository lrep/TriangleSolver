package de.repisoft.solver.triangle.TriangleSolver.solver;

import org.junit.jupiter.api.Test;
import static de.repisoft.solver.triangle.TriangleSolver.solver.Farbe.*;

import static org.assertj.core.api.Assertions.assertThat;

class SpielsteinpositionTest {

    @Test
    void drehung0Position0Test() {
        Spielstein s = new Spielstein(GRUEN, GELB, BLAU);
        Spielsteinposition spielsteinposition = new Spielsteinposition(0, s, 0);
        assertThat(spielsteinposition.getFarbeLinks()).isEqualTo(GRUEN);
        assertThat(spielsteinposition.getFarbeOben()).isNull();
        assertThat(spielsteinposition.getFarbeRechts()).isEqualTo(GELB);
        assertThat(spielsteinposition.getFarbeUnten()).isEqualTo(BLAU);
    }

    @Test
    void drehung1Position0Test() {
        Spielstein s = new Spielstein(GRUEN, GELB, BLAU);
        Spielsteinposition spielsteinposition = new Spielsteinposition(0, s, 1);
        assertThat(spielsteinposition.getFarbeLinks()).isEqualTo(BLAU);
        assertThat(spielsteinposition.getFarbeOben()).isNull();
        assertThat(spielsteinposition.getFarbeRechts()).isEqualTo(GRUEN);
        assertThat(spielsteinposition.getFarbeUnten()).isEqualTo(GELB);
    }

    @Test
    void drehung2Position0Test() {
        Spielstein s = new Spielstein(GRUEN, GELB, BLAU);
        Spielsteinposition spielsteinposition = new Spielsteinposition(0, s, 2);
        assertThat(spielsteinposition.getFarbeLinks()).isEqualTo(GELB);
        assertThat(spielsteinposition.getFarbeOben()).isNull();
        assertThat(spielsteinposition.getFarbeRechts()).isEqualTo(BLAU);
        assertThat(spielsteinposition.getFarbeUnten()).isEqualTo(GRUEN);
    }


    @Test
    void drehung0Position1Test() {
        Spielstein s = new Spielstein(GRUEN, GELB, WEISS);
        Spielsteinposition spielsteinposition = new Spielsteinposition(1, s, 0);
        assertThat(spielsteinposition.getFarbeLinks()).isEqualTo(GRUEN);
        assertThat(spielsteinposition.getFarbeOben()).isEqualTo(GELB);
        assertThat(spielsteinposition.getFarbeRechts()).isEqualTo(WEISS);
        assertThat(spielsteinposition.getFarbeUnten()).isNull();
    }

    @Test
    void drehung1Position1Test() {
        Spielstein s = new Spielstein(GRUEN, GELB, WEISS);
        Spielsteinposition spielsteinposition = new Spielsteinposition(1, s, 1);
        assertThat(spielsteinposition.getFarbeLinks()).isEqualTo(WEISS);
        assertThat(spielsteinposition.getFarbeOben()).isEqualTo(GRUEN);
        assertThat(spielsteinposition.getFarbeRechts()).isEqualTo(GELB);
        assertThat(spielsteinposition.getFarbeUnten()).isNull();
    }

    @Test
    void drehung2Position1Test() {
        Spielstein s = new Spielstein(GRUEN, GELB, WEISS);
        Spielsteinposition spielsteinposition = new Spielsteinposition(1, s, 2);
        assertThat(spielsteinposition.getFarbeLinks()).isEqualTo(GELB);
        assertThat(spielsteinposition.getFarbeOben()).isEqualTo(WEISS);
        assertThat(spielsteinposition.getFarbeRechts()).isEqualTo(GRUEN);
        assertThat(spielsteinposition.getFarbeUnten()).isNull();
    }

}