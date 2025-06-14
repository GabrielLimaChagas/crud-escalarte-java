package br.com.escalarte.crudescalarte.ui.contratoUI;

import br.com.escalarte.crudescalarte.dao.ContratoDAO;
import br.com.escalarte.crudescalarte.model.Contrato;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContratoMain {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Contratos");

        TableView<Contrato> table = new TableView<>();
        VBox vbox = new VBox(10);
        HBox botoesBox = new HBox(10);
        HBox excluirBox = new HBox(10);

        TextField idField = new TextField();
        idField.setPromptText("ID para exclusão");

        TableColumn<Contrato, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Contrato, String> colaboradorCol = new TableColumn<>("Nome do Colaborador");
        colaboradorCol.setCellValueFactory(new PropertyValueFactory<>("colaborador"));

        TableColumn<Contrato, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Contrato, Double> cargaCol = new TableColumn<>("Carga Diária");
        cargaCol.setCellValueFactory(new PropertyValueFactory<>("cargaHorariaDiaria"));

        TableColumn<Contrato, String> cargoCol = new TableColumn<>("Cargo atribuído");
        cargoCol.setCellValueFactory(new PropertyValueFactory<>("cargo"));

        TableColumn<Contrato, String> inicioCol = new TableColumn<>("Início do Contrato");
        inicioCol.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));

        TableColumn<Contrato, String> fimCol = new TableColumn<>("Fim do Contrato");
        fimCol.setCellValueFactory(new PropertyValueFactory<>("dataFim"));

        TableColumn<Contrato, Integer> semanaCol = new TableColumn<>("Dias Semanais de trabalho");
        semanaCol.setCellValueFactory(new PropertyValueFactory<>("diasTrabalhoSemanal"));

        TableColumn<Contrato, Integer> mensalCol = new TableColumn<>("Dias Mensais de trabalho");
        mensalCol.setCellValueFactory(new PropertyValueFactory<>("diasTrabalhoMensal"));

        table.getColumns().addAll(idCol, colaboradorCol, statusCol, cargaCol, cargoCol, inicioCol, fimCol, semanaCol, mensalCol);

        ObjectPersistenceUtils.lerDados("contratos.dat", ContratoDAO.getContratos());
        table.getItems().addAll(ContratoDAO.getContratos());

        Button cadastrar = new Button("Cadastrar");
        Button editar = new Button("Editar");
        Button excluir = new Button("Excluir");
        Button atualizar = new Button("Atualizar");

        cadastrar.setOnAction(_ -> new ContratoCadastro().start(new Stage()));
        editar.setOnAction(_ -> {
            Contrato selecionado = table.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                new ContratoEdit().start(new Stage(), selecionado);
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um contrato para editar.");
                alerta.showAndWait();
            }
        });
        excluir.setOnAction(_ -> ContratoDAO.excluir(idField.getText()));
        atualizar.setOnAction(_ -> ContratoDAO.atualizar(table));

        botoesBox.getChildren().addAll(cadastrar, editar, atualizar);
        excluirBox.getChildren().addAll(idField, excluir);
        vbox.getChildren().addAll(table, botoesBox, excluirBox);

        primaryStage.setScene(new Scene(vbox, 850, 500));
        primaryStage.show();
    }
}
