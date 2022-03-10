package state_table_solver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import state_table_solver.stateDiagram.StateDiagram;
import state_table_solver.userInterface.AppUI;

/**
 * <p> AppData is a component responsible for storing all data associated
 * with our app. It is referenced in app through the data method.
 * 
 * @author Jacob Head
 */

public class AppData {
    private StateDiagram stateDiagram = new StateDiagram();
    private String filePath;
    private final static String FILE_EXTENSION = ".proj";
    
    public StateDiagram getStateDiagram() {
        return this.stateDiagram;
    }

    public void setStateDiagram(StateDiagram stateDiagram) {
        this.stateDiagram = stateDiagram;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // TODO method
    public void open() {
        String filePath = "C:/Users/jake4/OneDrive/Desktop/testReadWrite/";
        String fileName = "test";
        setFilePath(filePath + fileName + FILE_EXTENSION);
        // TODO implement and use the filepicker component in AppUI
        load();
    }

    // TODO method
    public void saveAs() {
        String filePath = "C:/Users/jake4/OneDrive/Desktop/testReadWrite/";
        String fileName = "test";
        // TODO implement and use the filepicker component in AppUI
        setFilePath(filePath + fileName + FILE_EXTENSION);
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
            objStream.writeObject(getStateDiagram());

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
            setStateDiagram((StateDiagram) objInStream.readObject());
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
