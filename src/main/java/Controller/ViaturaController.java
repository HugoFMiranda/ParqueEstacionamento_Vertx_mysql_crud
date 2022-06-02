/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Viatura;

/**
 *
 * @author Hugo
 */
public class ViaturaController {
    Viatura viatura;

    public ViaturaController() {
    }

    /**
     * This function creates a new instance of the class Viatura
     */
    public void novaViatura() {
        this.viatura = new Viatura();
    }

    /**
     * I'm trying to get all the vehicles from a specific person
     * 
     * @param idp id of the person
     * @return A list of Viatura objects.
     */
    public List<Viatura> getAll(int idp) throws SQLException {
        List<Viatura> listV = new ArrayList<Viatura>();
        Viatura v = new Viatura();
        String STR_SQL = "SELECT * FROM Viaturas WHERE ID_Proprietario = ?;";
        // Creating a connection to the database.
        Connection conn = DBConnection.getConnection();
        // Creating a prepared statement for the SQL query.
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(STR_SQL);
            ps.setInt(1, idp);
            rset = ps.executeQuery();
            while (rset.next()) {
                v.setId(rset.getInt("id"));
                v.setTipo(rset.getString("tipo"));
                v.setMatricula(rset.getString("matricula"));
                v.setMarca(rset.getString("marca"));
                v.setModelo(rset.getString("modelo"));
                v.setAno(rset.getInt("ano"));
                v.setId(rset.getInt("ID_Proprietario"));
                listV.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Closing the connection to the database.
        conn.close();
        return listV;
    }

    /**
     * It deletes a row from the database table Viaturas, where the id is equal to
     * the id passed as a
     * parameter
     * 
     * @param id the id of the vehicle to be deleted
     * @return The method returns a boolean value.
     */
    public boolean deleteViatura(int id) throws SQLException {
        String STR_SQL = "DELETE FROM Viaturas WHERE id = ?;";
        // Creating a connection to the database.
        Connection conn = DBConnection.getConnection();
        // Creating a prepared statement for the SQL query.
        PreparedStatement ps;
        boolean res = false;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(STR_SQL);
            ps.setInt(1, id);
            rset = ps.executeQuery();
            res = rset.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Closing the connection to the database.
        conn.close();
        return res;
    }

    /**
     * It gets a vehicle from the database and returns it
     * 
     * @param id int
     * @return The method is returning the viatura object.
     */
    public Viatura getViatura(int id) throws ClassNotFoundException, SQLException {
        String GET_SQL = "SELECT * FROM Viaturas WHERE id = ?;";
        // Creating a connection to the database.
        Connection conn = DBConnection.getConnection();
        // Creating a prepared statement for the SQL query.
        PreparedStatement ps;
        boolean res = false;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            ps.setInt(1, id);
            rset = ps.executeQuery();
            res = rset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res) {
            this.viatura = new Viatura();
            this.viatura.setId(rset.getInt("id"));
            this.viatura.setTipo(rset.getString("tipo"));
            this.viatura.setMatricula(rset.getString("matricula"));
            this.viatura.setMarca(rset.getString("marca"));
            this.viatura.setModelo(rset.getString("modelo"));
            this.viatura.setAno(rset.getInt("ano"));
            this.viatura.setIdp(rset.getInt("idp"));
        }
        // Closing the connection to the database.
        conn.close();
        // System.out.println("Connection closed successfully!");
        return viatura;
    }

    /**
     * It takes 6 parameters, creates a new Viatura object, sets the parameters to
     * the object, creates
     * a connection to the database, creates a prepared statement, sets the
     * parameters to the prepared
     * statement, executes the query and returns the result of the query
     * 
     * @param ano       int
     * @param idp       int
     * @param tipo      String
     * @param matricula String
     * @param marca     String
     * @param modelo    String
     * @return The result of the query.
     */
    public int registerViatura(int ano, int idp, String tipo, String matricula, String marca, String modelo)
            throws ClassNotFoundException, SQLException {
        novaViatura();
        this.viatura.setAno(ano);
        this.viatura.setIdp(idp);
        this.viatura.setTipo(tipo);
        this.viatura.setMatricula(matricula);
        this.viatura.setMarca(marca);
        this.viatura.setModelo(modelo);

        String INSERT_SQL = "INSERT INTO Viatura (tipo, matricula, marca, modelo, ano, idp) VALUES (?,?,?,?,?,?);";
        int res = 0;
        // Creating a connection to the database.
        Connection conn = DBConnection.getConnection();
        // Creating a prepared statement for the SQL query.
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, viatura.getTipo());
            ps.setString(2, viatura.getMatricula());
            ps.setString(3, viatura.getMarca());
            ps.setString(4, viatura.getModelo());
            ps.setInt(5, viatura.getAno());
            ps.setInt(6, viatura.getIdp());
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
