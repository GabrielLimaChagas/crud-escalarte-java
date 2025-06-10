package br.com.escalarte.crudescalarte.ui.setorUI;

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
import br.com.escalarte.crudescalarte.dao.SetorDAO;

public class SetorCadastro {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Setores");

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

        Label nomeSetorLabel = new Label("Nome do Setor:");
        TextField nomeSetorField = new TextField();
        nomeSetorField.setPromptText("Nome do Setor ");
        nomeSetorField.setMaxWidth(150);

        Label nomeGerenteLabes = new Label("Nome do Gerente:");
        TextField nomeGerenteField = new TextField();
        nomeGerenteField.setPromptText("Nome do Gerente ");
        nomeGerenteField.setMaxWidth(150);

        Label quantidadeColaboradoresLabel = new Label("Quantidade de Colaboradores:");
        TextField quantidadeColaboradoresField = new TextField();
        quantidadeColaboradoresField.setPromptText("Quantidade de Colaboradores");
        quantidadeColaboradoresField.setMaxWidth(150);

        Button cadastrar = new Button("Cadastrar");
        Button limpar = new Button("Limpar");

        cadastrar.setOnAction(_ ->
                SetorDAO.cadastrar(idField.getText(),
                        nomeSetorField.getText(),
                        nomeGerenteField.getText(),
                        quantidadeColaboradoresField.getText())
        );

        limpar.setOnAction(_ ->
                SetorDAO.limpar(idField, nomeSetorField, nomeGerenteField, quantidadeColaboradoresField)
        );


        hbox.getChildren().addAll(cadastrar, limpar);
        vbox.getChildren().addAll(titulo,
                idLabel, idField,
                nomeSetorLabel, nomeSetorField,
                nomeGerenteLabes, nomeGerenteField,
                quantidadeColaboradoresLabel, quantidadeColaboradoresField,
                hbox);

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
