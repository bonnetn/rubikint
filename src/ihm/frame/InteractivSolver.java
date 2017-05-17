package ihm.frame;

import ihm.frame.interactivsolver.InteractivSolver_Solver;
import ihm.frame.interactivsolver.InteractivSolver_acc;
import ihm.frame.interactivsolver.InteractivSolver_capture;
import rendering.OpenGLRenderer;
import rubikscube.enums.*;
import rubikscube.enums.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by florian on 07/04/17.
 */

    /*

                                                      ARBRE DE LAYOUT AVEC LES BOUTONS (MAJ = bouton  Text = Layout (scene))

                                                                              InteractivSolver (similaire à Fenetre)
                                                                                    |
                                                                                    |
                                                                         InteractivSolver_acc
                                                                          |         |         |
                                                                     CAPTURE      SOLVE     ACCUEIL
                                                                        |           |          |
                                                                    CaptureCube SolverIhm     Accueil
                                                                     |   |   |      |
                                                                    /    |    \     |
                                                                   /     |     \    |
                                                               CAPTURE  NEXT  PREV  |
                                                                   & RETURN (OK)    |
                                                                          |        /|\
                                                      InteractivSolver_acc     / | \
                                                                                 /  |  \
                                                                                /   |   \
                                                                             NEXT  PREV RETURN
                                                                                           |
                                                                                           InteractivSolver_acc

            PROTOCOLE DE CAPTURE POUR LE CUBE :
                               AFFICHER LES INSTRUCTION A LA CAPTURE :
                               ON AFFICHE LA FACE QU'ON VEUT QUE L'UTILISATEUR CAPTURE DANS UNE CONFIGURATION DONNE (EX : ON VEUT LA BLUE, ON PRECISE FACE DESSUS (EX:BLANC)
                               ET DESSOUS (JAUNE) QUAND CETTE FACE EST PRESENTE POUR EVITER AU MAXIMUM DE CONFIGURATION ERONE)
                               UN BOUTON CAPTURE QUI PREND LES COULEUR SUR LA CAM AU MOMENT OU ON APPUIE
                               UN BOUTON NEXT POUR LA PROCHAINE FACE A CAPTURER
                               UN BOUTON PREVIOUS POUR REPRENDRE

                               SET OPENGL RENDERER DANS SOLVER
                               ON RECUPERERE CES DONNEES DANS INTERATIVSOLVER MERE QUI PERMET DE SET TOUT CE QU IL FAUT A UN INSTANT T AU CHANGEMENT DE FENETRE.


     */
public class InteractivSolver extends JLabel {

    String[] listeScene = {"Accueil","Solver","Capture"};
    InteractivSolver_acc Accueil = new InteractivSolver_acc();
    InteractivSolver_Solver Solver = new InteractivSolver_Solver();
    InteractivSolver_capture Capture = new InteractivSolver_capture();

    JButton retAccueil = Accueil.getAccueil();

    OpenGLRenderer renderer;
    Color[][][] faceColor;

    Color[][][] testColor = new Color[6][3][3];


    public InteractivSolver(){
        CardLayout cl = new CardLayout();
        setLayout(cl);

        // -------------------------ACCUEIL-------------------------------------------
        JButton captureCube = Accueil.getCapture();
        JButton solveCube = Accueil.getSolve();

        captureCube.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Accueil.setVisible(false);
                Solver.setVisible(false);
                Capture.setVisible(true);
            }
        });

        solveCube.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.setRubiksCubecolor(faceColor);
                System.out.println(renderer.getRubiksPermutation());
                Accueil.setVisible(false);
                Capture.setVisible(false);
                Solver.setVisible(true);

            }
        });

        //-------------------CAPTURE--------------------

        JButton done = Capture.getDone();

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                faceColor = Capture.getFaceColor();
                Solver.setVisible(false);
                Capture.setVisible(false);
                Accueil.setVisible(true);
            }
        });

        //-------------------SOLVER------------------------

        renderer = Solver.getRenderer();
        JButton returne = Solver.getReturne();


        returne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Capture.setVisible(false);
                Solver.setVisible(false);
                Accueil.setVisible(true);
            }
        });

        add(Accueil,listeScene[0]);
        add(Solver,listeScene[1]);
        add(Capture,listeScene[2]);
        Accueil.setVisible(true);
        Solver.setVisible(false);
        Capture.setVisible(false);
        setVisible(true);

    }

    public JButton getRetAccueil(){return retAccueil;}





}
