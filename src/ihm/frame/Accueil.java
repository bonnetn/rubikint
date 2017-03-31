package ihm.frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rendering.OpenGLRenderer;
import resolution.Solver;
import rubikscube.RubiksCube;

/**
 * Created by florian on 02/03/17.
 */
public class Accueil extends JLabel{

    JButton accCapture = new JButton();
    JButton accRandom;
    JButton accQuit;


    public Accueil() {

        final ImageIcon background = new ImageIcon("AccueilBackground.png");
        setIcon(background);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

       // JButton accCapture = new JButton();
        Icon i = new ImageIcon("AccueilCaptureCube.png");
        accCapture.setIcon(i);
        accCapture.setBorder(null);
        accCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Capture OK");
            }
        });

        JButton accRandom = new JButton();
        Icon i2 = new ImageIcon("AccueilSolveRandom.png");
        accRandom.setIcon(i2);
        //setOpaque(false);
        accRandom.setBorder(null);
        accRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Random OK");

            }
        });

        JButton accQuit = new JButton();
        Icon i3 = new ImageIcon("AccueilQuit.png");
        accQuit.setIcon(i3);
        //setOpaque(false);
        accQuit.setBorder(null);
        accQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Quit OK");
                System.exit(0);
            }
        });

        gbc.insets = new Insets(100,0,0,0);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.gridheight=2;
        add(accCapture,gbc);
       // gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(120,0,50,0);
        gbc.gridy=3;
        add(accRandom,gbc);
        gbc.insets = new Insets(75,0,0,0);
        gbc.gridy=6;
        add(accQuit,gbc);

        setVisible(true);


    }

    public JButton getCapture(){return accCapture;}
    public JButton getRandom(){return accRandom;}
    public JButton getQuit(){return accQuit;}


}