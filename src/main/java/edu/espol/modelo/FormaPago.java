/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.modelo;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class FormaPago {
    private String formaPago;
    final static FormaPago[] arrFormasPago = {new FormaPago("Efectivo"), new FormaPago("Tarjeta de crédito")};

    /**
     * 
     */
    public static List<FormaPago> formasPago = Arrays.asList(arrFormasPago);

    /**
     *
     * @param string
     */
    public FormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    
    /**
     *
     * @return la forma de pago
     */
    public String getFormaPago() {
        return formaPago;
    }
    /**
     *
     * @return la forma textual de forma de pago
     */
    @Override
    public String toString() {
        return formaPago;
    }
}
