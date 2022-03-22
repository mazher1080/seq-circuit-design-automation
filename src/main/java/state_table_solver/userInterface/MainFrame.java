package state_table_solver.userInterface;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.*;

/**
 * <p> The MainFrame is the view of the application. It renders a GUI for
 * the application using javax.swing.
 * @see javax.swing
 * 
 * @author Jacob Head
 */

public class MainFrame extends JFrame {
    private ToolBar toolBar;
    private StateTableUI stateTableUI;
    private JPanel mainPanel;

    /**
     * Class constructor. Initializes the main frame with a toolbar
     * and state table.
     */
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

        mainPanel.add(jPane);

        this.getContentPane().add(this.toolBar, BorderLayout.PAGE_START);
        this.getContentPane().add(this.mainPanel, BorderLayout.CENTER);
        
        this.setVisible(true);
    }

    /**
     * Renders a table type selection. (Moore or Mealy).
     * 
     * @return An int representing which selection was chosen. The Moore selection is 0.
     * The mealy selection is 1.
     */
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

    /**
     * Renders a dialog box with derived minimized sum of product equations from the
     * current state table.
     * 
     * @param booleanEquationStrings The list of strings to display as the boolean output equations.
     */
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

    /**
     * Renders a file chooser component. Returns the selected filepath.
     * 
     * @return The filepath selected from file picker.
     */
    public String renderFileChooser() {
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filePath = fc.getSelectedFile().getAbsolutePath();
            System.out.println(filePath);
            return filePath;
        } else {
            return null;
        }
    }

    /**
     * Renders a file saver component. Returns the selected filepath.
     * 
     * @return The filepath selected from file picker.
     */
    public String renderFileSaver() {
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filePath = fc.getSelectedFile().getAbsolutePath();
            System.out.println(filePath);
            return filePath;
        } else {
            return null;
        }
    }

    /**
     * Returns the tool bar button at the specified index.
     * @see ToolBarButton
     * 
     * @param i The index of the toolbar button.
     * @return The tool bar button at the specified index.
     */
    public ToolBarButton getToolBarButton(int i) {
        return this.toolBar.getButton(i);
    }
}
