package br.com.escalarte.crudescalarte.main;

import br.com.escalarte.crudescalarte.ui.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Button btnContrato = new Button("Gerenciar Contratos");
        Button btnCargo = new Button("Gerenciar Cargos");
        Button btnColaborador = new Button("Gerenciar Colaboradores");
        Button btnSetor = new Button("Gerenciar Setores");
        Button btnTurno = new Button("Gerenciar Turnos");

        // btnContrato.setOnAction(e -> new ContratoUI().start(new Stage()));
        // btnCargo.setOnAction(e -> new CargoUI().start(new Stage()));
        // btnColaborador.setOnAction(e -> new ColaboradorUI().start(new Stage()));
        // btnSetor.setOnAction(e -> new SetorUI().start(new Stage()));
        // btnTurno.setOnAction(e -> new TurnoUI().start(new Stage()));

        VBox layout = new VBox(10, btnContrato, btnCargo, btnColaborador, btnSetor, btnTurno);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Sistema Escalarte");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
