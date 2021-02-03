/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.modelo;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Cliente {
    private String nombreApellidos;
    private String cedula;
    private String paisOrigen;
    private FormaPago formaPago;

    /**
     *
     */
    public static ArrayList<Cliente> clientes = new ArrayList<>();
    
    /**
     *
     * @param nombreApellidos
     * @param cedula
     * @param paisOrigen
     * @param formaPago
     */
    public Cliente(String nombreApellidos, String cedula, String paisOrigen, FormaPago formaPago) {
        this.nombreApellidos = nombreApellidos;
        this.cedula = cedula;
        this.paisOrigen = paisOrigen;
        this.formaPago = formaPago;
    }

    /**
     *
     * @return 
     */
    public String getNombreApellidos() {
        return nombreApellidos;
    }

    /**
     *
     * @return
     */
    public String getCedula() {
        return cedula;
    }

    /**
     *
     * @return
     */
    public String getPaisOrigen() {
        return paisOrigen;
    }

    /**
     *
     * @return
     */
    public FormaPago isFormaPago() {
        return formaPago;
    }

    
    
    
    
}
