package br.com.escalarte.crudescalarte.ui.turnoUI;

import br.com.escalarte.crudescalarte.dao.TurnoDAO;
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

import java.time.format.DateTimeFormatter;

public class TurnoEdit {
    private ObservableList<Turno> turnosList;
    private Turno turnoSelecionado;

    public TurnoEdit(ObservableList<Turno> turnosList, Turno turnoSelecionado) {
        this.turnosList = turnosList;
        this.turnoSelecionado = turnoSelecionado;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Editar Turno");

        VBox vbox = new VBox(1);
        HBox hbox = new HBox(1);

        vbox.setAlignment(Pos.TOP_CENTER);
        hbox.setAlignment(Pos.BASELINE_CENTER);

        Label titulo = new Label("Editar");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label idLabel = new Label("Id:");
        TextField idField = new TextField();
        idField.setPromptText("ID");
        idField.setMaxWidth(150);
        idField.setEditable(false);

        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome ");
        nomeField.setMaxWidth(150);

        Label horarioInicioLabel = new Label("Horário Início:");
        TextField horarioInicioField = new TextField();
        horarioInicioField.setPromptText("Horário Início (HH:mm)");
        horarioInicioField.setMaxWidth(150);

        Label horarioFimLabel = new Label("Horário Fim:");
        TextField horarioFimField = new TextField();
        horarioFimField.setPromptText("Horário Fim (HH:mm)");
        horarioFimField.setMaxWidth(150);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        if (turnoSelecionado != null) {
            idField.setText(String.valueOf(turnoSelecionado.getId()));
            nomeField.setText(turnoSelecionado.getNome());
            horarioInicioField.setText(turnoSelecionado.getHorarioInicio().format(formatter));
            horarioFimField.setText(turnoSelecionado.getHorarioFim().format(formatter));
        }

        Button editar = new Button("Editar");
        Button limpar = new Button("Limpar");

        editar.setOnAction(_ -> {
            boolean sucesso = TurnoDAO.editar(
                    idField.getText(),
                    nomeField.getText(),
                    horarioInicioField.getText(),
                    horarioFimField.getText());
            turnosList.setAll(TurnoDAO.getTurnos());
            if (sucesso) {
                primaryStage.close();
            }
    });

        limpar.setOnAction(_ ->
                TurnoDAO.limpar(nomeField, horarioInicioField, horarioFimField)
        );

        hbox.getChildren().addAll(editar, limpar);
        vbox.getChildren().addAll(titulo, idLabel, idField, nomeLabel, nomeField, horarioInicioLabel, horarioInicioField, horarioFimLabel, horarioFimField, hbox);

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
    }
}
