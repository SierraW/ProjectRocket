package rocket.view.prepscene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import rocket.gameeco.variable.GlobalVariable;
import rocket.objcontrol.LiveGameObj;
import rocket.rocketcore.rocketcontrol.PowerTurnControl;
import rocket.rocketcore.rocketcontrol.RCSystemBuilder;
import rocket.rocketcore.rocketcontrol.RCSystemIO;
import rocket.rocketcore.rocketcontrol.ThrottleControl;

public class ControlWriterScene implements EventHandler<ActionEvent> {

    RCSystemBuilder rcSystemBuilder;
    RCSystemIO rcSystemIO;

    TextField txtThrottleStart = new TextField();
    ComboBox<Float> cmbThrottleControl = new ComboBox<>();
    Float[] tFloats = {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};

    TextField txtPowerTurnStart = new TextField();
    ComboBox<Float> cmbPowerTurnControl = new ComboBox<>();
    Float[] ptFloats = {-5f, -4f, -3f, -2f, -1f, 0f, 1f, 2f, 3f, 4f, 5f};

    TextField txtDetonateControl = new TextField();

    Button btnTPrevious = new Button("Previous");
    Button btnTNext = new Button("Next");

    Button btnTFirst = new Button("First");
    Button btnTLast = new Button("Last");
    Button btnTRemove = new Button("Remove");
    Button btnTEdit = new Button("Edit");
    Button btnTAdd = new Button("Add");

    Button btnPPrevious = new Button("Previous");
    Button btnPNext = new Button("Next");

    Button btnPFirst = new Button("First");
    Button btnPLast = new Button("Last");
    Button btnPRemove = new Button("Remove");
    Button btnPEdit = new Button("Edit");
    Button btnPAdd = new Button("Add");

    Button btnDEdit = new Button("Edit");

    Button btnWriteFile = new Button("WriteFile!");
    Button btnReadFile = new Button("Read");

    Button btnNewFile = new Button("New");

    Button btnSelect = new Button("Current file->rocket!");

    Button btnCWBack;

    Label lblWarning = new Label("");

    Label lblSearch = new Label("Search:");
    TextField txtSearch = new TextField();
    Button btnSearch = new Button("Search Control by Tick");

    Label lblTotalTControls = new Label("Total Throttle Control point(s):");
    Label lblTotalPControls = new Label("Total Power Turn Control point(s):");
    Label lblTControlPoints = new Label();
    Label lblPControlPoints = new Label();

    Label lblFile = new Label("Files:");

    Label lblCurrentLoadedControlFile = new Label("Rocket Current Control File: ");
    String[] strings = {"prefix-file1", "prefix-file2", "prefix-file3", "user-file1", "user-file2", "user-file3", "test-file"};
    Label lblCurrentFileName = new Label(strings[GlobalVariable.currentSelectedRCFileIndex]);

    ComboBox<String> cmbFiles = new ComboBox<>();


    //    ControlWriterScene() {
//        currentPane = getGridPane();
//    }
    public ControlWriterScene(Button btnCWBack) {
        creatNewControl();
        rcSystemIO = RCSystemIO.getInstance();
        this.btnCWBack = btnCWBack;
    }

