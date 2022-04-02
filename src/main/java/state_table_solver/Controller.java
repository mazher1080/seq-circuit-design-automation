package state_table_solver;

import state_table_solver.VHDLGeneration.VHDLFileWriter;
import state_table_solver.stateTable.MealyTable;
import state_table_solver.stateTable.MooreTable;
import state_table_solver.stateTable.StateTable;
import state_table_solver.userInterface.MainFrame;
import state_table_solver.userInterface.MealyTableUI;
import state_table_solver.userInterface.MooreTableUI;
import state_table_solver.userInterface.StateTableUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Controller is a class which performs operations on the app model and the app view.
 * These operations may be called by the EventListener class.
 * @see EventListener
 * 
 * @author Jacob Head
 */

public class Controller implements Serializable {

    private AppData appData;
    private MainFrame mainFrame;

    /**
     * Getter for app data.
     * 
     * @return The app data.
     */
    public AppData appData() {
        return this.appData;
    }

    /**
     * Getter for main frame.
     * 
     * @return The main frame.
     */
    public MainFrame mainFrame() {
        return this.mainFrame;
    }
    
    /**
     * Initializes a new application. Creates new model and view.
     */
    public void initApplication() {
        this.appData = new AppData();
        this.mainFrame = new MainFrame(appData);
        createNewProject();
    }

    /**
     * Creates a new project for the application. Renders a pop up where users can
     * specify a moore project or a mealy project.
     */
    public void createNewProject() {
        int response = mainFrame().renderTableSelection();
        
        // Selection window closed
        if(response == -1) return;

        StateTable defaultTable;
        if(response == 0) {
            // Moore button clicked
            defaultTable = new MooreTable();
        } else {
            // Mealy button clicked
            defaultTable = new MealyTable();
        }
        defaultTable.addState("Q1");
        defaultTable.addState("Q2");

        appData().setStateTable(defaultTable);

        StateTableUI defaultTableUI;
        if(response == 0) {
            // Moore button clicked
            defaultTableUI = new MooreTableUI(this);
        } else {
            // Mealy button clicked
            defaultTableUI = new MealyTableUI(this);
        }
        mainFrame().setStateTableUI(defaultTableUI);
        loadUserInterface();
        System.out.println(appData().getStateTable());
    }

    /**
     * Derives the sop from the state table associated with a project.
     * Renders a pop up which displays the minimized sum of products equations.
     */
    public void deriveSoP() {
        List<String> boolEqnStrings = new ArrayList<String>();
        StateTable stateTable = appData().getStateTable();

        if(stateTable == null) {
            this.mainFrame().renderErrorAlert(
                "State table is null."
            );
            return;
        }

        for(int i = 0; i < stateTable.getStateCount(); i++) {
            String stateId = stateTable.getCurrentStateCol().get(i).getId();
            String minStateSoPString = stateTable.getMinStateSoP(stateId).toString();
            String boolEqn = stateId + "(next) = " + minStateSoPString;
            boolEqnStrings.add(boolEqn);
        }
        String minOutputSoPString = stateTable.getMinOutputSoP().toString();
        String outputBoolEqn = stateTable.HIGH_OUTPUT.getId() + " = " + minOutputSoPString;
        boolEqnStrings.add(outputBoolEqn);

        mainFrame().renderDerivedSoP(boolEqnStrings);
    }

    /**
     * Opens a previously saved project file. Renders a file chooser.
     */
    public void openProjectFile() {
        String filePath = mainFrame().renderFileChooser();
        
        if(filePath == "" || filePath == null) return;

        try {
            appData().open(filePath);
        } catch (Exception e) {
            mainFrame().renderErrorAlert("Cannot open selected file.");
        }

        StateTableUI defaultTableUI;

        // Have to do this disgusting class check due to serialization of abstract classes.
        // Sorry Dr. Norvell
        if(appData().getStateTable().getTableType() == MooreTable.class) {
            defaultTableUI = new MooreTableUI(this);
        } else if (appData().getStateTable().getTableType() == MealyTable.class) {
            defaultTableUI = new MealyTableUI(this);
        } else {
            mainFrame().renderErrorAlert("Selected file does not contain a state table project.");
            return;
        }

        mainFrame().setStateTableUI(defaultTableUI);
        loadUserInterface();
    }

    /**
     * Save the current project file to a specified location.
     * Renders a file saver.
     */
    public void saveAsProjectFile() {
        String filePath = mainFrame().renderFileSaver();
        
        if(filePath == null) return;
        appData().saveAs(filePath);
    }

    /**
     * Save the project file to current filepath. If no filepath has
     * been specified render a file saver.
     */
    public void saveProjectFile() {
        if(appData().getFilePath() == null) {
            saveAsProjectFile();
        } else {
            appData().save();
        }
    }

    public void exportVHDL() {
        String filePath = mainFrame().renderFileSaver();

        if(filePath == null || filePath == "") return;

        if(appData == null || appData.getStateTable() == null) {
            mainFrame().renderErrorAlert("Cannot export VHDL. State table data is invalid.");
            return;
        }
        
        VHDLFileWriter fWriter = new VHDLFileWriter(filePath, appData());
        fWriter.writeFile();
    }

    public void removeRow(int rowIndex) {
        mainFrame().getStateTableUI().deleteRow(rowIndex);
        mainFrame().renderCenterPanel();
    }

    public void addState() {
        int response = mainFrame().renderNewStateChooser();
        // Add button was clicked
        if(response == 0) {
            String stateId = mainFrame().getNewStateResult();
            System.out.println("'" + stateId + "'");
            if(stateId == null || stateId.isBlank()) {
                mainFrame().renderErrorAlert(
                    "Cannot add state. State id not specified."
                );
                return;
            }
            appData().getStateTable().addState(stateId);
            mainFrame().getStateTableUI().getModel().fireTableDataChanged();
            mainFrame().getStateTableUI().refreshUI();
            mainFrame().renderCenterPanel();
        }
    }

    /**
     * Loads the user inerface.
     */
    private void loadUserInterface() {
        String projName = "Project1";
        try {
            projName = appData().getFileName();
        } catch (Exception e) {};

        mainFrame().renderCenterPanel();
        mainFrame().renderHeader(appData().getStateTable().getName(), projName);
        mainFrame().repaint();
        mainFrame().validate();
    }

}
