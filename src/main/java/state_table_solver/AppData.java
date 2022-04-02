package state_table_solver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import state_table_solver.stateTable.StateTable;

/**
 * <p> AppData is a component responsible for storing all data associated
 * with our app.
 * 
 * @author Jacob Head
 */

public class AppData {

    private StateTable stateTable; // = new StateTable()
    private String filePath;
    private final static String FILE_EXTENSION = ".proj";
    
    /**
     * Getter for state table.
     * 
     * @return The state table.
     */
    public StateTable getStateTable() {
        return this.stateTable;
    }

    
    /** 
     * Setter for state table.
    * 
    * @param stateTable The new state table.
    */
    public void setStateTable(StateTable stateTable) {
        this.stateTable = stateTable;
    }

    /**
     * Gets the file name from the filepath.
     * 
     * @return The file name
     */
    public String getFileName() {
        File f = new File(this.filePath);
        return f.getName();
    }

    /**
     * Getter for filePath.
     * 
     * @return The filePath.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Setter for file path.
     * 
     * @param filePath The new file path.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Opens a state table project at a given filepath.
     * 
     * @param filePath The filepath to load the project from.
     */
    public void open(String filePath) {
        setFilePath(filePath);
        load();
    }

    /**
     * Saves the state table project at the specified file path.
     * 
     * @param filePath The location to save the state table to.
     */
    public void saveAs(String filePath) {
        setFilePath(filePath + FILE_EXTENSION);
        save();
    }

    /** 
     * Saves AppData to specified filepath.
     * 
     * @throws FileNotFoundException If specified file path no longer exists.
     * @throws IOException If cannot initialize stream.
     */
    public void save() {
        try {
            FileOutputStream fileStream = new FileOutputStream(new File(getFilePath()));
            ObjectOutputStream objStream = new ObjectOutputStream(fileStream);

            // Write objects to file
            objStream.writeObject(getStateTable());

            objStream.close();
            fileStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the AppData stored in the given filePath
     * 
     * @throws FileNotFoundException If specified file path no longer exists.
     * @throws IOException If cannot initialize stream.
     * @throws ClassNotFoundException If file has been modified the typecast conversion to 
     * SerialiazableObject class will fail.
     */
    private void load() {
        try {
            FileInputStream fileInStream = new FileInputStream(getFilePath());
            ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
            setStateTable((StateTable) objInStream.readObject());
            objInStream.close();
            fileInStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }
    }
    
}
