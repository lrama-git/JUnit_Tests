import htl.steyr.ac.at.Fraction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FractionTest {


    private static Connection connect(String db) throws SQLException {
        Connection conn = null;

        try {
            if (db.length() <= 0) {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            } else {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, "root", "root"); //hänge ich einfach dazu
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
        return conn;
    }

    @Test
    @Order(1)
    void connectToDatabase() {
        Assertions.assertDoesNotThrow(new ThrowingSupplier<Object>() {
            @Override
            public Connection get() throws Throwable {
                return connect("");
            }
        });


        Assertions.assertDoesNotThrow(() -> connect("")); //kommz raus dass war wir im retunr programmiert haben

        /**
         *
         *
         * im interface ist nur get()
         * ich kann lantaxsyntax immer anwendern, wenn ich interfaces impementier wenn sie nur einer methode implementieren
         * und kann sie aufreufen, sobaal es 2 sind kann ich lanatxsynatx nicht verwenden
         *
         * was nach -> kommt ist der inhalt der funktion get()
         */
    }

    @Test
    @Order(2)
    void createDatabase() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("");

            Statement s = c.createStatement();
            s.executeUpdate("CREATE DATABASE testdb");

            s.close();
            c.close();
        });
    }

    @Test
    @Order(3)
    void connectToSpecificDatabase() {
        Assertions.assertDoesNotThrow(() -> connect("testdb"));

    }

    @Test
    @Order(4)
    void createTable() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");

            Statement s = c.createStatement();
            s.executeUpdate("CREATE TABLE testtable (mycolumn VARCHAR(255) NULL)");

            //was ist schlecht an dem?

            s.close();
            c.close();
        });
    }

    @Test
    @Order(5)
    void insertIntoTabe() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");

            Statement s = c.createStatement();
            s.executeUpdate("INSERT INTO testtable (mycolumn) VALUES ('hallo')");

            s.close();
            c.close();
        });
    }

    @Test
    @Order(6)
    void selectFromTable() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");

            Statement s = c.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM testtable LIMIT 1");

            if (res.first()) {
                Assertions.assertEquals(res.getString("mycolumn"), "hallo");
            }

            s.close();
            c.close();
        });
    }

    @Test
    @Order(7)
    void deleteInsert() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");

            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM testtable");



            s.close();
            c.close();
        });
    }


    @Test
    @Order(8)
    void deleteDatabase() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");

            Statement s = c.createStatement();
            s.executeUpdate("DROP TABLE testtable");


            s.close();
            c.close();
        });
    }

//
//    @Test
//    @Order(1)
//    public void createDatabase() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fraction?useSSL=false", "root", "root");
//
//        PreparedStatement db = mysql.prepareStatement("CREATE DATABSE `db`");
//        Assertions.assertFalse(db.execute());
//
//    }
//
//    @Test
//    @Order(2)
//    public void reconnectToDatabase() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fraction?useSSL=false", "root", "root");
//
//        mysql.close();
//        mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fraction?useSSL=false", "root", "root");
//        Assertions.assertNotNull(mysql);
//    }
//
//    @Test
//    @Order(3)
//    public void connectDatabase() throws SQLException {
//
//        Assertions.assertNotNull(postgres);
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fraction?useSSL=false", "root", "root");
//        //Connection mysql = connect("jdbc:mysql://localhost:3306/Fraction?user=root&password=root");
//        Assertions.assertNotNull(mysql);
//        System.out.println(postgres.getClientInfo());
//
//
//        PreparedStatement table = mysql.prepareStatement("CREATE TABLE 'db.Schoolclass' (`Name` varchar(255) )");
//        Assertions.assertFalse(table.execute());
//
//        PreparedStatement row = mysql.prepareStatement("INSERT INTO 'db.Schoolclass' (` Name` ) values " + "('Nita')");
//
//        Assertions.assertFalse(row.execute());
//
//        PreparedStatement select = mysql.prepareStatement("SELECT * From `db.Persons`");
//    }
//
//
//    @org.junit.jupiter.api.Test
//    void getDividend() {
//        Fraction f = new Fraction(1, 10);
//
//        Assertions.assertEquals(1, f.getDividend());
//    }
//
//    @org.junit.jupiter.api.Test
//    void setDividend() {
//        Fraction f = new Fraction(1, 10);
//        f.setDividend(2);
//
//        //erwarte dass dividend 2 ist
//        Assertions.assertEquals(2, f.getDividend());
//    }
//
//    @org.junit.jupiter.api.Test
//    void getDivisor() {
//        Fraction f = new Fraction(1, 10);
//
//        //du erwartest dass divisor 10 ist
//        Assertions.assertEquals(10, f.getDivisor());
//    }
//
//    @org.junit.jupiter.api.Test
//    void setDivisor() {
//        Fraction f = new Fraction(1, 10);
//        f.setDivisor(20);
//
//
//        Assertions.assertEquals(20, f.getDivisor());
//    }
//
//
//    @org.junit.jupiter.api.Test
//    void testToString() {
//        Fraction f = new Fraction(17, 3);
//
//        Assertions.assertEquals("17 / 3", f.toString());
//    }
//
//
//    @Test
//    void add() {
//        Fraction f = new Fraction(3, 2);
//        Fraction other = new Fraction(4, 3);
//        /**
//         *  --> 9/6
//         *  --> 8/6
//         */
//
//        Fraction res = f.add(other);
//
//        Assertions.assertEquals("17 / 6", res.toString());
//
//    }
//
//    @Test
//    void subtract() {
//
//        Fraction f = new Fraction(6, 2);
//        Fraction other = new Fraction(4, 3);
//
//        Fraction res = f.subtract(other);
//
//        Assertions.assertEquals("10 / 6", res.toString());
//
//    }
//
//    @Test
//    void multiply() {
//        Fraction f = new Fraction(3, 2);
//        Fraction other = new Fraction(4, 3);
//
//        Fraction res = f.multiply(other);
//
//        Assertions.assertEquals("12 / 6", res.toString());
//    }
//
//    @Test
//    void divide() {
//        Fraction f = new Fraction(3, 2);
//        Fraction other = new Fraction(4, 3);
//
//        Fraction res = f.divide(other);
//
//        Assertions.assertEquals("9 / 8", res.toString());
//    }
//
//    @Test
//    void shorten() {
//
//        Fraction f = new Fraction(18, 27);
//
//        Fraction res = f.shorten();
//
//        Assertions.assertEquals("2 / 3", res.toString());
//    }
}