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

public class Main extends Frame{

    private GLCanvas canvas; //Canvas pour le JPanel

    private final FPSAnimator animator;

    public Main(){
        super();
        setSize(1000,1000);
        setTitle("Rubik'INT");
        // Permet de centrer la fenetre à l'ouverture
        setLocationRelativeTo(null);

        OpenGLRenderer2 renderer = new OpenGLRenderer2();
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

        animator.start();
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

}