/*
package ihm.frame;

/**
 * Created by florian on 02/03/17.
 */

/*
import java.awt.*;
import java.awt.event.*;

import com.jogamp.opengl.util.FPSAnimator;
import ihm.Fenetre;
import rendering.OpenGLRenderer;
import rubikscube.RubiksCube;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
public class Solverold extends JPanel{
    RubiksCube rubiksCube = new RubiksCube();
    private GLCanvas canvas;
    OpenGLRenderer renderer; //= new OpenGLRenderer();
    JPanel pan;
    JButton bouton = new JButton("Accueil");


    public Solverold(){
        //JPanel pan = new JPanel();

        setSize(800, 700);
        setLayout(new BorderLayout());

        //renderer = new OpenGLRenderer();
        //canvas = new GLCanvas();
        //canvas.setSize(640,480);
        //canvas.addGLEventListener(renderer);
        JPanel rendu3D = rendu3D();

        add(rendu3D());
        //add("South",bouton());
        //FPSAnimator animator = new FPSAnimator(canvas,60);

        //addKeyListener(new MyKeyListener(renderer));
        //canvas.addKeyListener(new MyKeyListener(renderer));
        //animator.start();

        //this.setMenuBar(createMenuBar());
        add(bouton);
        //setVisible(true);
        //return frame;
    }

    public JPanel getPanel(){return pan;}
    public JButton getBouton(){ return bouton;}

    private JPanel rendu3D(){
        JPanel panel = new JPanel();
        panel.setSize(800,400);
        panel.setLayout(new BorderLayout());

        renderer = new OpenGLRenderer();
        canvas = new GLCanvas();
        canvas.addGLEventListener(renderer);
        panel.add(canvas);
        FPSAnimator animator = new FPSAnimator(canvas,60);
        panel.addKeyListener(new MyKeyListener(renderer));
        canvas.addKeyListener(new MyKeyListener(renderer));
        animator.start();
        panel.setVisible(true);
        return panel;
    }

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

*/