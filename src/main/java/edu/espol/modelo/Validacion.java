/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.modelo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 *
 * @author Usuario
 */
public class Validacion {

    /**
     *
     * @param cadena
     * @return
     */
    public static boolean isLetter(String cadena){
        for (int i = 0; i < cadena.length(); i++) {
            if (!Character.isLetter(cadena.charAt(i)) && cadena.charAt(i)!=' ')
                return false;
            }
        return true;
    }
    
    /**
     *
     * @param cadena
     * @return
     */
    public static boolean isEntero(String cadena){
        try{
            int entero = Integer.parseInt(cadena);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }        
    }
    
    /**
     *
     * @param cadena
     * @return
     */
    public static boolean isDouble(String cadena){
        try{
            double entero = Double.parseDouble(cadena);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }   
    }
    
    /**
     *
     * @param mensaje
     * @param encabezado
     * @param titulo
     * @param al
     * @return
     */
    public static Alert alerta(String mensaje, String encabezado, String titulo, AlertType al){
        Alert alert = new Alert(al);
        alert.setContentText(mensaje);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.showAndWait();
        return alert;
    }
    
    /**
     *
     * @param t
     * @param mensaje
     */
    public static void txt_isLetter(TextField t,String mensaje){
        t.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->{
            if(!t.getText().trim().isEmpty()){
                if (!newValue && !isLetter(t.getText())){
                Validacion.alerta(mensaje,"Solo letras","Formato invalido", Alert.AlertType.WARNING);
                t.requestFocus();
            }
            }

        });   
    }
    
    /**
     *
     * @param t
     * @param mensaje
     */
    public static void txt_isDigit(TextField t, String mensaje){
        t.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->{
            if(!t.getText().trim().isEmpty()){
                if (!newValue && !isEntero(t.getText())){
                    Validacion.alerta(mensaje,"Solo enteros","Formato invalido", Alert.AlertType.WARNING);
                    t.requestFocus();
                }    
            }

        });   
    }
    
    /**
     *
     * @param t
     * @param len
     * @param mensaje
     */
    public static void txt_isDigit(TextField t, int len, String mensaje){
        t.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->{
            if(!t.getText().trim().isEmpty()){
                
                if (!newValue && (!isEntero(t.getText()) || t.getText().length()!=len)){
                    Validacion.alerta(mensaje,"Solo enteros","Formato invalido", Alert.AlertType.WARNING);
                    t.requestFocus();                 
                }    
            }
        });                
    }
     
    /**
     *
     * @param t
     * @param mensaje
     */
    public static void txt_isDecimal(TextField t, String mensaje){
        t.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->{
            if(!t.getText().trim().isEmpty()){
                if (!newValue && !isDouble(t.getText())){
                    Validacion.alerta(mensaje,"Solo puede ingresar valores numericos","Formato invalido", Alert.AlertType.WARNING);
                    t.requestFocus();
                }    
            }
        });   
    }
    
    
    
}
