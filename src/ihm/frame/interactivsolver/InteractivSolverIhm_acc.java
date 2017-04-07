package ihm.frame.interactivsolver;

import rendering.OpenGLRenderer;
import rubikscube.RubiksCube;
import rubikscube.enums.Rotation;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by florian on 07/04/17.
 */
public class InteractivSolverIhm_acc extends JLabel {



    ArrayList<Rotation> solution;

    JLabel afficheEtape;
    RubiksCube rubiksCube;
    private GLCanvas canvas;
    OpenGLRenderer renderer;
    JButton retAccueil = new JButton();
    Boolean isSolved = false;
    JButton capture = new JButton();
    JButton solve = new JButton();
    int indice = 0;
    int max;

    public InteractivSolverIhm_acc(){
        final ImageIcon background = new ImageIcon("img/interactiv_solver.png");
        setIcon(background);
        setLayout(null);


        Icon i = new ImageIcon("img/randomSolver_accueil.png");
        Icon i_2 = new ImageIcon("img/randomSolver_accueil2.png");
        Icon i_3 = new ImageIcon("img/randomSolver_accueil3.png");
        retAccueil.setIcon(i);
        retAccueil.setRolloverIcon(i_2);
        retAccueil.setPressedIcon(i_3);
        retAccueil.setBorder(null);
        retAccueil.setBounds(100,500,241,55);
        retAccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton retour accueil OK");
            }
        });



        Icon i2 = new ImageIcon("img/capture.png");
        Icon i2_2 = new ImageIcon("img/capture2.png");
        Icon i2_3 = new ImageIcon("img/capture3.png");
        capture.setIcon(i2);
        capture.setRolloverIcon(i2_2);
        capture.setPressedIcon(i2_3);
        capture.setBorder(null);
        capture.setBounds(50,300,322,59);
        capture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.out.println("Bouton Capture OK");}
        });

        Icon i3 = new ImageIcon("img/randomSolver_solve.png");
        Icon i3_2 = new ImageIcon("img/randomSolver_solve2.png");
        Icon i3_3 = new ImageIcon("img/randomSolver_solve3.png");
        solve.setIcon(i3);
        solve.setRolloverIcon(i3_2);
        solve.setPressedIcon(i3_3);
        solve.setBorder(null);
        solve.setBounds(100,400,210,58);
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.out.println("Bouton Solve OK");}
        });

        add(retAccueil);
        add(capture);
        add(solve);
    }
}
