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
    JButton accRandom = new JButton();
    JButton accQuit = new JButton();


    public Accueil() {

        final ImageIcon background = new ImageIcon("img/Accueil.png");
        setIcon(background);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

       // JButton accCapture = new JButton();
        Icon i = new ImageIcon("img/SolveYourCube.png");
        Icon i_hover = new ImageIcon("img/SolveYourCube2.png");
        Icon i_click = new ImageIcon("img/SolveYourCube3.png");
        accCapture.setIcon(i);
        accCapture.setRolloverIcon(i_hover);
        accCapture.setPressedIcon(i_click);
        accCapture.setBorder(null);
        accCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Capture OK");
            }
        });

        Icon i2 = new ImageIcon("img/SolveRandomCube.png");
        Icon i2_hover = new ImageIcon("img/SolveRandomCube2.png");
        Icon i2_click = new ImageIcon("img/SolveRandomCube3.png");
        accRandom.setRolloverIcon(i2_hover);
        accRandom.setPressedIcon(i2_click);
        accRandom.setIcon(i2);
        accRandom.setBorder(null);
        accRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Random OK");

            }
        });

        Icon i3 = new ImageIcon("img/Quit.png");
        Icon i3_hover = new ImageIcon("img/Quit2.png");
        Icon i3_click = new ImageIcon("img/Quit3.png");
        accQuit.setRolloverIcon(i3_hover);
        accQuit.setPressedIcon(i3_click);
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
        gbc.gridy=2;
        gbc.gridwidth=2;
        gbc.gridheight=2;
        add(accCapture,gbc);
       // gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(120,0,50,0);
        gbc.gridy=4;
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