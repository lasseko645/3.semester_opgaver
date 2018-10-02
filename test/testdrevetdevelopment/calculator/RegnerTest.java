package testdrevetdevelopment.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegnerTest {


    //  lasse  \\
    //simple test for the first funktion
    //this first test will funktion as a sort of template for future test
    //this will be done primarily with comments, so if you dont care for that ignore the first test
    //the first test will just be for the adition funktion so should be simple enough
    @Test
    void testAdition(){
        //datafield: for all local variable and objects that the test will need to funktion
        //datafield start

        RegnerAddition addition = new RegnerAddition();
        double result;

        //datafield end


        //method use: potential use of different functions in order to make the test work
        //method use start

        result = addition.calcSimpleAddition(1, 7);

        //method use end


        //assertion: assert equals to make an assumption to make the test respond with a success or a failure
        //assertion start

        assertEquals(8,result);

        //assertion end
    }


    @Test
    void testSubtraction(){

        RegnerSubtraction subtraction = new RegnerSubtraction();

        assertEquals(6,subtraction.calcSimpleSub(13, 7));
    }


    @Test
    void testRegnemaskineFirstEdition(){
        RegneMaskine regner = new RegneMaskine();

        double valAadd = regner.addition.calcSimpleAddition(2,1);
        double valBsub = regner.subtraction.calcSimpleSub(10,3);

        assertEquals(3,valAadd);
        assertEquals(7,valBsub);


    }


}
