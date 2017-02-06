package resolution;

import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;

/**
 * Created by shininisan on 26/01/17.
 */
public class FacetConfig {
    private int x; //décalage par rapport à une face
    private int y;
    private Face face;
    private Color couleur;
    public FacetConfig(int x, int y, Face face, Color couleur)
    {
        this.x=x;
        this.y=y;
        this.face=face;
        this.couleur=couleur;
    }
    public void changeFace( RubiksCube cube,Face nouvelleFace)
    {
        if(this.face==Face.F)
        {
            this.face=nouvelleFace;
            this.couleur=cube.getFacetColor(nouvelleFace,1,1);
        }


    }

    public Color getCouleur() {
        return couleur;
    }

    public Face getFace() {
        return face;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
