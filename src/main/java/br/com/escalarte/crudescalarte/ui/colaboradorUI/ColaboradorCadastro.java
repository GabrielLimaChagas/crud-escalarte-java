package br.com.escalarte.crudescalarte.ui.colaboradorUI;

import br.com.escalarte.crudescalarte.dao.ColaboradorDAO;
import br.com.escalarte.crudescalarte.dao.TurnoDAO;
import br.com.escalarte.crudescalarte.model.Colaborador;
import br.com.escalarte.crudescalarte.model.Turno;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ColaboradorCadastro {
    private ObservableList<Colaborador> colaboradorList;

    public ColaboradorCadastro(ObservableList<Colaborador> colaboradorList) {
        this.colaboradorList = colaboradorList;
    }
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Colaboradores");

        VBox vbox = new VBox(1);
        HBox hbox = new HBox(1);

        vbox.setAlignment(Pos.TOP_CENTER);
        hbox.setAlignment(Pos.BASELINE_CENTER);

        Label titulo = new Label("Cadastro");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));


        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome ");
        nomeField.setMaxWidth(150);

        Label senhaLabel = new Label("Senha:");
        TextField senhaField = new TextField();
        senhaField.setPromptText("senha");
        senhaField.setMaxWidth(150);

        Label dataNascimentoLabel = new Label("Data de Nascimento:");
        TextField dataNascimentoField = new TextField();
        dataNascimentoField.setPromptText("dataNascimento");
        dataNascimentoField.setMaxWidth(150);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("email");
        emailField.setMaxWidth(150);

        Label telefoneLabel = new Label("Telefone:");
        TextField telefoneField = new TextField();
        telefoneField.setPromptText("telefone");
        telefoneField.setMaxWidth(150);

        Label cpfLabel = new Label("CPF:");
        TextField cpfField = new TextField();
        cpfField.setPromptText("cpf");
        cpfField.setMaxWidth(150);

        Button cadastrar = new Button("Cadastrar");
        Button limpar = new Button("Limpar");

        cadastrar.setOnAction(_ -> {
            boolean sucesso = ColaboradorDAO.cadastrar(
                    nomeField.getText(),
                    senhaField.getText(),
                    dataNascimentoField.getText(),
                    emailField.getText(),
                    telefoneField.getText(),
                    cpfField.getText());
            colaboradorList.setAll(ColaboradorDAO.getColaboradores());
            if (sucesso) {
                primaryStage.close();
            }
        });

        limpar.setOnAction(_ ->
                ColaboradorDAO.limpar(nomeField, senhaField, dataNascimentoField, emailField, telefoneField, cpfField)
        );


        hbox.getChildren().addAll(cadastrar, limpar);
        vbox.getChildren().addAll(titulo,nomeLabel, nomeField, senhaLabel, senhaField, dataNascimentoLabel, dataNascimentoField, emailLabel, emailField,telefoneLabel, telefoneField, cpfLabel, cpfField, hbox);

        Scene scene = new Scene(vbox, 700, 500);
        primaryStage.setScene(scene);
    }
}
