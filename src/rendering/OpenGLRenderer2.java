package rendering;

/**
 * Created by florian on 17/02/17.
 */

import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.MouseListener;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

// Attention, voire le probleme des fenetres qui ne se ferme pas


public class OpenGLRenderer2 implements GLEventListener /* KeyListener, MouseListener */ {

    private GLU glu;
    public final Cube[] listeCube = new Cube[27];


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
	    drawRubiksCube(gl,rubiksCube);

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
            case BLACK:
                gl.glColor3f(0f,0f,0f); break;
        }
    }

    private void drawCube( GL2 gl, Cube cube, int coloredFaces ){
        gl.glBegin(GL2.GL_QUADS);

        //face du haut
        setGlColor(gl, Color.RED); //noire par defaut
        if ((coloredFaces & cube.upFace) == cube.upFace) { setGlColor(gl, cube.upColor);}
        gl.glVertex3f(0f,0f,1f);
        gl.glVertex3f(0f,1f,1f);
        gl.glVertex3f(1f,1f,1f);
        gl.glVertex3f(1f,0f,1f);

        // face du bas
        setGlColor(gl, Color.BLACK);
        if ((coloredFaces & cube.downFace) == cube.downFace) { setGlColor(gl,cube.downColor);}
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,0f,0f);

        // face devant
        setGlColor(gl, Color.BLACK);
        if ((coloredFaces & cube.frontFace) == cube.frontFace) { setGlColor(gl,cube.frontColor);}
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(1f,0f,0f);
        gl.glVertex3f(1f,0f,1f);
        gl.glVertex3f(0f,0f,1f);

        // face arriere
        setGlColor(gl, Color.BLACK);
        if ((coloredFaces & cube.backFace) == cube.backFace) { setGlColor(gl,cube.backColor);}
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,1f,1f);
        gl.glVertex3f(0f,1f,1f);

        //face de gauche
        setGlColor(gl, Color.BLACK);
        if ((coloredFaces & cube.leftFace) == cube.leftFace) { setGlColor(gl,cube.leftColor);}
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(0f,1f,1f);
        gl.glVertex3f(0f,0f,1f);

        //face de droite
        setGlColor(gl, Color.BLACK);
        if ((coloredFaces & cube.rightFace) == cube.rightFace) { setGlColor(gl,cube.rightColor);}
        gl.glVertex3f(1f,0f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,1f,1f);
        gl.glVertex3f(1f,0f,1f);
        gl.glEnd();
    }

    private void drawRubiksCube(GL2 gl, RubiksCube rubiksCube){
        setCube(rubiksCube); // cree les differents cubes en fonction de la config des facette de rubikscube
        for ( int i=0; i<27 ; i++){
            gl.glPushMatrix();  //met en place le curseur qui va effectuer le tracé
            Cube cubeToDraw = listeCube[i]; //recupere dans la liste
            int[] position = cubeToDraw.getPosition(); // recupere les position pour placer le curseur
            gl.glTranslatef(position[0],position[1],position[2]);
            drawCube(gl,cubeToDraw,cubeToDraw.getColoredFaces()); //dessine le cube
            gl.glPopMatrix(); //réinitialise le curseur de dessin


        }
    }

    // besoin de definir le cube dans un repère 3D (x,y,z)e [-1,1]³ (en petit cube) et non pas Face puis (x,y) e [0,2]² (par facette)

    public void setCube(RubiksCube rCube){  // POSITION X ET Y A VERIFIER SELON LA NORME UTILISE DANS RUBIKS CUBE

        // PREMIERE COURONNE FRONTALE
        // [-1,-1,-1]
        listeCube[0] = new Cube(rCube.getFacetColor(Face.F,0,0),Color.BLACK,rCube.getFacetColor(Face.L,2,0),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,0,2),-1,-1,-1);
        // [0,-1,-1]
        listeCube[1] = new Cube(rCube.getFacetColor(Face.F,1,0),Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,1,2),0,-1,-1);
        //[1,-1,-1]
        listeCube[2] = new Cube(rCube.getFacetColor(Face.F,2,0),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,0,0),Color.BLACK,rCube.getFacetColor(Face.D,2,2),1,-1,-1);
        //[-1,-1,0]
        listeCube[3] = new Cube(rCube.getFacetColor(Face.F,0,1),Color.BLACK,rCube.getFacetColor(Face.L,2,1),Color.BLACK,Color.BLACK,Color.BLACK,-1,-1,0);
        //[0,-1,0] centre front
        listeCube[4] = new Cube(rCube.getFacetColor(Face.F,1,1),Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,0,-1,0);
        //[1,-1,0]
        listeCube[5] = new Cube(rCube.getFacetColor(Face.F,2,1),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,0,1),Color.BLACK,Color.BLACK,1,-1,0);
        //[-1,-1,1]
        listeCube[6] = new Cube(rCube.getFacetColor(Face.F,0,2),Color.BLACK,rCube.getFacetColor(Face.L,2,2),Color.BLACK,rCube.getFacetColor(Face.U,0,0),Color.BLACK,-1,-1,1);
        //[0,-1,1]
        listeCube[7] = new Cube(rCube.getFacetColor(Face.F,1,2),Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.U,1,0),Color.BLACK,0,-1,1);
        //[1,-1,1]
        listeCube[8] = new Cube(rCube.getFacetColor(Face.F,2,2),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,0,2),rCube.getFacetColor(Face.U,2,0),Color.BLACK,1,-1,1);

        //SECONDE COURONNE CENTRALE
        //[-1,0,-1]
        listeCube[9] = new Cube(Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.L,1,0),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,0,1),-1,0,-1);
        //[0,0,-1] centre down
        listeCube[10] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,1,1),0,0,-1);
        //[1,0,-1]
        listeCube[11] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,1,0),Color.BLACK,rCube.getFacetColor(Face.D,2,1),1,0,-1);
        //[-1,0,0] centre left
        listeCube[12] = new Cube(Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.L,1,1),Color.BLACK,Color.BLACK,Color.BLACK,-1,0,0);
        //[0,0,0] cube interne tout en noir
        listeCube[13] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,0,0,0);
        //[1,0,0] // centre right
        listeCube[14] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,1,1),Color.BLACK,Color.BLACK,1,0,0);
        //[-1,0,1]
        listeCube[15] = new Cube(Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.L,1,2),Color.BLACK,rCube.getFacetColor(Face.U,0,1),Color.BLACK,-1,0,1);
        //[0,0,1]
        listeCube[16] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.U,1,1),Color.BLACK,0,0,1);
        //[1,0,1]
        listeCube[17] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,1,2),Color.BLACK,rCube.getFacetColor(Face.U,2,1),1,0,1);


        //TROISIEME COURONNE ARRIERE
        //[-1,1,-1]
        listeCube[18] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,2,0),rCube.getFacetColor(Face.L,0,0),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,0,0),-1,1,-1);
        //[0,1,-1]
        listeCube[19] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,1,0),Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,1,0),0,1,-1);
        //[1,1,-1]
        listeCube[20] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,0,0),Color.BLACK,rCube.getFacetColor(Face.R,2,0),Color.BLACK,rCube.getFacetColor(Face.D,2,0),1,1,-1);
        //[-1,1,0]
        listeCube[21] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,2,1),rCube.getFacetColor(Face.L,0,1),Color.BLACK,Color.BLACK,Color.BLACK,-1,-1,0);
        //[0,1,0] centre back
        listeCube[22] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,1,1),Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,0,1,0);
        //[1,1,0]
        listeCube[23] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,0,1),Color.BLACK,rCube.getFacetColor(Face.R,2,1),Color.BLACK,Color.BLACK,1,1,0);
        //[-1,1,1]
        listeCube[24] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B, 2,2),rCube.getFacetColor(Face.L,2,2),Color.BLACK,rCube.getFacetColor(Face.U,0,2),Color.BLACK,-1,1,1);
        //[0,1,1]
        listeCube[25] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,1,2),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.U,1,2),Color.BLACK,0,1,1);
        //[1,1,1]
        listeCube[26] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,0,2),Color.BLACK,rCube.getFacetColor(Face.R,2,2),rCube.getFacetColor(Face.U,2,2),Color.BLACK,1,1,1);

    }
}
