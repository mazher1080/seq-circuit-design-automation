package state_table_solver.userInterface;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class Header extends JPanel {
    private String headerLabel;
    private String headerName;
    private JLabel labelComp;
    private JLabel nameComp;

    public Header(String headerLabel, String headerName, int height) {
        super();
        this.headerLabel = headerLabel;
        this.headerName = headerName;
        this.labelComp = new JLabel(this.headerLabel);
        this.labelComp.setFont(new Font("Calibri", Font.BOLD, 16));
        this.nameComp = new JLabel(this.headerName);
        this.nameComp.setFont(new Font("Calibri", Font.ITALIC, 16));

        // LayoutManager gridLay = new GridLayout(0, 1);
        // this.setLayout(gridLay);
        this.add(this.labelComp);
        this.add(this.nameComp);

        this.setVisible(true);
    }

    public void setLabelText(String headerLabel) {
        this.headerLabel = headerLabel;
        this.labelComp.setText(headerLabel);
    }

    public void setNameText(String headerName) {
        this.headerName = headerName;
        this.nameComp.setText(headerName);
    }
}
