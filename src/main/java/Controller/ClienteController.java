package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Cliente;

/**
 *
 * @author Hugo
 */
public class ClienteController {

    private Cliente cliente;

    public ClienteController() {
    }

    /**
     * This function creates a new cliente object
     */
    public void novoCliente() {
        this.cliente = new Cliente();
    }

    /**
     * It inserts a new cliente into the database
     * 
     * @param cliente the cliente object to be inserted into the database.
     * @return The result of the query.
     */
    public int registerCliente(String nome, String email, int nif, String password)
            throws ClassNotFoundException, SQLException {
        novoCliente();
        this.cliente.setNome(nome);
        this.cliente.setEmail(email);
        this.cliente.setNif(nif);
        this.cliente.setPassword(password);
        // Creating a string with the SQL query to insert a new cliente into the
        // database.
        String INSERT_SQL = "INSERT INTO Cliente (nome, email, nif, password) VALUES (?,?,?,?);";
        int res = 0;
        // Creating a connection to the database.
        Connection conn = DBConnection.getConnection();
        // Creating a prepared statement for the SQL query.
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getNif());
            ps.setString(4, cliente.getPassword());
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
