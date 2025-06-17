package br.com.escalarte.crudescalarte.ui.turnoUI;

import br.com.escalarte.crudescalarte.dao.ColaboradorDAO;
import br.com.escalarte.crudescalarte.dao.TurnoDAO;
import br.com.escalarte.crudescalarte.model.Colaborador;
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

public class ColaboradorCadastro {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Colaboradores");

        VBox vbox = new VBox(1);
        HBox hbox = new HBox(1);

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

        Label senhaLabel = new Label("Senha:");
        TextField senhaField = new TextField();
        senhaField.setPromptText("senha");
        senhaField.setMaxWidth(150);

        Label dataNascimentoLabel = new Label("Data de Nascimento:");
        TextField dataNascimentoField = new TextField();
        dataNascimentoField.setPromptText("dataNascimento");
        dataNascimentoField.setMaxWidth(150);

        Button cadastrar = new Button("Cadastrar");
        Button limpar = new Button("Limpar");

        cadastrar.setOnAction(_ ->
                ColaboradorDAO.cadastrar(idField.getText(),
                        nomeField.getText(),
                        senhaField.getText(),
                        dataNascimentoField.getText())
        );

        limpar.setOnAction(_ ->
                ColaboradorDAO.limpar(idField, nomeField, senhaField, dataNascimentoField)
        );


        hbox.getChildren().addAll(cadastrar, limpar);
        vbox.getChildren().addAll(titulo, idLabel, idField, nomeLabel, nomeField, senhaLabel, senhaField, dataNascimentoLabel, dataNascimentoField, hbox);

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
