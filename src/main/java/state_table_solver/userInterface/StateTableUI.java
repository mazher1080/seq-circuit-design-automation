package state_table_solver.userInterface;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.*;

import state_table_solver.AppData;
import state_table_solver.stateTable.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * UI structure for a state table. Houses the JTable containing the necessary app data. To be constructed with a StateTableModel.
 * 
 * @author Jacob Head
 * @author Muneeb Azher
 */

public abstract class StateTableUI {
    private JTable jTable;
    private AppData appData;

    public StateTableUI(AppData appData) {
        this.appData = appData;
    }

    public void renderStateTable(TableModel defaultTable, String[] columnLabels, int numCols) {
        jTable = new JTable(defaultTable);
        JTableHeader th = this.jTable.getTableHeader();
        th.setReorderingAllowed(false); // Disable column moving
        th.setResizingAllowed(false); // Disable column resizing
        jTable.setSelectionForeground(Color.WHITE);
        TableColumn currentStateCol = getjTable().getColumnModel().getColumn(0);
        TableColumn nextStatecol0 = getjTable().getColumnModel().getColumn(1);
        TableColumn nextStatecol1 = getjTable().getColumnModel().getColumn(2);
        ArrayList<State> listNextState = new ArrayList<State>();
        listNextState.add(new State("A", "d"));
        listNextState.add(new State("B", "d"));
        listNextState.add(new State("C", "d"));
        listNextState.add(new State("D", "d"));
        nextStatecol0.setCellEditor(new NextStateCellEditor(listNextState));
        nextStatecol0.setCellRenderer(new NextStateCellRenderer(listNextState));
        nextStatecol1.setCellEditor(new NextStateCellEditor(listNextState));
        nextStatecol1.setCellRenderer(new NextStateCellRenderer(listNextState));
        JTextField textField = new JTextField();
        currentStateCol.setCellEditor(new DefaultCellEditor(textField));
        currentStateCol.setCellRenderer(new DefaultTableCellRenderer());
        setOutputCellEditor();
    }

    public abstract int getNumCols();

    public abstract void setOutputCellEditor();
    
    public void setTableWidth(int width) {
        Dimension defaultDim = this.jTable.getPreferredSize();
        defaultDim.width = width;
        this.jTable.setPreferredSize(defaultDim);
    }

    public Object[] getEmptyRow() {
        return new Object[getNumCols()];
    }

    public AppData getAppData() {
        return appData;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setAppData(AppData appData) {
        this.appData = appData;
    }

    public void addRow() {
        return;
    }
}
