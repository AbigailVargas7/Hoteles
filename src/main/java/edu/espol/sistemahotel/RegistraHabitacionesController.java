/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.sistemahotel;

import edu.espol.modelo.Habitacion;
import edu.espol.modelo.TipoHabitacion;
import edu.espol.modelo.Validacion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class RegistraHabitacionesController implements Initializable {
    @FXML
    private TextField txtNumeroH;
    @FXML
    private TextField txtPrecioH;
    @FXML
    private ComboBox<TipoHabitacion> cboTipoH;
    @FXML
    private TextArea txtDetallesH;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnVer;
    @FXML
    private ImageView regresa;
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        validarTextF();
        cboTipoH.getItems().addAll(TipoHabitacion.tiposH);
        
    }    
    /** 
     este metodo permite validar la informacion que se ingresa por teclado
     **/
    private void validarTextF(){
        Validacion.txt_isDigit(txtNumeroH, "Debe ingresar el numero de la habitación");
        Validacion.txt_isDecimal(this.txtPrecioH, "Debe ingresar el precio por noche de la habitación "+this.txtNumeroH.getText());
        
    }
    
    /**
     * este metodo permite ver la pantalla de la recepcion 
     * @param event recibe un evento
     */
    @FXML
    public void verPantallaRecepcion(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Recepcion.fxml"));
            Parent root = loader.load();
            Stage actual = (Stage) btnVer.getScene().getWindow();
            actual.close();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Pantalla de recepción");
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getCause());
        }
        
    }

    /**
     * este metodo permite agregar una habitacion y almacenarla 
     */
    @FXML
    public void agregarH(){
        if (cboTipoH.getValue()==null || txtNumeroH.getText().trim().isEmpty() || this.txtPrecioH.getText().trim().isEmpty() 
                || txtDetallesH.getText().trim().isEmpty()){
            Validacion.alerta("Debe llenar todos los campos", "Faltan datos", "Incompletos", Alert.AlertType.WARNING);
        }else{
            Habitacion h = new Habitacion(txtNumeroH.getText(), Float.parseFloat(txtPrecioH.getText()), 
                cboTipoH.getValue(), txtDetallesH.getText() );        
            if(!App.hotel.getHabitaciones().contains(h)){
                App.hotel.getHabitaciones().add(h);
                App.serializarHoteles();
                deshabilitarTexto();
                Validacion.alerta("Habitacion agregada exitosamente", "Informacion: ", "INFORMACION", Alert.AlertType.INFORMATION);
            }else{
                int indice = App.hotel.getHabitaciones().indexOf(h);
                Habitacion ht = App.hotel.getHabitaciones().get(indice);
                Validacion.alerta(ht.toString(), "Habitacion ya existente ", "Error", Alert.AlertType.INFORMATION);
            }

        }
    }
    /**
     * este metedo permite regresar a la pantalla anterior 
     */
    @FXML
    private void regresar(){
         try {
            Stage actual = (Stage) regresa.getScene().getWindow();
            actual.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrearHotel.fxml"));            
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sistema Hoteles");
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());        
        }
    }
    /** 
     este metodo deshabilita los campos a llenar cuando la informaciond de uno de estos es erronea
     **/
    private void deshabilitarTexto(){
        this.btnAgregar.setDisable(true);
        this.txtDetallesH.setDisable(true);
        this.txtNumeroH.setDisable(true);
        this.txtPrecioH.setDisable(true);
    }
    
  }
