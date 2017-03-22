package ihm.frame;

import com.jogamp.opengl.util.FPSAnimator;
import rendering.OpenGLRenderer;
import rubikscube.RubiksCube;
import rubikscube.enums.Rotation;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Created by florian on 05/03/17.
 */
public class Solver extends JLabel{

    Rotation[] testListMove = new Rotation[12];

    JLabel afficheEtape;
    RubiksCube rubiksCube;
    private GLCanvas canvas;
    OpenGLRenderer renderer;
    JButton retAccueil = new JButton();
    JButton next = new JButton("NEXT");
    int indice = 0;
    int max = testListMove.length;
    boolean isclockwise;


    public Solver(){
        setTestListMove();

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
                    if (indice < max) {
                        doNextRotation();
                        indice++;
                        setNextJLabel();
                    }else if (indice >= max){
                        afficheEtape=new JLabel("GG");
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
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        addKeyListener(new MyKeyListener(renderer));
        canvas.addKeyListener(new MyKeyListener(renderer));
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

    public void setTestListMove(){
        testListMove[0] = Rotation.L;
        testListMove[1] = Rotation.Li;
        testListMove[2] = Rotation.R;
        testListMove[3] = Rotation.Ri;
        testListMove[4] = Rotation.B;
        testListMove[5] = Rotation.Bi;
        testListMove[6] = Rotation.F;
        testListMove[7] = Rotation.Fi;
        testListMove[8] = Rotation.U;
        testListMove[9] = Rotation.Ui;
        testListMove[10] = Rotation.D;
        testListMove[11] = Rotation.Di;

    }

    public void setNextJLabel(){
        if (indice == max){
            afficheEtape = new JLabel("GG");
        }else{

             switch(testListMove[indice].getValue()) {
                case 0:
                afficheEtape = new JLabel("L");
                break;
                case 2:
                afficheEtape = new JLabel("B");
                break;
                case 4:
                afficheEtape = new JLabel("R");
                break;
                case 6:
                afficheEtape = new JLabel("F");
                break;
                case 8:
                afficheEtape = new JLabel("U");
                break;
                case 10:
                afficheEtape = new JLabel("D");
                break;
                case 1:
                afficheEtape = new JLabel("L'");
                break;
                case 3:
                afficheEtape = new JLabel("B'");
                break;
                case 5:
                afficheEtape = new JLabel("R'");
                break;
                case 7:
                afficheEtape = new JLabel("F'");
                break;
                case 9:
                afficheEtape = new JLabel("U'");
                break;
                case 11:
                afficheEtape = new JLabel("D'");
                break;
             }
        }
    }

    public void doNextRotation(){

        switch(testListMove[indice].getValue()) {
            case 0:
                renderer.rotate(0, Rotation.Axis.X,true);
                break;
            case 2:
                renderer.rotate(2, Rotation.Axis.Y,false);
                break;
            case 4:
                renderer.rotate(2, Rotation.Axis.X,false);
                break;
            case 6:
                renderer.rotate(0, Rotation.Axis.Y,true);
                break;
            case 8:
                renderer.rotate(2, Rotation.Axis.Z,false);
                break;
            case 10:
                renderer.rotate(0, Rotation.Axis.Z,true);
                break;
            case 1:
                renderer.rotate(0, Rotation.Axis.X,false);
                break;
            case 3:
                renderer.rotate(2, Rotation.Axis.Y,true);
                break;
            case 5:
                renderer.rotate(2, Rotation.Axis.X,true);
                break;
            case 7:
                renderer.rotate(0, Rotation.Axis.Y,false);
                break;
            case 9:
                renderer.rotate(2, Rotation.Axis.Z,true);
                break;
            case 11:
                renderer.rotate(0, Rotation.Axis.Z,false);
                break;
        }

    }
}
