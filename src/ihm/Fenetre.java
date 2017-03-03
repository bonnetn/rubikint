package ihm;

import java.awt.*;
import java.awt.event.*;

import com.jogamp.opengl.util.FPSAnimator;
import ihm.frame.Accueil;
import ihm.frame.SolvingCube;
import rendering.OpenGLRenderer;
import rubikscube.RubiksCube;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;

/**
 * Created by florian on 29/01/17.
 */

public class Fenetre{

    public Fenetre() { // constructeur pour etablir les settings de la fenetre

        JFrame frame = new JFrame();

        frame.setTitle("Rubik'INT");
        frame.setSize(1280, 750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        //SolvingCube sCube = new SolvingCube();
        Accueil acc = new Accueil();
        SolvingCube sCube = new SolvingCube();
        sCube.setLocation(0,20);
        frame.add("Center",sCube);
        frame.add("Center",acc);

        acc.setVisible(true);
        sCube.setVisible(false);
        frame.setVisible(true);

        //JButton bouton = acc.getBouton();
        acc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acc.setVisible(false);
                sCube.setVisible(true);
            }
        });
        JButton boutonCube = sCube.getBouton();
        boutonCube.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sCube.setVisible(false);
                acc.setVisible(true);
            }
        });


    }

}