package state_table_solver;

import state_table_solver.stateTable.MealyTable;
import state_table_solver.stateTable.MooreTable;
import state_table_solver.stateTable.StateTable;
import state_table_solver.userInterface.MainFrame;

import javax.swing.JFileChooser;
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

// TODO entire class
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
    
    public void initApplication() {
        this.appData = new AppData();
        this.mainFrame = new MainFrame();
    }

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
        String outputBoolEqn = "y = " + minOutputSoPString;
        boolEqnStrings.add(outputBoolEqn);

        mainFrame().renderDerivedSoP(boolEqnStrings);
    }

    // public void openProjectFile() {
    //     //Create a file chooser
    //     final JFileChooser fc = new JFileChooser();
    //     int returnVal = fc.showOpenDialog(mainFrame());

    //     if (returnVal == JFileChooser.APPROVE_OPTION) {
    //         File file = fc.getSelectedFile();
    //         //This is where a real application would open the file.
    //         log.append("Opening: " + file.getName() + "." + newline);
    //     } else {
    //         log.append("Open command cancelled by user." + newline);
    //     }
    // }


}
