import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @org.junit.jupiter.api.Test
    void getDividend() {
        Fraction f = new Fraction(1, 10);

        Assertions.assertEquals(1, f.getDividend());
    }

    @org.junit.jupiter.api.Test
    void setDividend() {
        Fraction f = new Fraction(1, 10);
        f.setDividend(2);

        //erwarte dass dividend 2 ist
        Assertions.assertEquals(2, f.getDividend());
    }

    @org.junit.jupiter.api.Test
    void getDivisor() {
        Fraction f = new Fraction(1, 10);

        //du erwartest dass divisor 10 ist
        Assertions.assertEquals(10, f.getDivisor());
    }

    @org.junit.jupiter.api.Test
    void setDivisor() {
        Fraction f = new Fraction(1, 10);
        f.setDivisor(20);


        Assertions.assertEquals(20, f.getDivisor());
    }


    @org.junit.jupiter.api.Test
    void testToString() {
        Fraction f = new Fraction(17, 3);

        Assertions.assertEquals("17 / 3", f.toString());
    }


    @Test
    void add() {
        Fraction f = new Fraction(3, 2);
        Fraction other = new Fraction(4, 3);
        /**
         *  --> 9/6
         *  --> 8/6
         */

        Fraction res = f.add(other);

        Assertions.assertEquals("17 / 6", res.toString());

    }

    @Test
    void subtract() {

        Fraction f = new Fraction(6, 2);
        Fraction other = new Fraction(4, 3);

        Fraction res = f.subtract(other);

        Assertions.assertEquals("10 / 6", res.toString());

    }

    @Test
    void multiply() {
        Fraction f = new Fraction(3, 2);
        Fraction other = new Fraction(4, 3);

        Fraction res = f.multiply(other);

        Assertions.assertEquals("12 / 6", res.toString());
    }

    @Test
    void divide() {
        Fraction f = new Fraction(3, 2);
        Fraction other = new Fraction(4, 3);

        Fraction res = f.divide(other);

        Assertions.assertEquals("9 / 8", res.toString());
    }

    @Test
    void shorten() {

        Fraction f = new Fraction(18, 27);

        Fraction res = f.shorten();

        Assertions.assertEquals("2 / 3", res.toString());
    }
}