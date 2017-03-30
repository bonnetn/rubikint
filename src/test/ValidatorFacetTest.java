package test;

import static org.junit.Assert.*;

import org.junit.Test;

import resolution.ValidatorFacet;
import rubikscube.RubiksCube;
import rubikscube.enums.Rotation;

public class ValidatorFacetTest {

	@Test
	public void test() {
		ValidatorFacet v = new ValidatorFacet( 1 );
		RubiksCube r = new RubiksCube();
		
		assertTrue( "Rubiks should be valid", v.isValid(r) );
		r.rotate(Rotation.U);
		assertFalse( "Rubiks shouldn't be valid", v.isValid(r) );
	}

}
