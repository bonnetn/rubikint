import java.util.ArrayList;

import resolution.*;
import rubikscube.*;
import rubikscube.enums.*;


public class Test2 {
	

	public static void main( String[] args) {
	
		Solver solver = new Solver();
		RubiksCube rubiksCube = new RubiksCube();
		rubiksCube.randomMelange();
		
		System.out.println("Solving... please wait...");
		ArrayList<Rotation> solution;
		try {
			solution = solver.solve(rubiksCube);
		} catch (NoSolutionFound e) {
			System.out.println("ERROR: no solution! :(");
			return;
		}
		
		System.out.println("Solved in " + solution.size() + " moves!");
		
		System.out.println("Before:");
		rubiksCube.printFace(Face.U);
		rubiksCube.printFace(Face.D);
		rubiksCube.printFace(Face.L);
		rubiksCube.printFace(Face.F);
		rubiksCube.printFace(Face.R);
		rubiksCube.printFace(Face.B);
		
		for( Rotation r : solution ) {
			rubiksCube.rotate(r);
		}
		
		System.out.println("After:");
		rubiksCube.printFace(Face.U);
		rubiksCube.printFace(Face.D);
		rubiksCube.printFace(Face.L);
		rubiksCube.printFace(Face.F);
		rubiksCube.printFace(Face.R);
		rubiksCube.printFace(Face.B);
		
		
		
		

		
		
		
	}
		
	

}

