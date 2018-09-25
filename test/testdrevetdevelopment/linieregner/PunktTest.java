package testdrevetdevelopment.linieregner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PunktTest {
    private final double testDelta = 0.001;
    @Test
    void testNytPunkt() {
        Punkt punkt = new Punkt(3,6);

        double x = punkt.getX();
        double y = punkt.getY();

        assertEquals(3.0, x, testDelta );
        assertEquals(6.0, y, testDelta );
    }

    @Test
    void testToPunkter(){
        Punkt punkt_1 = new Punkt(3,6);
        Punkt punkt_2 = new Punkt(2,1);



        assertEquals(3.0, punkt_1.getX(), testDelta);
        assertEquals(6.0, punkt_1.getY(), testDelta);
        assertEquals(2.0, punkt_2.getX(), testDelta);
        assertEquals(1.0, punkt_2.getY(), testDelta);
    }

    @Test
    void testLinieFake(){

        double expectedA_1_x = 3.0;
        double expectedA_1_y = 6.0;
        double expectedB_2_x = 2.0;
        double expectedB_2_y = 1.0;
        Punkt punkt_a = new Punkt(expectedA_1_x, expectedA_1_y);
        Punkt punkt_b = new Punkt(expectedB_2_x, expectedB_2_y);

        //double x_1 = 3;
        //double y_1 = 6;

        //double x_2 = 2;
        //double y_2 = 1;


        assertEquals(expectedA_1_x, punkt_a.getX(), testDelta);
        assertEquals(expectedA_1_y, punkt_a.getY(), testDelta);
        assertEquals(expectedB_2_x, punkt_b.getX(), testDelta);
        assertEquals(expectedB_2_y, punkt_b.getY(), testDelta);
    }

    @Test
    void TestLinie(){
        double expectedA_1_x = 3.0;
        double expectedA_1_y = 6.0;
        double expectedB_2_x = 2.0;
        double expectedB_2_y = 1.0;
        Linie testLinie = new Linie(expectedA_1_x,expectedA_1_y,expectedB_2_x,expectedB_2_y);

        assertEquals(expectedA_1_x,testLinie.getPunkt_a().getX());
        assertEquals(expectedA_1_y,testLinie.getPunkt_a().getY());
        assertEquals(expectedB_2_x,testLinie.getPunkt_b().getX());
        assertEquals(expectedB_2_y,testLinie.getPunkt_b().getY());
    }

    @Test
    void testLinieRegner(){
        double expectedA_1_x = 3.0;
        double expectedA_1_y = 0.0;
        double expectedB_2_x = 0.0;
        double expectedB_2_y = 4.0;

        Linie testLinie = new Linie(expectedA_1_x,expectedA_1_y,expectedB_2_x,expectedB_2_y);


        assertEquals(5,testLinie.length());

    }




}