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

    /**
     * Class constructor. Initilizes all necessary event listeners to communicate
     * with the controller.
     * @see Controller
     * 
     * @param c The controller to use.
     */
    public EventListener(Controller c) {
        Controller controller = c;
        MainFrame view = controller.mainFrame();

        /**
         * Connecting the New Project toolbar button's action listener 
         * to controller's createNewProject method.
         */
        view.getToolBarButton(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createNewProject();
            }
        });

        /**
         * Connecting the Open toolbar button's action listener 
         * to controller's openProjectFile method.
         */
        view.getToolBarButton(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.openProjectFile();
            }
        });

        /**
         * Connecting the Save toolbar button's action listener 
         * to controller's saveProjectFile method.
         */
        view.getToolBarButton(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.saveProjectFile();
            }
        });

        /**
         * Connecting the Save As toolbar button's action listener 
         * to controller's saveAsProjectFile method.
         */
        view.getToolBarButton(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.saveAsProjectFile();
            }
        });
        
        /**
         * Connecting the Derive toolbar button's action listener 
         * to controller's deriveSoP method.
         */
        view.getToolBarButton(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deriveSoP();
            }
        });
    }

}
