package ihm;

import java.awt.*;
import java.awt.event.*;

import ihm.frame.Accueil;
import ihm.frame.SolvingCube;
import ihm.frame.accueil.AccBackground;
import ihm.frame.accueil.AccRandomCube;

import javax.swing.*;

/**
 * Created by florian on 29/01/17.
 */

public class Fenetre{

    public Fenetre() { // constructeur pour etablir les settings de la fenetre

        JFrame frame = new JFrame();

        frame.setTitle("Rubik'INT");
        frame.setSize(1280, 755); //tester et aprouve pour BACKGROUND VISIBLE
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setLayout(new GridLayout());
        frame.setLayout(new GridLayout());

        JLayeredPane accueil = new JLayeredPane();
        accueil.setLayout(null);
        AccBackground accBackground = new AccBackground();
        AccRandomCube accRandomCube = new AccRandomCube();


        accueil.add(accBackground, 1);
        accRandomCube.setPreferredSize(new Dimension(400,33));
        accueil.add(accRandomCube, 2);
        accBackground.setVisible(true);
        accRandomCube.setVisible(true);
        frame.add(accueil);


        //SolvingCube sCube = new SolvingCube();
        //AccBackground background = new AccBackground();
        //AccRandomCube randomCube = new AccRandomCube();

        //randomCube.setLocation(600,200);
        //sCube.setLocation(0,20);
        //frame.add(sCube);
        //frame.add(randomCube);
        //frame.add(background);
        //frame.add("Center",randomCube);


        //sCube.setVisible(false);
        //background.setVisible(true);
        //randomCube.setVisible(true);
        frame.setVisible(true);

/*
        JButton boutonCube = sCube.getBouton();
        boutonCube.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sCube.setVisible(false);
                //acc.setVisible(true);
            }
        });

        randomCube.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomCube.setVisible(false);
                sCube.setVisible(true);
            }
        });
*/

    }

}