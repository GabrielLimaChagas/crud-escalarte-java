package br.com.escalarte.crudescalarte.ui.contratoUI;

import br.com.escalarte.crudescalarte.dao.ContratoDAO;
import br.com.escalarte.crudescalarte.model.Contrato;
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

public class ContratoCadastro {
    private ObservableList<Contrato> contratosList;

    public ContratoCadastro(ObservableList<Contrato> contratosList) {
        this.contratosList = contratosList;
    }

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

        Label diasFolgaSemanalLabel = new Label("Dias de Folga:");
        List<CheckBox> checkBoxes = new ArrayList<>();
        List<String> diasSemana = Arrays.asList("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
        FlowPane diasFolgaBox = new FlowPane(10, 5);
        for (String dia : diasSemana) {
            CheckBox cb = new CheckBox(dia);
            checkBoxes.add(cb);
            diasFolgaBox.getChildren().add(cb);
        }

        Button cadastrar = new Button("Cadastrar");
        Button limpar = new Button("Limpar");

        cadastrar.setOnAction(_ -> {
                List<String> diasFolgaSelecionados = new ArrayList<>();
                for (CheckBox cb : checkBoxes) {
                    if (cb.isSelected()) {
                        diasFolgaSelecionados.add(cb.getText());
                    }
                }

                boolean sucesso = ContratoDAO.cadastrar(
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

            if (sucesso) {
                contratosList.setAll(ContratoDAO.getContratos());
                primaryStage.close();
            }
        });


        limpar.setOnAction(_ -> {
            ContratoDAO.limpar(
                    statusField,colaboradorField, cargaDiariaField, cargoField, dataInicioField, dataFimField, diasSemanaisField
            );
            idField.setText(String.valueOf(ContratoDAO.gerarNovoId()));
            for (CheckBox cb : checkBoxes) {
                cb.setSelected(false);
            }
        });

        hbox.getChildren().addAll(cadastrar, limpar);
        vbox.getChildren().addAll(titulo, idField,colaboradorField, statusField, cargaDiariaField, cargoField,
                dataInicioField, dataFimField, diasSemanaisField, diasFolgaSemanalLabel, diasFolgaBox, hbox);

        primaryStage.setScene(new Scene(vbox, 600, 500));
    }
}
