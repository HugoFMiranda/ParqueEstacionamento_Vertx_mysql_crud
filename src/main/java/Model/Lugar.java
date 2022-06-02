package Model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Lugar {
    private static final AtomicInteger idc = new AtomicInteger(000);
    private int id;
    private String estado;
    private static final String ESTADO = "livre";

    // A constructor.
    public Lugar() {
        this.id = idc.incrementAndGet();
        this.estado = ESTADO;
    }

    // A constructor.
    public Lugar(String estado) {
        this.id = idc.incrementAndGet();
        this.estado = estado;
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
     * This function returns the value of the variable estado
     * 
     * @return The value of the variable estado.
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * This function sets the value of the variable estado to the value of the
     * parameter estado
     * 
     * @param estado The state of the user.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * If the object is the same object, return true. If the object is not an
     * instance of the same
     * class, return false. If the object is an instance of the same class, compare
     * the fields
     * 
     * @param o The object to be compared for equality with this object.
     * @return The hashcode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Lugar)) {
            return false;
        }
        Lugar lugar = (Lugar) o;
        return id == lugar.id && Objects.equals(estado, lugar.estado);
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString() method is being overridden to return the id and estado
     *         of the object.
     */
    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", estado='" + getEstado() + "'" +
                "}";
    }

}
