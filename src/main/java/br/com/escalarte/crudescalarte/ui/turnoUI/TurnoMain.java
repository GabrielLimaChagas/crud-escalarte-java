package br.com.escalarte.crudescalarte.ui.turnoUI;

import br.com.escalarte.crudescalarte.dao.TurnoDAO;
import br.com.escalarte.crudescalarte.model.Turno;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;



public class TurnoMain {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Turnos");

        TableView<Turno> table = new TableView<>();

        table.setEditable(false);

        VBox vbox = new VBox(1);
        HBox hbox = new HBox(1);
        HBox excluirBox = new HBox(1);

        vbox.setSpacing(15);

        TextField idField = new TextField();
        idField.setPromptText("Selecione um ID");
        idField.setMaxWidth(150);

        TableColumn<Turno, String> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Turno, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Turno, String> horarioInicioCol = new TableColumn<>("Horário Início");
        horarioInicioCol.setCellValueFactory(new PropertyValueFactory<>("horarioInicio"));

        TableColumn<Turno, String> horarioFimCol = new TableColumn<>("Horário Fim");
        horarioFimCol.setCellValueFactory(new PropertyValueFactory<>("horarioFim"));

        table.getColumns().addAll(idCol, nomeCol, horarioInicioCol, horarioFimCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        ObjectPersistenceUtils.lerDados("turnos.dat", TurnoDAO.getTurnos());
        table.getItems().addAll(TurnoDAO.getTurnos());

        Button cadastrar = new Button("Cadastrar");
        Button editar = new Button("Editar");
        Button excluir = new Button("Excluir");
        Button atualizar = new Button("Atualizar");

        cadastrar.setOnAction(_ -> new TurnoCadastro().start(new Stage()));
        editar.setOnAction(_ -> new TurnoEdit().start(new Stage()));
        excluir.setOnAction(_ -> TurnoDAO.excluir(idField.getText()));
        atualizar.setOnAction(_ -> TurnoDAO.atualizar(table));

        hbox.getChildren().addAll(cadastrar, editar, atualizar);
        excluirBox.getChildren().addAll(idField, excluir);
        vbox.getChildren().addAll(table, hbox, excluirBox);

        Scene scene = new Scene(vbox, 750, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}