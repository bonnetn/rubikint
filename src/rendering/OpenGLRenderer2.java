package rendering;

/**
 * Created by florian on 17/02/17.
 */

import jogamp.opengl.egl.EGL;

import static jogamp.opengl.egl.EGL.EGL_TEXTURE_FORMAT;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


public class OpenGLRenderer2 implements GLEventListener{

    private GLU glu;
    // angle gerant la rotation suivant les differents angles
    public float alphaX = 0f;
    public float alphaY = 0f;
    public float alphaZ = 0f;

    @Override
    public void init(GLAutoDrawable drawable){
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f); //fond noir
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glShadeModel(GL2.GL_SMOOTH);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        glu.gluLookAt(4f, 0f, 4f, 0f, 0f, 0f, 0f, 1f, 0f); //Placement de la caméra au point (4,0,12) regardant vers (0,0,0) suivant axe y (0,1,0)

        gl.glRotatef(alphaX, 1f, 0f, 0f); // rotation matrice courante d'angle alphaX autour de axe X
        gl.glRotatef(alphaY, 0f, 1f, 0f);
        gl.glRotatef(alphaZ, 0f, 0f, 1f);

        new Cube(2.0f, 0, 0, 0).draw(gl);
    }
    @Override
    public void dispose (GLAutoDrawable arg0) {
        // rien a mettre ici mais la methode doit être présente.
    }

    @Override
    public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        //En cas de division par 0
        if (height == 0) height = 1;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, (float)width/height, 0.1, 100.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }



}
