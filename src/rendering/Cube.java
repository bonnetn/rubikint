package rendering;

/**
 * Created by florian on 17/02/17.
 */

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
}
