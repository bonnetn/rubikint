// Etape 1 :
// Importation des packages Java 2
import java.applet.Applet;
import javax.vecmath.*;
import java.awt.*;
//import java.awt.Color;

import RubiksCube.*;
import RubiksCube.Color;
import com.sun.j3d.utils.behaviors.vp.*;

// Etape 2 :
// Importation des packages Java 3D
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;

public class ColorCube3D extends Applet {

  public ColorCube3D() {
    this.setLayout(new BorderLayout());

    // Etape 3 :
    // Creation du Canvas 3D
    Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

    this.add(canvas3D, BorderLayout.CENTER);

    // Etape 4 :
    // Creation d'un objet SimpleUniverse
    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

    // Etape 5 :
    // Positionnement du point d'observation pour avoir une vue correcte de la
    // scene 3D
    OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
    orbit.setSchedulingBounds(new BoundingSphere());
    simpleU.getViewingPlatform().setViewPlatformBehavior(orbit);
    //orbit.setMinRadius(100);
    simpleU.getViewingPlatform().setNominalViewingTransform();

    // Etape 6 :
    // Creation de la scene 3D qui contient tous les objets 3D que l'on veut visualiser
    BranchGroup scene = createSceneGraph();

    // Etape 7 :
    // Compilation de la scene 3D
    scene.compile();

    // Etape 8:
    // Attachement de la scene 3D a l'objet SimpleUniverse
    simpleU.addBranchGraph(scene);
  }

  /**
   * Creation de la scene 3D qui contient tous les objets 3D
   * @return scene 3D
   */
  public BranchGroup createSceneGraph() {
    // Creation de l'objet parent qui contiendra tous les autres objets 3D
    BranchGroup parent = new BranchGroup();

    /************ Partie de code concernant l'animation du cube *************/
    /* Elle sera expliquee en details dans les chapitres relatifs aux
     transformations geometriques et aux animations */
    TransformGroup objSpin = new TransformGroup();
    objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    Alpha rotationAlpha = new Alpha(-1, 4000);
    RotationInterpolator rotator =
        new RotationInterpolator(rotationAlpha, objSpin);
    BoundingSphere bounds = new BoundingSphere();
    rotator.setSchedulingBounds(bounds);
    objSpin.addChild(rotator);
    /*************** Fin de la partie relative a l'animation ***************/

    // Construction du cube couleur
    
    for(int x=-1; x<=1; x++) {
    	for( int y=-1; y<=1; y++) {
    		for( int z=-1; z<=1; z++) {
	        	
	    		Transform3D pos = new Transform3D();
	    	    pos.setTranslation( new Vector3d(0.4*x,0.4*y,z*0.4) );
	    	    
	    	    TransformGroup posG = new TransformGroup(pos);
	    	    
	    	    posG.addChild(new ColorCube(0.18));
	    	    //parent.addChild(posG);
    		}
    	}
    }
   Color[] orange={Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE};
   Color[] red={Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED};
   Color[] white={Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE};
   Color[] green={Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN};
   Color[] blue={Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE};
   Color[] yellow={Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW,Color.YELLOW};

    face(parent, new Vector3d(0,0,0.6), new Vector3d(1,0,0), new Vector3d(0,1,0),yellow);
    face(parent, new Vector3d(0,-0.6,0), new Vector3d(1,0,0), new Vector3d(0,0,1),blue);
    face(parent, new Vector3d(0,0,-0.6), new Vector3d(-1,0,0), new Vector3d(0,1,0),white);
    face(parent, new Vector3d(0,0.6,0), new Vector3d(-1,0,0), new Vector3d(0,0,1),green);
    
    face(parent, new Vector3d(0.6,0,0), new Vector3d(0,1,0), new Vector3d(0,0,1),red);
    face(parent, new Vector3d(-0.6,0,0), new Vector3d(0,-1,0), new Vector3d(0,0,1),orange);
    
    
    

    return parent;
  }
  
  void face( Group parent, Vector3d posV, Vector3d xdir, Vector3d ydir,Color[] color ) {
	  Transform3D origin = new Transform3D();
	  origin.setTranslation( posV );
	  
	  TransformGroup originG = new TransformGroup(origin);
	    
	  for( int x=-1; x<=1; x++) {
		  for( int y=-1; y<=1; y++) {
			  Transform3D pos = new Transform3D();
	    	  pos.setTranslation( new Vector3d(0.4*(x*xdir.x+y*ydir.x),0.4*(x*xdir.y+y*ydir.y),0.4*(x*xdir.z+y*ydir.z)) );
	    	  TransformGroup posG = new TransformGroup(pos);
	    	    
	    	  facet(posG,0.19f, xdir, ydir,Color.givecolor(color[x+1+(y+1)*3]));
	    	  originG.addChild(posG);
		  }
	  }
	  parent.addChild(originG);
  }
  
  void facet( Group parent, float size, Vector3d xdir, Vector3d ydir,Color3b color) {
	  
	    QuadArray lsa = new QuadArray(4*2,QuadArray.COORDINATES | QuadArray.COLOR_3 );
		  
		  Point3f pts[] = new Point3f[4];
		  for(int i=0;i<4;i++)
			  pts[i]=new Point3f();
		  
		 /* Vector3f normals[] = new Vector3f[4];
		  for(int i=0;i<4;i++)
			  normals[i]=new Vector3f();
		  */

		  	pts[0].x=(float)(xdir.x-ydir.x)*size; 
		    pts[0].y=(float)(xdir.y-ydir.y)*size;
		    pts[0].z=(float)(xdir.z-ydir.z)*size;

		    pts[1].x=(float)(xdir.x+ydir.x)*size; 
		    pts[1].y=(float)(xdir.y+ydir.y)*size;
		    pts[1].z=(float)(xdir.z+ydir.z)*size;

		    pts[2].x=(float)(-xdir.x+ydir.x)*size; 
		    pts[2].y=(float)(-xdir.y+ydir.y)*size;
		    pts[2].z=(float)(-xdir.z+ydir.z)*size;
		    
		    pts[3].x=(float)(-xdir.x-ydir.x)*size; 
		    pts[3].y=(float)(-xdir.y-ydir.y)*size;
		    pts[3].z=(float)(-xdir.z-ydir.z)*size;
		    
		    
		    
	    //normals[0].z=-1;normals[1].z=-1;normals[2].z=-1;normals[3].z=-1;
	    
	    //lsa.setNormals(0, normals);
	    lsa.setCoordinates(0, pts);
	    lsa.setColor(0, color);
	    lsa.setColor(1, color);
	    lsa.setColor(2, color);
	    lsa.setColor(3, color);
	    
	    
	    parent.addChild(new Shape3D(lsa));
  }
  
  /**
   * Etape 9 :
   * Methode main() nous permettant d'utiliser cette classe comme une applet
   * ou une application.
   * @param args
   */
  public static void main(String[] args) {
    Frame frame = new MainFrame(new ColorCube3D(), 256, 256);
  }
}