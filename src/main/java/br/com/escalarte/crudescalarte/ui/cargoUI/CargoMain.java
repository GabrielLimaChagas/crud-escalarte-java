package br.com.escalarte.crudescalarte.ui.cargoUI;

import br.com.escalarte.crudescalarte.dao.CargoDAO;
import br.com.escalarte.crudescalarte.model.Cargo;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

        // Formulário
        VBox formularioBox = new VBox(8);
        HBox botoesFormularioBox = new HBox(8);

        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();
        nomeField.setPromptText("");
        nomeField.setMaxWidth(150);

        Label cargaHorariaLabel = new Label("Carga Horária:");
        TextField cargaHorariaField = new TextField();
        cargaHorariaField.setPromptText("");
        cargaHorariaField.setMaxWidth(150);

        Label interjornadaLabel = new Label("Interjornada:");
        TextField interjornadaField = new TextField();
        interjornadaField.setPromptText("");
        interjornadaField.setMaxWidth(150);

        Button cadastrar = new Button("Cadastrar");
        cadastrar.setOnAction(e -> CargoDAO.cadastrar(
                nomeField.getText(),
                cargaHorariaField.getText(),
                interjornadaField.getText()));

        Button editar = new Button("Editar");
        // editar.setOnAction(e -> new CargoEdit().start(new Stage()));

        botoesFormularioBox.getChildren().addAll(cadastrar, editar);
        formularioBox.getChildren().addAll(nomeLabel, nomeField, cargaHorariaLabel, cargaHorariaField,
                interjornadaLabel, interjornadaField, botoesFormularioBox);

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

        // Botões de Excluir e Atualizar
        HBox botoesBox = new HBox(8);

        Button excluir = new Button("Excluir");
        excluir.setOnAction(e -> CargoDAO.excluir(table.getSelectionModel().getSelectedItem()));

        Button atualizar = new Button("Atualizar");
        atualizar.setOnAction(e -> CargoDAO.atualizar(table));

        botoesBox.getChildren().addAll(excluir, atualizar);

        // Layout Principal
        VBox vbox = new VBox(8);
        vbox.setSpacing(16);
        vbox.getChildren().addAll(formularioBox, table, botoesBox);

        Scene scene = new Scene(vbox, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
