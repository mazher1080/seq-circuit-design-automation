package state_table_solver.userInterface;

import javax.swing.JTable;
import javax.swing.table.*;

import state_table_solver.Controller;
import state_table_solver.stateTable.State;
import state_table_solver.userInterface.tableModel.CurrentStateCellEditor;
import state_table_solver.userInterface.tableModel.CurrentStateCellRenderer;
import state_table_solver.userInterface.tableModel.NextStateCellEditor;
import state_table_solver.userInterface.tableModel.NextStateCellRenderer;
import state_table_solver.userInterface.tableModel.StateTableModel;

import java.awt.*;
import java.util.List;

/**
 * UI structure for a state table. Houses the JTable containing the necessary app data. To be constructed with a StateTableModel.
 * 
 * @author Jacob Head
 * @author Muneeb Azher
 */

public abstract class StateTableUI {
    private JTable jTable;
    private Controller controller;
    private StateTableModel stateTableModel;

    public StateTableUI(Controller c) {
        this.controller = c;
        this.setStateTableModel(new StateTableModel(getColumnLabels(), getNumCols(), getController()));
        createStateTable(getModel());
        this.setTableWidth(getTableWidth());
    }

    public void createStateTable(StateTableModel defaultTable) {
        this.jTable = new JTable(defaultTable);
        this.jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.stateTableModel = defaultTable;
        JTableHeader th = this.jTable.getTableHeader();
        th.setReorderingAllowed(false); // Disable column moving
        th.setResizingAllowed(false); // Disable column resizing
        getJTable().setSelectionForeground(Color.WHITE);

        TableColumn currentStateCol = getJTable().getColumnModel().getColumn(0);
        TableColumn nextStatecol0 = getJTable().getColumnModel().getColumn(1);
        TableColumn nextStatecol1 = getJTable().getColumnModel().getColumn(2);

        List<State> stateList = getController().appData().getStateTable().getCurrentStateCol();

        nextStatecol0.setCellEditor(new NextStateCellEditor(stateList));
        nextStatecol0.setCellRenderer(new NextStateCellRenderer());
        nextStatecol1.setCellEditor(new NextStateCellEditor(stateList));
        nextStatecol1.setCellRenderer(new NextStateCellRenderer());
        currentStateCol.setCellEditor(new CurrentStateCellEditor(getController()));
        currentStateCol.setCellRenderer(new CurrentStateCellRenderer());

        setOutputCellEditor();
    }

    public void refreshUI() {
        createStateTable(getModel());
        setTableWidth(getTableWidth());
    }

    public abstract int getTableWidth();

    public abstract String[] getColumnLabels();

    public abstract int getNumCols();

    public abstract void setOutputCellEditor();
    
    public void setTableWidth(int width) {
        Dimension defaultDim = getJTable().getPreferredSize();
        defaultDim.width = width;
        getJTable().setPreferredSize(defaultDim);
        getJTable().setPreferredScrollableViewportSize(getJTable().getPreferredSize());
    }

    public Object[] getEmptyRow() {
        return new Object[getNumCols()];
    }

    public Controller getController() {
        return this.controller;
    }

    public JTable getJTable() {
        return jTable;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public StateTableModel getModel() {
        return this.stateTableModel;
    }

    public void setStateTableModel(StateTableModel stateTableModel) {
        this.stateTableModel = stateTableModel;
    }

    public void deleteRow(int i) {
        this.stateTableModel.deleteRow(i);
        refreshUI();
    }
}
