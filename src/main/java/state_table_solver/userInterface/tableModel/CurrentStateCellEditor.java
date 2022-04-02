package state_table_solver.userInterface.tableModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import state_table_solver.Controller;
import state_table_solver.stateTable.State;

/**
 * <p>State cell editor for current state column in JTable.
 * 
 * @author Jacob Head
 * @author Muneeb Azher
 */

public class CurrentStateCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private State nextState;
    private int stateIndex;
    private JButton stateSelectorBtn;
    private JRootPane parentPane;
    private Controller controller;
    private JLabel inputLabel = new JLabel("State id:");
    private JTextField textField = new JTextField();
    private Object textInput = new Object[] {inputLabel, textField};
    private Object[] options = new Object[] {"Delete", "Save", "Cancel"};

    /**
     * Class constructor. 
     * 
     * @param c The controller for the cell editor.
     */
    public CurrentStateCellEditor(Controller c) {
        JButton stateSelectorBtn = new JButton();
        stateSelectorBtn.setOpaque(false);
        stateSelectorBtn.setContentAreaFilled(false);
        stateSelectorBtn.setBorderPainted(false);
        stateSelectorBtn.addActionListener(this);
        this.stateSelectorBtn = stateSelectorBtn;
        this.controller = c;
    }

    /** 
     * @return State as an object value.
     */
    @Override
    public Object getCellEditorValue() {
        return this.nextState;
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
        this.parentPane = table.getRootPane();
        setStateIndex(row);
        if (value instanceof State) {
            this.nextState = (State) value;
        }
        stateSelectorBtn.setText(this.nextState.getId());
        return this.stateSelectorBtn;
    }

    
    /** 
     * Handles an action performed on the editor. 
     * Renders a pop up to edit or delete the row.
     * 
     * @param event The event object
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        int response = JOptionPane.showOptionDialog(
            this.parentPane,
            textInput,
            "Edit State",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            null
        );

        switch(response) {
            case 0:
                if(this.controller.appData().getStateTable().getStateCount() < 3) {
                    this.controller.mainFrame().renderInfoAlert(
                        "Cannot have less than two states."
                    );
                    return;
                }
                controller.removeRow(getStateIndex());
                cancelCellEditing();
            case 1:
                this.nextState.setId(textField.getText());
                this.parentPane.repaint();
                this.parentPane.validate();
                stopCellEditing();
            default:
                cancelCellEditing();
        }
        
    }

    /** 
     * Setter for <code>stateIndex</code>
     * 
     * @param stateIndex
     */
    private void setStateIndex(int stateIndex) {
        this.stateIndex = stateIndex;
    }

    /** 
     * Getter for <code>stateIndex</code>
     * 
     * @return State index value
     */
    private int getStateIndex() {
        return stateIndex;
    }

}
