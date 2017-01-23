package RubiksCube;

import com.sun.j3d.utils.applet.MainFrame;

import javax.media.j3d.BranchGroup;
import javax.vecmath.Vector3d;
import java.awt.*;

/**
 * Created by shininisan on 23/01/17.
 */
public class RubiksRenderer {
    private Displayer3D displayer;
    private RubiksCube cube;
    private BranchGroup parent;
    private Frame frame;
    public RubiksRenderer(RubiksCube cube)
    {
        this.cube=cube;

        //parent= displayer.createSceneGraph();
       // frame = new MainFrame(displayer, 256, 256);

    }
    public void afficheCubeFait()
    {
        Color[] orange={Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE};
        Color[] red={Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED};
        Color[] white={Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE};
        Color[] green={Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN};
        Color[] blue={Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE};
        Color[] yellow={Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW};
        Color[][] colorsToGive={red,white,blue,yellow,green,orange};//FURDLB
        displayer=new Displayer3D(colorsToGive);

    }

    public void renderCube()
    {
        Color[][] toRender=new Color[6][];
        for (int i=0;i<6;i++)
        {
            toRender[i]=this.cube.getColorFace(RubiksFace.values()[i]);
        }
        displayer=new Displayer3D(toRender);
    }
    public static void main(String[] args) {
        RubiksRenderer p=new RubiksRenderer(new RubiksCubeFacetPermutation());
        p.renderCube();
        Frame frame = new MainFrame(p.displayer, 256, 256);}
}
