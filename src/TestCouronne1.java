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
		rb.rotate(Rotation.U);
		rb.rotate(Rotation.R);
		rb.rotate(Rotation.B);
		rb.rotate(Rotation.D);
		rb.rotate(Rotation.U);
		rb.rotate(Rotation.U);
		rb.rotate(Rotation.R);
		rb.rotate(Rotation.D);
		rb.rotate(Rotation.B);
		rb.rotate(Rotation.F);
		rb.rotate(Rotation.F);
		rb.rotate(Rotation.L);
		rb.rotate(Rotation.L);
		
		
		
		long startTime = System.nanoTime();
		BFSSearcher b = new BFSSearcher(rb, rotation, test);
		for( int x=0; x<=2; x++) {
			for( int y=0; y<=2; y++) {
				
				if( x==1 && y==1)
					continue;
				System.out.println("Pos " + x +";" + y);
			
				EdgePositionned v =  new EdgePositionned(Face.U, x,y);
				test.add(0,v);
				b.v = test;
				
				b.getSolution();
				rb.printFace(Face.U);
			}
		}
		System.out.println( (System.nanoTime() - startTime)/1000/1000 );
		
		System.out.println("done.");
		
	
		
		
				
	}
}
