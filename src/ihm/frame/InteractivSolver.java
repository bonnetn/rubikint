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

    /* TO DO :
    Faire une interface similaire à RandomSolverIhm mais avec le bouton CAPTURE cette fois ci.
    Mais là il faut pouvoir afficher la solution étape par étape, donc il faut un bouton next/ previous.
    IDEAL : BOUTON CAPTURE qui ouvre interface à la webcam pour capture.
                   UNE FOIS LE BOUTON CAPTURE APPUYE, BACKGROUND PAREIL MAIS BOUTON STOP CAPTURE, A DROITE LA WEBCAM POUR CAPTURER, ET EVENTUELLEMENT UN DESSIN DU PLAN DU CUBE CAPTURE
                       SI LA CAPTURE SE FAIT PAS AUTOMATIQUEMENT PAR FACE, FAIRE UN BOUTON CAPTURER LA FACE, ET AFFICHER LA FACE A CAPTURER DE SORTE QU ELLE SOIT DANS LE BON SENS
                   BOUTON STOP CAPTURE FERME LA WEBCAM ET AFFICHE LE RENDERER DU CUBE PRECEDEMMENT CAPTURE.
                   ALORS ON REVIENT BOUTON CAPTURE, CETTE FOIS CI LE BOUTON SOLVE PEUT ETRE APPUYE
                   SI ON APPUIE SUR SOLVE, DE LA MEME FACON QUE CAPTURE, ON AFFICHE LA PREMIERE ETAPE DE RESOLUTION, LE NB DE COUP TOTAL, LE NOMBRE RESTANT, UN BOUTON NEXT ET PREVIOUS.
                   LE BOUTON RETURN qui RESET TOUT. ?

            IDEE : CARDLAYOUT QUI AFFICHE L UN OU L AUTRE EN FONCTION DU BOUTON APPUYE A LA FACON DE LA CLASSE FENETRE.

                                                      ARBRE DE LAYOUT AVEC LES BOUTONS (MAJ = bouton  Text = Layout (fenetre))

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

            POUR CAPTURE CUBE :
                      FAIRE UN AFFICHEUR D'INSTRUCTION DE LA CAPTURE :
                               ON AFFICHE LA FACE QU'ON VEUT QUE L'UTILISATEUR CAPTURE DANS UNE CONFIGURATION DONNE (EX : ON VEUT LA BLUE, ON PRECISE FACE DESSUS (EX:BLANC)
                               ET DESSOUS (JAUNE) QUAND CETTE FACE EST PRESENTE POUR EVITER AU MAXIMUM DE CONFIGURATION ERONE)
                               UN BOUTON CAPTURE QUI PREND LES COULEUR SUR LA CAM AU MOMENT OU ON APPUIE (VOIR SI CELA PEUT ETRE FAIT AUTOMATIQUEMENT)
                               UN BOUTON NEXT POUR LA PROCHAINE FACE A CAPTURER
                               UN BOUTON PREVIOUS POUR REPRENDRE
                               VOIR SI TOUT CA PEUT ETRE FAIT AUTOMATIQUEMENT MAIS PLUS SIMPLE AVEC BOUTON


                               SET OPENGL RENDERER DANS SOLVER
                               SET CONFIGURATION DE LA CAPTURE DANS CAPTURE
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
