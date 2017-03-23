import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import resolution.BFSSearcher;
import resolution.Validator;
import resolution.validators.EdgePositionned;
import resolution.validators.FirstCrownValidator;
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
		rb.randomMelange();
		
		long startTime = System.nanoTime();
		BFSSearcher b = new BFSSearcher(rb, rotation, test);

		for( int i=1; i<=8; i++) {
			
			Validator v = new FirstCrownValidator(i);
			ArrayList<Validator> a = new ArrayList<Validator>();
			a.add(v);
			b.v = a;
			System.out.println("Movment count: " + b.getSolution().size());
			rb.printFace(Face.U);
			
			
		}
		System.out.println("Time to solve: " +((System.nanoTime() - startTime)/1000/1000) +"ms");
		
		
		System.out.println("done.");
		
	
		
		
				
	}
}
