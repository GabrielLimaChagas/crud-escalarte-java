package br.com.escalarte.crudescalarte.ui.colaboradorUI;

import br.com.escalarte.crudescalarte.dao.ColaboradorDAO;
import br.com.escalarte.crudescalarte.dao.TurnoDAO;
import br.com.escalarte.crudescalarte.model.Colaborador;
import br.com.escalarte.crudescalarte.ui.turnoUI.ColaboradorCadastro;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
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

        table.getColumns().addAll(idCol, nomeCol, senhaCol, dataNascimentoCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        ObjectPersistenceUtils.lerDados("colaborador.dat", ColaboradorDAO.getColaboradores());
        table.getItems().addAll(ColaboradorDAO.getColaboradores());

        Button cadastrar = new Button("Cadastrar");
        Button editar = new Button("Editar");
        Button excluir = new Button("Excluir");
        Button atualizar = new Button("Atualizar");

        cadastrar.setOnAction(_ -> new ColaboradorCadastro().start(new Stage()));
        editar.setOnAction(_ -> new ColaboradorEdit().start(new Stage()));
        excluir.setOnAction(_ -> ColaboradorDAO.excluir(idField.getText()));
        atualizar.setOnAction(_ -> ColaboradorDAO.atualizar(table));

        hbox.getChildren().addAll(cadastrar, editar, atualizar);
        excluirBox.getChildren().addAll(idField, excluir);
        vbox.getChildren().addAll(table, hbox, excluirBox);

        Scene scene = new Scene(vbox, 750, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}