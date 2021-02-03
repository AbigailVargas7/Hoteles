/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.sistemahotel;

import edu.espol.modelo.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John
 */
public class InicioHotelController implements Initializable {

    @FXML 
    private TextField txtNombre;
    @FXML 
    private Label mensaje;
    @FXML 
    private Button btnComenzar;
    
    InicioHotelController ihc;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ihc = this;
        
    }    

    /**
     * este metodo permite visualizar la pantalla de crear hotel 
     * @param event recibe un evento
     */
    @FXML
    public void verPantallaCrearHotel(ActionEvent event){
        if (txtNombre.getText().trim().isEmpty()){
            mensaje.setText("*Debe ingresar el nombre del hotel con el que va a ingresar*");
        }else{
            Hotel h = buscarHotel();
            if (h!=null)
                App.hotel=h;
            try {
            Stage actual = (Stage) btnComenzar.getScene().getWindow();
            actual.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("crearHotel.fxml"));            
            Parent root = loader.load();
            
            CrearHotelController ct = (CrearHotelController) loader.getController();
            if (h!=null)
                ct.recibeParametros(ihc,App.hotel.getNombre());
            else
                ct.recibeParametros(ihc,this.txtNombre.getText().trim());
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Pantalla de crear hoteles");
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());        
        }
        }
    }
    
    /**
     *este metodo permite buscar el hotel al cual se le esta ingresando informacion
     * @return retorna un hotel
     */
    public Hotel buscarHotel(){
        for(Hotel ht: App.hoteles){
            if (ht.getNombre().toUpperCase().equals(txtNombre.getText().trim().toUpperCase()))
                return ht;
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    public String getNombre(){
        return this.txtNombre.getText().trim();
    }
}
