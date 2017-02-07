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
    private Face couleurFaceCorrespondante;
    public FacetConfig(int x, int y, Face face, Face couleurFaceCorrespondante)
    {
        this.x=x;
        this.y=y;
        this.face=face;
        this.couleurFaceCorrespondante=couleurFaceCorrespondante;
    }
    public void changeFace( RubiksCube cube,Face nouvelleFace)
    {
        if(this.face==Face.F)
        {
            this.face=nouvelleFace;
            this.couleurFaceCorrespondante=nouvelleFace;
        }


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

    public Face getCouleurFaceCorrespondante() {
        return couleurFaceCorrespondante;
    }

    public void setCouleurFaceCorrespondante(Face couleurFaceCorrespondante) {
        this.couleurFaceCorrespondante = couleurFaceCorrespondante;
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