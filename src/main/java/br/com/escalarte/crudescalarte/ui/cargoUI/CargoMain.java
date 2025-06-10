package br.com.escalarte.crudescalarte.ui.cargoUI;

import br.com.escalarte.crudescalarte.dao.CargoDAO;
import br.com.escalarte.crudescalarte.model.Cargo;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CargoMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Cargos");

        TableView<Cargo> table = new TableView<>();

        table.setEditable(false);

        VBox vbox = new VBox(8);
        HBox hbox = new HBox(8);
        HBox excluirBox = new HBox(8);

        vbox.setSpacing(16);

        TextField idField = new TextField();
        idField.setPromptText("Selecione um ID");
        idField.setMaxWidth(150);

        TableColumn<Cargo, String> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cargo, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Cargo, String> cargaHorariaCol = new TableColumn<>("Carga Horária");
        cargaHorariaCol.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));

        TableColumn<Cargo, String> interjornadaCol = new TableColumn<>("Interjornada");
        interjornadaCol.setCellValueFactory(new PropertyValueFactory<>("interjornada"));

        table.getColumns().addAll(idCol, nomeCol, cargaHorariaCol, interjornadaCol);

        ObjectPersistenceUtils.lerDados("cargos.dat");
        table.getItems().addAll();

        Button cadastrar = new Button("Cadastrar");
        Button editar = new Button("Editar");
        Button excluir = new Button("Excluir");
        Button atualizar = new Button("Atualizar");

        cadastrar.setOnAction(e -> new CargoCadastro().start(new Stage()));
        // editar.setOnAction(e -> new CargoEdit().start(new Stage()));
        excluir.setOnAction(e -> CargoDAO.excluir(idField.getText()));
        atualizar.setOnAction(e -> CargoDAO.atualizar(table));

        hbox.getChildren().addAll(cadastrar, editar, atualizar);
        excluirBox.getChildren().addAll(idField, excluir);
        vbox.getChildren().addAll(table, hbox, excluirBox);

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
