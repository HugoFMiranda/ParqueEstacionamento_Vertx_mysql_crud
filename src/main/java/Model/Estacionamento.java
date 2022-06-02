package Model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import Utils.*;

public class Estacionamento {
    private static final AtomicInteger idc = new AtomicInteger(000);
    private Data datae, datas;
    private DataHora horae, horas;
    private Lugar lugar;
    private Cliente cliente;
    private Viatura viatura;
    private int id, idp, idv, idl;

    // A constructor.
    public Estacionamento() {

    }

    // A constructor.
    public Estacionamento(Data datae, Data datas, DataHora horae, DataHora horas, Lugar lugar, Cliente cliente,
            Viatura viatura, int id) {
        this.id = idc.getAndIncrement();
        // TODO data automatica
        // LocalDate date = LocalDate.now();
        this.datae = datae;
        this.datas = datas;
        this.horae = horae;
        this.horas = horas;
        this.idp = cliente.getId();
        this.idv = viatura.getId();
        this.idl = lugar.getId();
    }

    /**
     * The getDatae function returns the data from the Data class.
     * 
     * @return A data object
     *
     */
    public Data getDatae() {
        return this.datae;
    }

    /**
     * The setDatae function sets the datae field of this object to the specified
     * value.
     * 
     *
     * @param datae Set the datae property of the data class
     *
     * @return The datae variable
     *
     */
    public void setDatae(Data datae) {
        this.datae = datae;
    }

    /**
     * This function returns the datas of the class
     * 
     * @return The data object.
     */
    public Data getDatas() {
        return this.datas;
    }

    // A getter and setter method.
    /**
     * This function sets the datas variable to the datas variable passed in
     * 
     * @param datas The data to be displayed in the list.
     */
    public void setDatas(Data datas) {
        this.datas = datas;
    }

    /**
     * This function returns the value of the variable horae
     * 
     * @return The method is returning the value of the instance variable horae.
     */
    public DataHora getHorae() {
        return this.horae;
    }

    /**
     * This function sets the value of the variable horae to the value of the
     * parameter horae
     * 
     * @param horae DataHora
     */
    public void setHorae(DataHora horae) {
        this.horae = horae;
    }

    /**
     * This function returns the value of the variable horas
     * 
     * @return The method is returning the value of the instance variable horas.
     */
    public DataHora getHoras() {
        return this.horas;
    }

    /**
     * It sets the value of the variable horas to the value of the variable horas.
     * 
     * @param horas DataHora
     */
    public void setHoras(DataHora horas) {
        this.horas = horas;
    }

    /**
     * This function returns the value of the variable lugar
     * 
     * @return The object lugar.
     */
    public Lugar getLugar() {
        return this.lugar;
    }

    /**
     * It sets the lugar variable to the lugar parameter.
     * 
     * @param lugar is a class that has a list of Lugar
     */
    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    /**
     * This function returns the cliente attribute of the object
     * 
     * @return The cliente object.
     */
    public Cliente getCliente() {
        return this.cliente;
    }

    /**
     * This function sets the cliente variable to the cliente parameter
     * 
     * @param cliente Cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * This function returns the viatura of the object
     * 
     * @return The object viatura.
     */
    public Viatura getViatura() {
        return this.viatura;
    }

    /**
     * This function sets the value of the viatura variable to the value of the
     * viatura parameter
     * 
     * @param viatura is a class that has a list of objects of the class "Pessoa"
     */
    public void setViatura(Viatura viatura) {
        this.viatura = viatura;
    }

    /**
     * This function returns the id of the current object
     * 
     * @return The id of the object.
     */
    public int getId() {
        return this.id;
    }

    /**
     * This function sets the id of the object to the id passed in as a parameter
     * 
     * @param id The id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function returns the value of the private variable idp
     * 
     * @return The idp of the object.
     */
    public int getIdp() {
        return this.idp;
    }

    /**
     * This function sets the value of the idp variable
     * 
     * @param idp The id of the product
     */
    public void setIdp(int idp) {
        this.idp = idp;
    }

    /**
     * This function returns the value of the variable idv.
     * 
     * @return The idv variable is being returned.
     */
    public int getIdv() {
        return this.idv;
    }

    /**
     * This function sets the value of the idv variable
     * 
     * @param idv The id of the video
     */
    public void setIdv(int idv) {
        this.idv = idv;
    }

    /**
     * This function returns the value of the private variable idl
     * 
     * @return The idl of the object.
     */
    public int getIdl() {
        return this.idl;
    }

    /**
     * This function sets the value of the idl variable to the value of the idl
     * parameter
     * 
     * @param idl The id of the list
     */
    public void setIdl(int idl) {
        this.idl = idl;
    }

    /**
     * The equals() method compares the object to the object passed in
     * 
     * @param o The object to compare to.
     * @return The hashcode of the object.
     */
    @Override
    // Comparing the object to the object passed in.
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estacionamento)) {
            return false;
        }
        Estacionamento estacionamento = (Estacionamento) o;
        return Objects.equals(datae, estacionamento.datae) && Objects.equals(datas, estacionamento.datas)
                && Objects.equals(horae, estacionamento.horae) && Objects.equals(horas, estacionamento.horas)
                && Objects.equals(lugar, estacionamento.lugar) && Objects.equals(cliente, estacionamento.cliente)
                && Objects.equals(viatura, estacionamento.viatura) && id == estacionamento.id
                && idp == estacionamento.idp && idv == estacionamento.idv && idl == estacionamento.idl;
    }

    /**
     * It returns a string with the values of the variables of the object
     * 
     * @return The toString() method returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "{" +
                " datae='" + datae + "'" +
                ", datas='" + datas + "'" +
                ", horae='" + horae + "'" +
                ", horas='" + horas + "'" +
                ", lugar='" + lugar + "'" +
                ", cliente='" + cliente + "'" +
                ", viatura='" + viatura + "'" +
                ", id='" + id + "'" +
                ", idp='" + idp + "'" +
                ", idv='" + idv + "'" +
                ", idl='" + idl + "'" +
                "}";
    }

}
