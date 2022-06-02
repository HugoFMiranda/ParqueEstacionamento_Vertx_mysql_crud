/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.*;
import Utils.*;

/**
 *
 * @author Hugo
 */
public class EstacionamentoController {

    private Estacionamento estacionamento;

    public EstacionamentoController() {

    }

    public void novoEstacionamento() {
        this.estacionamento = new Estacionamento();

    }

    public int registerEstacionamento(Data datae, Data datas, DataHora horae, DataHora horas, Lugar lugar,
            Cliente cliente,
            Viatura viatura, int id)
            throws ClassNotFoundException, SQLException {
        novoEstacionamento();
        this.estacionamento.setDatae(datae);
        this.estacionamento.setDatas(datas);
        this.estacionamento.setHorae(horae);
        this.estacionamento.setHoras(horas);
        this.estacionamento.setLugar(lugar);
        this.estacionamento.setCliente(cliente);
        this.estacionamento.setViatura(viatura);
        this.estacionamento.setId(id);

        String INSERT_SQL = "INSERT INTO Estacionamento (datae, datas, horae, horas, lugar, cliente, viatura, id) VALUES (?,?,?,?,?,?,?,?);";
        int res = 0;

        Connection conn = DBConnection.getConnection();

        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            /*
             * ps.setData(1, datae.getDatae());
             * ps.setData(2, datas.getDatas());
             * ps.setDataHora(3, horae.getHorae());
             * ps.setDataHora(4, horas.getHoras());
             * ps.setLugar(5, lugar.getLugar());
             * ps.setCliente(6, cliente.getCliente());
             * ps.setViatura(7, viatura.getViatura());
             * ps.setInt(8, id.getId());
             */
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
