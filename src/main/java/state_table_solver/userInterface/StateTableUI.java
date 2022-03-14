package state_table_solver.userInterface;

import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.*;

public abstract class StateTableUI extends JTable {
    public StateTableUI(Object[][] defaultRows, Object[] columnLabels) {
        super(new StateTableModel());

        JTableHeader th = this.getTableHeader();
        th.setReorderingAllowed(false); // Disable column moving
        th.setResizingAllowed(false); // Disable column resizing
    }

    public abstract int getNumCols();
    
    public void setTableWidth(int width) {
        Dimension defaultDim = this.getPreferredSize();
        defaultDim.width = width;
        this.setPreferredSize(defaultDim);
    }

    public Object[] getEmptyRow() {
        return new Object[getNumCols()];
    }

    public void addRow() {
        return;
    }
}
