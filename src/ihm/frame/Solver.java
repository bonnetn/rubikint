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

    int[] testListMove = new int[6];

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


        afficheEtape = new JLabel();
        afficheEtape.setFont(new Font("Tahoma", Font.BOLD,100));
        afficheEtape.setForeground(Color.RED);
        afficheEtape.setBounds(200,150,200,120);

        next.setBounds(50,300,190,100);
              /*L(0), Li(1),
                B(2), Bi(3),
                R(4), Ri(5),
                F(6), Fi(7),
                U(8), Ui(9),
                D(10), Di(11);*/
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    indice++;
                    afficheEtape.setVisible(false);
                    if (indice <= max){
                        if(testListMove[indice-1]% 2 ==0) //si la rotation est clockwise
                        {
                            isclockwise = true;
                            switch(testListMove[indice-1]){
                                case 0:
                                    afficheEtape = new JLabel("L");
                                    renderer.rotate(0, Rotation.Axis.X,isclockwise);
                                    break;
                                case 2:
                                    afficheEtape = new JLabel("B");
                                    renderer.rotate(2,Rotation.Axis.Z,isclockwise);
                                    break;
                                case 4:
                                    afficheEtape = new JLabel("R");
                                    renderer.rotate(2, Rotation.Axis.X,isclockwise);
                                    break;
                                case 6:
                                    afficheEtape = new JLabel("F");
                                    renderer.rotate(0, Rotation.Axis.Z,isclockwise);
                                    break;
                                case 8:
                                    afficheEtape = new JLabel("U");
                                    renderer.rotate(2, Rotation.Axis.Y,isclockwise);
                                    break;
                                case 10:
                                    afficheEtape = new JLabel("D");
                                    renderer.rotate(0, Rotation.Axis.Y,isclockwise);
                                    break;
                            }
                        }
                       // afficheEtape=new JLabel("U");
                      //  renderer.rotate(0, Rotation.Axis.X,true);
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

        /* TO DO : FAIRE UNE IHM SIMPLISTE POUR RETESTER TOUTE MES ROTATIONS PROBLEME AU NIVEAU DE AXE Z */



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
        testListMove[0] = 0;
        testListMove[1] = 2;
        testListMove[2] = 4;
        testListMove[3] = 6;
        testListMove[4] = 8;
        testListMove[5] = 10;

    }
}
