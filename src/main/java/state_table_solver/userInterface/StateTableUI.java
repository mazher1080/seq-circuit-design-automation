package state_table_solver.userInterface;

import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.*;

/**
 * StateTableUI is an extension of JTable which is constructed with a StateTableModel.
 * 
 * @author Jacob Head
 * @author Muneeb Azher
 */

public abstract class StateTableUI extends JTable {
    public StateTableUI(Object[][] defaultRows, String[] columnLabels, int numCols) {
        super(numCols == 5 ? new MealyTableModel(columnLabels, numCols) : new MooreTableModel(columnLabels, numCols));
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
