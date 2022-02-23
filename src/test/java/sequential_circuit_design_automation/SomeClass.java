package sequential_circuit_design_automation;

import java.io.Serializable;

public class SomeClass implements SerializableObject<SomeClass> {
    private int num;
    public SomeClass() {
        this.num = 123;
    }

    public boolean returnTrue() {
        return true;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public Serializable getSerialized() {
        return this;
    }

    @Override
    public SomeClass getUnserialized() {
        return this;
    }

}
