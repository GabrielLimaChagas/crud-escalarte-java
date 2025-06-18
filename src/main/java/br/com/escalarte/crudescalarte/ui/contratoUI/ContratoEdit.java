package br.com.escalarte.crudescalarte.ui.contratoUI;

import br.com.escalarte.crudescalarte.dao.ContratoDAO;
import br.com.escalarte.crudescalarte.model.Contrato;
import br.com.escalarte.crudescalarte.model.Turno;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContratoEdit {
    private ObservableList<Contrato> contratosList;

    public ContratoEdit(ObservableList<Contrato> contratosList) {
        this.contratosList = contratosList;
    }

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

        Label folgaLabel = new Label("Dias de Folga:");
        List<String> diasSemana = Arrays.asList("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
        List<CheckBox> checkBoxes = new ArrayList<>();
        FlowPane diasFolgaPane = new FlowPane(10, 5);
        diasFolgaPane.setPrefWrapLength(300);
        for (String dia : diasSemana) {
            CheckBox cb = new CheckBox(dia);
            checkBoxes.add(cb);
            diasFolgaPane.getChildren().add(cb);
        }

        if (contratoSelecionado != null) {
            idField.setText(String.valueOf(contratoSelecionado.getId()));
            colaboradorField.setText(contratoSelecionado.getColaborador());
            statusField.setText(contratoSelecionado.getStatus());
            cargaDiariaField.setText(String.valueOf(contratoSelecionado.getCargaHorariaDiaria()));
            cargoField.setText(contratoSelecionado.getCargo());
            dataInicioField.setText(contratoSelecionado.getDataInicio().toString());
            dataFimField.setText(contratoSelecionado.getDataFim().toString());
            diasSemanaisField.setText(String.valueOf(contratoSelecionado.getDiasTrabalhoSemanal()));

            if (contratoSelecionado.getDiasFolgaSemanal() != null) {
                for (CheckBox cb : checkBoxes) {
                    if (contratoSelecionado.getDiasFolgaSemanal().contains(cb.getText())) {
                        cb.setSelected(true);
                    }
                }
            }
        }

        Button editar = new Button("Editar");

        editar.setOnAction(_ -> {
            List<String> diasFolgaSelecionados = new ArrayList<>();
            for (CheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    diasFolgaSelecionados.add(cb.getText());
                }
            }
            ContratoDAO.editar(
                    idField.getText(),
                    statusField.getText(),
                    cargaDiariaField.getText(),
                    cargoField.getText(),
                    colaboradorField.getText(),
                    dataInicioField.getText(),
                    dataFimField.getText(),
                    diasSemanaisField.getText(),
                    diasFolgaSelecionados
            );
            contratosList.setAll(ContratoDAO.getContratos());
            primaryStage.close();
        });

        hbox.getChildren().addAll(editar);
        vbox.getChildren().addAll(titulo, idField,colaboradorField, statusField, cargaDiariaField, cargoField,
                dataInicioField, dataFimField, diasSemanaisField, folgaLabel, diasFolgaPane, hbox);

        primaryStage.setScene(new Scene(vbox, 600, 500));
        primaryStage.show();
    }
}
