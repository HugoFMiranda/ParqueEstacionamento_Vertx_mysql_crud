/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.Serializable;

/**
 *
 * @author Hugo Miranda
 */
public class DataHora implements Serializable {

    private int hora;
    private int minuto;

    private static final int INT_PREDEFINIDO = 0;

    /**
     * Construtor vazio
     */
    public DataHora() {
        this.hora = INT_PREDEFINIDO;
        this.minuto = INT_PREDEFINIDO;
    }

    /**
     * Construtor com os parametros:
     *
     * @param i
     * @param i1
     * @param hora
     */
    public DataHora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    /**
     * Construtor com os parametros:
     *
     * @param data
     * @param i
     * @param i1
     * @param hora
     * @param minuto
     */
    public DataHora(Data data, int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    /**
     * Construtor com os parametros:
     *
     * @param dh
     */
    public DataHora(DataHora dh) {
        this.hora = dh.hora;
        this.minuto = dh.minuto;
    }

    /**
     * Retorna a hora
     *
     * @return
     */
    public int getHora() {
        return hora;
    }

    /**
     * Retorna o minuto
     *
     * @return
     */
    public int getMinuto() {
        return minuto;
    }

    /**
     * Define a hora
     *
     * @param hora
     */
    public void setHora(int hora) {
        this.hora = hora;
    }

    /**
     * Define o minuto
     *
     * @param minuto
     */
    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    /**
     * Metodo equals
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final DataHora other = (DataHora) obj;
        return this.hora == other.hora
                && this.minuto == other.minuto;
    }

    /**
     * Retorna a hora em string
     *
     * @return
     */
    @Override
    public String toString() {
        return hora + "H:" + minuto + "M";
    }

    /**
     *
     * @param horas
     * @param minutos
     * @return
     */
    public DataHora somarTempo(int horas, int minutos) {

        int tempo = this.getMinuto() + horas * 60 + minutos + this.getHora() * 60;
        int hours = tempo / 60;
        int minutes = tempo % 60;
        return new DataHora(hours, minutes);
    }

    /**
     *
     * @return
     */
    public int ToMinutos() {
        return this.getMinuto() + (this.getHora() * 60);
    }
}
