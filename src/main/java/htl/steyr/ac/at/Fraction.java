package htl.steyr.ac.at;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Fraction {

    private int dividend = 1;
    private int divisor = 1;

    public Fraction(int dividend, int divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }
    //jar push prüfung



    public int getDividend() {
        return dividend;
    }

    public void setDividend(int dividend) {
        this.dividend = dividend;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public Fraction add(Fraction other) {
        /**
         * @ToDo
         * Add this with other
         */

        //nummerator oben
        //denumerator unten

        //dividend oben
        //divisor unten

        return new Fraction(this.dividend * other.divisor + other.dividend * this.divisor, this.divisor * other.divisor);
    }

    public Fraction subtract(Fraction other) {
        /**
         * @ToDo
         * subtract this with other
         */

        return new Fraction(this.dividend * other.divisor - other.dividend * this.divisor, this.divisor * other.divisor);
    }

    public Fraction multiply(Fraction other) {
        /**
         * @ToDo
         * multiply this with other
         */

        return new Fraction((this.dividend * other.dividend), this.divisor * other.divisor);
    }

    public Fraction divide(Fraction other) {
        /**
         * @ToDo
         * divide this with other
         * divisor * jeweils anderen divident
         */

        Fraction result = new Fraction(1, 1);

        result.setDivisor(this.getDivisor() * other.getDividend()); //Kreuzrechnen
        result.setDividend(this.getDividend() * other.getDivisor()); //Kreuzrechnen

        return result;
        //return new htl.steyr.ac.at.Fraction((this.dividend*other.dividend), this.divisor/other.divisor);
    }

    public Fraction shorten() {
        /**
         * @ToDo
         * shorten the htl.steyr.ac.at.Fraction
         */

        //-----------------------------------------------ggT

        int ggT = 0;

        int gg1 = this.getDividend();
        int gg2 = this.getDivisor();

        while (gg2 != 0) {
            if (gg1 > gg2) {
                gg1 = gg1 - gg2;
            } else {
                gg2 = gg2 - gg1;
            }
        }

        ggT = gg1;  //der größte gemeinsame Teiler herausfinden!!


        //-----------------------------------------------ggT

        Fraction result = new Fraction((this.getDividend() / ggT), (this.getDivisor() / ggT));

        return result;

        //this.other -->
        //ergebnis einen neuen bruch der ein
        // this wäre 4/10
        //other wäre 7/8
        //28/80 --> new htl.steyr.ac.at.Fraction
    }

    @Override
    public String toString() {
        return this.dividend + " / " + this.divisor;
    }


}
