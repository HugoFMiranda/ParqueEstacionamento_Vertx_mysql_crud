package Model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Gestor {
    private static final AtomicInteger idc = new AtomicInteger(000);
    private int id, nif;
    private String nome, email, password;
    private final String NOME = "", EMAIL = "", PASSWORD = "";
    private final int NIF = 0;

    // It's a constructor.
    public Gestor() {
        this.id = idc.getAndIncrement();
        this.nome = NOME;
        this.email = EMAIL;
        this.nif = NIF;
        this.password = PASSWORD;
    }

    // It's a constructor.
    public Gestor(String nome, String email, int nif, String password) {
        this.id = idc.incrementAndGet();
        this.nome = nome;
        this.email = email;
        this.nif = nif;
        this.password = password;
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
     * This function returns the nif of the client
     * 
     * @return The nif of the client.
     */
    public int getNif() {
        return this.nif;
    }

    /**
     * This function returns the value of the variable nome
     * 
     * @return The value of the variable nome.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * This function returns the email of the user
     * 
     * @return The email address of the user.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * This function returns the password of the user
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return this.password;
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
     * This function sets the nif of the client to the nif passed as a parameter
     * 
     * @param nif The NIF of the client.
     */
    public void setNif(int nif) {
        this.nif = nif;
    }

    /**
     * This function sets the name of the object
     * 
     * @param nome The name of the person
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * This function sets the email of the user
     * 
     * @param email The email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This function sets the password of the user
     * 
     * @param password The password to use for the connection.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The function toString() is used to convert the object to a string
     * 
     * @return The toString() method is being returned.
     */
    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", nif='" + nif + "'" +
                ", nome='" + nome + "'" +
                ", email='" + email + "'" +
                ", password='" + password + "'" +
                ", NOME='" + NOME + "'" +
                ", EMAIL='" + EMAIL + "'" +
                ", PASSWORD='" + PASSWORD + "'" +
                ", NIF='" + NIF + "'" +
                "}";
    }

    /**
     * The equals() method is used to compare two objects for equality
     * 
     * @param o the object to be compared for equality with this cliente
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Gestor)) {
            return false;
        }
        Gestor cliente = (Gestor) o;
        return id == cliente.id && nif == cliente.nif && Objects.equals(nome, cliente.nome)
                && Objects.equals(email, cliente.email) && Objects.equals(password, cliente.password)
                && Objects.equals(NOME, cliente.NOME) && Objects.equals(EMAIL, cliente.EMAIL)
                && Objects.equals(PASSWORD, cliente.PASSWORD) && NIF == cliente.NIF;
    }
}
