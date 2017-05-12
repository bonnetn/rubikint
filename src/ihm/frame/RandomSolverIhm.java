package ihm.frame;

import com.jogamp.opengl.util.FPSAnimator;
import com.sun.org.apache.xpath.internal.operations.Bool;
import rendering.OpenGLRenderer;
import rendering.enums.Axis;
import resolution.NoSolutionFound;
import rubikscube.RubiksCube;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * Created by florian on 05/03/17.
 */
public class RandomSolverIhm extends JLabel{

    ArrayList<Rotation> solution;

    JLabel afficheEtape;
    RubiksCube rubiksCube;
    private GLCanvas canvas;
    OpenGLRenderer renderer;
    JButton retAccueil = new JButton();
    Boolean isSolved = false;
    JButton generate = new JButton();
    JButton solve = new JButton();
    int indice = 0;
    int max;
    boolean isclockwise;


    public RandomSolverIhm(){
        final ImageIcon background = new ImageIcon("img/randomSolver/randomSolver.png");
        setIcon(background);
        //setLayout(new GridBagLayout());
        //GridBagConstraints gbc = new GridBagConstraints();
        setLayout(null);




        //JButton retAccueil = new JButton();
        Icon i = new ImageIcon("img/randomSolver/randomSolver_accueil.png");
        Icon i_2 = new ImageIcon("img/randomSolver/randomSolver_accueil2.png");
        Icon i_3 = new ImageIcon("img/randomSolver/randomSolver_accueil3.png");
        retAccueil.setIcon(i);
        retAccueil.setRolloverIcon(i_2);
        retAccueil.setPressedIcon(i_3);
        retAccueil.setBorder(null);
        retAccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton retour accueil OK");
            }
        });
        retAccueil.setBounds(100,500,241,55);

        Icon i3 = new ImageIcon("img/randomSolver/randomSolver_solve.png");
        Icon i3_2 = new ImageIcon("img/randomSolver/randomSolver_solve2.png");
        Icon i3_3 = new ImageIcon("img/randomSolver/randomSolver_solve3.png");
        solve.setIcon(i3);
        solve.setRolloverIcon(i3_2);
        solve.setPressedIcon(i3_3);
        solve.setBorder(null);
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.Solve();
                afficheEtape.setText("Found solution in "+renderer.getSizeSolution()+" moves");
                afficheEtape.setFont(new Font("Tahoma", Font.BOLD,25));
                afficheEtape.setForeground(Color.RED);
                afficheEtape.setBounds(50, 100, 450,200);
                afficheEtape.setVisible(true);
                add(afficheEtape);
            }
        });
        solve.setBounds(100,400,210,58);


        //setNextJLabel();
        afficheEtape = new JLabel();

        Icon i2 = new ImageIcon("img/randomSolver/randomSolver_generate.png");
        Icon i2_2 = new ImageIcon("img/randomSolver/randomSolver_generate2.png");
        Icon i2_3 = new ImageIcon("img/randomSolver/randomSolver_generate3.png");
        generate.setIcon(i2);
        generate.setRolloverIcon(i2_2);
        generate.setPressedIcon(i2_3);
        generate.setBorder(null);
        generate.setBounds(50,300,322,59);
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {renderer.Scramble();}
        });



        //JLabel rendu3D = new JLabel();
        //rendu3D.setLayout(null);
        //rendu3D.setBounds(600,50,600,600);
        renderer = new OpenGLRenderer();
        canvas = new GLCanvas();
        canvas.addGLEventListener(renderer);
        addKeyListener(new MyKeyListener(renderer));
        canvas.addKeyListener(new MyKeyListener(renderer));
        //canvas.addMouseListener((MouseListener) renderer);
        FPSAnimator animator = new FPSAnimator(canvas, 60);

        animator.start();
        //rendu3D.add(canvas);
        canvas.setBounds(600,50,600,600);

        add(retAccueil);
        add(canvas);
        add(generate);
        add(afficheEtape);
        add(solve);

    }

    public JButton getacc(){ return retAccueil;}
    public JButton getNext(){return generate;}


    final class MyKeyListener extends KeyAdapter
    {
        //OpenGLRenderer renderer = new OpenGLRenderer();
        boolean isUpPressed;
        boolean isDownPressed;
        boolean isLeftPressed;
        boolean isRightPressed;

        public MyKeyListener (OpenGLRenderer r) { renderer = r ;}

        void doThat()
        {
            if(isDownPressed)
                renderer.alphaX +=2;
            if(isUpPressed)
                renderer.alphaX -=2;
            if(isLeftPressed)
                renderer.alphaY -=2;
            if(isRightPressed)
                renderer.alphaZ +=2;
        }

        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    isUpPressed = true; break;
                case KeyEvent.VK_DOWN:
                    isDownPressed = true; break;
                case KeyEvent.VK_LEFT:
                    isLeftPressed = true; break;
                case KeyEvent.VK_RIGHT:
                    isRightPressed = true; break;
            }
            doThat();
        }
        public void keyReleased(KeyEvent e){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    isUpPressed = false;
                    break;
                case KeyEvent.VK_DOWN:
                    isDownPressed = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    isRightPressed = false;
                    break;
                case KeyEvent.VK_LEFT:
                    isLeftPressed = false;
                    break;
            }
        }
    }



    public OpenGLRenderer getRenderer(){return renderer;}
}

