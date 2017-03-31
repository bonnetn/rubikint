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

import rendering.enums.Axis;
import rubikscube.RubiksCube;

public class MainRenderer extends Frame{

    public OpenGLRenderer renderer;

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
                    renderer.rotate(2, Axis.X,true);
                    break;
                case KeyEvent.VK_A :
                    renderer.rotate(0,Axis.X,true); //toute une face tourne au lieu d'une seule couronne sur axe Z
                    break;
                case KeyEvent.VK_Z: // tourne L
                    renderer.rotate(0, Axis.Y,true);
                    break;
                case KeyEvent.VK_S :
                    renderer.rotate(2,Axis.Y,true); //toute une face tourne au lieu d'une seule couronne sur axe Z
                    break;
                case KeyEvent.VK_E :
                    renderer.rotate(0,Axis.Z,true); //toute une face tourne au lieu d'une seule couronne sur axe Z
                    break;
                case KeyEvent.VK_D :
                    renderer.rotate(2,Axis.Z,true); //toute une face tourne au lieu d'une seule couronne sur axe Z
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
        //setTitle("Rubik'INT");
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

        setVisible(true);
        animator.start();
    }

     public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainRenderer();
            }
        });
    }

    public GLCanvas getCanvas() { return canvas;}
    public OpenGLRenderer getOpenGLRenderer() {return renderer;}
}
