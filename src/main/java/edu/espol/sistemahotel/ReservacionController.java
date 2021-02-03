/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.sistemahotel;

import edu.espol.modelo.Habitacion;
import edu.espol.modelo.Validacion;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ReservacionController implements Initializable {
    
    @FXML
    private DatePicker dateInicio;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField txtDetalleH;
    @FXML
    private TextField txtCantDias;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Button reservar;
    @FXML
    private ImageView regresar;
    
    ReservacionController reservacionController;
    
    private RecepcionController recepcionController;
    private Habitacion habitacion;
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        reservacionController = this;
        validarFechaInicio();
        validarFechaFin();
        dateInicio.setEditable(false);
        dateFin.setEditable(false);
    }    
    
    /**
     * este metodo permite reservar y abre le menu de registro de cliente
     */
    @FXML
    public void reservar(){
        //click en "reservar" abre el menú de registro del cliente que va a reservar 
        try {
            clickReservar(habitacion);
            Stage actual = (Stage) this.dateFin.getScene().getWindow();
            actual.close();
            
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * ete metodo ejecuta una accion cuando se hace click en el boton reservar 
     * @param h recibe una habitacion
     */
    public void clickReservar(Habitacion h){
        if (txtCantDias.getText().trim().isEmpty()){
            Validacion.alerta("DEBE SELECCIONAR UN RANGO DE FECHAS", "Falta informacion", "Completar formulario", Alert.AlertType.WARNING);
        }else{
            
        
        LocalDate date = LocalDate.now();
        if((dateInicio.getValue().equals(date))){
            h.setEstadoH("Reservada");
        }
        else{h.setEstadoH("Disponible hasta "+dateInicio.getValue());}
        System.out.println(dateInicio.getValue()+"="+date);
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InfoClientes.fxml"));
            Parent root = loader.load();      
            InfoClientesController infoClientes = (InfoClientesController) loader.getController();
            infoClientes.recibeParametros(this.reservacionController, this.txtTotal.getText());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Registro de clientes");
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
        }
        App.serializarHoteles();
    }

    /**
     * este metedo permite regresar a la pantalla anterior 
     */
    @FXML
    public void regresar(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Recepcion.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Pantalla de recepción");
            stage.show();
            Stage myStage = (Stage) this.dateFin.getScene().getWindow();
            myStage.close();
            
            
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
    }
    /** 
     este metodo permite validar que la fecha final escogida sea correcta
     **/
    private void validarFechaFin(){
        Callback<DatePicker, DateCell> dayCellFactory = date -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {

                super.updateItem(item, empty);
                
                this.setDisable(false);
                this.setBackground(null);
                this.setTextFill(Color.BLACK);

                // deshabilitar las fechas antiguas
                if (item.isBefore(dateInicio.getValue())) {
                    this.setDisable(true);
                }
                
                // fines de semana en color verde
                DayOfWeek dayweek = item.getDayOfWeek();
                if (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY) {
                     this.setTextFill(Color.GREEN);
                }
            }
        };
        dateFin.setDisable(true);
        dateFin.setDayCellFactory(dayCellFactory);

    }
    /** 
     este metodo permite validar que la fecha inical escogida sea correcta
     **/
    private void validarFechaInicio(){
        Callback<DatePicker, DateCell> dayNowCellFactory = date -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {

                super.updateItem(item, empty);
                
                this.setDisable(false);
                this.setBackground(null);
                this.setTextFill(Color.BLACK);

                // deshabilitar las fechas futuras
                if (item.isBefore(LocalDate.now())) {
                    this.setDisable(true);
                }
                try{
                    
                if(item.isAfter(dateFin.getValue())){
                    this.setDisable(true);
                }    
                }catch(Exception ex){ }
                

                // fines de semana en color verde
                DayOfWeek dayweek = item.getDayOfWeek();
                if (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY) {
                    this.setTextFill(Color.GREEN);
                }
            }
        };
       
       dateInicio.setDayCellFactory(dayNowCellFactory);
       dateInicio.setOnAction(e->{
           this.calcularDias();
           dateFin.setDisable(false);});
    }
    /**
     * este metodo llena los campos del registro de la aplicacion
     */
    private void llenarCampos(){
        this.txtDetalleH.setText("N°: "+habitacion.getNumeroHabitacion()+" || Tipo: "+habitacion.getTipoH());
        this.txtPrecio.setText("$ "+String.valueOf(habitacion.getPrecioHabitacion()));
    }

    /**
     * este metodo recibe parametros para luego usarlos mas adelante en el codigo
     * @param rc recibe un controlador
     * @param hab recibe una habitacion
     */
    @FXML
    public void recibeParametros(RecepcionController rc, Habitacion hab) {
        this.habitacion = hab;
        this.recepcionController = rc;
        llenarCampos();
        
    }
    /**
     * este metodo permite calcular los diass que se va a quedar el cliente en el hotel usando las fechas
     */
    @FXML
    private void calcularDias(){
        if (dateFin.getValue()!=null){
            int dias = calcularDias(dateInicio.getValue(), dateFin.getValue());
            this.txtCantDias.setText(String.valueOf(dias));
            calcularTotal();
        }
    }
    /**
     * este metodo permite calcular los diass que se va a quedar el cliente en el hotel usando las fechas
     */
    private int calcularDias(LocalDate fecha1, LocalDate fecha2){
        SimpleDateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        //fecha inicio
        Calendar fechaInicio = new GregorianCalendar();
        fechaInicio.set(fecha1.getYear(), fecha1.getMonthValue(),fecha1.getDayOfMonth());
 
        //fecha fin
        Calendar fechaFin = new GregorianCalendar();
        fechaFin.set( fecha2.getYear(),fecha2.getMonthValue(),fecha2.getDayOfMonth());
        
        if(fechaFin.equals(fechaInicio)){
            return 1;
        }
        c.setTimeInMillis(fechaFin.getTime().getTime() - fechaInicio.getTime().getTime());
        return c.get(Calendar.DAY_OF_YEAR);
        
    }
    /**
     * este metodo permite calcular el total de una operacion 
     */
    @FXML
    private void calcularTotal(){
        int dias = Integer.parseInt(this.txtCantDias.getText().trim());
        double tot = dias*habitacion.getPrecioHabitacion();
             
        this.txtTotal.setText("$ "+String.format("%.2f", tot));
    }
    
    
}
