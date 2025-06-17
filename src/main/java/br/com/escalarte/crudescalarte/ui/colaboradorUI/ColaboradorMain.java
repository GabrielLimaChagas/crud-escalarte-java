package br.com.escalarte.crudescalarte.ui.colaboradorUI;

import br.com.escalarte.crudescalarte.dao.ColaboradorDAO;
import br.com.escalarte.crudescalarte.dao.TurnoDAO;
import br.com.escalarte.crudescalarte.model.Colaborador;
import br.com.escalarte.crudescalarte.model.Turno;
import br.com.escalarte.crudescalarte.ui.colaboradorUI.ColaboradorCadastro;
import br.com.escalarte.crudescalarte.ui.turnoUI.TurnoEdit;
import br.com.escalarte.crudescalarte.util.AlertUtils;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class ColaboradorMain {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Colaboradores");

        TableView<Colaborador> table = new TableView<>();

        table.setEditable(false);

        VBox vbox = new VBox(1);
        HBox hbox = new HBox(1);
        HBox excluirBox = new HBox(1);

        vbox.setSpacing(15);

        TextField idField = new TextField();
        idField.setPromptText("Selecione um ID");
        idField.setMaxWidth(150);

        TableColumn<Colaborador, String> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Colaborador, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Colaborador, String> senhaCol = new TableColumn<>("Senha");
        senhaCol.setCellValueFactory(new PropertyValueFactory<>("senha"));

        TableColumn<Colaborador, String> dataNascimentoCol = new TableColumn<>("Data de Nascimento");
        dataNascimentoCol.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));

        TableColumn<Colaborador, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Colaborador, String> telefoneCol = new TableColumn<>("Telefone");
        telefoneCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        TableColumn<Colaborador, String> cpfCol = new TableColumn<>("CPF");
        cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        table.getColumns().addAll(idCol, nomeCol, senhaCol, dataNascimentoCol, emailCol, telefoneCol, cpfCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        ObjectPersistenceUtils.lerDados("colaborador.dat", ColaboradorDAO.getColaboradores());
        ObservableList<Colaborador> colaboradorList = FXCollections.observableArrayList(ColaboradorDAO.getColaboradores());
        table.setItems(colaboradorList);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idField.setText(String.valueOf(newSelection.getId()));
            }
        });


        Button cadastrar = new Button("Cadastrar");
        Button editar = new Button("Editar");
        Button excluir = new Button("Excluir");

        cadastrar.setOnAction(e -> {
            Stage colaboradorStage = new Stage();
            colaboradorStage.initModality(Modality.APPLICATION_MODAL);
            colaboradorStage.setTitle("Cadastro de Colaboradores");
            new ColaboradorCadastro(colaboradorList).start(colaboradorStage);
            colaboradorStage.showAndWait();
        });
        editar.setOnAction(_ -> {
            Colaborador colaboradorSelecionado = table.getSelectionModel().getSelectedItem();
            if (colaboradorSelecionado != null) {
                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("Editar Colaborador");
                new ColaboradorEdit(colaboradorList, colaboradorSelecionado).start(modalStage);
                modalStage.showAndWait();
            } else {
                AlertUtils.mostrarErro("Erro", "Selecione um Colaborador existente");
            }
        });
        excluir.setOnAction(_ -> {
            ColaboradorDAO.excluir(idField.getText());
            colaboradorList.setAll(ColaboradorDAO.getColaboradores());
        });

        hbox.getChildren().addAll(cadastrar, editar);
        excluirBox.getChildren().addAll(idField, excluir);
        vbox.getChildren().addAll(table, hbox, excluirBox);

        Scene scene = new Scene(vbox, 750, 450);
        primaryStage.setScene(scene);
    }
}