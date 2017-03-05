package ihm.frame;

import com.jogamp.opengl.util.FPSAnimator;
import rendering.OpenGLRenderer;
import rubikscube.RubiksCube;

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

    RubiksCube rubiksCube;
    private GLCanvas canvas;
    OpenGLRenderer renderer;
    JButton retAccueil = new JButton();


    public Solver(){

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
        retAccueil.setBounds(50,500,190,100);

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
}
