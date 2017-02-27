package rendering;

/**
 * Created by florian on 17/02/17.
 */
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import rubikscube.RubiksCube;
import rubikscube.enums.Rotation.*;

public class MainRenderer extends Frame{

    private static RubiksCube rubiksCube;

    private GLCanvas canvas; //Canvas pour le JPanel

    private final FPSAnimator animator;

    private static final class MyKeyListener extends KeyAdapter {

        private final MainRenderer $this;
        private final OpenGLRenderer renderer;
        private boolean isUpPressed;
        private boolean isDownPressed;
        private boolean isLeftPressed;
        private boolean isrightPressed;
        private boolean isUPressed;

        private MyKeyListener(MainRenderer m, OpenGLRenderer r) {
            $this = m;
            renderer = r;
        }

        private void doThat(){
            if(isDownPressed)
                renderer.alphaX += 2;
            if(isUpPressed)
                renderer.alphaX -= 2;
            if(isLeftPressed)
                renderer.alphaY -= 2;
            if(isrightPressed)
                renderer.alphaY += 2;

        }

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    $this.animator.stop();
                    System.exit(0);
                    break;
                case KeyEvent.VK_UP:
                    isUpPressed = true;
                    break;
                case KeyEvent.VK_DOWN:
                    isDownPressed = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    isrightPressed = true;
                    break;
                case KeyEvent.VK_LEFT:
                    isLeftPressed = true;
                    break;
                case KeyEvent.VK_Q: // tourne L
                    renderer.rotate(0, Axis.X,true);
                    break;
                case KeyEvent.VK_A :
                    renderer.rotate(2,Axis.X,true);
                    break;
            }
            doThat();
        }

        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    isUpPressed = false;
                    break;
                case KeyEvent.VK_DOWN:
                    isDownPressed = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    isrightPressed = false;
                    break;
                case KeyEvent.VK_LEFT:
                    isLeftPressed = false;
                    break;
            }
        }
    }

    public MainRenderer(){
        super();
        setSize(640,480);
        setTitle("Rubik'INT");
        // Permet de centrer la fenetre à l'ouverture
        setLocationRelativeTo(null);

        OpenGLRenderer renderer = new OpenGLRenderer();
        canvas = new GLCanvas();
        canvas.addGLEventListener(renderer);
        add(canvas);

        animator  = new FPSAnimator(canvas, 60);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Arret de l'animateur
                animator.stop();
                System.exit(0);
            }
        });

        addKeyListener(new MyKeyListener(this, renderer));
        canvas.addKeyListener(new MyKeyListener(this, renderer));

        animator.start();
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainRenderer();
            }
        });
    }

}