public class Main {
    public static void main(String[] args) {
        int dividend = Integer.parseInt(args[0]);
        int divisor = Integer.parseInt(args[1]);

        Fraction f = new Fraction(dividend, divisor);

        Fraction res = f.shorten();

        System.out.println(res);
    }
}
