package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Gestor;

/**
 *
 * @author Hugo
 */
public class GestorController {

    // A private variable of the class GestorController.
    private Gestor gestor;

    // A constructor.
    public GestorController() {
    }

    /**
     * This function creates a new Gestor object and assigns it to the gestor
     * variable
     */
    public void novoGestor() {
        this.gestor = new Gestor();
    }

    /**
     * It creates a new Gestor object, sets its attributes, creates a connection to
     * the database,
     * creates a prepared statement, executes the query and returns the result of
     * the query
     * 
     * @param nome     String
     * @param email    "teste@teste.com"
     * @param nif      int
     * @param password "12345"
     * @return The result of the query.
     */
    public int registerGestor(String nome, String email, int nif, String password)
            throws ClassNotFoundException, SQLException {
        novoGestor();
        this.gestor.setNome(nome);
        this.gestor.setEmail(email);
        this.gestor.setNif(nif);
        this.gestor.setPassword(password);
        // Creating a string with the SQL query to insert a new gestor into the
        // database.
        String INSERT_SQL = "INSERT INTO Gestor (nome, email, nif, password) VALUES (?,?,?,?);";
        int res = 0;
        // Creating a connection to the database.
        Connection conn = DBConnection.getConnection();
        // Creating a prepared statement for the SQL query.
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, gestor.getNome());
            ps.setString(2, gestor.getEmail());
            ps.setInt(3, gestor.getNif());
            ps.setString(4, gestor.getPassword());
            // Printing the prepared statement to the console.
            System.out.println(ps);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Closing the connection to the database.
        conn.close();
        System.out.println("Connection closed successfully!");
        // Returning the result of the query.
        return res;
    }
}
