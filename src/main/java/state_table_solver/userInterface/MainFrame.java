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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import state_table_solver.AppData;

import javax.swing.JOptionPane;
import java.awt.*;

/**
 * <p> The MainFrame is the view of the application. It renders a GUI for
 * the application using javax.swing.
 * @see javax.swing
 * 
 * @author Jacob Head
 * @author Muneeb Azher
 */

public class MainFrame extends JFrame {
    private ToolBar toolBar;
    private StateTableUI stateTableUI;
    private JPanel centerPanel;
    private JPanel tableContainer = new JPanel();
    private Header projHeader;
    private JLabel addStateLabel = new JLabel("New state id:");
    private JTextField textField = new JTextField();
    private Object textInput = new Object[] {addStateLabel, textField};
    private Object[] addStateOptions = new Object[] {"Add", "Cancel"};

    /**
     * Class constructor. Initializes the main frame with a toolbar
     * and state table.
     */
    public MainFrame(AppData appData) {
        super("State Table Solver");
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.toolBar = new ToolBar();
        this.toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.projHeader = new Header("", "", 0);

        this.centerPanel = new JPanel();
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        this.add(this.toolBar, BorderLayout.PAGE_START);
        this.add(this.centerPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    /**
     * Renders a header with a given label and name.
     * 
     */
    public void renderHeader(String label, String name) {
        this.projHeader.setLabelText(label);
        this.projHeader.setNameText(name);
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
        JDialog dialog1 = new JDialog(this, "Minimized Boolean Equations");
        dialog1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JScrollPane popupScrollView = new JScrollPane();
        JPanel popupView = new JPanel();
        
        popupView.setLayout(new BoxLayout(popupView, BoxLayout.PAGE_AXIS));

        int padding = 10;
        popupView.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
 
        for(String boolEqnString : booleanEquationStrings) {
            JLabel l = new JLabel(boolEqnString);
            popupView.add(l);
            popupView.add(Box.createVerticalStrut(50));
        }
        popupScrollView.setViewportView(popupView);
        dialog1.add(popupScrollView);

        int dialogSize = 400;
        dialog1.setLocationRelativeTo(this);
        Double centeredX = dialog1.getLocation().getX() - (dialogSize / 2);
        Double centeredY = dialog1.getLocation().getY() - (dialogSize / 2);
        dialog1.setLocation(centeredX.intValue(), centeredY.intValue());
        
        dialog1.setSize(dialogSize, dialogSize);
        dialog1.setVisible(true);
    }

    /**
     * Renders a file chooser component. Returns the selected filepath.
     * 
     * @return The filepath selected from file picker.
     */
    public String renderFileChooser() {
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filePath = fc.getSelectedFile().getAbsolutePath();
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
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filePath = fc.getSelectedFile().getAbsolutePath();
            return filePath;
        } else {
            return null;
        }
    }

    public void renderCenterPanel() {
        JTable stateTable = getStateTableUI().getJTable();
        JScrollPane jPane = new JScrollPane(stateTable);
        tableContainer.removeAll();
        centerPanel.removeAll();
        this.tableContainer.add(jPane);
        centerPanel.add(this.projHeader, BorderLayout.PAGE_START);
        centerPanel.add(this.tableContainer);
        centerPanel.repaint();
        centerPanel.validate();
        this.getContentPane().add(this.centerPanel);
    }

    public int renderNewStateChooser() {
        this.textField.setText("");
        int response = JOptionPane.showOptionDialog(
            this,
            this.textInput,
            "Add State",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            this.addStateOptions,
            null
        );
        return response;
    }

    public void renderInfoAlert(String str){
        JOptionPane.showMessageDialog(this, str,
        "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void renderErrorAlert(String errorMessage){
        JOptionPane.showMessageDialog(this, "ERROR: " + errorMessage,
        "Error", JOptionPane.ERROR_MESSAGE);
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

    public StateTableUI getStateTableUI() {
        return stateTableUI;
    }

    public String getNewStateResult() {
        return textField.getText();
    }

    public void setStateTableUI(StateTableUI stateTableUI) {
        this.stateTableUI = stateTableUI;
    }
}
