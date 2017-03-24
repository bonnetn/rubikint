package rendering;

import com.sun.j3d.utils.universe.SimpleUniverse;

import rubikscube.enums.Color;
import rubikscube.enums.Face;


import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;

import javax.media.j3d.*;
import javax.*;
import javax.vecmath.Vector3d;
import javax.vecmath.Color3b;
import javax.vecmath.Point3f;

public class Java3DRenderer extends AbstractRenderer {

	

   private SimpleUniverse universe;
   private Canvas3D canvas3D;
	
	private static Color3b convertRubiksColorToJava3DColor( Color c ) {
		 
	     Color3b result = null;
	     switch (c) {
	 
	         case ORANGE:
	             result = new Color3b((byte) 255, (byte) 128, (byte) 0);
	             break;
	         case YELLOW:
	             result = new Color3b((byte) 255, (byte) 255, (byte) 0);
	             break;
	         case BLUE:
	             result = new Color3b((byte) 0, (byte) 0, (byte) 255);
	             break;
	         case WHITE:
	             result = new Color3b((byte) 255, (byte) 255, (byte) 255);
	             break;
	         case RED:
	             result = new Color3b((byte) 255, (byte) 0, (byte) 0);
	             break;
	         case GREEN:
	             result = new Color3b((byte) 0, (byte) 255, (byte) 0);
	             break;
	 
	         default:
	             throw new IllegalArgumentException();
	 
	     }
	     return result;
	 
	 }

	private static void drawFacet( Group parent, float size, Vector3d xdir, Vector3d ydir, Color facetColor) {
		
		Color3b color = convertRubiksColorToJava3DColor(facetColor);
		
		QuadArray lsa = new QuadArray(4*2,QuadArray.COORDINATES | QuadArray.COLOR_3 );
		  
		Point3f pts[] = new Point3f[4];
		for(int i=0;i<4;i++)
			pts[i]=new Point3f();
		
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
			   
	    lsa.setCoordinates(0, pts);
	    lsa.setColor(0, color);
	    lsa.setColor(1, color);
	    lsa.setColor(2, color);
	    lsa.setColor(3, color);
	    
	    parent.addChild(new Shape3D(lsa));
  }
	
	private static void drawFace( Group parent, Vector3d posV, Vector3d xdir, Vector3d ydir, Renderable cube, Face face) {
		  
		  Transform3D origin = new Transform3D();
		  origin.setTranslation( posV );
		  
		  TransformGroup originG = new TransformGroup(origin);
		    
		  for( int x=-1; x<=1; x++) {
			  for( int y=-1; y<=1; y++) {
				  Transform3D pos = new Transform3D();
		    	  pos.setTranslation( new Vector3d(0.4*(x*xdir.x+y*ydir.x),0.4*(x*xdir.y+y*ydir.y),0.4*(x*xdir.z+y*ydir.z)) );
		    	  TransformGroup posG = new TransformGroup(pos);
		    	    
		    	  Color faceColor = cube.getFacetColor(face, x+1, y+1);
		    	  drawFacet( posG, 0.19f, xdir, ydir, faceColor);
		    	  originG.addChild(posG);
			  }
		  }
		  parent.addChild(originG);
	  }
	
	private static void drawCube( Group parent, Renderable cube ) {
		 drawFace(parent, new Vector3d(0,0,0.6), new Vector3d(1,0,0), new Vector3d(0,1,0), cube, Face.F);
		 drawFace(parent, new Vector3d(0,0.6,0), new Vector3d(1,0,0), new Vector3d(0,0,-1), cube, Face.U);
	
		 drawFace(parent, new Vector3d(-0.6,0,0), new Vector3d(0,0,1), new Vector3d(0,1,0), cube, Face.L );
		 drawFace(parent, new Vector3d(0,-0.6,0), new Vector3d(1,0,0), new Vector3d(0,0,1), cube, Face.D );
		 drawFace(parent, new Vector3d(0,0,-0.6), new Vector3d(-1,0,0), new Vector3d(0,1,0), cube, Face.B );

		 drawFace(parent, new Vector3d(0.6,0,0), new Vector3d(0,0,-1), new Vector3d(0,1,0), cube, Face.R);

	}
	
	@Override
	public void drawItem(Renderable item) {

		   BranchGroup group = new BranchGroup();

		   //group.addChild(new ColorCube(0.3));
		   drawCube( group, item );
		   universe.getViewingPlatform().setNominalViewingTransform();
		   OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
		   orbit.setSchedulingBounds(new BoundingSphere());
		   universe.getViewingPlatform().setViewPlatformBehavior(orbit);
		   universe.addBranchGraph(group);
	}

	public Java3DRenderer() {

		canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

		SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);
		canvas3D.getView().setFieldOfView(0.30);
		canvas3D.getView().setWindowEyepointPolicy(View.RELATIVE_TO_WINDOW);
		simpleUniverse.getViewingPlatform().setNominalViewingTransform();

		universe = simpleUniverse;

	}

	public Canvas3D getCanvas3D() {
		return canvas3D;
	}

}
