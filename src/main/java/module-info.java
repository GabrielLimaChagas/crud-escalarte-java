module br.com.escalarte.crudescalarte {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.escalarte.crudescalarte to javafx.fxml;
    exports br.com.escalarte.crudescalarte;
}