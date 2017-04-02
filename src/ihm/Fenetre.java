package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ihm.frame.Accueil;
import ihm.frame.RandomSolverIhm;

import javax.swing.*;

/**
 * Created by florian on 29/01/17.
 */

public class Fenetre{

    String[] listeScene = {"Accueil","RandomSolverIhm","Capture"};

    public Fenetre() { // constructeur pour etablir les settings de la fenetre
        CardLayout cl = new CardLayout();
        JFrame frame = new JFrame();

        frame.setTitle("Rubik'INT");
        frame.setSize(1280, 755); //tester et aprouve pour BACKGROUND VISIBLE
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Accueil accueil = new Accueil();
        RandomSolverIhm randomSolverIhm = new RandomSolverIhm();

        JButton accSolver = accueil.getRandom();
        accSolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accueil.setVisible(false);
                randomSolverIhm.setVisible(true);
            }
        });

        JButton retAccueil = randomSolverIhm.getacc();
        retAccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomSolverIhm.setVisible(false);
                accueil.setVisible(true);
            }
        });


        frame.setLayout(cl); //utiliser ici un CardLayout
        frame.add(accueil,listeScene[0]);
        randomSolverIhm.setVisible(true);
        frame.add(randomSolverIhm,listeScene[1]);
        frame.setVisible(true);
    }

}