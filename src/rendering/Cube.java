package rendering;

/**
 * Created by florian on 17/02/17.
 */

//  1 cube corresponds à 6 facettes. Il s'agit d'un petit cube du Rubik's cube.



import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;

import javax.media.opengl.GL2;
import java.awt.*;

public class Cube{

    public int x,y,z;
    public int x3d,y3d,z3d;

    //bit pour definir les faces visibles.
    public int frontFace = (1 << 0);
    public int backFace = (1 << 1);
    public int leftFace = (1 << 2);
    public int rightFace = (1 << 3);
    public int upFace = (1 << 4);
    public int downFace = (1 << 5);

    public Color frontColor;
    public Color backColor;
    public Color leftColor;
    public Color rightColor;
    public Color upColor;
    public Color downColor;

    public final Cube[] listeCube = new Cube[27];



    //constructeur pour les couleurs
    public Cube(Color front, Color back, Color left, Color right, Color up, Color down, int z, int x, int y){
        this.frontColor = front;
        this.backColor = back;
        this.leftColor = left;
        this.rightColor = right;
        this.upColor = up;
        this.downColor = down;
        this.x = x;
        this.y = y;
        this.z = z;

    }

    //constructeur pour la position
    public Cube(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int[] getPosition(){
        int[] position = new int[3];
        position[0] = this.z;
        position[1] = this.x;
        position[2] = this.y;
        return position;
    }


    // methode pour definir les facettes coloré de notre petit cube
    public int getColoredFaces(){
        int[] position = this.getPosition();

        int x = position[0];
        int y = position[1];
        int z = position[2];
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
            coloredFaces |= this.backFace;
        }else{
            coloredFaces |= 0;
        }

        if (z==0 /* si en bas */){
            coloredFaces |= this.downFace;
        }else if (z==2 /* si en haut */){
            coloredFaces |= this.upFace;
        }else {
            coloredFaces |= 0;
        }
        return coloredFaces;
    }
}
