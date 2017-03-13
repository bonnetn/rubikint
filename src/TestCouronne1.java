import java.util.ArrayList;

import resolution.BFSSearcher;
import resolution.Validator;
import resolution.validators.EdgePositionned;
import rubikscube.RubiksCube;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

public class TestCouronne1 {
	public static void main( String[] args ) {
		RubiksCube rb = new RubiksCube();
		ArrayList<Rotation> rotation = new ArrayList<Rotation>();
		
		rotation.add(Rotation.B);
		rotation.add(Rotation.Bi);
		
		rotation.add(Rotation.U);
		rotation.add(Rotation.Ui);
		
		rotation.add(Rotation.R);
		rotation.add(Rotation.Ri);
		
		rotation.add(Rotation.L);
		rotation.add(Rotation.Li);
		
		rotation.add(Rotation.F);
		rotation.add(Rotation.Fi);
		
		rotation.add(Rotation.D);
		rotation.add(Rotation.Di);
	
		
		ArrayList<Validator> test = new ArrayList<Validator>();
		
		rb.randomMelange();
		for( int x=0; x<=2; x++)
		{
			for( int y=0; y<=2; y++) {
				System.out.println("Pos " + x +";" + y);
				System.out.println();
				EdgePositionned v =  new EdgePositionned(Face.U, x,y);
				test.add(v);
				
				BFSSearcher b = new BFSSearcher(rb, rotation, test);
				b.getSolution();
			}
		}
		
		System.out.println("done.");
				
	}
}
