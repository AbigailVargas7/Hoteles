module edu.espol.sistemahotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens edu.espol.sistemahotel to javafx.fxml;
    exports edu.espol.sistemahotel;
}