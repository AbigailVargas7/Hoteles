/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Hotel implements Serializable{
    
    private String nombre;
    private String ciudad;
    private String direccion;
    private String telefono;
    private  ArrayList<Habitacion> habitaciones = new ArrayList<>();
    private static final long serialVersionUID = 771223652324025287L;
    

    /**
     *
     * @param nombre
     * @param ciudad
     * @param direccion
     * @param telefono
     * @param h
     */
    public Hotel(String nombre, String ciudad, String direccion, String telefono, ArrayList<Habitacion> h) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        habitaciones = h;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     *
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @return
     */
    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.direccion);
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
        final Hotel other = (Hotel) obj;
        if (!Objects.equals(this.nombre.toUpperCase(), other.nombre.toUpperCase())) {
            return false;
        }
       
        return true;
    }

    @Override
    public String toString() {
        return "Hotel " +nombre;
    }
    
    
}
