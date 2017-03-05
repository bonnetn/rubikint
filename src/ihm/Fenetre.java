package ihm;

import java.awt.*;
import java.awt.event.*;

import ihm.frame.Accueil;
import ihm.frame.SolvingCube;
import ihm.frame.accueil.AccBackground;
import ihm.frame.accueil.AccCapture;
import ihm.frame.accueil.AccQuit;
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

        Accueil accueil = new Accueil();
        frame.setLayout(new BorderLayout());
        frame.setContentPane(accueil);
        frame.setVisible(true);
    }

}