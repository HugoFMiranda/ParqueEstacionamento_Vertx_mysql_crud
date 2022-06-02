/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Cliente;
import Model.Estacionamento;
import Model.Reserva;
import Model.Viatura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.*;
import Utils.*;

/**
 *
 * @author Hugo
 */
public class FacturacaoController {
    private Facturacao facturacao;

    public FacturacaoController() {
    }

    /**
     * This function creates a new cliente object
     */
    public void novoFacturacao() {
        this.facturacao = new Facturacao();
    }

    /**
     * It inserts a new cliente into the database
     * 
     * @param facturacao the cliente object to be inserted into the database.
     * @return The result of the query.
     */
    public int registerFacturacao(Cliente cliente, Viatura viatura, Estacionamento estacionamento, Reserva reserva,
            int id,
            int nif)
            throws ClassNotFoundException, SQLException {
        novoFacturacao();
        this.facturacao.setCliente(cliente);
        this.facturacao.setViatura(viatura);
        this.facturacao.setEstacionamento(estacionamento);
        this.facturacao.setReserva(reserva);
        this.facturacao.setId(id);
        this.facturacao.setNif(nif);
        // Creating a string with the SQL query to insert a new cliente into the
        // database.
        String INSERT_SQL = "INSERT INTO  (cliente, viatura, estacionamento, reserva, id, nif) VALUES (?,?,?,?,?,?,?);";
        int res = 0;
        // Creating a connection to the database.
        Connection conn = DBConnection.getConnection();
        // Creating a prepared statement for the SQL query.
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            /*
             * ps.setString(1, facturacao.getCliente());
             * ps.setString(2, facturacao.getViatura());
             * ps.setString(3, facturacao.getEstacionamento());
             * ps.setString(4, facturacao.getReserva());
             * ps.setInt(5, facturacao.getId);
             * ps.setInt(6, facturacao.getNif);
             */
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
