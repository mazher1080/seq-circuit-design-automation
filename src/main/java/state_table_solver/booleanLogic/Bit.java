package state_table_solver.booleanLogic;

import java.io.Serializable;

import state_table_solver.VHDLGeneration.VHDLSignal;

/**
 * <p> Bit is an abstract class used to store bits which can be associated
 * with a variable and/or a constant.
 * @author Jacob Head
 */

public abstract class Bit implements VHDLSignal, Serializable {

    public static final BitValue HIGH = BitValue.HIGH;
    public static final BitValue LOW = BitValue.LOW;
    public static final BitValue UNKNOWN = BitValue.UNKNOWN;
    private BitValue value;
    protected String id;

    /**
     * Class Constructor.
     * 
     * @param id Id used to access the bit.
     * @param value Value of the bit.
     */
    protected Bit(String id, BitValue value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Setter for id.
     * 
     * @param id String to set bit id to be.
     */
    @Override
    abstract public void setId(String id);

    /**
     * Negates the bits value.
     */
    public void negate() {
        setValue(getValue().negatedValue());
    }

    /**
     * Checks if both bits have the same value.
     * 
     * @param b Bit to compare to.
     * @return True if the both bits have the same value.
     */
    public boolean equalsValue(Bit b) {
        return getValue() == b.getValue();
    }

    /**
     * Checks if both bits have the same id.
     * 
     * @param b Bit to compare to.
     * @return True if the both bits have the same id.
     */
    public boolean equalsId(Bit b) {
        return this.getId() == b.getId();
    }

    /**
     * Checks for complete equality
     * 
     * @param b Bit to compare to.
     * @return True if the both bits have the same id and same value.
     */
    public boolean equals(Bit b) {
        return equalsId(b) && equalsValue(b);
    }

    /**
     * Returns the value of the bit.
     * 
     * @return The value of the bit.
     */
    public BitValue getValue() {
        return this.value;
    }

    /**
     * Sets the value of the bit.
     * 
     * @param The value of the bit.
     */
    public void setValue(BitValue v) {
        this.value = v;
    }

    /**
     * Returns the id that represents the bit.
     * 
     * @return The id that represents the bit.
     */
    @Override
    public String getId() {
        return this.id;
    }
}