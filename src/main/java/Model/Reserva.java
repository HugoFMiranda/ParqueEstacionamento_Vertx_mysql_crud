package Model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.LocalDate;
import Utils.Data;

public class Reserva {
    private static final AtomicInteger idc = new AtomicInteger(000);
    private int id, idp, idv, idl;
    private Cliente c;
    private Lugar l;
    private Viatura v;
    private Data datai, dataf, dataEm;

    // A constructor.
    public Reserva(int id, Cliente c, Viatura v, Lugar l, Data datai, Data dataf) {
        this.id = idc.getAndIncrement();
        this.idp = c.getId();
        this.idv = v.getId();
        this.idl = l.getId();
        this.datai = datai;
        this.dataf = dataf;
        LocalDate date = LocalDate.now();
        this.dataEm = new Data(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
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
     * This function returns the cliente object
     * 
     * @return The object c.
     */
    public Cliente getC() {
        return this.c;
    }

    /**
     * public void setC(Cliente c) {
     * this.c = c;
     * }
     * 
     * @param c Cliente
     */
    public void setC(Cliente c) {
        this.c = c;
    }

    /**
     * public Lugar getL() {
     * return this.l;
     * }
     * 
     * @return The object l.
     */
    public Lugar getL() {
        return this.l;
    }

    /**
     * public void setL(Lugar l) {
     * this.l = l;
     * }
     * 
     * @param l is a class that has a list of objects of the class "Lugar"
     */
    public void setL(Lugar l) {
        this.l = l;
    }

    /**
     * public Viatura getV() {
     * return this.v;
     * }
     * 
     * @return The object v.
     */
    public Viatura getV() {
        return this.v;
    }

    /**
     * public void setV(Viatura v) {
     * this.v = v;
     * }
     * 
     * @param v Viatura
     */
    public void setV(Viatura v) {
        this.v = v;
    }

    /**
     * This function returns the datai variable
     * 
     * @return The datai variable is being returned.
     */
    public Data getDatai() {
        return this.datai;
    }

    /**
     * This function sets the datai variable to the datai variable in the Data class
     * 
     * @param datai the data object that contains the data to be displayed
     */
    public void setDatai(Data datai) {
        this.datai = datai;
    }

    /**
     * This function returns the dataf field of the class
     * 
     * @return The dataf variable is being returned.
     */
    public Data getDataf() {
        return this.dataf;
    }

    /**
     * This function sets the dataf variable to the dataf parameter
     * 
     * @param dataf Data
     */
    public void setDataf(Data dataf) {
        this.dataf = dataf;
    }

    /**
     * The function is used to print the object in a readable format
     * 
     * @return The toString() method is being returned.
     */
    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", idp='" + idp + "'" +
                ", idv='" + idv + "'" +
                ", idl='" + idl + "'" +
                ", c='" + c + "'" +
                ", l='" + l + "'" +
                ", v='" + v + "'" +
                ", datai='" + datai + "'" +
                ", dataf='" + dataf + "'" +
                ", dataEm='" + dataEm + "'" +
                "}";
    }

    /**
     * If the object is the same as the one being compared, return true. If the
     * object is not an
     * instance of the class, return false. If the object is an instance of the
     * class, compare the
     * values of the fields. If the values are the same, return true. If the values
     * are not the same,
     * return false
     * 
     * @param o Object
     * @return The method returns a boolean value.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reserva)) {
            return false;
        }
        Reserva reserva = (Reserva) o;
        return id == reserva.id && idp == reserva.idp && idv == reserva.idv && idl == reserva.idl
                && Objects.equals(c, reserva.c) && Objects.equals(l, reserva.l) && Objects.equals(v, reserva.v)
                && Objects.equals(datai, reserva.datai) && Objects.equals(dataf, reserva.dataf)
                && Objects.equals(dataEm, reserva.dataEm);
    }

}
