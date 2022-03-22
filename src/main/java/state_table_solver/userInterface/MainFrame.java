package state_table_solver.userInterface;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.*;

public class MainFrame extends JFrame {
    private ToolBar toolBar;
    private StateTableUI stateTableUI;
    private JPanel mainPanel;

    public MainFrame() {
        super("State Table Solver");
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);


        this.toolBar = new ToolBar();
        this.stateTableUI = new MooreTableUI();
        this.mainPanel = new JPanel();

        
        
        JScrollPane jPane = new JScrollPane(this.stateTableUI);
        this.stateTableUI.setPreferredScrollableViewportSize(this.stateTableUI.getPreferredSize());
        // Dimension paneDim = jPane.getPreferredSize();
        // Dimension tableDim = this.stateTableUI.getPreferredSize();
        // paneDim.width = 600;
        // paneDim.height = tableDim.getHeight();
        // jPane.setPreferredSize(paneDim);

        mainPanel.add(jPane);

        this.getContentPane().add(this.toolBar, BorderLayout.PAGE_START);
        this.getContentPane().add(this.mainPanel, BorderLayout.CENTER);
        
        // this.pack();
        this.setVisible(true);
    }

    public int renderTableSelection() {
        Object[] options = {
            "Moore",
            "Mealy"
        };
        int response = JOptionPane.showOptionDialog(
            this,
            "Select the state table type",
            "Create a new project",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[1]
        );
        
        return response;
    }

    public void renderDerivedSoP(java.util.List<String> booleanEquationStrings) {
        JDialog d1 = new JDialog(this, "Minimized Boolean Equations");
        d1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        d1.setLayout(new BoxLayout(d1.getContentPane(), BoxLayout.PAGE_AXIS));

        int padding = 10;
        d1.getRootPane().setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
 
        for(String boolEqnString : booleanEquationStrings) {
            JLabel l = new JLabel(boolEqnString);
            d1.add(l);
            d1.add(Box.createVerticalStrut(50));
        }

        int dialogSize = 400;
        d1.setLocationRelativeTo(this);
        Double centeredX = d1.getLocation().getX() - (dialogSize / 2);
        Double centeredY = d1.getLocation().getY() - (dialogSize / 2);
        d1.setLocation(centeredX.intValue(), centeredY.intValue());
        
        d1.setSize(dialogSize, dialogSize);
        d1.setVisible(true);
    }

    public ToolBarButton getToolBarButton(int i) {
        return this.toolBar.getButton(i);
    }
}
