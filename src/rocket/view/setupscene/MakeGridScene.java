package rocket.view.setupscene;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

public class MakeGridScene {
    Label lblAvailableStructure = new Label("Available Structure (Click to Select! Structure make in red is mandatory)");
    Label lblYourGrid = new Label("Your current grid!(Select a structure above then click on an empty slot)");
    Label lblCurrentlySelected;
    Label lblWarning;
    int currentlySelected;
    StructureSelectBtn[] btnStructures;
    StructureSelectBtn[] btnGrids;
    Button btnSubmit = new Button("Submit");
    Button btnBack;
    VBox displayVbox;

    public MakeGridScene(Button btnBack) {
        lblCurrentlySelected = new Label("Selected:");
        lblWarning = new Label("");
        currentlySelected = 0;
        btnStructures = new StructureSelectBtn[StructureType.values().length];
        btnGrids = new StructureSelectBtn[GlobalVariable.PLAYERBLOCKS];
        this.btnBack = btnBack;
        displayVbox = getLayout();
    }

    public Scene getScene(AnchorPane backgroundPane) throws Exception{
        File file = new File("Data/GameGrid.dat");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        final int SIZE_INT = Integer.SIZE;

        btnSubmit.setOnAction(event -> {
            if (checkRequirement()) {
                lblWarning.setText("Fail to write: Must have Command Center and Rocket Launch Pad1");
            } else {

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
            }
        });



        Scene scene = new Scene(displayVbox, GlobalVariable.screenWidth, GlobalVariable.screenHeight);
        return scene;
    }

    private boolean checkRequirement() {
        int count = 0;
        for (StructureSelectBtn btn : btnGrids) {
            if (btn.getStructureType() == StructureType.RocketLaunchPad || btn.getStructureType() == StructureType.CommandCentre) {
                count++;
            }
        }
        return count != 2;
    }



    private VBox getLayout() {
        HBox hBox = new HBox();
        for (int i = 1; i < StructureType.values().length; i++) {
            btnStructures[i] = new StructureSelectBtn(true);
            btnStructures[i].setStructureType(StructureType.values()[i]);
            btnStructures[i].setOnAction(new ButtonHandle());
            if (StructureType.values()[i] == StructureType.CommandCentre || StructureType.values()[i] == StructureType.RocketLaunchPad) {
                btnStructures[i].setStyle("-fx-text-fill: red;");
            }
            hBox.getChildren().add(btnStructures[i]);
        }

        HBox grid = new HBox();
        for (int i = 0; i < GlobalVariable.PLAYERBLOCKS; i++) {
            btnGrids[i] = new StructureSelectBtn(false);
            btnGrids[i].setOnAction(new ButtonHandle());
            grid.getChildren().add(btnGrids[i]);
        }
        grid.setAlignment(Pos.CENTER);

        HBox selectedSub = new HBox();
        selectedSub.getChildren().addAll(lblCurrentlySelected, btnSubmit);
        selectedSub.setAlignment(Pos.CENTER);
        selectedSub.setSpacing(100);
        VBox btnAndLbls = new VBox();
        btnAndLbls.getChildren().addAll(lblAvailableStructure,hBox,lblYourGrid, grid, selectedSub ,lblWarning,btnBack);
        btnAndLbls.setAlignment(Pos.CENTER);
        btnAndLbls.setSpacing(100);
        return btnAndLbls;
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
}
