package state_table_solver;

import state_table_solver.userInterface.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p> EventListener is a class which listens for ui events such as button presses
 * and performs the associated action on the controller.
 * 
 * @author Jacob Head
 */

public class EventListener {
    // Controller controller;
    // MainFrame view;

    public EventListener(Controller c) {
        Controller controller = c;
        MainFrame view = controller.mainFrame();

        view.getToolBarButton(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createNewProject();
            }
        });

        view.getToolBarButton(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deriveSoP();
            }
        });
    }

    // public Controller controller() {
    //     return this.controller;
    // }

    // public MainFrame view() {
    //     return this.view;
    // }

}
