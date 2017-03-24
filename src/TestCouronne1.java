import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import resolution.*;
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

		ArrayList<Procedure> listeProcedure= new ArrayList<Procedure>();

		RubiksConfiguration couronne2=new RubiksConfiguration(new ArrayList<FacetConfig>(Arrays.asList(
				new FacetConfig(0,1,Face.F,Face.F),new FacetConfig(2,1,Face.F,Face.F),
				new FacetConfig(0,1,Face.R,Face.R),new FacetConfig(2,1,Face.R,Face.R),
				new FacetConfig(0,1,Face.B,Face.B),new FacetConfig(2,1,Face.B,Face.B),
				new FacetConfig(0,1,Face.L,Face.L),new FacetConfig(2,1,Face.L,Face.L)
		)));
		ArrayList<Rotation> rotater=new ArrayList<Rotation>(Arrays.asList(Rotation.D,Rotation.R,Rotation.Di,Rotation.Ri,Rotation.Di,Rotation.Fi,Rotation.D,Rotation.F));
		RubiksConfiguration conf=new RubiksConfiguration(new ArrayList<FacetConfig>(Arrays.asList(new FacetConfig(1,0,Face.F,Face.R))));
		Procedure p=new Procedure(rotater,10,conf,Rotation.B,couronne2);

		Procedure p2=new Procedure(p);
		p2.symetry();
		for(int i=1;i<=4;i++) {
			listeProcedure.add(new Procedure(p));
			listeProcedure.add(new Procedure(p2));
			p.translation(Face.values()[Face.F.getValue()+i]);
			p2.translation(Face.values()[Face.F.getValue()+i]);
		}
		ProceduralSolver proceduralSolver=new ProceduralSolver(rb,10,listeProcedure);
		proceduralSolver.resolve();
		System.out.println("U");
		rb.printFace(Face.U);
		System.out.println("F");
		rb.printFace(Face.F);
		System.out.println("L");
		rb.printFace(Face.L);
		System.out.println("R");
		rb.printFace(Face.R);
		System.out.println("B");
		rb.printFace(Face.B);



	}
}
