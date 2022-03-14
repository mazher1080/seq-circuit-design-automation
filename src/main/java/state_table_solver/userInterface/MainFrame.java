package state_table_solver.userInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.*;

public class MainFrame extends JFrame {
    private ToolBar toolBar;
    private StateTableUI stateTableUI;
    private JPanel workingArea;

    public MainFrame() {
        super("test");
        this.setSize(1000, 750);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        this.toolBar = new ToolBar();
        this.stateTableUI = new MooreTableUI();
        this.workingArea = new JPanel();

        
        
        JScrollPane jPane = new JScrollPane(this.stateTableUI);
        this.stateTableUI.setPreferredScrollableViewportSize(this.stateTableUI.getPreferredSize());
        // Dimension paneDim = jPane.getPreferredSize();
        // Dimension tableDim = this.stateTableUI.getPreferredSize();
        // paneDim.width = 600;
        // paneDim.height = tableDim.getHeight();
        // jPane.setPreferredSize(paneDim);

        workingArea.add(jPane);

        this.getContentPane().add(this.toolBar, BorderLayout.PAGE_START);
        this.getContentPane().add(this.workingArea, BorderLayout.CENTER);
        
        // this.pack();
        this.setVisible(true);
    }
}
