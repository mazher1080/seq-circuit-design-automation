package state_table_solver.userInterface;

public class MooreTableUI extends StateTableUI {
    private static final String[] COLUMN_LABELS = {"State", "Next State x = 0", "Next State x = 1", "Output"};
    private static final Object[][] DEFAULT_TABLE = {{null, null, null, null}, {null, null, null, null}};

    private int numCols = 4;

    public MooreTableUI() {
        super(DEFAULT_TABLE, COLUMN_LABELS);
        this.setTableWidth(500);
    }

    @Override
    public int getNumCols() {
        return this.numCols;
        
    }
}
