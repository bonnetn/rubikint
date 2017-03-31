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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * Created by florian on 05/03/17.
 */
public class SolverIhm extends JLabel{

    Rotation[] testListMove;
    ArrayList<Rotation> solution;

    JLabel afficheEtape;
    RubiksCube rubiksCube;
    private GLCanvas canvas;
    OpenGLRenderer renderer;
    JButton retAccueil = new JButton();
    Boolean isSolved = false;
    JButton next = new JButton("GENERER");
    int indice = 0;
    int max;
    boolean isclockwise;


    public SolverIhm(){
        final ImageIcon background = new ImageIcon("solverRubikINT.png");
        setIcon(background);
        //setLayout(new GridBagLayout());
        //GridBagConstraints gbc = new GridBagConstraints();
        setLayout(null);




        //JButton retAccueil = new JButton();
        Icon i = new ImageIcon("solverAcc.png");
        retAccueil.setIcon(i);
        retAccueil.setBorder(null);
        retAccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton retour accueil OK");
            }
        });
        retAccueil.setBounds(50,500,180,90);


        setNextJLabel();
        afficheEtape.setFont(new Font("Tahoma", Font.BOLD,100));
        afficheEtape.setForeground(Color.RED);
        afficheEtape.setBounds(200,150,200,120);
        afficheEtape.setVisible(true);

        next.setBounds(50,300,190,100);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    afficheEtape.setVisible(false);

                    if (indice ==0 && isSolved == false){
                        isSolved = true;
                        resolution.Solver solver = new resolution.Solver();
                        renderer.setVisible(false);
                        renderer.randomGLMelange();
                        renderer.setVisible(true);
                        RubiksCube rubiksCube = renderer.getCube();
                        System.out.println("Solving... please wait...");

                        try{
                            solution = solver.solve(rubiksCube);
                            max = solution.size();
                        }catch (NoSolutionFound ee){
                            System.out.println("ERROR : No solution :(");
                            return;
                        }
                        System.out.println("Solved in " + solution.size() + " moves!");


                        //add(canvas);
                        next.setText("NEXT");
                    }else if (indice < max && isSolved == true ){
                        doNextRotation();
                        indice++;
                        setNextJLabel();
                    }else if (indice >=max){
                        indice =0;
                        afficheEtape = new JLabel("GG");
                        isSolved=false;
                        next.setText("GENERER");
                    }
                    afficheEtape.setFont(new Font("Tahoma", Font.BOLD,100));
                    afficheEtape.setForeground(Color.RED);
                    afficheEtape.setBounds(200, 150, 200,120);
                    afficheEtape.setVisible(true);
                    add(afficheEtape);


            }
        });



        //JLabel rendu3D = new JLabel();
        //rendu3D.setLayout(null);
        //rendu3D.setBounds(600,50,600,600);
        renderer = new OpenGLRenderer();
        canvas = new GLCanvas();
        canvas.addGLEventListener(renderer);
        addKeyListener(new MyKeyListener(renderer));
        canvas.addKeyListener(new MyKeyListener(renderer));
        canvas = new GLCanvas();
        canvas.addGLEventListener(renderer);
        FPSAnimator animator = new FPSAnimator(canvas, 60);

        animator.start();
        //rendu3D.add(canvas);
        canvas.setBounds(600,50,600,600);

        add(retAccueil);
        add(canvas);
        add(next);
        add(afficheEtape);




        /*
        //gbc.insets = new Insets(500,0,0,0);
        gbc.gridx=0;
        gbc.gridy=0;
        add(retAccueil);

        gbc.gridx=1;
        //gbc.insets=new Insets(0,500,0,0);
        add(rendu3D);

        //setVisible(true);
        */


    }

    public JButton getacc(){ return retAccueil;}
    public JButton getNext(){return next;}


    final class MyKeyListener extends KeyAdapter
    {
        OpenGLRenderer renderer = new OpenGLRenderer();
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

    public void setNextJLabel(){
        if (indice == max){
            afficheEtape = new JLabel("GG");
        }else{

             switch(solution.get(indice)) {
                case L:
                afficheEtape = new JLabel("L");
                break;
                case B:
                afficheEtape = new JLabel("B");
                break;
                case R:
                afficheEtape = new JLabel("R");
                break;
                case F:
                afficheEtape = new JLabel("F");
                break;
                case U:
                afficheEtape = new JLabel("U");
                break;
                case D:
                afficheEtape = new JLabel("D");
                break;
                case Li:
                afficheEtape = new JLabel("L'");
                break;
                case Bi:
                afficheEtape = new JLabel("B'");
                break;
                case Ri:
                afficheEtape = new JLabel("R'");
                break;
                case Fi:
                afficheEtape = new JLabel("F'");
                break;
                case Ui:
                afficheEtape = new JLabel("U'");
                break;
                case Di:
                afficheEtape = new JLabel("D'");
                break;
             }
        }
    }

    public void doNextRotation(){

        switch(solution.get(indice)) {
            case L:
                renderer.rotate(0, Axis.X,true);
                break;
            case B:
                renderer.rotate(2, Axis.Y,false);
                break;
            case R:
                renderer.rotate(2, Axis.X,false);
                break;
            case F:
                renderer.rotate(0, Axis.Y,true);
                break;
            case U:
                renderer.rotate(2, Axis.Z,false);
                break;
            case D:
                renderer.rotate(0, Axis.Z,true);
                break;
            case Li:
                renderer.rotate(0, Axis.X,false);
                break;
            case Bi:
                renderer.rotate(2, Axis.Y,true);
                break;
            case Ri:
                renderer.rotate(2, Axis.X,true);
                break;
            case Fi:
                renderer.rotate(0, Axis.Y,false);
                break;
            case Ui:
                renderer.rotate(2, Axis.Z,true);
                break;
            case Di:
                renderer.rotate(0, Axis.Z,false);
                break;
        }

    }

    public OpenGLRenderer getRenderer(){return renderer;}
}

