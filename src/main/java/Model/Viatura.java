package Model;

import java.util.Objects;

public class Viatura {
    private int id, ano, idp;
    private Cliente c;
    private String tipo, matricula, marca, modelo;
    private final int ANO = 2000;
    private final String TIPO = "", MATRICULA = "", MARCA = "", MODELO = "";

    public Viatura() {
    }

    // A constructor.
    public Viatura(Cliente c) {
        this.ano = ANO;
        this.idp = c.getId();
        this.tipo = TIPO;
        this.matricula = MATRICULA;
        this.marca = MARCA;
        this.modelo = MODELO;
    }

    // A constructor.
    public Viatura(int ano, int idp, String tipo, String matricula, String marca, String modelo) {
        this.ano = ano;
        this.idp = idp;
        this.tipo = tipo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
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
     * This function returns the value of the variable ano
     * 
     * @return The year of the car.
     */
    public int getAno() {
        return this.ano;
    }

    /**
     * This function sets the year of the car
     * 
     * @param ano The year of the car
     */
    public void setAno(int ano) {
        this.ano = ano;
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
     * This function returns the value of the variable tipo
     * 
     * @return The tipo of the object.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * This function sets the tipo of the object
     * 
     * @param tipo The type of the object.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * This function returns the matricula of the object
     * 
     * @return The matricula of the student.
     */
    public String getMatricula() {
        return this.matricula;
    }

    /**
     * This function sets the matricula of the object
     * 
     * @param matricula String
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * This function returns the value of the variable marca
     * 
     * @return The value of the variable marca.
     */
    public String getMarca() {
        return this.marca;
    }

    /**
     * This function sets the marca of the object
     * 
     * @param marca String
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * This function returns the modelo of the car
     * 
     * @return The modelo attribute of the object.
     */
    public String getModelo() {
        return this.modelo;
    }

    /**
     * This function sets the modelo of the car
     * 
     * @param modelo model
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
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
     * This function sets the value of the variable c to the value of the variable c
     * in the function
     * 
     * @param c Cliente
     */
    public void setC(Cliente c) {
        this.c = c;
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
                ", ano='" + ano + "'" +
                ", idp='" + idp + "'" +
                ", tipo='" + tipo + "'" +
                ", matricula='" + matricula + "'" +
                ", marca='" + marca + "'" +
                ", modelo='" + modelo + "'" +
                ", ANO='" + ANO + "'" +
                ", TIPO='" + TIPO + "'" +
                ", MATRICULA='" + MATRICULA + "'" +
                ", MARCA='" + MARCA + "'" +
                ", MODELO='" + MODELO + "'" +
                "}";
    }

    /**
     * The function returns true if the object passed as an argument is equal to the
     * object that called
     * the function
     * 
     * @param o the object to compare to
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Viatura)) {
            return false;
        }
        Viatura viaturas = (Viatura) o;
        return id == viaturas.id && ano == viaturas.ano && idp == viaturas.idp && Objects.equals(tipo, viaturas.tipo)
                && Objects.equals(matricula, viaturas.matricula) && Objects.equals(marca, viaturas.marca)
                && Objects.equals(modelo, viaturas.modelo) && ANO == viaturas.ANO && Objects.equals(TIPO, viaturas.TIPO)
                && Objects.equals(MATRICULA, viaturas.MATRICULA) && Objects.equals(MARCA, viaturas.MARCA)
                && Objects.equals(MODELO, viaturas.MODELO);
    }

}