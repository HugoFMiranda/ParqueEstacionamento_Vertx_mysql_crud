package Controller;

import java.sql.*;

import Model.Cliente;
import Model.Gestor;
import Model.login;

/**
 *
 * @author Hugo
 */
public class LoginController {

    Cliente cliente;
    Gestor gestor;
    login login;

    public LoginController() {
    }

    /**
     * This function creates a login object with the given email and password
     * 
     * @param email    The email address of the user.
     * @param password The password for the user.
     * @return A new login object is being returned.
     */
    public login createLogin(String email, String password) throws ClassNotFoundException, SQLException {
        return new login(email, password);
    }

    /**
     * VerfLogin() returns 1 if the user is a client, 2 if the user is a manager,
     * and 0 if the user is
     * neither
     * 
     * @param email    String
     * @param password String
     * @return The return value is the result of the method verfLoginCliente or
     *         verfLoginGestor.
     */
    public int verfLogin(String email, String password) throws ClassNotFoundException, SQLException {

        if (verfLoginCliente(email, password)) {
            return 1;
        } else if (verfLoginGestor(email, password)) {
            return 2;
        }
        return 0;
    }

    /**
     * It checks if the email and password entered by the user are in the database
     * 
     * @param email    teste@teste.com
     * @param password 12345
     * @return A boolean value.
     */
    public boolean verfLoginCliente(String email, String password) throws ClassNotFoundException, SQLException {
        // Creating a string with the SQL query to insert a new cliente into the
        // database.
        this.login = new login();
        login.setEmail(email);
        login.setPassword(password);

        String GET_SQL = "SELECT * FROM Cliente WHERE email = ? AND password = ?;";
        boolean res = false;
        // Creating a connection to the database.
        Connection conn = DBConnection.getConnection();
        // Creating a prepared statement for the SQL query.
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getPassword());
            // Printing the prepared statement to the console.
            // System.out.println(ps);
            rset = ps.executeQuery();
            res = rset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res) {
            this.cliente = new Cliente();
            this.cliente.setId(rset.getInt("id"));
            this.cliente.setNome(rset.getString("nome"));
            this.cliente.setEmail(rset.getString("email"));
            this.cliente.setNif(rset.getInt("nif"));
            this.cliente.setPassword(rset.getString("password"));
            // System.out.println(this.cliente);
        }
        // Closing the connection to the database.
        conn.close();
        // System.out.println("Connection closed successfully!");
        return res;
    }

    /**
     * It checks if the email and password match the ones in the database
     * 
     * @param email    teste@teste.com
     * @param password 12345
     * @return A boolean value.
     */
    public boolean verfLoginGestor(String email, String password) throws ClassNotFoundException, SQLException {
        // Creating a string with the SQL query to insert a new gestor into the
        // database.
        this.login = new login();
        login.setEmail(email);
        login.setPassword(password);

        String GET_SQL = "SELECT * FROM Gestor WHERE email = ? AND password = ?;";
        boolean res = false;
        // Creating a connection to the database.
        Connection conn = DBConnection.getConnection();
        // Creating a prepared statement for the SQL query.
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getPassword());
            // Printing the prepared statement to the console.
            // System.out.println(ps);
            rset = ps.executeQuery();
            res = rset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res) {
            this.gestor = new Gestor();
            this.gestor.setId(rset.getInt("id"));
            this.gestor.setNome(rset.getString("nome"));
            this.gestor.setEmail(rset.getString("email"));
            this.gestor.setNif(rset.getInt("nif"));
            this.gestor.setPassword(rset.getString("password"));
            // System.out.println(this.gestor);
        }
        // Closing the connection to the database.
        conn.close();
        // System.out.println("Connection closed successfully!");
        // Returning the result of the query.
        return res;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Gestor getGestor() {
        return this.gestor;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public login getLogin() {
        return this.login;
    }

    public void setLogin(login login) {
        this.login = login;
    }

}
