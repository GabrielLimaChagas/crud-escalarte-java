package br.com.escalarte.crudescalarte.ui;

import br.com.escalarte.crudescalarte.dao.TurnoDAO;
import br.com.escalarte.crudescalarte.model.Turno;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;



public class TurnoUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Turnos");

        TableView<Turno> table = new TableView<>();

        table.setEditable(false);

        VBox vbox = new VBox(1);
        HBox hbox = new HBox(1);

        TextField idField = new TextField();
        idField.setPromptText("ID");
        idField.setMaxWidth(150);

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome ");
        nomeField.setMaxWidth(150);

        TextField horarioInicioField = new TextField();
        horarioInicioField.setPromptText("Horário Início (HH/mm)");
        horarioInicioField.setMaxWidth(150);

        TextField horarioFimField = new TextField();
        horarioFimField.setPromptText("Horário Fim (HH/mm)");
        horarioFimField.setMaxWidth(150);

        TableColumn<Turno, String> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Turno, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Turno, String> horarioInicioCol = new TableColumn<>("Horário Início");
        horarioInicioCol.setCellValueFactory(new PropertyValueFactory<>("horarioInicio"));

        TableColumn<Turno, String> horarioFimCol = new TableColumn<>("Horário Fim");
        horarioFimCol.setCellValueFactory(new PropertyValueFactory<>("horarioFim"));

        table.getColumns().addAll(idCol, nomeCol, horarioInicioCol, horarioFimCol);

        ObjectPersistenceUtils.lerDados("turnos.dat", TurnoDAO.turnos);
        table.getItems().addAll(TurnoDAO.turnos);

        Button cadastrar = new Button("Cadastrar");
        Button editar = new Button("Editar");
        Button excluir = new Button("Excluir");
        Button atualizar = new Button("Atualizar");

        cadastrar.setOnAction(e -> TurnoDAO.cadastrar(idField.getText(), nomeField.getText(), horarioInicioField.getText(), horarioFimField.getText()));
        editar.setOnAction(e -> TurnoDAO.editar(idField.getText(), nomeField.getText(), horarioInicioField.getText(), horarioFimField.getText()));
        excluir.setOnAction(e -> TurnoDAO.excluir(idField.getText()));
        atualizar.setOnAction(e -> TurnoDAO.atualizar(table));

        hbox.getChildren().addAll(cadastrar, editar, excluir, atualizar);
        vbox.getChildren().addAll(table, hbox, idField, nomeField, horarioInicioField, horarioFimField);

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