    public Scene getScene(AnchorPane backgroundPane, Button btnSimulate) {
        btnTAdd.setOnAction(this);
        btnTNext.setOnAction(this);
        btnTPrevious.setOnAction(this);

        btnPPrevious.setOnAction(this);
        btnPAdd.setOnAction(this);
        btnPNext.setOnAction(this);

        btnDEdit.setOnAction(this);

        btnWriteFile.setOnAction(this);
        btnReadFile.setOnAction(this);

        btnTEdit.setOnAction(this);
        btnTRemove.setOnAction(this);

        btnPEdit.setOnAction(this);
        btnPRemove.setOnAction(this);

        btnSearch.setOnAction(this);

        btnNewFile.setOnAction(this);

        btnSelect.setOnAction(this);

        btnTFirst.setOnAction(this);
        btnTLast.setOnAction(this);
        btnPLast.setOnAction(this);
        btnPFirst.setOnAction(this);

        GridPane gridPane = new GridPane();

        cmbThrottleControl.getItems().addAll(tFloats);
        cmbThrottleControl.getSelectionModel().select(0);

        cmbPowerTurnControl.getItems().addAll(ptFloats);
        cmbPowerTurnControl.getSelectionModel().select(5);

        txtThrottleStart.setText("0");
        txtPowerTurnStart.setText("0");
        txtDetonateControl.setText("999");

        cmbFiles.getItems().addAll(strings);
        cmbFiles.getSelectionModel().select(6);

        Label lblTitle = new Label("Controller(100ticks/sec)");

        Label lblThrottleControl = new Label("Throttle Control (0 ~ 1)");
        Label lblPowerTurnControl = new Label("Power Turn Control (-5 ~ +5)");
        Label lblDetonateTimer = new Label("Explode at:");

        Label lblFrom = new Label("At(ticks)");
        Label lblControl = new Label("Control");

        gridPane.add(lblTitle, 0, 0, 1, 1);
        gridPane.add(lblFrom, 1, 0);
        gridPane.add(lblControl, 2, 0);
        gridPane.add(lblThrottleControl, 0, 1);
        gridPane.add(txtThrottleStart, 1, 1);
        gridPane.add(cmbThrottleControl, 2, 1);

        gridPane.add(lblPowerTurnControl, 0, 2);
        gridPane.add(txtPowerTurnStart, 1, 2);
        gridPane.add(cmbPowerTurnControl, 2, 2);

        gridPane.add(lblDetonateTimer, 0, 3);
        gridPane.add(txtDetonateControl, 1, 3);

        gridPane.add(btnTFirst, 8, 1);
        gridPane.add(btnTLast, 9, 1);
        gridPane.add(btnTPrevious, 3, 1);
        gridPane.add(btnTNext, 4, 1);
        gridPane.add(btnTAdd, 5, 1);
        gridPane.add(btnTRemove, 6, 1);
        gridPane.add(btnTEdit, 7, 1);

        gridPane.add(btnPFirst, 8, 2);
        gridPane.add(btnPLast, 9, 2);
        gridPane.add(btnPPrevious, 3, 2);
        gridPane.add(btnPNext, 4, 2);
        gridPane.add(btnPAdd, 5, 2);
        gridPane.add(btnPRemove, 6, 2);
        gridPane.add(btnPEdit, 7, 2);

        gridPane.add(btnDEdit, 3, 3);

        gridPane.add(lblFile, 0, 4);
        gridPane.add(cmbFiles, 1, 4);
        gridPane.add(btnWriteFile, 4, 4);
        gridPane.add(btnNewFile, 5, 4);
        gridPane.add(btnReadFile, 3, 4);

        gridPane.add(lblWarning, 0, 6);
        lblWarning.setId("warning");

        gridPane.add(lblSearch, 0, 5);
        gridPane.add(txtSearch, 1, 5);
        gridPane.add(btnSearch, 2, 5);
        gridPane.add(lblTotalTControls, 0, 7);
        gridPane.add(lblTControlPoints, 1, 7);
        gridPane.add(lblTotalPControls, 0, 8);
        gridPane.add(lblPControlPoints, 1, 8);
        gridPane.add(lblCurrentLoadedControlFile, 0, 9);
        gridPane.add(lblCurrentFileName, 1, 9);
        gridPane.add(btnSelect, 2, 9);

        gridPane.add(btnSimulate, 2, 10);
        gridPane.add(btnCWBack, 0, 10);


        GridPane.setHalignment(lblTitle, HPos.CENTER);

        gridPane.setHgap(10);
        gridPane.setVgap(5);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col23 = new ColumnConstraints();
        col1.setMinWidth(10);
        col23.setPercentWidth(10);
        col1.setPercentWidth(15);
        gridPane.getColumnConstraints().addAll(col1, col23, col23);

        btnReadFile.fire();

        backgroundPane.getChildren().add(gridPane);
        return new Scene(backgroundPane, GlobalVariable.screenWidth, GlobalVariable.screenHeight);
    }

