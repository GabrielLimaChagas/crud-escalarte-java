package br.com.escalarte.crudescalarte.ui.contratoUI;

import br.com.escalarte.crudescalarte.dao.ContratoDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContratoCadastro {
    public void start(Stage primaryStage) {
        // Inicializa a janela de cadastro de contrato
        primaryStage.setTitle("Cadastro de Contratos");

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);

        vbox.setAlignment(Pos.TOP_CENTER);
        hbox.setAlignment(Pos.CENTER);

        Label titulo = new Label("Cadastro de Contrato");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        TextField idField = new TextField(String.valueOf(ContratoDAO.gerarNovoId()));
        idField.setEditable(false);

        TextField colaboradorField = new TextField();
        colaboradorField.setPromptText("Nome do Colaborador");

        TextField statusField = new TextField();
        statusField.setPromptText("Status (Ativo/Inativo)");

        TextField cargaDiariaField = new TextField();
        cargaDiariaField.setPromptText("Carga Horária Diária");

        TextField cargoField = new TextField();
        cargoField.setPromptText("Cargo");

        TextField dataInicioField = new TextField();
        dataInicioField.setPromptText("Data Início (AAAA-MM-DD)");

        TextField dataFimField = new TextField();
        dataFimField.setPromptText("Data Fim (AAAA-MM-DD)");

        TextField diasSemanaisField = new TextField();
        diasSemanaisField.setPromptText("Dias Trabalho Semana");

        TextField diasMensaisField = new TextField();
        diasMensaisField.setPromptText("Dias Trabalho Mês");

        Button cadastrar = new Button("Cadastrar");
        Button limpar = new Button("Limpar");

        cadastrar.setOnAction(_ -> ContratoDAO.cadastrar(
                idField.getText(),
                statusField.getText(),
                cargaDiariaField.getText(),
                cargoField.getText(),
                colaboradorField.getText(),
                dataInicioField.getText(),
                dataFimField.getText(),
                diasSemanaisField.getText(),
                diasMensaisField.getText()
        ));

        limpar.setOnAction(_ -> {
            ContratoDAO.limpar(
                    statusField,colaboradorField, cargaDiariaField, cargoField, dataInicioField, dataFimField, diasSemanaisField, diasMensaisField
            );
            idField.setText(String.valueOf(ContratoDAO.gerarNovoId()));
        });

        hbox.getChildren().addAll(cadastrar, limpar);
        vbox.getChildren().addAll(titulo, idField,colaboradorField, statusField, cargaDiariaField, cargoField,
                dataInicioField, dataFimField, diasSemanaisField, diasMensaisField, hbox);

        primaryStage.setScene(new Scene(vbox, 600, 500));
        primaryStage.show();
    }
}
