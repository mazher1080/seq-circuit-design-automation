package state_table_solver.userInterface.tableModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import state_table_solver.booleanLogic.Bit;
import state_table_solver.booleanLogic.BitConst;
import state_table_solver.booleanLogic.BitValue;

/**
 * <p> Output cell editor for each cell in an output column of a
 * state table.
 * @author Jacob Head
 * @author Muneeb Azher
 */

public class OutputCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    
    private Bit nextOutput;
    private JComboBox<Bit> comboBox;
    private static final Bit[] outputList = new Bit[] {
        new BitConst(BitValue.UNKNOWN),
        new BitConst(BitValue.LOW),
        new BitConst(BitValue.HIGH)
    };

    /**
     * Class constructor. Creates a new combo box for the cell and
     * adds possible states.
     */
    public OutputCellEditor() {
        JComboBox<Bit> comboBox = new JComboBox<Bit>();
        for (Bit aBit : outputList) {
            comboBox.addItem(aBit);
        }
        comboBox.addActionListener(this);

        this.comboBox = comboBox;
    }

    /**
     * Returns the bit for the current cell.
     * 
     * @return Object containing associated bit.
     */
    @Override
    public Object getCellEditorValue() {
        return this.nextOutput;
    }

    /** 
     * Gets the table editor component.
     * 
     * @param table The current table.
     * @param value The selected value.
     * @param isSelected If the cell is selected.
     * @param row The current row index.
     * @param column The current column index.
     * @return Cell editor component.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof Bit) {
            this.nextOutput = (Bit) value;
        }
        this.comboBox.setSelectedItem(this.nextOutput);

        if (isSelected) {
            this.comboBox.setBackground(table.getSelectionBackground());
        } else {
            this.comboBox.setBackground(table.getSelectionForeground());
        }
        return this.comboBox;
    }

    /**
     * Handles action performed on the editor.
     * 
     * @param event The event object.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        this.comboBox = (JComboBox<Bit>) event.getSource();
        this.nextOutput = (Bit) this.comboBox.getSelectedItem();
        stopCellEditing();
    }

}