    private void creatNewControl() {
        rcSystemBuilder = new RCSystemBuilder();
    }

    private void updatePTCText() {

        PowerTurnControl ptc = rcSystemBuilder.getCurrentPowerTurnControl();
        txtPowerTurnStart.setText(String.valueOf(ptc.getStartTick()));
        cmbPowerTurnControl.getSelectionModel().select(floatComboBoxConverter(ptc.getControl(), false));

        lblPControlPoints.setText(rcSystemBuilder.getPowerTurnControl().size() + "");
    }

    private void updateTCText() {
        ThrottleControl tc = rcSystemBuilder.getCurrentThrottleControl();
        txtThrottleStart.setText(String.valueOf(tc.getStartTick()));
        cmbThrottleControl.getSelectionModel().select(floatComboBoxConverter(tc.getControl(), true));

        lblTControlPoints.setText(rcSystemBuilder.getThrottleControl().size() + "");
    }

    private void updateDText() {
        txtDetonateControl.setText(String.valueOf(rcSystemBuilder.getExplodeTimer()));
    }

    private void newControl() {
        rcSystemBuilder.readFromFile(rcSystemIO.readRCMap(7));
        lblWarning.setText("new Control File");
        updateDText();
        updatePTCText();
        updateTCText();
    }

    private void addTControl() {
        Integer integer = integerValidator(txtThrottleStart.getText());
        if (integer >= 0) {
            rcSystemBuilder.addThrottleControl(integer, floatComboBoxConverter(true));
            lblWarning.setText("Add Throttle control successful");
        }
    }

    private void addPTControl() {
        Integer integer = integerValidator(txtPowerTurnStart.getText());
        if (integer >= 0) {
            rcSystemBuilder.addPowerTurnControl(integer, floatComboBoxConverter(false));
            lblWarning.setText("Add Power turn control successful");
        }
    }

    private void addDControl() {
        Integer integer = integerValidator(txtDetonateControl.getText());
        if (integer >= 0) {
            rcSystemBuilder.addDetonateControl(integer);
            lblWarning.setText("Add detonate control successful");
        }
    }

    private void setTControl() {
        rcSystemBuilder.removeThrottleControl();
        addTControl();
    }

    private void setPControl() {
        rcSystemBuilder.removePowerTurnControl();
        addPTControl();
    }

    private void searchByTick() {
        Integer integer = integerValidator(txtSearch.getText());
        if (integer >= 0) {
            if (rcSystemBuilder.searchByTick(integer)) {
                lblWarning.setText("Search control successful");
            } else {
                lblWarning.setText("Search control fail: no control at tick");
            }
        }
    }

    private Integer integerValidator(String text) {
        try {
            Integer integer = Integer.valueOf(text);
            return integer;
        } catch (Exception e) {
            lblWarning.setText("ticks must be integer");
        }
        return -1; // illegal status
    }

    private Float floatComboBoxConverter(boolean isThrottle) {
        if (isThrottle) {
            return tFloats[cmbThrottleControl.getSelectionModel().getSelectedIndex()];
        } else {
            return ptFloats[cmbPowerTurnControl.getSelectionModel().getSelectedIndex()];
        }
    }

