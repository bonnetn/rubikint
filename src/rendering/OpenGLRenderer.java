package rendering;

/**
 * Created by florian on 17/02/17.
 *
 * CETTE CLASSE CREE LE MOTEUR GRAPHIQUE PERMETTANT LE RENDU DU RUBIKSCUBE
 */

import rendering.enums.Direction;
import rendering.enums.Axis;
import resolution.NoSolutionFound;
import resolution.Solver;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


import java.awt.Frame;
import java.util.ArrayList;





public class OpenGLRenderer extends Frame implements GLEventListener/* KeyListener, MouseListener */ {

    /**----------------------------------------DEFINITION DES VARIABLES---------------------------------------------**/
    private GLU glu; //C'est notre scene
    public Cube[][][] listeCube = new Cube[3][3][3];
    ArrayList<Rotation> solution;


    // angle gerant la rotation camera et valeur pour le zoom
    private static final float defaultAlphaX = -45f;
    private static final float defaultAlphaY = 0f;
    private static final float defaultAlphaZ = -30f;
    private static final float defaultZoom = -18f;


    public float alphaX = defaultAlphaX;
    public float alphaY = defaultAlphaY;
    public float alphaZ = defaultAlphaZ;
    public float zoom = defaultZoom;

    public final float distanceEntreCube = 0.1f;

    // liste pour tourner les bonnes faces
    private float[] coloneAnglesX;
    private float[] ligneAnglesY;
    private float[] profondeurAnglesZ;

    // entier pour definir la face a tourner
    private int rotateX = -1;
    private int rotateY = -1;
    private int rotateZ = -1;
    private float rotationSpeed = 5f;

    private RubiksCube rubiksCube;
    private RandomSolverThread animation;


/**----------------------CONSTRUCTEUR ET OVERRIDE DES METHODE NECESSAIRE AU RENDU 3D---------------------------------**/
    public OpenGLRenderer(){
        rubiksCube = new RubiksCube();
        this.coloneAnglesX = new float[3];
        this.ligneAnglesY = new float[3];
        this.profondeurAnglesZ = new float[3];

    }

    @Override
    public void init(GLAutoDrawable drawable){ //Initialisation du rendu
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
        //Methode appelée en boucle : le cube se dessine en boucle ce qui permet de faire les rotations en redessinant
        // par de petite rotation petit à petit, petite rotation defini par rotationSpeed
        updateAngles(); //permet d'incrementer les angles des faces en train de tourner
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // evite les chevauchement de face
        gl.glLoadIdentity(); //reset du rendu
        glu.gluLookAt(0f, 0f, 12f, 0f, 0f, 0f, 0f, 1f, 0f); //Placement de la caméra
        // au point (4,0,12) regardant vers (0,0,0) suivant axe y (0,1,0)

        gl.glRotatef(alphaX, 1f, 0f, 0f); // rotation de la matrice courante selon X,Y et Z.
        gl.glRotatef(alphaY, 0f, 1f, 0f); // permet de tourner autour du cube
	    gl.glRotatef(alphaZ, 0f, 0f, 1f);
	    drawRubiksCube(gl,rubiksCube); // Dessine le rubikscube en 3D


    }
    @Override
    public void dispose (GLAutoDrawable arg0) {} // pas utile ici mais doit être Override (demande par OpenGL)

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

    /**-----------------------------------------RENDU DU RUBIKSCUBE--------------------------------------------------**/

    private void setGlColor(GL2 gl, Color color) { //Definition des couleurs dans OpenGL à partir de l'enum Colo
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

    private void drawCube( GL2 gl, Cube cube){ //Permet de dessiner un petit cube d'un RubiksCube par la class
        //defini pour dans le package rendering


        gl.glBegin(GL2.GL_QUADS);

        //face du haut
        setGlColor(gl,cube.upColor);
        gl.glVertex3f(0f,0f,1f);
        gl.glVertex3f(0f,1f,1f);
        gl.glVertex3f(1f,1f,1f);
        gl.glVertex3f(1f,0f,1f);

        // face du bas
        setGlColor(gl,cube.downColor);
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,0f,0f);

        // face devant
        setGlColor(gl,cube.frontColor);
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(1f,0f,0f);
        gl.glVertex3f(1f,0f,1f);
        gl.glVertex3f(0f,0f,1f);

        // face arriere
        setGlColor(gl,cube.backColor);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,1f,1f);
        gl.glVertex3f(0f,1f,1f);

        //face de gauche
        setGlColor(gl,cube.leftColor);
        gl.glVertex3f(0f,0f,0f);
        gl.glVertex3f(0f,1f,0f);
        gl.glVertex3f(0f,1f,1f);
        gl.glVertex3f(0f,0f,1f);

