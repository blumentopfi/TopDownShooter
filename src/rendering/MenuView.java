package rendering;

import framework.rendering.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Eike Nils on 27.04.2017.
 */
public class MenuView extends JPanel implements ActionListener {
    public Camera camera;
    public JButton startB;
    public MenuView(Camera mainC) {
        camera = mainC;
        startB = new JButton("Start");
        setLayout(new BorderLayout());
        add(startB, BorderLayout.CENTER);
        System.out.println("Menu Created!");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource() == startB) {
         //camera.startGameView();
     }
    }
}
