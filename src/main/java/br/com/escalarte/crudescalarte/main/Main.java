package br.com.escalarte.crudescalarte.main;

import br.com.escalarte.crudescalarte.ui.cargoUI.CargoMain;
import br.com.escalarte.crudescalarte.ui.colaboradorUI.ColaboradorMain;
import br.com.escalarte.crudescalarte.ui.contratoUI.ContratoMain;
import br.com.escalarte.crudescalarte.ui.setorUI.SetorMain;
import br.com.escalarte.crudescalarte.ui.turnoUI.TurnoMain;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        //Criação dos botões no menu
        Button btnContrato = new Button("Gerenciar Contratos");
        Button btnCargo = new Button("Gerenciar Cargos");
        Button btnColaborador = new Button("Gerenciar Colaboradores");
        Button btnSetor = new Button("Gerenciar Setores");
        Button btnTurno = new Button("Gerenciar Turnos");

        // Abrem janelas específicas quando os botões são clicados

        btnContrato.setOnAction(e -> new ContratoMain().start(new Stage()));
        btnCargo.setOnAction(e -> new CargoMain().start(new Stage()));
        btnColaborador.setOnAction(e -> {
                Stage colaboradorStage = new Stage();
                colaboradorStage.initModality(Modality.APPLICATION_MODAL);
                colaboradorStage.setTitle("Gerenciador de Turnos");
                new ColaboradorMain().start(colaboradorStage);
                colaboradorStage.showAndWait();
        });
        btnSetor.setOnAction(e -> new SetorMain().start(new Stage()));
        btnTurno.setOnAction(e -> {
            Stage turnoStage = new Stage();
            turnoStage.initModality(Modality.APPLICATION_MODAL);
            new TurnoMain().start(turnoStage);
            turnoStage.showAndWait();
        });
        //VBox é um layout que organiza os botões verticalmente com espaçamento de 10 pixels
        VBox layout = new VBox(10, btnContrato, btnCargo, btnColaborador, btnSetor, btnTurno);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Sistema Escalarte");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); // Chama launch(args), que inicializa o JavaFX e chama automaticamente o método start()
    }
}
