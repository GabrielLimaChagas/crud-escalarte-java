package br.com.escalarte.crudescalarte.ui.contratoUI;

import br.com.escalarte.crudescalarte.dao.ColaboradorDAO;
import br.com.escalarte.crudescalarte.dao.ContratoDAO;
import br.com.escalarte.crudescalarte.model.Colaborador;
import br.com.escalarte.crudescalarte.model.Contrato;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

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

        TableColumn<Contrato, String> diasFolgaCol = new TableColumn<>("Dias de Folga");
        diasFolgaCol.setCellValueFactory(cellData -> {
            List<String> folgas = cellData.getValue().getDiasFolgaSemanal();
            String dias = folgas != null ? String.join(", ", folgas) : "";
            return new ReadOnlyStringWrapper(dias);
        });

        table.getColumns().addAll(idCol, colaboradorCol, statusCol, cargaCol, cargoCol, inicioCol, fimCol, semanaCol, diasFolgaCol);

        ObjectPersistenceUtils.lerDados("contratos.dat", ContratoDAO.getContratos());
        table.getItems().addAll(ContratoDAO.getContratos());

        ObservableList<Contrato> contratosList = FXCollections.observableArrayList(ContratoDAO.getContratos());
        table.setItems(contratosList);

        Button cadastrar = new Button("Cadastrar");
        Button editar = new Button("Editar");
        Button excluir = new Button("Excluir");
        Button atualizar = new Button("Atualizar");

        cadastrar.setOnAction(_ -> {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Cadastro de Contrato");
            new ContratoCadastro(contratosList).start(modalStage);
            modalStage.showAndWait();
        });

        editar.setOnAction(_ -> {
            Contrato selecionado = table.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("Editar Contrato");
                new ContratoEdit(contratosList).start(modalStage, selecionado);
                modalStage.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um contrato para editar.");
                alerta.showAndWait();
            }
        });

        excluir.setOnAction(_ -> {
            ContratoDAO.excluir(idField.getText());
            contratosList.setAll(ContratoDAO.getContratos());
        });

        botoesBox.getChildren().addAll(cadastrar, editar);
        excluirBox.getChildren().addAll(idField, excluir);
        vbox.getChildren().addAll(table, botoesBox, excluirBox);

        primaryStage.setScene(new Scene(vbox, 850, 500));
    }
}
