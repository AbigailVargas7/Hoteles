package edu.espol.sistemahotel;

import edu.espol.modelo.Hotel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {
    
    /**
     *
     */
    public static ArrayList<Hotel> hoteles = new ArrayList<>();

    /**
     *
     */
    public static Hotel hotel = null;
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("InicioHotel"), 640, 480);
        stage.setResizable(false);
        stage.setTitle("Sistema de hotel");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        leerHoteles();
        launch();
        
        
        
    }
    
    /**
     *
     */
    public static void leerHoteles(){
        try(ObjectInputStream obj = new ObjectInputStream(new FileInputStream("hoteles.dat"))){
            hoteles = (ArrayList<Hotel>) obj.readObject();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getCause());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getCause());
        }
    }
    
    /**
     *
     */
    public static void serializarHoteles(){
        try(ObjectOutputStream hot = new ObjectOutputStream(new FileOutputStream("hoteles.dat"))){
            hot.writeObject(hoteles);
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getCause());
        } catch (IOException ex) {
            System.err.println(ex.getCause());
        }
    }
    
    
}