    private int floatComboBoxConverter(Float original, boolean isThrottle) {
        if (isThrottle) {
            for (int i = 0; i < tFloats.length; i++) {
                if (original.equals(tFloats[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < ptFloats.length; i++) {
                if (original.equals(ptFloats[i])) {
                    return i;
                }
            }
        }
        return -1;//not found
    }

    //private

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnTPrevious) {
            if (rcSystemBuilder.movePointerToLeft(true)) {
                lblWarning.setText("Move Pointer Successfully");
            } else {
                lblWarning.setText("First Element");
            }
            updateTCText();
        } else if (event.getSource() == btnTAdd) {
            addTControl();
            updateTCText();
        } else if (event.getSource() == btnTNext) {
            if (rcSystemBuilder.movePointerToRight(true)) {
                lblWarning.setText("Move Pointer Successfully");
            } else {
                lblWarning.setText("Last Element");
            }
            updateTCText();
        } else if (event.getSource() == btnPPrevious) {
            if (rcSystemBuilder.movePointerToLeft(false)) {
                lblWarning.setText("Move Pointer Successfully");
            } else {
                lblWarning.setText("First Element");
            }
            updatePTCText();
        } else if (event.getSource() == btnPAdd) {
            addPTControl();
            updatePTCText();
        } else if (event.getSource() == btnPNext) {
            if (rcSystemBuilder.movePointerToRight(false)) {
                lblWarning.setText("Move Pointer Successfully");
            } else {
                lblWarning.setText("Last Element");
            }
            updatePTCText();
        } else if (event.getSource() == btnDEdit) {
            addDControl();
            updateDText();
        } else if (event.getSource() == btnWriteFile) {
            rcSystemIO.writeRCObj(rcSystemBuilder.toTCMap(), rcSystemBuilder.toPTCMap(), rcSystemBuilder.getExplodeTimer(), cmbFiles.getSelectionModel().getSelectedIndex());
            lblWarning.setText("Write File Successful");
        } else if (event.getSource() == btnReadFile) {
            rcSystemBuilder.readFromFile(rcSystemIO.readRCMap(cmbFiles.getSelectionModel().getSelectedIndex()));
            lblWarning.setText("Read File Successful");
            updateDText();
            updateTCText();
            updatePTCText();
        } else if (event.getSource() == btnTEdit) {
            setTControl();
            updateTCText();
        } else if (event.getSource() == btnPEdit) {
            setPControl();
            updatePTCText();
        } else if (event.getSource() == btnTRemove) {
            rcSystemBuilder.removeThrottleControl();
            updateTCText();
        } else if (event.getSource() == btnPRemove) {
            rcSystemBuilder.removePowerTurnControl();
            updatePTCText();
        } else if (event.getSource() == btnSearch) {
            searchByTick();
            updatePTCText();
            updateTCText();
            updateDText();
        } else if (event.getSource() == btnNewFile) {
            newControl();
        } else if (event.getSource() == btnSelect) {
            btnReadFile.fire();
            GlobalVariable.currentSelectedRCFileIndex = cmbFiles.getSelectionModel().getSelectedIndex();
            lblCurrentFileName.setText(cmbFiles.getSelectionModel().getSelectedItem());
        } else if (event.getSource() == btnTFirst) {
            if (rcSystemBuilder.setPointer(0, true)) {
                lblWarning.setText("First Element");
            }
            updateTCText();
        } else if (event.getSource() == btnPFirst) {
            if (rcSystemBuilder.setPointer(0, false)) {
                lblWarning.setText("First Element");
            }
            updatePTCText();
        } else if (event.getSource() == btnTLast) {
            if (rcSystemBuilder.setPointer(rcSystemBuilder.getThrottleControl().size() - 1, true)) {
                lblWarning.setText("Last Element");
            }
            updateTCText();
        } else if (event.getSource() == btnPLast) {
            if (rcSystemBuilder.setPointer(rcSystemBuilder.getPowerTurnControl().size() - 1, false)) {
                lblWarning.setText("Last Element");
            }
            updatePTCText();
        }
    }

    //    public GridPane getCurrentPane() {
//        return currentPane;
//    }
}
