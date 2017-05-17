package rendering;

/**
 * Created by florian on 17/02/17.
 *
 * CETTE CLASSE EST UTILISEE DANS OpenGLRenderer pour passer DE LA CONFIGURATION PAR FACETTE A UNE CONFIGURATION PAR PEETIT CUBE
 */

//  1 cube corresponds à 6 facettes. Il s'agit d'un petit cube du Rubik's cube.
import rubikscube.enums.Color;

public class Cube{

    public int x,y,z;

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

    public int[] getPosition(){
        int[] position = new int[3];
        position[0] = this.z;
        position[1] = this.x;
        position[2] = this.y;
        return position;
    }
}
