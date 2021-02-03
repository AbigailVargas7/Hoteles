  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.sistemahotel;

import edu.espol.modelo.Habitacion;
import edu.espol.modelo.Hotel;
import edu.espol.modelo.Validacion;
import static edu.espol.modelo.Validacion.isEntero;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class CrearHotelController implements Initializable {
    @FXML 
    private TextField txtNombre;
    @FXML 
    private TextField txtCiudad;
    @FXML 
    private TextField txtDireccion;
    @FXML 
    private TextField txtTelefono;
    @FXML
    private Button btnSig;
    @FXML
    private Button btnConsultaH;
    @FXML
    private Button btnCrearHotel;
    @FXML
    private ImageView regresa;
    
    private InicioHotelController ihc;
    

       
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.txtCiudad.requestFocus();
        validarHotel();
        validarTextF();
    }    
    /**
     este metodo valida que la informacion ingresada al hotel sea correcta
     **/
    private void validarHotel(){
        this.txtNombre.setDisable(true);
        if (App.hotel!=null){
            this.txtNombre.setText(App.hotel.getNombre());
            this.txtCiudad.setText(App.hotel.getCiudad());
            this.txtDireccion.setText(App.hotel.getDireccion());
            this.txtTelefono.setText(App.hotel.getTelefono());
            deshabilitarTextField();
        }
        else{
            this.btnConsultaH.setDisable(true);
            this.btnSig.setDisable(true);
        }
    }
    
    /**
     * este metodo recibe los parametros para cambiar a la siguiente pantalla de creacion de hotel
     * @param ih recibe el inciador controller
     * @param nombreHotel recibe el nombre del hotel
     */
    @FXML
    public void recibeParametros(InicioHotelController ih, String nombreHotel) {
        this.txtNombre.setText(nombreHotel);
        ihc = ih;
        
    }
    
    /**
     * este metodo ejecuta la accion de un boton que se presenta en pantalla, y de paso a la siguiente pantalla del sistema del hotel
     * @param event recibe un evento
     */
    @FXML
    public void siguiente(ActionEvent event){
        try {
            Stage actual = (Stage) regresa.getScene().getWindow();
            actual.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registraHabitaciones.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("estilo/estilo.css");
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Registro de habitacion");
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
        System.out.println(App.hoteles);
    }

    /**
     * este metodo presenta la pantalla de la recepcion
     * @param event recibe un evento
     */
    @FXML
    public void verPantallaRecepcion(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Recepcion.fxml"));
            Parent root = loader.load();
            Stage actual = (Stage) btnConsultaH.getScene().getWindow();
            actual.close();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Pantalla de recepción");
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
        
    }
    /**
     este metodo valida que la informacion ingresada por teclado sea correcta
     **/
    private void validarTextF(){
        Validacion.txt_isLetter(this.txtCiudad,"Ingrese el nombre correcto de la ciudad");
        Validacion.txt_isDigit(this.txtTelefono,"El telefono solo debe contener caracteres numericos");
        
    }

    /**
     *este metodo crea el hotel y lo almacena en una lista
     */
    @FXML
    public void creaHotel(){
        if (txtNombre.getText().isEmpty() || txtCiudad.getText().isEmpty() || txtDireccion.getText().isEmpty() || 
                txtTelefono.getText().isEmpty()){
            Validacion.alerta("Debe llenar todos los campos", "Faltan datos", "Incompletos", Alert.AlertType.WARNING);
        }else{
            Hotel hotel = new Hotel(txtNombre.getText(), txtCiudad.getText(), txtDireccion.getText(), txtTelefono.getText(), new ArrayList<Habitacion>());
            App.hotel = hotel;
            App.hoteles.add(hotel);
            App.serializarHoteles();
            
            Validacion.alerta("Se ha creado el hotel!","Información: ","INFORMACION",Alert.AlertType.INFORMATION);
            this.btnCrearHotel.setDisable(true);
            deshabilitarTextField();
            this.btnConsultaH.setDisable(false);
            this.btnSig.setDisable(false);
        }       
    }
    
    /**
     * este metodo permite por pantalla regresar a la ventana anterior
     */
    @FXML
    public void regresar(){
        try {
            Stage actual = (Stage) regresa.getScene().getWindow();
            actual.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioHotel.fxml"));            
            App.hotel=null;
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Sistema Hoteles");
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());        
        }
    }
    /** 
     este metodo deshabilita poder seguir llenando la informacion cuando un dato escrito no es el adecuado
     **/
    private void deshabilitarTextField(){
        this.txtCiudad.setEditable(false);
        this.txtDireccion.setEditable(false);
        this.txtTelefono.setEditable(false);
        this.btnCrearHotel.setDisable(true);
    }
    
    
}