        //face de droite
        setGlColor(gl,cube.rightColor);
        gl.glVertex3f(1f,0f,0f);
        gl.glVertex3f(1f,1f,0f);
        gl.glVertex3f(1f,1f,1f);
        gl.glVertex3f(1f,0f,1f);
        gl.glEnd();
    }


    public void drawRubiksCube(GL2 gl, RubiksCube rubiksCube) // dessine tous les petits cube a partir de la config de RubiksCube
    {
        setCube(rubiksCube); // cree les differents cubes en fonction de la config des facettes de rubikscube
        for (int x =0; x<3 ; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                for (int z = 0; z < 3; z++)
                {
                    gl.glPushMatrix();
                    gl.glTranslatef(0f,0f,0f); // revient au centre pour eviter un decalage

                    gl.glRotatef(coloneAnglesX[x],1f,0f,0f);
                    gl.glRotatef(ligneAnglesY[y],0f,1f,0f);
                    gl.glRotatef(profondeurAnglesZ[z],0f,0f,1f);

                    Cube cubeToDraw = listeCube[x][y][z];
                    gl.glTranslatef((x-1f),(y-1f),(z-1f)); // va au centre du petit cube
                    if (x == 0) gl.glTranslatef(-distanceEntreCube,0f,0f); // decalage si à gauche de origine
                    if (x == 2) gl.glTranslatef(distanceEntreCube,0f,0f); // decalage si à droite de origine
                    if (y == 0) gl.glTranslatef(0f,-distanceEntreCube,0f); // decalage si à l'avant de origine
                    if (y == 2) gl.glTranslatef(0f,distanceEntreCube,0f); // decalage si à l'arrière de origine
                    if (z == 0) gl.glTranslatef(0f,0f,-distanceEntreCube); // decalage si à gauche de origine
                    if (z == 2) gl.glTranslatef(0f,0f,distanceEntreCube); // decalage si à gauche de origine
                    gl.glTranslatef(-0.5f,-0.5f,-0.5f); //passage du centre du cube au coin avant bas gauche là ou demarre dessin de drawCube()

                    drawCube(gl,cubeToDraw);
                    gl.glPopMatrix();
                }
            }
        }
    }

    // besoin de definir le cube dans un repère 3D (x,y,z)e [-1,1]³ (en petit cube) et non pas Face puis (x,y) e [0,2]² (par facette)

    public void setCube(RubiksCube rCube){ //Cree les petits cube en fonction de la configuration par facette de RubiksCube

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
        listeCube[0][2][2] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B, 2,2),rCube.getFacetColor(Face.L,0,2),Color.BLACK,rCube.getFacetColor(Face.U,0,2),Color.BLACK,-1,1,1);
        //[0,1,1]
        listeCube[1][2][2] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,1,2),Color.BLACK,Color.BLACK,rCube.getFacetColor(Face.U,1,2),Color.BLACK,0,1,1);
        //[1,1,1]
        listeCube[2][2][2] = new Cube(Color.BLACK,rCube.getFacetColor(Face.B,0,2),Color.BLACK,rCube.getFacetColor(Face.R,2,2),rCube.getFacetColor(Face.U,2,2),Color.BLACK,1,1,1);

    }
