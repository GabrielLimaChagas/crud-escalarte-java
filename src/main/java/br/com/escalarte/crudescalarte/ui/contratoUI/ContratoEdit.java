package br.com.escalarte.crudescalarte.ui.contratoUI;

import br.com.escalarte.crudescalarte.dao.ContratoDAO;
import br.com.escalarte.crudescalarte.model.Contrato;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContratoEdit {
    public void start(Stage primaryStage, Contrato contratoSelecionado) {
        primaryStage.setTitle("Editar Contrato");

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);

        vbox.setAlignment(Pos.TOP_CENTER);
        hbox.setAlignment(Pos.CENTER);

        Label titulo = new Label("Editar Contrato");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        TextField idField = new TextField();
        idField.setEditable(false);
        idField.setPromptText("ID");

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

        if (contratoSelecionado != null) {
            idField.setText(String.valueOf(contratoSelecionado.getId()));
            colaboradorField.setText(contratoSelecionado.getColaborador());
            statusField.setText(contratoSelecionado.getStatus());
            cargaDiariaField.setText(String.valueOf(contratoSelecionado.getCargaHorariaDiaria()));
            cargoField.setText(contratoSelecionado.getCargo());
            dataInicioField.setText(contratoSelecionado.getDataInicio().toString());
            dataFimField.setText(contratoSelecionado.getDataFim().toString());
            diasSemanaisField.setText(String.valueOf(contratoSelecionado.getDiasTrabalhoSemanal()));
            diasMensaisField.setText(String.valueOf(contratoSelecionado.getDiasTrabalhoMensal()));
        }

        Button editar = new Button("Editar");

        editar.setOnAction(_ -> { ContratoDAO.editar(
                    idField.getText(),
                    statusField.getText(),
                    cargaDiariaField.getText(),
                    cargoField.getText(),
                    colaboradorField.getText(),
                    dataInicioField.getText(),
                    dataFimField.getText(),
                    diasSemanaisField.getText(),
                    diasMensaisField.getText()
            );
            primaryStage.close();
        });

        hbox.getChildren().addAll(editar);
        vbox.getChildren().addAll(titulo, idField,colaboradorField, statusField, cargaDiariaField, cargoField,
                dataInicioField, dataFimField, diasSemanaisField, diasMensaisField, hbox);

        primaryStage.setScene(new Scene(vbox, 600, 500));
        primaryStage.show();
    }
}
