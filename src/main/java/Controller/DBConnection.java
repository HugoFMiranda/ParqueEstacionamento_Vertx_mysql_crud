package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // nome da base de dados da equipa
    static final String DBNAME = "2022sinf1_011";
    static final String URL = "jdbc:mysql://ctesp.dei.isep.ipp.pt:3306/" + DBNAME;
    // login enviado por e-mail
    static final String USER = "2022sinf1_011";
    // palavra-passe enviada por e-mail
    static final String PASS = "Dy27900jML%";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            // System.out.println("Connection to " + conn.getCatalog() + " succeded!");
            return conn;
        } catch (SQLException exc) {
            throw new RuntimeException("Error connecting!", exc);
        }
    }

}
