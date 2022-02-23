package sequential_circuit_design_automation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p> ObjectDataManager is a class used to manage the data associated with an object by providing
 * a predefined file location to save the object and a consistent way to save and load objects from
 * it's associated file.
 * 
 * @author Jacob Head
 */

public class ObjectDataManager<T> {
    private SerializableObject<T> loadedObject;
    private String filePath;

    /**
     * Class constructor
     * 
     * @param ldObject Object to read and write to file
     * @param fPath File path for storing the object data
     */
    public ObjectDataManager(SerializableObject<T> ldObject, String fPath) {
        this.loadedObject = ldObject;
        this.filePath = fPath;
    }

    /**
     * Loads objects current state stored in data file
     * 
     * @return loaded object from specified file
     */
    public T getLoadedObject() {
        return this.loadedObject.getUnserialized();
    }

    /** 
     * Saves object to specified filepath.
     * 
     * @throws FileNotFoundException If specified file path no longer exists.
     * @throws IOException If cannot initialize stream.
     */
    public void saveObjectFile() {
        try {
			FileOutputStream fileStream = new FileOutputStream(new File(filePath));
			ObjectOutputStream objStream = new ObjectOutputStream(fileStream);

			// Write objects to file
			objStream.writeObject(this.loadedObject.getSerialized());

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
     * Retrieves the object stored in the data file and loads it into loadedObject
     * 
     * @throws FileNotFoundException If specified file path no longer exists.
     * @throws IOException If cannot initialize stream.
     * @throws ClassNotFoundException If file has been modified the typecast conversion to 
     * SerialiazableObject class will fail.
     */
    public void loadObjectFile() {
        try {
            FileInputStream fileInStream = new FileInputStream(this.filePath);
            ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
            this.loadedObject = (SerializableObject<T>) objInStream.readObject();
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
