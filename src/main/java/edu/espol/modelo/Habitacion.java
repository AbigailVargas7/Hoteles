/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Habitacion implements Serializable{
    private String numeroHabitacion;
    private float precioHabitacion;
    private TipoHabitacion tipoH;
    private String estadoH;
    private String servicios;
    private static final long serialVersionUID = 771223652324043287L;

    /**
     *
     * @param string
     * @param f
     * @param th
     * @param string1
     */
    public Habitacion(String numeroHabitacion, float precioHabitacion, TipoHabitacion tipoH, String servicios) {
        this.numeroHabitacion = numeroHabitacion;
        this.precioHabitacion = precioHabitacion;
        this.tipoH = tipoH;
        this.servicios = servicios;
        this.estadoH = "Disponible";
    }
    
    /**
     *
     * @return
     */
    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    /**
     *
     * @return
     */
    public float getPrecioHabitacion() {
        return precioHabitacion;
    }

    /**
     *
     * @return
     */
    public String getServicios() {
        return servicios;
    }

    /**
     *
     * @return
     */
    public TipoHabitacion getTipoH() {
        return tipoH;
    }

    /**
     *
     * @param estadoH
     */
    public void setEstadoH(String estadoH) {
        this.estadoH = estadoH;
    }

    /**
     *
     * @return
     */
    public String getEstadoH() {
        return estadoH;
    }

    @Override
    /**
     
     **/
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.numeroHabitacion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Habitacion other = (Habitacion) obj;
        if (!Objects.equals(this.numeroHabitacion, other.numeroHabitacion)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "N° "+numeroHabitacion +"\nPrecio: "+precioHabitacion+"\nTipo: "+tipoH+"\nEstado: "+estadoH+"\nServicios: "+servicios;
    }

    

    

    
    
    
    
    
    
}
