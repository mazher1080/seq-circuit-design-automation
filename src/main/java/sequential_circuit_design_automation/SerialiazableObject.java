package sequential_circuit_design_automation;

import java.io.Serializable;

/**
 * <p> SerializableObject is a class used to wrap any object which needs to be saved to a file.
 * It is necessary to implement this for each serializable class because of the way Serializable
 * is structured in Java. Itis bad practice to use serializable with abstract classes.
 * Used in {@link ObjectDataManager} to ensure objects being managed are serializable.
 * 
 * @author Jacob Heaa
 */
interface SerializableObject<T> extends Serializable {
    /**
     * Returns the serialized object of a class.
     * 
     * @return The object with serialize type.
     */
    public Serializable getSerialized();

    /**
     * Returns the unserialized object of a class.
     * 
     * @return The object.
     */
    public T getUnserialized();
}