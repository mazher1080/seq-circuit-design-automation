package sequential_circuit_design_automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ObjectDataManagerTest {
    @Test
    public void readsAndWrites() {
        SomeClass testClass = new SomeClass();
        ObjectDataManager<SomeClass> myManager = new ObjectDataManager<SomeClass>(testClass, "C:/Users/jake4/OneDrive/Desktop/testReadWrite/some.txt");
        testClass.setNum(123);
        myManager.saveObjectFile();
        myManager.loadObjectFile();
        assertEquals(myManager.getLoadedObject().getNum(), 123);

        SomeClass myClass = myManager.getLoadedObject();
        assertTrue(myClass.returnTrue());

        SomeClass testClass2 = new SomeClass();
        ObjectDataManager<SomeClass> myManager2 = new ObjectDataManager<SomeClass>(testClass2, "C:/Users/jake4/OneDrive/Desktop/testReadWrite/some.txt");
        testClass2.setNum(321);
        myManager2.saveObjectFile();
        myManager2.loadObjectFile();
        assertEquals(myManager2.getLoadedObject().getNum(), 321);

        assertEquals(myManager.getLoadedObject().getNum(), 123);
        myManager.loadObjectFile();
        assertTrue(myManager.getLoadedObject().getNum() == 321);
    }
}
