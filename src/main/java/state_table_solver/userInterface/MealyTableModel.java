package state_table_solver.userInterface;
/**
 * Responsible for the table model of a Mealy state machine project
 * @author Muneeb Azher
 */
public class MealyTableModel extends StateTableModel {
    public MealyTableModel(String[] columnLabels, int numCols) {
        super(columnLabels, numCols);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }
    
}
