package rendering;

/**
 * Created by florian on 17/02/17.
 */

import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation.*;
import rubikscube.enums.Rotation;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import java.awt.Frame;
import javax.media.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.Object;

// Attention, voire le probleme des fenetres qui ne se ferme pas


public class OpenGLRenderer extends Frame implements GLEventListener /* KeyListener, MouseListener */ {

    private GLU glu;
    public Cube[][][] listeCube = new Cube[3][3][3];


    // angle gerant la rotation camera
    private static final float defaultAlphaX = 45f;
    private static final float defaultAlphaY = 45f;
    private static final float defaultAlphaZ = 0f;
    private static final float defaultZoom = -18f;

    public float alphaX = defaultAlphaX;
    public float alphaY = defaultAlphaY;
    public float alphaZ = defaultAlphaZ;
    public float zooom = defaultZoom;

    public final float distanceEntreCube = 0.1f;

    private float[] coloneAnglesX;
    private float[] ligneAnglesY;
    private float[] profondeurAnglesZ;

    private int rotateX = -1;
    private int rotateY = -1;
    private int rotateZ = -1;
    private float rotationSpeed = 3f;


    private RubiksCube rubiksCube;



    public OpenGLRenderer(){
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
    } //initialisation de mon rendu OpenGL

    @Override
    public void display(GLAutoDrawable drawable) {

        // faire une methode d'update des angles, on va appeler en boucle et faire des minis rotations de 5degre et cela va redessiner a chaque fois on aura alors une sensation d'animation de rotation.
        updateAngles();
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear du buffer pour eviter chevauchement des faces.
        gl.glLoadIdentity();
        //glu.gluLookAt(4f, 5f, 12f, 0f, 0f, 0f, 0f, 1f, 0f); //Placement de la caméra au point (4,0,12) regardant vers (0,0,0) suivant axe y (0,1,0)

        gl.glTranslatef(0f,0f,-10f);
        gl.glRotatef(alphaX, 1f, 0f, 0f); // rotation matrice courante d'angle alphaX autour de axe X
        gl.glRotatef(alphaY, 0f, 1f, 0f);
	    gl.glRotatef(alphaZ, 0f, 0f, 1f);
	    drawRubiksCube(gl,rubiksCube);


    } //est appelé en boucle : le cube se dessine en boucle ce qui permet de faire les rotations en redessinant
    @Override                                          // par de petite rotation petit à petit, petite rotation defini par rotationSpeed
    public void dispose (GLAutoDrawable arg0) {
        // rien a mettre ici mais la methode doit être présente.
    } // pas utile ici mais doit être Override (demande par OpenGL)

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

    }  //permet de changer la taille de la fenetre

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
    } //permet de definir les couleur pour OpenGL

    private void drawCube( GL2 gl, Cube cube){


        gl.glBegin(GL2.GL_QUADS);

        //face du haut
        //gl.glColor3f(1f,0f,0.5f); //noire par defaut
        //if ((coloredFaces & cube.upFace) == cube.upFace) { setGlColor(gl, cube.upColor);}
        setGlColor(gl,cube.upColor);
        gl.glVertex3f(0f,0f,1f);
        gl.glVertex3f(0f,1f,1f);
        gl.glVertex3f(1f,1f,1f);
        gl.glVertex3f(1f,0f,1f);

        // face du bas
        //gl.glColor3f(1f,0f,0.5f);
        //if ((coloredFaces & cube.downFace) == cube.downFace) { setGlColor(gl,cube.downColor);}
        setGlColor(gl,cube.downColor);
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,0f,0f);

        // face devant
        //gl.glColor3f(1f,0f,0.5f);
        //if ((coloredFaces & cube.frontFace) == cube.frontFace) { setGlColor(gl,cube.frontColor);}
        setGlColor(gl,cube.frontColor);
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(1f,0f,0f);
        gl.glVertex3f(1f,0f,1f);
        gl.glVertex3f(0f,0f,1f);

        // face arriere
        //gl.glColor3f(1f,0f,0.5f);
        //if ((coloredFaces & cube.backFace) == cube.backFace) { setGlColor(gl,cube.backColor);}
        setGlColor(gl,cube.backColor);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,1f,1f);
        gl.glVertex3f(0f,1f,1f);

        //face de gauche
        //gl.glColor3f(1f,0f,0.5f);
        //if ((coloredFaces & cube.leftFace) == cube.leftFace) { setGlColor(gl,cube.leftColor);}
        setGlColor(gl,cube.leftColor);
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(0f,1f,1f);
        gl.glVertex3f(0f,0f,1f);

        //face de droite
        //gl.glColor3f(1f,0f,0.5f);
        //if ((coloredFaces & cube.rightFace) == cube.rightFace) { setGlColor(gl,cube.rightColor);}
        setGlColor(gl,cube.rightColor);
        gl.glVertex3f(1f,0f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,1f,1f);
        gl.glVertex3f(1f,0f,1f);
        gl.glEnd();
    } //dessine un petit cube dont les couleurs sont donnée en argument.


    private void drawRubiksCube(GL2 gl, RubiksCube rubiksCube) // dessine tous les petits cube a partir de la config de RubiksCube
    {
        setCube(rubiksCube); // cree les differents cubes en fonction de la config des facettes de rubikscube
        for (int x =0; x<3 ; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                for (int z = 0; z < 3; z++)
                {
                    // mettre ici des rotates des différents cube selon s'il sont en train de tourner
                    gl.glPushMatrix();
                    gl.glTranslatef(0f,0f,0f); // revient au centre pour eviter un decalage

                    gl.glRotatef(coloneAnglesX[x],1f,0f,0f);
                    gl.glRotatef(ligneAnglesY[y],0f,1f,0f);
                    gl.glRotatef(profondeurAnglesZ[x],0f,0f,1f);

                    Cube cubeToDraw = listeCube[x][y][z];
                    gl.glTranslatef((x-1f),(y-1f),(z-1f)); // va au centre du petit cube
                    if (x == 0) gl.glTranslatef(-distanceEntreCube,0f,0f); // decalage si à gauche de origine
                    if (x == 2) gl.glTranslatef(distanceEntreCube,0f,0f); // decalage si à droite de origine
                    if (y == 0) gl.glTranslatef(0f,-distanceEntreCube,0f); // decalage si à l'avant de origine
                    if (y == 2) gl.glTranslatef(0f,distanceEntreCube,0f); // decalage si à l'arrière de origine
                    if (z == 0) gl.glTranslatef(0f,0f,-distanceEntreCube); // decalage si à gauche de origine
                    if (z == 2) gl.glTranslatef(0f,0f,distanceEntreCube); // decalage si à gauche de origine
                    gl.glTranslatef(-0.5f,-0.5f,-0.5f);

                    drawCube(gl,cubeToDraw);
                    gl.glPopMatrix();
                }
            }
        }
    }

    // besoin de definir le cube dans un repère 3D (x,y,z)e [-1,1]³ (en petit cube) et non pas Face puis (x,y) e [0,2]² (par facette)

    public void setCube(RubiksCube rCube){  // POSITION X ET Y A VERIFIER SELON LA NORME UTILISE DANS RUBIKS CUBE

        // PREMIERE COURONNE FRONTALE
        // [-1,-1,-1] | [0,0,0] +1 a chaque composantes pour en faire une liste.
        listeCube[0][0][0] = new Cube(rCube.getFacetColor(Face.F,0,0),Color.BLACK,rCube.getFacetColor(Face.L,2,0),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,0,2),-1,-1,-1);
        // [0,-1,-1] | [1,0,0]
        listeCube[1][0][0] = new Cube(rCube.getFacetColor(Face.F,1,0),Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,1,2),0,-1,-1);
        //[1,-1,-1]
        listeCube[2][0][0] = new Cube(rCube.getFacetColor(Face.F,2,0),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,0,0),Color.BLACK,rCube.getFacetColor(Face.D,2,2),1,-1,-1);
        //[-1,-1,0]
        listeCube[0][0][1] = new Cube(rCube.getFacetColor(Face.F,0,1),Color.BLACK,rCube.getFacetColor(Face.L,2,1),Color.BLACK,Color.BLACK,Color.BLACK,-1,-1,0);
        //[0,-1,0] centre front
        listeCube[1][0][1] = new Cube(rCube.getFacetColor(Face.F,1,1),Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,0,-1,0);
        //[1,-1,0]
        listeCube[2][0][1] = new Cube(rCube.getFacetColor(Face.F,2,1),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,0,1),Color.BLACK,Color.BLACK,1,-1,0);
        //[-1,-1,1]
        listeCube[0][0][2] = new Cube(rCube.getFacetColor(Face.F,0,2),Color.BLACK,rCube.getFacetColor(Face.L,2,2),Color.BLACK,rCube.getFacetColor(Face.U,0,0),Color.BLACK,-1,-1,1);
        //[0,-1,1]
        listeCube[1][0][2] = new Cube(rCube.getFacetColor(Face.F,1,2),Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.U,1,0),Color.BLACK,0,-1,1);
        //[1,-1,1]
        listeCube[2][0][2] = new Cube(rCube.getFacetColor(Face.F,2,2),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,0,2),rCube.getFacetColor(Face.U,2,0),Color.BLACK,1,-1,1);

        //SECONDE COURONNE CENTRALE
        //[-1,0,-1]
        listeCube[0][1][0] = new Cube(Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.L,1,0),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,0,1),-1,0,-1);
        //[0,0,-1] centre down
        listeCube[1][1][0] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,1,1),0,0,-1);
        //[1,0,-1]
        listeCube[2][1][0] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,1,0),Color.BLACK,rCube.getFacetColor(Face.D,2,1),1,0,-1);
        //[-1,0,0] centre left
        listeCube[0][1][1] = new Cube(Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.L,1,1),Color.BLACK,Color.BLACK,Color.BLACK,-1,0,0);
        //[0,0,0] cube interne tout en noir
        listeCube[1][1][1] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,0,0,0);
        //[1,0,0] // centre right
        listeCube[2][1][1] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,1,1),Color.BLACK,Color.BLACK,1,0,0);
        //[-1,0,1]
        listeCube[0][1][2] = new Cube(Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.L,1,2),Color.BLACK,rCube.getFacetColor(Face.U,0,1),Color.BLACK,-1,0,1);
        //[0,0,1]
        listeCube[1][1][2] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.U,1,1),Color.BLACK,0,0,1);
        //[1,0,1]
        listeCube[2][1][2] = new Cube(Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.R,1,2),rCube.getFacetColor(Face.U,2,1),Color.BLACK,1,0,1);


        //TROISIEME COURONNE ARRIERE
        //[-1,1,-1]
        listeCube[0][2][0] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,2,0),rCube.getFacetColor(Face.L,0,0),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,0,0),-1,1,-1);
        //[0,1,-1]
        listeCube[1][2][0] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,1,0),Color.BLACK,Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.D,1,0),0,1,-1);
        //[1,1,-1]
        listeCube[2][2][0] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,0,0),Color.BLACK,rCube.getFacetColor(Face.R,2,0),Color.BLACK,rCube.getFacetColor(Face.D,2,0),1,1,-1);
        //[-1,1,0]
        listeCube[0][2][1] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,2,1),rCube.getFacetColor(Face.L,0,1),Color.BLACK,Color.BLACK,Color.BLACK,-1,1,0);
        //[0,1,0] centre back
        listeCube[1][2][1] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,1,1),Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,0,1,0);
        //[1,1,0]
        listeCube[2][2][1] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,0,1),Color.BLACK,rCube.getFacetColor(Face.R,2,1),Color.BLACK,Color.BLACK,1,1,0);
        //[-1,1,1]
        listeCube[0][2][2] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B, 2,2),rCube.getFacetColor(Face.L,2,2),Color.BLACK,rCube.getFacetColor(Face.U,0,2),Color.BLACK,-1,1,1);
        //[0,1,1]
        listeCube[1][2][2] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,1,2),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.U,1,2),Color.BLACK,0,1,1);
        //[1,1,1]
        listeCube[2][2][2] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,0,2),Color.BLACK,rCube.getFacetColor(Face.R,2,2),rCube.getFacetColor(Face.U,2,2),Color.BLACK,1,1,1);

    } // cree les petits cube à partir de la representation plan par facettes de RubiksCube

    public boolean isRotating() //determine si le cube a deja une face qui est en train de tourner, eviter plusieurs mouvement en meme temps
    {
        return rotateX + rotateY + rotateZ > -3; // valeur par default des rotate font = -3
    }


    /* Rotations :
           rotateX = 0 => tourne L   rotateX = 2 => tourne R selon axe X  si 1 on tourne couronne centrale selon X
           rotateY = 0 => tourne F   rotateY = 2 => tourne B selon axe Y  si 1 on tourne couronne centrale selon Y
           rotateZ = 0 => tourne D   rotateZ = 2 => tourne U selon axe Z  si 1 on tourne couronne centrale selon Z
     */
    public void updateAngles() // va etre appele a chaque boucle de display et permet donc d'avoir les angles qui s'incrémente au fur et a mesure pour effectuer la rotation
    {
        Direction direction = (rotationSpeed > 0) ? Direction.COUNTER_CLOCKWISE : Direction.CLOCKWISE;

        if (rotateX >=0)
        {
            coloneAnglesX[rotateX] += rotationSpeed;
            if (coloneAnglesX[rotateX] % 90f ==0)
            {
                coloneAnglesX[rotateX] = 0;
                if (rotateX ==0) rubiksCube.rotate(Rotation.Li);
                if (rotateX ==2) rubiksCube.rotate(Rotation.R);
                rotateX = -1;

            }
        }else if (rotateY >=0)
        {
            ligneAnglesY[rotateY] += rotationSpeed;
            if (ligneAnglesY[rotateY] % 90f ==0)
            {
                ligneAnglesY[rotateY] = 0;
                rotateY = -1;
            }
        }else if (rotateZ >=0)
        {
            profondeurAnglesZ[rotateZ] += rotationSpeed;
            if (profondeurAnglesZ[rotateZ] % 90f ==0)
            {
                profondeurAnglesZ[rotateZ] = 0;
                rotateZ = -1;
            }
        }
    }

    public void rotate(int face,Axis axis,boolean clock) // permet de faire une rotation et d'en définir le sens (horaire ou trigo)
    {
        if (!isRotating())
        {
            if (axis == Axis.X) rotateX = face;
            if (axis == Axis.Y) rotateY = face;
            if (axis == Axis.Z) rotateZ = face;
            rotationSpeed = clock ? - Math.abs(rotationSpeed) : Math.abs(rotationSpeed); //permet de definir si la rotation est clockwise ou counter_clockwise
        }
    }




}
