/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.sistemahotel;
import edu.espol.modelo.Cliente;
import edu.espol.modelo.FormaPago;
import edu.espol.modelo.Habitacion;
import edu.espol.modelo.Validacion;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class InfoClientesController implements Initializable {
    
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtOrigen;
    @FXML
    private TextField txtCedula;
    @FXML
    private ComboBox cboFormaPago;
    @FXML
    private Button confirmar;
    
    private ReservacionController reservaController;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        validarTextF();
        cboFormaPago.getItems().addAll(FormaPago.formasPago);
    }    
    /** 
     este metodo valida que la informacion ingresada por teclado sea correcta
     **/
    private void validarTextF(){
        Validacion.txt_isLetter(this.txtNombres, "Nombres y apellidos no pueden contener numeros");
        Validacion.txt_isDigit(this.txtCedula, 10, "La cedula debe tener 10 digitos numericos");
        Validacion.txt_isLetter(txtOrigen, "El país de origen no puede contener numeros");
        
        
    }
    
    /**
     * este metodo registra las habitaciones con la informacion que se pide por pantalla
     */
    public void registrar(){
        if (this.cboFormaPago.getValue()==null){
            Validacion.alerta("Debe elegir una forma de pago", "Complete los datos", "Error", Alert.AlertType.WARNING);
        }else{
            Cliente cliente = new Cliente(txtNombres.getText(),txtCedula.getText(),  txtOrigen.getText(), (FormaPago) cboFormaPago.getValue());
            Cliente.clientes.add(cliente);
            System.out.println(Cliente.clientes);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Cliente registrado");
            alert.setTitle("Información: ");
            alert.setHeaderText("INFORMACION");
            Optional<ButtonType> action = alert.showAndWait();
            //action.get() == ButtonType.OK
            if(action.get()==ButtonType.OK)
                regresar();
            
        }
    }
    /** 
     este metodo permite regresar a la pantalla anterior
     **/
    private void regresar(){
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Recepcion.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Pantalla de recepción");
            stage.show();
            Stage myStage = (Stage) this.cboFormaPago.getScene().getWindow();
            myStage.close();
             
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());        
        }
    }
    
    /**
     * este metodo recibe parametros para usarlo en otros metodos
     * @param ih recibe un controlador
     * @param total recibe un String con a informacion total
     */
    public void recibeParametros(ReservacionController ih, String total) {
        this.reservaController = ih;
        this.txtTotal.setText(total);
        
    }
    

}
