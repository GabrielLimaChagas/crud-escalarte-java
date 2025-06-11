package br.com.escalarte.crudescalarte.ui.setorUI;


import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import br.com.escalarte.crudescalarte.model.Setor;
import br.com.escalarte.crudescalarte.dao.SetorDAO;

public class SetorMain {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Setores");

        TableView<Setor> table = new TableView<>();

        table.setEditable(false);

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        HBox excluirBox = new HBox();

        vbox.setSpacing(15);
        hbox.setSpacing(10);
        excluirBox.setSpacing(10);


        vbox.setSpacing(15);

        TextField idField = new TextField();
        idField.setPromptText("Selecione um ID");
        idField.setMaxWidth(150);

        TableColumn<Setor, String> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Setor, String> nomeSetorCol = new TableColumn<>("Nome do Setor");
        nomeSetorCol.setCellValueFactory(new PropertyValueFactory<>("nomeSetor"));

        TableColumn<Setor, String> nomeGerenteCol = new TableColumn<>("Nome do Gerente");
        nomeGerenteCol.setCellValueFactory(new PropertyValueFactory<>("nomeGerente"));

        TableColumn<Setor, Integer> quantidadeColaboradoresCol = new TableColumn<>("Quantidade de Colaboradores");
        quantidadeColaboradoresCol.setCellValueFactory(new PropertyValueFactory<>("quantidadeColaboradores"));


        table.getColumns().addAll(idCol, nomeSetorCol, nomeGerenteCol, quantidadeColaboradoresCol);

        ObjectPersistenceUtils.lerDados("setores.dat", SetorDAO.getSetores());
        table.getItems().addAll(SetorDAO.getSetores());

        Button cadastrar = new Button("Cadastrar");
        Button editar = new Button("Editar");
        Button apagar = new Button("Apagar");
        Button atualizar = new Button("Atualizar");

        cadastrar.setOnAction(_ -> new SetorCadastro().start(new Stage()));
        editar.setOnAction(_ -> new SetorEdit().start(new Stage()));
        apagar.setOnAction(_ -> SetorDAO.apagar(idField.getText()));
        atualizar.setOnAction(_ -> SetorDAO.atualizar(table));

        hbox.getChildren().addAll(cadastrar, editar, atualizar, idField, apagar);
        //excluirBox.getChildren().addAll();
        vbox.getChildren().addAll(table, hbox, excluirBox);

        Scene scene = new Scene(vbox, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
