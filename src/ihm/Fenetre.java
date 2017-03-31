package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ihm.frame.Accueil;
import ihm.frame.SolverIhm;

import javax.swing.*;

/**
 * Created by florian on 29/01/17.
 */

public class Fenetre{

    String[] listeScene = {"Accueil","SolverIhm","Capture"};

    public Fenetre() { // constructeur pour etablir les settings de la fenetre
        CardLayout cl = new CardLayout();
        JFrame frame = new JFrame();

        frame.setTitle("Rubik'INT");
        frame.setSize(1280, 755); //tester et aprouve pour BACKGROUND VISIBLE
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Accueil accueil = new Accueil();
        SolverIhm solver = new SolverIhm();

        JButton accSolver = accueil.getCapture();
        accSolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accueil.setVisible(false);
                solver.setVisible(true);
            }
        });

        JButton retAccueil = solver.getacc();
        retAccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solver.setVisible(false);
                accueil.setVisible(true);
            }
        });


        frame.setLayout(cl); //utiliser ici un CardLayout
        frame.add(accueil,listeScene[0]);
        solver.setVisible(true);
        frame.add(solver,listeScene[1]);
        frame.setVisible(true);
    }

}