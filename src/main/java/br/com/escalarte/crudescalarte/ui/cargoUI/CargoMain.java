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

        // Layout
        VBox vbox = new VBox(8);
        vbox.setSpacing(16);

        HBox hbox = new HBox(8);

        // Tabela
        TableView<Cargo> table = new TableView<>();
        table.setEditable(false);

        TableColumn<Cargo, String> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cargo, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Cargo, String> cargaHorariaCol = new TableColumn<>("Carga Horária");
        cargaHorariaCol.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));

        TableColumn<Cargo, String> interjornadaCol = new TableColumn<>("Interjornada");
        interjornadaCol.setCellValueFactory(new PropertyValueFactory<>("interjornada"));

        table.getColumns().addAll(idCol, nomeCol, cargaHorariaCol, interjornadaCol);

        // Botões
        Button cadastrar = new Button("Cadastrar");
        cadastrar.setOnAction(e -> new CargoCadastro().start(new Stage()));

        Button editar = new Button("Editar");
        // editar.setOnAction(e -> new CargoEdit().start(new Stage()));

        Button excluir = new Button("Excluir");
        excluir.setOnAction(e -> CargoDAO.excluir(table.getSelectionModel().getSelectedItem()));

        Button atualizar = new Button("Atualizar");
        atualizar.setOnAction(e -> CargoDAO.atualizar(table));

        vbox.getChildren().addAll(table, excluir, hbox);
        hbox.getChildren().addAll(cadastrar, editar, atualizar);

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
