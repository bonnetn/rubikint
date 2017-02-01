
import rendering.Java3DRenderer;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

public class Test {

	public static void main( String[] args) {
		Java3DRenderer r = new Java3DRenderer();
		RubiksCube rc = new RubiksCube(); //R L U D OK
		rc.rotate(Rotation.U);
		Color m=rc.getFacetColor(Face.R,0,0);
		 m=rc.getFacetColor(Face.R,0,1);
		 m=rc.getFacetColor(Face.R,0,2);
		 m=rc.getFacetColor(Face.R,1,0);
		 m=rc.getFacetColor(Face.R,1,1);
		 m=rc.getFacetColor(Face.R,1,2);
		 m=rc.getFacetColor(Face.R,2,0);
		 m=rc.getFacetColor(Face.R,2,1);
		 m=rc.getFacetColor(Face.R,2,2);
		rc.randomMelange();
		 r.drawItem(rc);
		
	}
	
}
