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
    //private BranchGroup parent;
    //private Frame frame;
    public RubiksRenderer(RubiksCube cube)
    {
        this.cube=cube;

        //parent= displayer.createSceneGraph();
       // frame = new MainFrame(displayer, 256, 256);

    }
    
    /**
     * Retourne un tableau constitué de 9 fois la couleur choisie
     * @param color 
     * @return La face colorée uniformément
     */
    private Color[] _getColorFace( Color color ) {
    	Color[] tbl = new Color[9];
    	for(int i=0; i<9; i++)
    		tbl[i] = color;
    	return tbl;
    	
    }
    
    public void afficheCubeFait()
    {
        Color[] orange = _getColorFace(Color.ORANGE);
        Color[] red    = _getColorFace(Color.RED); 
        Color[] white  = _getColorFace(Color.WHITE);
        Color[] green  = _getColorFace(Color.GREEN);
        Color[] blue   = _getColorFace(Color.BLUE);
        Color[] yellow = _getColorFace(Color.YELLOW);
        
        Color[][] colorsToGive={red,white,blue,yellow,green,orange};//FURDLB
        displayer=new Displayer3D(colorsToGive);

    }

    public void renderCube()
    {
        Color[][] toRender=new Color[6][];
        for (int i=0;i<6;i++)
            toRender[i]=this.cube.getColorFace(RubiksFace.values()[i]);
        
        displayer=new Displayer3D(toRender);
    }
    
    public static void main(String[] args) {
    
    	RubiksRenderer p=new RubiksRenderer(new RubiksCubeFacetPermutation());
        p.renderCube();
        //Frame frame = new MainFrame(p.displayer, 256, 256);
    }
}
