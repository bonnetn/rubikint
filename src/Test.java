
import Rendering.Java3DRenderer;
import RubiksCube.RubiksCube;
import RubiksCube.enums.Rotation;

public class Test {

	public static void main( String[] args) {
		Java3DRenderer r = new Java3DRenderer();
		RubiksCube rc = new RubiksCube();
		//rc.rotate(Rotation.R);
		r.drawItem(rc);
		
	}
	
}
