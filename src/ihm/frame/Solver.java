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

    String[] testListMove = new String[4];

    JLabel afficheEtape;
    RubiksCube rubiksCube;
    private GLCanvas canvas;
    OpenGLRenderer renderer;
    JButton retAccueil = new JButton();
    JButton next = new JButton("NEXT");
    int indice = 0;
    int max = testListMove.length;


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


        afficheEtape = new JLabel(testListMove[indice]);
        afficheEtape.setFont(new Font("Tahoma", Font.BOLD,100));
        afficheEtape.setForeground(Color.RED);
        afficheEtape.setBounds(200,150,200,120);

        next.setBounds(50,300,190,100);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    indice++;
                    afficheEtape.setVisible(false);
                    if (indice < max){
                        afficheEtape=new JLabel(testListMove[indice]);
                        renderer.rotate(0, Rotation.Axis.X,true);
                    }else{
                        afficheEtape=new JLabel("GG");
                    }
                    afficheEtape.setFont(new Font("Tahoma", Font.BOLD,100));
                    afficheEtape.setForeground(Color.RED);
                    afficheEtape.setBounds(200, 150, 200,120);
                    afficheEtape.setVisible(true);
                    add(afficheEtape);


            }
        });

        /* TO DO : Definir a partir d'une liste de rotation [U,L,R,...] L'axe et l'indice de face à tourner et clockwise ou counterclockwise */



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
        testListMove[0] = "U";
        testListMove[1] = "L";
        testListMove[2] = "B";
        testListMove[3] = "R";
    }
}
