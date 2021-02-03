/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.sistemahotel;

import edu.espol.modelo.Habitacion;
import edu.espol.modelo.Hotel;
import edu.espol.modelo.TipoHabitacion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class RecepcionController implements Initializable {
    
    @FXML
    private FlowPane panHabitaciones;
    @FXML
    private Label regresar;
    @FXML
    private VBox filtros;
    
    private ArrayList<TipoHabitacion> tiposH_elegidas = new ArrayList<>();
    RecepcionController recepcionController;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        App.leerHoteles();
        crearFiltros();
        llenarHabitaciones();
        recepcionController = this;
        
    }    
    
    /**
     * este metedo permite almacenar las habitacion que se ingresen y mostrarlas
     */
    public void llenarHabitaciones(){
        panHabitaciones.setHgap(25);
        panHabitaciones.setVgap(25);
        panHabitaciones.getChildren().clear();
        Collections.sort(App.hotel.getHabitaciones(), (Habitacion p1, Habitacion p2) -> 
                new Integer(p1.getNumeroHabitacion()).compareTo(new Integer(p2.getNumeroHabitacion())));     
        for(Habitacion h: App.hotel.getHabitaciones()){
            Image image = new Image("https://previews.123rf.com/images/valentint/valentint1606/valentint160600824/57837035-icono-de-la-cama-doble-bot%C3%B3n-de-internet-sobre-fondo-verde-.jpg");
            ImageView imgHabitacion = new ImageView(image);
            imgHabitacion.setFitWidth(100);
            imgHabitacion.setFitHeight(100);
            VBox vPanelH = new VBox();
            Label lblHabitacion= new Label("Habitación "+h.getNumeroHabitacion()+"\n"+h.getTipoH().toString()+"\n"+h.getEstadoH());
            if(!(h.getEstadoH().equals("Reservada") || h.getEstadoH().equals("Ocupada"))){
                imgHabitacion.setImage(new Image("https://previews.123rf.com/images/valentint/valentint1606/valentint160600824/57837035-icono-de-la-cama-doble-bot%C3%B3n-de-internet-sobre-fondo-verde-.jpg"));
                vPanelH.setOnMouseClicked(e->reservar(h));
            }
            else if(h.getEstadoH().equals("Ocupada")){
                imgHabitacion.setImage(new Image("https://us.123rf.com/450wm/valentint/valentint1606/valentint160602210/57796620-icono-de-la-cama-doble-bot%C3%B3n-de-internet-sobre-fondo-rojo-.jpg?ver=6"));
                vPanelH.setOnMouseClicked(e->checkOut(h));
                
            }
            else if(h.getEstadoH().equals("Reservada")){
                imgHabitacion.setImage(new Image("https://previews.123rf.com/images/valentint/valentint1606/valentint160600824/57837035-icono-de-la-cama-doble-bot%C3%B3n-de-internet-sobre-fondo-verde-.jpg"));
                vPanelH.setOnMouseClicked(e->checkIn(h));
            }
            vPanelH.getChildren().addAll(imgHabitacion, lblHabitacion);
            panHabitaciones.getChildren().add(vPanelH);
 
        }
    }
    /*
    este metodo creo filtros para que se pueda encontrar la informacion que busca
    */
    private void crearFiltros(){
        for(TipoHabitacion tp: TipoHabitacion.tiposH){
            CheckBox ch = new CheckBox(tp.toString());
            ch.setOnAction(e->{
                validarFiltros(ch,tp);
                if(this.tiposH_elegidas.isEmpty())    
                    llenarHabitaciones();                    
                else
                    llenarHabitacionesFiltradas();
                });
            ch.setTextFill(Color.WHITE);
            this.filtros.getChildren().add(ch);
        }
    }
    /** 
     este metodo permite validar los filtros para que no genere error el codigo
     **/
    private void validarFiltros(CheckBox c, TipoHabitacion tp){
        panHabitaciones.setHgap(25);
        panHabitaciones.setVgap(25);
        panHabitaciones.getChildren().clear();
        Collections.sort(App.hotel.getHabitaciones(), (Habitacion p1, Habitacion p2) -> 
                new Integer(p1.getNumeroHabitacion()).compareTo(new Integer(p2.getNumeroHabitacion())));     
        if (c.isSelected()){
            tiposH_elegidas.add(tp);
        }else{
            tiposH_elegidas.remove(tp);
        }
    }
    /** 
     este metodo permite llenar la informacion de las habitaciones mediante filtros
     **/
    private void llenarHabitacionesFiltradas(){
        for(Habitacion h: App.hotel.getHabitaciones()){
            Image image = new Image("https://previews.123rf.com/images/valentint/valentint1606/valentint160600824/57837035-icono-de-la-cama-doble-bot%C3%B3n-de-internet-sobre-fondo-verde-.jpg");
            ImageView imgHabitacion = new ImageView(image);
            imgHabitacion.setFitWidth(100);
            imgHabitacion.setFitHeight(100);
            VBox vPanelH = new VBox();
            Label lblHabitacion= new Label("Habitación "+h.getNumeroHabitacion()+"\n"+h.getTipoH().toString()+"\n"+h.getEstadoH());
            if(this.tiposH_elegidas.contains(h.getTipoH()) && !h.getEstadoH().equals("Ocupada")){
                if(!(h.getEstadoH().equals("Reservada") || h.getEstadoH().equals("Ocupada"))){
                imgHabitacion.setImage(new Image("https://previews.123rf.com/images/valentint/valentint1606/valentint160600824/57837035-icono-de-la-cama-doble-bot%C3%B3n-de-internet-sobre-fondo-verde-.jpg"));
                vPanelH.setOnMouseClicked(e->reservar(h));
            }
            else if(h.getEstadoH().equals("Ocupada")){
                imgHabitacion.setImage(new Image("https://us.123rf.com/450wm/valentint/valentint1606/valentint160602210/57796620-icono-de-la-cama-doble-bot%C3%B3n-de-internet-sobre-fondo-rojo-.jpg?ver=6"));
                vPanelH.setOnMouseClicked(e->checkOut(h));
                
            }
            else if(h.getEstadoH().equals("Reservada")){
                imgHabitacion.setImage(new Image("https://previews.123rf.com/images/valentint/valentint1606/valentint160600824/57837035-icono-de-la-cama-doble-bot%C3%B3n-de-internet-sobre-fondo-verde-.jpg"));
                vPanelH.setOnMouseClicked(e->checkIn(h));
            }
            vPanelH.getChildren().addAll(imgHabitacion, lblHabitacion);
            panHabitaciones.getChildren().add(vPanelH);
            }
            
 
        }
    }

    /**
     * este metodo reserva una habitacion selecionada por pantalla
     * @param h recibe una habitacion
     */
    public void reservar(Habitacion h){
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reservacion.fxml"));
            Parent root = loader.load();
            ReservacionController controlador = (ReservacionController) loader.getController();
            controlador.recibeParametros(recepcionController,h);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Reservaciones");
            stage.setResizable(false);
            stage.show();
            Stage myStage = (Stage) this.panHabitaciones.getScene().getWindow();
            myStage.close();
                    
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
    
    }
    
    /**
     * este metodo permite hacer checkIn a las habitaciones que esten reservadas
     * @param h recibe una habitacion
     */
    public void checkIn(Habitacion h){
        h.setEstadoH("Ocupada");
        App.serializarHoteles();
        llenarHabitaciones();
    
    }
    
    /**
     * este metodo permite hacer checkOut a las habitaciones que esten ocupadas
     * @param h recibe una habitacion
     */
    public void checkOut(Habitacion h){
        h.setEstadoH("Disponible");
        App.serializarHoteles();
        llenarHabitaciones();
    
    }
    
    /**
     * este metedo permite regresar a la pantalla anterior 
     */
    @FXML
    public void regresar(){
        try {
            Stage actual = (Stage) regresar.getScene().getWindow();
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
    
    
}
