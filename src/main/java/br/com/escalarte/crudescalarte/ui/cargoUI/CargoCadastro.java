package br.com.escalarte.crudescalarte.ui.cargoUI;

import br.com.escalarte.crudescalarte.dao.CargoDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CargoCadastro {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Cargos");

        VBox vbox = new VBox(8);
        HBox hbox = new HBox(8);

        vbox.setAlignment(Pos.TOP_CENTER);
        hbox.setAlignment(Pos.BASELINE_CENTER);

        Label titulo = new Label("Cadastro");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label idLabel = new Label("Id:");
        TextField idField = new TextField();
        idField.setPromptText("ID");
        idField.setMaxWidth(150);

        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome ");
        nomeField.setMaxWidth(150);

        Label cargaHorariaLabel = new Label("Carga Horária:");
        TextField cargaHorariaField = new TextField();
        cargaHorariaField.setPromptText("");
        cargaHorariaField.setMaxWidth(150);

        Label interjornadaLabel = new Label("Interjornada:");
        TextField interjornadaField = new TextField();
        interjornadaField.setPromptText("");
        interjornadaField.setMaxWidth(150);

        Button cadastrar = new Button("Cadastrar");
        Button limpar = new Button("Limpar");

        cadastrar.setOnAction(_ -> CargoDAO.cadastrar(
                nomeField.getText(),
                cargaHorariaField.getText(),
                interjornadaField.getText()));

        limpar.setOnAction(_ -> CargoDAO.limpar(idField, nomeField, cargaHorariaField, interjornadaField));

        hbox.getChildren().addAll(cadastrar, limpar);
        vbox.getChildren().addAll(titulo, idLabel, idField, nomeLabel, nomeField, cargaHorariaLabel,
                cargaHorariaField, interjornadaLabel, interjornadaField, hbox);

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