/**------------------------------------METHODE RELATIVE A L'ANIMATION DU CUBE (ROTATION)-----------------------------**/
    public boolean isRotating() //determine si le cube a deja une face qui est en train de tourner, eviter plusieurs mouvement en meme temps
    {
        return rotateX + rotateY + rotateZ > -3; // valeur par default des rotate font = -3
    }


    /* Rotations :
           rotateX = 0 => tourne L   rotateX = 2 => tourne R selon axe X
           rotateY = 0 => tourne F   rotateY = 2 => tourne B selon axe Y
           rotateZ = 0 => tourne D   rotateZ = 2 => tourne U selon axe Z
     */
    public void updateAngles() // va etre appele a chaque boucle de display et permet donc d'avoir les angles qui s'incrémente au fur et a mesure pour effectuer la rotation
    {
        Direction direction = (rotationSpeed > 0) ? Direction.CLOCKWISE : Direction.COUNTER_CLOCKWISE;

        if (rotateX >=0)
        {
            coloneAnglesX[rotateX] += rotationSpeed;
            if (coloneAnglesX[rotateX] % 90f ==0)
            {
                coloneAnglesX[rotateX] = 0;
                if (rotateX ==0 && direction == Direction.CLOCKWISE){ rubiksCube.rotate(Rotation.L);}
                if (rotateX ==0 && direction == Direction.COUNTER_CLOCKWISE){rubiksCube.rotate(Rotation.Li);}
                if (rotateX ==2 && direction == Direction.CLOCKWISE) {rubiksCube.rotate(Rotation.Ri);}
                if (rotateX ==2 && direction == Direction.COUNTER_CLOCKWISE){ rubiksCube.rotate(Rotation.R);}

                rotateX = -1;

            }
        }else if (rotateY >=0)
        {
            ligneAnglesY[rotateY] += rotationSpeed;
            if (ligneAnglesY[rotateY] % 90f ==0)
            {
                ligneAnglesY[rotateY] = 0;
                if (rotateY == 0 && direction == Direction.CLOCKWISE) {rubiksCube.rotate(Rotation.F);}
                if (rotateY == 0 && direction == Direction.COUNTER_CLOCKWISE) {rubiksCube.rotate(Rotation.Fi);}
                if (rotateY == 2 && direction == Direction.CLOCKWISE) {rubiksCube.rotate(Rotation.Bi);}
                if (rotateY == 2 && direction == Direction.COUNTER_CLOCKWISE) {rubiksCube.rotate(Rotation.B);}
                rotateY = -1;
            }
        }else if (rotateZ >=0)
        {
            profondeurAnglesZ[rotateZ] += rotationSpeed;
            if (profondeurAnglesZ[rotateZ] % 90f ==0)
            {
                profondeurAnglesZ[rotateZ] = 0;
                if (rotateZ == 0 && direction == Direction.CLOCKWISE) rubiksCube.rotate(Rotation.D);
                if (rotateZ == 0 && direction == Direction.COUNTER_CLOCKWISE) rubiksCube.rotate(Rotation.Di);
                if (rotateZ == 2 && direction == Direction.CLOCKWISE) rubiksCube.rotate(Rotation.Ui);
                if (rotateZ == 2 && direction == Direction.COUNTER_CLOCKWISE) rubiksCube.rotate(Rotation.U);
                rotateZ = -1;
            }
        }
    }

    public void rotate(int face, Axis axis, boolean clock) // permet de faire une rotation et d'en définir le sens (horaire ou trigo)
    {
        if (!isRotating())
        {
            if (axis == Axis.X) rotateX = face;
            if (axis == Axis.Y) rotateY = face;
            if (axis == Axis.Z) rotateZ = face;
            rotationSpeed = clock ?  Math.abs(rotationSpeed) : -Math.abs(rotationSpeed); //permet de definir si la rotation est clockwise ou counter_clockwise
        }
    }


    public void setRubiksCube(RubiksCube cube){rubiksCube = cube;}

    public void randomGLMelange(){rubiksCube.randomMelange();}

    public RubiksCube getCube(){return rubiksCube;}

    /**----------CLASSE ET METHODE RELAVITE A LA RESOLUTION (THREAD POUR LA RESOLUDION DANS RandonSolverIhm----------**/


    private abstract class RandomSolverThread extends Thread {
        private boolean isTerminated = false;

        public void terminate() { isTerminated = true; }


        protected abstract boolean isComplete(int i);

        @Override
        public void run() {
            int i = 0;
            while (!isTerminated && !isComplete(i)) {
                while (isRotating()) {
                    try { Thread.sleep(10); }
                    catch (InterruptedException e) { }
                }
                doNextRotation(i);
                i++;
            }
        }
    }

    public void ScrambleAndSolve(){
        Solver solver = new Solver();
        setVisible(false);
        randomGLMelange();
        setVisible(true);

        if (animation == null || !animation.isAlive()){
            try {
                solution = solver.solve(rubiksCube);
                System.out.println("Solved in " + solution.size() + "moves");
                System.out.println(solution);
            }catch(NoSolutionFound e){
                System.out.println("ERROR : No solution !");
                return;
            }
            animation = new RandomSolverThread() {
                @Override
                protected boolean isComplete(int i) {return (i == solution.size());}

            };
            animation.start();
        }else{
            animation.terminate();
        }

    }

    public void Scramble(){
        setVisible(false);
        randomGLMelange();
        setVisible(true);
    }

    public void Solve(){
        Solver solver = new Solver();
        if (animation == null || !animation.isAlive()){
            try{
                solution = solver.solve(rubiksCube);
            }catch(NoSolutionFound e){
                return;
            }
            animation = new RandomSolverThread() {
                @Override
                protected boolean isComplete(int i) {return (i== solution.size());}
            };
            animation.start();
        }else{
            animation.terminate();
        }
    }

    public void InteractivSolver(){
        Solver solver = new Solver();
        try{
            solution = solver.solve(rubiksCube);
        }catch(NoSolutionFound e){
            return;
        }
    }
    public void doNextRotation(int indice){

        switch(solution.get(indice)) {
            case L:
                rotate(0, Axis.X,true);
                break;
            case B:
                rotate(2, Axis.Y,false);
                break;
            case R:
                rotate(2, Axis.X,false);
                break;
            case F:
                rotate(0, Axis.Y,true);
                break;
            case U:
                rotate(2, Axis.Z,false);
                break;
            case D:
                rotate(0, Axis.Z,true);
                break;
            case Li:
                rotate(0, Axis.X,false);
                break;
            case Bi:
                rotate(2, Axis.Y,true);
                break;
            case Ri:
                rotate(2, Axis.X,true);
                break;
            case Fi:
                rotate(0, Axis.Y,false);
                break;
            case Ui:
                rotate(2, Axis.Z,true);
                break;
            case Di:
                rotate(0, Axis.Z,false);
                break;
        }

    }

    public int getSizeSolution(){return solution.size();}
    public ArrayList<Rotation> getSolution(){return solution;}

    public void setRubiksCubecolor(Color[][][] color){ rubiksCube.setRubiksCubeColor(color);}
    public int[] getRubiksPermutation(){return rubiksCube.getPermutationTable();}
}
