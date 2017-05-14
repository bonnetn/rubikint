/*
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import resolution.Maneuver;
import resolution.Move;
import resolution.NoSolutionFound;
import resolution.BFSSearcher;
import resolution.ValidatorFacet;
import resolution.ValidatorSet;
import rubikscube.RubiksCube;
import rubikscube.enums.Rotation;

public class SolverTest {

	@Test
	public void testSolve() {

		RubiksCube rb = new RubiksCube();

		BFSSearcher s = new BFSSearcher();
		Maneuver m[] = { 
				new Move(Rotation.U),
				new Move(Rotation.D),
				new Move(Rotation.R),
				new Move(Rotation.L),
				new Move(Rotation.F),
				new Move(Rotation.B),
		};
		
		Maneuver solution;
		
		
		ValidatorSet v = new ValidatorSet();
		
		v.add( new ValidatorFacet(14));
		
		
		while(v.isValid(rb))
			rb.randomMelange();
		
		try {
			solution = s.getSolution( rb, v, m );
		} catch( NoSolutionFound nop ) {
			fail("No solution found...");
			return;
		}
		
		solution.apply(rb);
		assertTrue("The solver did not manage to solve the cube properly",v.isValid(rb));
		
	}

}
*/
