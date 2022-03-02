package sequential_circuit_design_automation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p> App is the top level component which distributes all data associated
 * with our app to all objects. It is responsible for starting and killing program.
 * 
 * @author Jacob Head
 */

public class App 
{
    private AppData appData;
    private String filePath;
    private final static String FILE_EXTENSION = ".proj";

    public App() {
        this.appData = new AppData();
    }

    public void start() {

    }

    public void kill() {

    }

    public AppData data() {
        return this.appData;
    }

    public void open() {
        String filePath = "C:/Users/jake4/OneDrive/Desktop/testReadWrite/";
        String fileName = "test";
        this.filePath = filePath + fileName + FILE_EXTENSION;
        // TODO implement and use the filepicker component in AppUI
        load();
    }

    public void saveAs() {
        String filePath = "C:/Users/jake4/OneDrive/Desktop/testReadWrite/";
        String fileName = "test";
        // TODO implement and use the filepicker component in AppUI
        this.filePath = filePath + fileName + FILE_EXTENSION;
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
			FileOutputStream fileStream = new FileOutputStream(new File(this.filePath));
			ObjectOutputStream objStream = new ObjectOutputStream(fileStream);

			// Write objects to file
            // Serializable serialiazedAppData = this.appData;
			objStream.writeObject(this.appData);

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
     * @return loaded object from specified file
     */
    public AppData load() {
        try {
            FileInputStream fileInStream = new FileInputStream(this.filePath);
            ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
            this.appData = (AppData) objInStream.readObject();
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
        return this.appData;
    }
}
