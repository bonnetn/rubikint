import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
		
		Random rand = new Random(42);
		
		for(int i=0;i<60;i++)
		{
			
			int rot=rand.nextInt(11 + 1);
			rb.rotate(Rotation.values()[rot]);
		}
		
		
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
				
				System.out.println("nb coups:"+b.getSolution().size());
				//rb.printFace(Face.U);
			}
		}
	
		System.out.println("Time to solve: " +((System.nanoTime() - startTime)/1000/1000) +"ms");
		
		
		System.out.println("done.");
		
	
		
		
				
	}
}
