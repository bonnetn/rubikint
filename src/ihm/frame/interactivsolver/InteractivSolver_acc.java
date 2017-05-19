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
 *
 * Cette scène permet de passer par les différentes scène d'InteractivSolver. Elle permet donc de naviguer entre InteractivSolver_capture et InteractiveSolver_solver
 * Elle permet également de retrouner sur l'Accueil du programme par le bouton retAccueil
 */
public class InteractivSolver_acc extends JLabel {



    ArrayList<Rotation> solution;
    JLabel afficheEtape;
    RubiksCube rubiksCube;
    private GLCanvas canvas;
    OpenGLRenderer renderer = new OpenGLRenderer();
    JButton retAccueil = new JButton();
    Boolean isSolved = false;
    JButton capture = new JButton();
    JButton solve = new JButton();
    JLabel accueilText = new JLabel();
    int indice = 0;
    int max;

    public InteractivSolver_acc(){
        final ImageIcon background = new ImageIcon("img/InteractivSolver/interactiv_solver.png");
        setIcon(background);
        setLayout(null);


        Icon i = new ImageIcon("img/InteractivSolver/Accueil/randomSolver_accueil.png");
        Icon i_2 = new ImageIcon("img/InteractivSolver/Accueil/randomSolver_accueil2.png");
        Icon i_3 = new ImageIcon("img/InteractivSolver/Accueil/randomSolver_accueil3.png");
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



        Icon i2 = new ImageIcon("img/InteractivSolver/Accueil/capture.png");
        Icon i2_2 = new ImageIcon("img/InteractivSolver/Accueil/capture2.png");
        Icon i2_3 = new ImageIcon("img/InteractivSolver/Accueil/capture3.png");
        capture.setIcon(i2);
        capture.setRolloverIcon(i2_2);
        capture.setPressedIcon(i2_3);
        capture.setBorder(null);
        capture.setBounds(50,300,292,58);
        capture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.out.println("Bouton Capture OK");}
        });

        Icon i3 = new ImageIcon("img/InteractivSolver/Accueil/randomSolver_solve.png");
        Icon i3_2 = new ImageIcon("img/InteractivSolver/Accueil/randomSolver_solve2.png");
        Icon i3_3 = new ImageIcon("img/InteractivSolver/Accueil/randomSolver_solve3.png");
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

        accueilText.setText("<html>Bonjour et bienvenue dans notre solveur de Rubik's Cube de Rubik'INT." +
                "<br>Vous ne savez pas comment le résoudre ou bien,comme notre tutrice,vous êtes en galère pour la dernière face ? (désolé Mme GUERMOUCHE, il me fallait un exemple pour que l'utilisateur se sente moins seul)"+
                "<br>"+
                "<br>J'ai une solution très simple, avec ma méthode, je vais vous montrer comment résoudre votre Rubik's Cube en seulement 5 minutes et 2 étapes. (normalement c'est le moment où je demande votre numéro de carte bleue pour vérifier que vous êtes majeur mais on m'a dit que j'avais pas le droit )= )"+
                "<br>"+
                "<br>Premièrement, entre dans le menu de capture et suit clairement les instruction pour que le programme puisse comprendre la configuration de ton Rubik's Cube" +
                "<br>Dans un second temps (et là c'est la dernière étape, il y en a vraiment que deux, je vous ai pas mentis),revient sur l'accueil du solveur (bouton 'done') et entre dans le menu 'solve' le tour est joué"+
                "<br>Vous n'avez plus qu'a suivre les étapes de résolution"+
                "<br>"+
                "<br>"+
                "<br>Ah oui j'oubliais, j'imagine que vu que vous savez pas résoudre le Rubik's Cube, vous n'êtes pas à l'aise avec sa notation, donc je vous fais une petit tuto / rappel"+
                "<br>  - Les face centrales sont fixes ! faut bien commentcer par le début."+
                "<br>  - La face Rouge : Front F ;                      La face Bleue : Right R;"+
                "<br>  - La face Orange : Back B ;                      La face Verte : Left L;"+
                "<br>  - La face Blanc : Up U;                          La face Jaune : Down D"+
                "<br>"+
                "<br> Il ne me reste plus qu'a dire : GL & HF (Good Luck & Have Fun pour les novices ;) )</html>");
        accueilText.setFont(new Font("Tahoma",Font.BOLD,18));
        accueilText.setForeground(java.awt.Color.RED);
        accueilText.setBounds(400,50,800,600);
        accueilText.setVisible(true);
        add(accueilText);



    }

    public JButton getAccueil(){return retAccueil;}
    public JButton getCapture(){ return capture;}
    public JButton getSolve(){return solve;}

    public OpenGLRenderer getRenderer(){return renderer;}
}
