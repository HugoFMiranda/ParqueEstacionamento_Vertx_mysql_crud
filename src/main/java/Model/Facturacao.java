package Model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Facturacao {
    private static final AtomicInteger idc = new AtomicInteger(000);
    private Cliente cliente;
    private Viatura viatura;
    private Estacionamento estacionamento;
    private Reserva reserva;
    private int id, preco, nif, idp, idv, ide, idr;

    // A constructor.
    public Facturacao() {

    }

    // A constructor.
    public Facturacao(Cliente cliente, Viatura viatura, Estacionamento estacionamento, Reserva reserva, int id,
            int nif) {
        this.cliente = cliente;
        this.viatura = viatura;
        this.estacionamento = estacionamento;
        this.reserva = reserva;
        this.id = idc.getAndIncrement();
        // ! FIXME FALTA FAZER PREÇO
        // TODO PRECO
        this.preco = 0;
        this.nif = nif;
        this.idp = cliente.getId();
        this.idv = viatura.getId();
        this.ide = estacionamento.getId();
        this.idr = reserva.getId();
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
     * It sets the value of the viatura variable.
     * 
     * @param viatura is a class that has a list of objects of the class "Viatura"
     */
    public void setViatura(Viatura viatura) {
        this.viatura = viatura;
    }

    /**
     * This function returns the value of the attribute estacionamento
     * 
     * @return The object estacionamento.
     */
    public Estacionamento getEstacionamento() {
        return this.estacionamento;
    }

    /**
     * It sets the value of the variable estacionamento.
     * 
     * @param estacionamento Estacionamento
     */
    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    /**
     * This function returns the value of the private variable reserva
     * 
     * @return The reservation.
     */
    public Reserva getReserva() {
        return this.reserva;
    }

    /**
     * It sets the value of the variable reserva.
     * 
     * @param reserva the object that is being passed to the method
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
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
     * public int getPreco() {
     * return this.preco;
     * }
     * 
     * @return The price of the item.
     */
    public int getPreco() {
        return this.preco;
    }

    /**
     * This function sets the price of the product
     * 
     * @param preco The price of the item
     */
    public void setPreco(int preco) {
        this.preco = preco;
    }

    /**
     * This function returns the nif of the client
     * 
     * @return The nif of the client.
     */
    public int getNif() {
        return this.nif;
    }

    /**
     * This function sets the nif of the client to the nif passed as a parameter
     * 
     * @param nif The NIF of the client.
     */
    public void setNif(int nif) {
        this.nif = nif;
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
     * This function sets the value of the idv variable to the value of the idv
     * parameter
     * 
     * @param idv The id of the video
     */
    public void setIdv(int idv) {
        this.idv = idv;
    }

    /**
     * This function returns the value of the private variable ide
     * 
     * @return The ide of the object.
     */
    public int getIde() {
        return this.ide;
    }

    /**
     * This function sets the value of the variable ide to the value of the
     * parameter ide
     * 
     * @param ide The ID of the element.
     */
    public void setIde(int ide) {
        this.ide = ide;
    }

    /**
     * This function returns the value of the private variable idr
     * 
     * @return The idr of the object.
     */
    public int getIdr() {
        return this.idr;
    }

    /**
     * This function sets the value of the idr variable to the value of the idr
     * parameter
     * 
     * @param idr The id of the row in the database
     */
    public void setIdr(int idr) {
        this.idr = idr;
    }

    /**
     * The function is checking if the object is equal to itself, if it is not an
     * instance of the
     * class, if the cliente, viatura, estacionamento, reserva, id, preco, nif, idp,
     * idv, ide, idr are
     * equal to the other object
     * 
     * @param o Object
     * @return The hashCode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Facturacao)) {
            return false;
        }
        Facturacao facturacao = (Facturacao) o;
        return Objects.equals(cliente, facturacao.cliente) && Objects.equals(viatura, facturacao.viatura)
                && Objects.equals(estacionamento, facturacao.estacionamento)
                && Objects.equals(reserva, facturacao.reserva) && id == facturacao.id && preco == facturacao.preco
                && nif == facturacao.nif && idp == facturacao.idp && idv == facturacao.idv && ide == facturacao.ide
                && idr == facturacao.idr;
    }

    /**
     * It returns a string representation of the object.
     * 
     * @return The toString() method returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "{" +
                " cliente='" + cliente + "'" +
                ", viatura='" + viatura + "'" +
                ", estacionamento='" + estacionamento + "'" +
                ", reserva='" + reserva + "'" +
                ", id='" + id + "'" +
                ", preco='" + preco + "'" +
                ", nif='" + nif + "'" +
                ", idp='" + idp + "'" +
                ", idv='" + idv + "'" +
                ", ide='" + ide + "'" +
                ", idr='" + idr + "'" +
                "}";
    }

}
