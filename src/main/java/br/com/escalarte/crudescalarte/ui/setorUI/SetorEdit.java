package br.com.escalarte.crudescalarte.ui.setorUI;

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
import br.com.escalarte.crudescalarte.dao.SetorDAO;

public class SetorEdit {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Editar Setor");

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

        Label nomeSetorLabel = new Label("Nome do Setor:");
        TextField nomeSetorField = new TextField();
        nomeSetorField.setPromptText("Nome do Setor ");
        nomeSetorField.setMaxWidth(150);

        Label nomeGerenteLabel = new Label("Nome do Gerente:");
        TextField nomeGerenteField = new TextField();
        nomeGerenteField.setPromptText("Nome do Gerente ");
        nomeGerenteField.setMaxWidth(150);

        Label quantidadeColaboradoresLabel = new Label("Quantidade de Colaboradores");
        TextField quantidadeColaboradoresField = new TextField();
        quantidadeColaboradoresField.setPromptText("Quantidade de Colaboradores");
        quantidadeColaboradoresField.setMaxWidth(150);

        Button editar = new Button("Editar");
        Button limpar = new Button("Limpar");

        editar.setOnAction(_ -> {
            try {
                SetorDAO.editar(
                        idField.getText(),
                        Integer.parseInt(quantidadeColaboradoresField.getText()),
                        nomeSetorField.getText(),
                        nomeGerenteField.getText()
                );
            } catch (NumberFormatException e) {
                System.out.println("Erro: a quantidade de colaboradores deve ser um número inteiro válido.");
            }
        });

        limpar.setOnAction(_ ->
                SetorDAO.limpar(idField,quantidadeColaboradoresField, nomeSetorField,nomeGerenteField)
        );

        hbox.getChildren().addAll(editar, limpar);
        vbox.getChildren().addAll(titulo, idLabel, idField, nomeSetorLabel, nomeSetorField, nomeGerenteLabel, nomeGerenteField, quantidadeColaboradoresLabel, quantidadeColaboradoresField, hbox);

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
