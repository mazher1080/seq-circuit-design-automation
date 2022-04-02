package state_table_solver.userInterface.tableModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import state_table_solver.stateTable.State;

/**
 * State cell editor for next state columns in JTable
 * @author Muneeb Azher
 */
public class NextStateCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private State nextState;
    private List<State> listNextState;

    public NextStateCellEditor(List<State> listNextState) {
        this.listNextState = listNextState;
    }

    /** 
     * @return Object value of associated next state
     */
    @Override
    public Object getCellEditorValue() {
        return this.nextState;
    }

    /** 
     * @param table
     * @param value
     * @param isSelected
     * @param row
     * @param column
     * @return Component
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof State) {
            this.nextState = (State) value;
        }
        
        JComboBox<State> comboNextState = new JComboBox<State>();

        for (State aState : listNextState) {
            comboNextState.addItem(aState);
        }
        comboNextState.setSelectedItem(this.nextState);
        comboNextState.addActionListener(this);

        if (isSelected) {
            comboNextState.setBackground(table.getSelectionBackground());
        } else {
            comboNextState.setBackground(table.getSelectionForeground());
        }
        return comboNextState;
    }
    
    /** Handles action performed on the editor
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<State> comboNextState = (JComboBox<State>) event.getSource();
        this.nextState = (State) comboNextState.getSelectedItem();
        stopCellEditing();
    }
}
