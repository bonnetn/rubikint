package rendering;

/**
 * Created by florian on 17/02/17.
 */

<<<<<<< HEAD
//  1 cube corresponds à 6 facettes. Il s'agit d'un petit cube du Rubik's cube.



import rubikscube.enums.Color;
import rubikscube.enums.Face;


import javax.media.opengl.GL2;
import java.awt.*;

public class Cube {

    public int x,y;
    public Face face;

    public int x3d,y3d,z3d;

    //bit pour definir les faces visibles.
    public int frontFace = (1 << 0);
    public int rearFace = (1 << 1);
    public int leftFace = (1 << 2);
    public int rightFace = (1 << 3);
    public int topFace = (1 << 4);
    public int downFace = (1 << 5);

    public Color frontColor;
    public Color rearColor;
    public Color leftColor;
    public Color rightColor;
    public Color topColor;
    public Color downColor;

    //constructeur pour les couleurs
    public Cube(Color front, Color rear, Color left, Color right, Color top, Color down, Face face, int x, int y){
        this.frontColor = front;
        this.rearColor = rear;
        this.leftColor = left;
        this.rightColor = right;
        this.topColor = top;
        this.downColor = down;
        this.x = x;
        this.y = y;
        this.face = face;

    }

    //constructeur pour la position
    public Cube(int x, int y, Face face){
        this.x = x;
        this.y = y;
        this.face = face;
    }

    public int[] getPosition(){
        int[] position = new int[3];
        position[0] = this.face.getValue();
        position[1] = this.x;
        position[2] = this.y;
        return position;
    }

    // besoin de definir le cube dans un repère 3D (x,y,z)e [0,2]³ et non pas Face puis (x,y) e [0,2]²
    public void setXYZ(Face face, int x, int y){
        // doit, a partir de face, x et y definir x3d, y3d, z3d
    }

    // methode pour definir les facettes coloré de notre petit cube
    public int getColoredFaces(){
        int x = this.x3d;
        int y = this.y3d;
        int z = this.z3d;
        int coloredFaces;

        /* operation sur les bit, par exemple ici si le coin haut droit devant (blanc rouge et bleue) donc position (2,0,2) coloredFaces = 0101001b */
        if (x==0 /*si a gauche */){
            coloredFaces= this.leftFace;
        }else if (x==2 /* si a droite*/ ){
            coloredFaces= this.rightFace;
        }else{
            coloredFaces=0;
        }

        if (y==0 /* si devant */){
            coloredFaces |= this.frontFace;
        }else if (y==2 /*si a l arriere*/){
            coloredFaces |= this.rearFace;
        }else{
            coloredFaces |= 0;
        }

        if (z==0 /* si en bas */){
            coloredFaces |= this.downFace;
        }else if (z==2 /* si en haut */){
            coloredFaces |= this.topFace;
        }else {
            coloredFaces |= 0;
        }
        return coloredFaces;
    }

=======
import javax.media.opengl.GL2;

public class Cube {

    private float demiTaille;

    private int[] position;

    public Cube(float taille, int x, int y, int z){
        demiTaille =taille;

        position = new int[3];
        position[0] = x;
        position[1] = y;
        position[2] = z;
    }

    public void draw(GL2 gl){
        gl.glBegin(GL2.GL_QUADS); // cube  formé de  6 faces : 1 carré = 4 vertex

        gl.glColor3f(1f,0f,0f); // face avant en rouge
        gl.glVertex3f(position[0] - demiTaille, position[1] - demiTaille , position[2] + demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] - demiTaille, position[2] + demiTaille);
        gl.glVertex3f( position[0] - demiTaille, position[1] + demiTaille, position[2] + demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] + demiTaille, position[2] + demiTaille);

        gl.glColor3f(1f,0.27f,0f);// face arriere en orange
        gl.glVertex3f(position[0] - demiTaille, position[1] - demiTaille , position[2] - demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] - demiTaille, position[2] - demiTaille);
        gl.glVertex3f( position[0] - demiTaille, position[1] + demiTaille, position[2] - demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] + demiTaille, position[2] - demiTaille);

        gl.glColor3f(1f,1f,1f); // face du haut blanc
        gl.glVertex3f(position[0] - demiTaille, position[1] + demiTaille , position[2] - demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] + demiTaille, position[2] - demiTaille);
        gl.glVertex3f( position[0] - demiTaille, position[1] + demiTaille, position[2] + demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] + demiTaille, position[2] + demiTaille);

        gl.glColor3f(1f,1f,0f); //face du bas en jaune
        gl.glVertex3f(position[0] - demiTaille, position[1] - demiTaille , position[2] - demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] - demiTaille, position[2] - demiTaille);
        gl.glVertex3f( position[0] - demiTaille, position[1] - demiTaille, position[2] + demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] - demiTaille, position[2] + demiTaille);

        gl.glColor3f(0f,0f,1f); //face de droite en bleu
        gl.glVertex3f(position[0] + demiTaille, position[1] + demiTaille , position[2] - demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] - demiTaille, position[2] - demiTaille);
        gl.glVertex3f( position[0] + demiTaille, position[1] + demiTaille, position[2] + demiTaille);
        gl.glVertex3f(position[0] + demiTaille, position[1] - demiTaille, position[2] + demiTaille);

        gl.glColor3f(0f,1f,0f); //face gauche verte
        gl.glVertex3f(position[0] - demiTaille, position[1] + demiTaille , position[2] - demiTaille);
        gl.glVertex3f(position[0] - demiTaille, position[1] - demiTaille, position[2] - demiTaille);
        gl.glVertex3f( position[0] - demiTaille, position[1] + demiTaille, position[2] + demiTaille);
        gl.glVertex3f(position[0] - demiTaille, position[1] - demiTaille, position[2] + demiTaille);
        gl.glEnd();
    }
>>>>>>> OpenGLRenderer test affichage 1 cube
}
