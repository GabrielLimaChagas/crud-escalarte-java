package br.com.escalarte.crudescalarte.ui.colaboradorUI;

import br.com.escalarte.crudescalarte.dao.ColaboradorDAO;
import br.com.escalarte.crudescalarte.dao.TurnoDAO;
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

public class ColaboradorEdit {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Edit de Colaboradores");

        VBox vbox = new VBox(1);
        HBox hbox = new HBox(1);

        vbox.setAlignment(Pos.TOP_CENTER);
        hbox.setAlignment(Pos.BASELINE_CENTER);

        Label titulo = new Label("Editar");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label idLabel = new Label("Id:");
        TextField idField = new TextField();
        idField.setPromptText("ID");
        idField.setMaxWidth(150);

        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome ");
        nomeField.setMaxWidth(150);

        Label senhaLabel = new Label("Senha:");
        TextField senhaField = new TextField();
        senhaField.setPromptText("senha");
        senhaField.setMaxWidth(150);

        Label dataNascimentoLabel = new Label("Data de Nascimento:");
        TextField dataNascimentoField = new TextField();
        dataNascimentoField.setPromptText("dataNascimento");
        dataNascimentoField.setMaxWidth(150);

        Button editar = new Button("Editar");
        Button limpar = new Button("Limpar");

        editar.setOnAction(_ ->

                ColaboradorDAO.editar(
                        idField.getText(),
                        nomeField.getText(),
                        senhaField.getText(),
                        dataNascimentoField.getText())
        );

        limpar.setOnAction(_ ->
                ColaboradorDAO.limpar(idField, nomeField, senhaField, dataNascimentoField)
        );

        hbox.getChildren().addAll(editar, limpar);
        vbox.getChildren().addAll(titulo, idLabel, idField, nomeLabel, nomeField, senhaLabel, senhaField, dataNascimentoLabel, dataNascimentoField, hbox);

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
