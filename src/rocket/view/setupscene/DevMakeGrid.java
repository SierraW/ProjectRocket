package rocket.view.setupscene;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rocket.environment.StructureType;
import rocket.gameeco.variable.GlobalVariable;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class DevMakeGrid extends Application {
    Label lblCurrentlySelected;
    Label lblWarning;
    int currentlySelected;
    StructureSelectBtn[] btnStructures = new StructureSelectBtn[StructureType.values().length];
    StructureSelectBtn[] btnGrids = new StructureSelectBtn[GlobalVariable.PLAYERBLOCKS];
    Button btnSubmit = new Button("Submit");
    Button btnReset = new Button("Reset");
    AnchorPane displayHbox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        startStage(primaryStage);
    }

    void startStage(Stage stage) throws Exception{
        reset();
        File file = new File("Data/GameGrid.dat");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        final int SIZE_INT = Integer.SIZE;

        btnSubmit.setOnAction(event -> {
            try {
                raf.seek(0); // init
                raf.writeInt(GlobalVariable.GAMEVER);
                for (StructureSelectBtn btn : btnGrids) {
                    raf.writeInt(btn.getStructureType().ordinal());
                }
                lblWarning.setText("Submit successful.");
            } catch (Exception e) {
                System.out.println("rocket.view.setupscene.DMG: file not exist.");
            }
        });

        btnReset.setOnAction(event -> {
            try {
                restart(stage);
            } catch (Exception e) {
                System.out.println("setupscene: btnReset");
            }
        });




        Scene scene = new Scene(displayHbox, GlobalVariable.screenWidth, GlobalVariable.screenHeight);
        stage.setScene(scene);
        stage.show();
    }

    void restart(Stage stage) throws Exception{
        startStage(stage);
    }

    private void reset() {
        lblCurrentlySelected = new Label("Selected:");
        lblWarning = new Label("");
        currentlySelected = 0;
        btnStructures = new StructureSelectBtn[StructureType.values().length];
        btnGrids = new StructureSelectBtn[GlobalVariable.PLAYERBLOCKS];
        displayHbox = getLayout();
    }

    private AnchorPane getLayout() {
        VBox vBox = new VBox();
        for (int i = 1; i < StructureType.values().length; i++) {
            btnStructures[i] = new StructureSelectBtn(true);
            btnStructures[i].setStructureType(StructureType.values()[i]);
            btnStructures[i].setOnAction(new ButtonHandle());
            vBox.getChildren().add(btnStructures[i]);
        }

        VBox grid = new VBox();
        for (int i = 0; i < GlobalVariable.PLAYERBLOCKS; i++) {
            btnGrids[i] = new StructureSelectBtn(false);
            btnGrids[i].setOnAction(new ButtonHandle());
            grid.getChildren().add(btnGrids[i]);
        }
        VBox btnAndLbls = new VBox();
        btnAndLbls.getChildren().addAll(btnSubmit,btnReset,lblCurrentlySelected,lblWarning);

        AnchorPane anchorPane = new AnchorPane();

        anchorPane.getChildren().addAll(vBox, btnAndLbls, grid);
        AnchorPane.setLeftAnchor(vBox, 10.0);
        AnchorPane.setTopAnchor(btnAndLbls, GlobalVariable.screenHeight*0.7);
        AnchorPane.setRightAnchor(grid, 10.0);
        return anchorPane;
    }

    class ButtonHandle implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() instanceof StructureSelectBtn) {
                StructureSelectBtn structureSelectBtn = (StructureSelectBtn) event.getSource();
                if (structureSelectBtn.isStructureSelection()) {
                    lblCurrentlySelected.setText("Currently Select: " + structureSelectBtn.getStructureType().getName());
                    currentlySelected = structureSelectBtn.getStructureType().ordinal();
                } else {
                    if (currentlySelected != 0) {
                        if (btnStructures[currentlySelected].setToGrid()) {
                            structureSelectBtn.setStructureType(StructureType.values()[currentlySelected]);
                        } else {
                            lblWarning.setText(StructureType.values()[currentlySelected] + " exceed!");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
