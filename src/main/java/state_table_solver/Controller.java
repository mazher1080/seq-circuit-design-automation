package state_table_solver;

import state_table_solver.VHDLGeneration.VHDLFileWriter;
import state_table_solver.stateTable.MealyTable;
import state_table_solver.stateTable.MooreTable;
import state_table_solver.stateTable.StateTable;
import state_table_solver.userInterface.MainFrame;

import java.io.File;
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
        this.mainFrame = new MainFrame();
    }

    /**
     * Creates a new project for the application. Renders a pop up where users can
     * specify a moore project or a mealy project.
     */
    public void createNewProject() {
        int response = mainFrame().renderTableSelection();

        if (response != 0 && response != 1) return;

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
        System.out.println(appData().getStateTable());
    }

    /**
     * Derives the sop from the state table associated with a project.
     * Renders a pop up which displays the minimized sum of products equations.
     */
    public void deriveSoP() {
        List<String> boolEqnStrings = new ArrayList<String>();
        StateTable stateTable = appData().getStateTable();

        // TODO throw an error or alert here
        if(stateTable == null)
            return;

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
        
        if(filePath == null) return;
        appData().open(filePath);
        // TODO catch errors if wrong file type is specified
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

        if(filePath == null || appData == null || appData.getStateTable() == null) 
            return;
        // TODO alert error here
        
        VHDLFileWriter fWriter = new VHDLFileWriter(filePath, appData);
        fWriter.writeFile();
    }

}
