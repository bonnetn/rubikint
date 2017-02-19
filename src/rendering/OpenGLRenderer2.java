package rendering;

/**
 * Created by florian on 17/02/17.
 */

import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.MouseListener;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


public class OpenGLRenderer2 implements GLEventListener, KeyListener, MouseListener{

    private GLU glu;


    // angle gerant la rotation suivant les differents angles
    public float alphaX = 45f;
    public float alphaY = 45f;
    public float alphaZ = 0f;
    public final float distanceEntreCube = 0.2f;

    private float[] coloneAnglesX;
    private float[] ligneAnglesY;
    private float[] profondeurAnglesZ;

    private RubiksCube rubiksCube;

    public OpenGLRenderer2(){
        rubiksCube = new RubiksCube();
        this.coloneAnglesX = new float[3];
        this.ligneAnglesY = new float[3];
        this.profondeurAnglesZ = new float[3];
    }

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
        //glu.gluLookAt(4f, 5f, 12f, 0f, 0f, 0f, 0f, 1f, 0f); //Placement de la caméra au point (4,0,12) regardant vers (0,0,0) suivant axe y (0,1,0)

        gl.glTranslatef(0f,0f,-18f);
        gl.glRotatef(alphaX, 1f, 0f, 0f); // rotation matrice courante d'angle alphaX autour de axe X
        gl.glRotatef(alphaY, 0f, 1f, 0f);
        gl.glRotatef(alphaZ, 0f, 0f, 1f);

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


    private void setGlColor(GL2 gl, Color color) {
        switch (color) {
            case WHITE:
                gl.glColor3f(1f, 1f, 1f); break;
            case YELLOW:
                gl.glColor3f(1f, 1f, 0f); break;
            case GREEN:
                gl.glColor3f(0f, 1f, 0f); break;
            case ORANGE:
                gl.glColor3f(1f, 1f/2, 0f); break;
            case BLUE:
                gl.glColor3f(0f, 0f, 1f); break;
            case RED:
                gl.glColor3f(1f, 0f, 0f); break;
        }
    }

    private void drawCube( GL2 gl, Cube cube, int coloredFaces){
        gl.glBegin(GL2.GL_QUADS);

        //face du haut
        gl.glColor3f(0f,0f,0f); //noire par defaut
        if ((coloredFaces & cube.topFace) == cube.topFace) { setGlColor(gl, cube.topColor);}
        gl.glVertex3f(0f,0f,1f);
        gl.glVertex3f(0f,1f,1f);
        gl.glVertex3f(1f,0f,1f);
        gl.glVertex3f(1f,1f,1f);

        // face du bas
        gl.glColor3f(0f,0f,0f);
        if ((coloredFaces & cube.downFace) == cube.downFace) { setGlColor(gl,cube.downColor);}
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(1f,0f,0f);
        gl.glVertex3f(1f,1f,0f);

        // face devant
        gl.glColor3f(0f,0f,0f);
        if ((coloredFaces & cube.frontFace) == cube.frontFace) { setGlColor(gl,cube.frontColor);}
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(1f,0f,0f);
        gl.glVertex3f(0f,0f,1f);
        gl.glVertex3f(1f,0f,1f);

        // face arriere
        gl.glColor3f(0f,0f,0f);
        if ((coloredFaces & cube.rearFace) == cube.rearFace) { setGlColor(gl,cube.rearColor);}
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(0f,1f,1f);
        gl.glVertex3f(1f,1f,1f);

        //face de gauche
        gl.glColor3f(0f,0f,0f);
        if ((coloredFaces & cube.leftFace) == cube.leftFace) { setGlColor(gl,cube.leftColor);}
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(0f,0f,1f);
        gl.glVertex3f(0f,1f,1f);

        //face de droite
        gl.glColor3f(0f,0f,0f);
        if ((coloredFaces & cube.rightFace) == cube.rightFace) { setGlColor(gl,cube.rightColor);}
        gl.glVertex3f(1f,0f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,0f,1f);
        gl.glVertex3f(1f,1f,1f);

        gl.glEnd();
    }

    private drawRubiksCube(GL2 gl){
        for (int x=0 ;x<3 ;x++ ) {
            for(int y=0;y<3;y++){
                for(int z=0;z<3;z++){
                    gl.glPushMatrix();

                    gl.glTranslatef(x-0.5f,y-0.5f,z-0.5f);





                }
            }

        }
    }
}
