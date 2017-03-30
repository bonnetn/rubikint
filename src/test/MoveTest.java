package test;

import static org.junit.Assert.*;

import org.junit.Test;

import resolution.Move;
import rubikscube.enums.Rotation;

public class MoveTest {

	@Test
	public void testInverse() {
		
		assertEquals("invert(U) != Ui", (new Move(Rotation.U)).getInverse(), new Move(Rotation.Ui));
		assertEquals("invert(D) != Di", (new Move(Rotation.D)).getInverse(), new Move(Rotation.Di));
		assertEquals("invert(R) != Ri", (new Move(Rotation.R)).getInverse(), new Move(Rotation.Ri));
		assertEquals("invert(L) != Li", (new Move(Rotation.L)).getInverse(), new Move(Rotation.Li));
		assertEquals("invert(F) != Fi", (new Move(Rotation.F)).getInverse(), new Move(Rotation.Fi));
		assertEquals("invert(B) != Bi", (new Move(Rotation.B)).getInverse(), new Move(Rotation.Bi));

		
	}
	
	@Test
	public void testEqual() {
		
		Move upRot1 = new Move(Rotation.U);
		Move upRot2 = new Move(Rotation.U);
		
		assertEquals("Up != Up", upRot1, upRot1);
		assertEquals("Up (1) != Up (2)", upRot1, upRot2);

		for (Rotation r : Rotation.values()) {
			for (Rotation r2 : Rotation.values()) {
				if(r != r2 ) {
					assertFalse("Two different Moves are equal", (new Move(r)).equals(new Move(r2)));
				} else {
					assertTrue("Two same moves are not equal", (new Move(r)).equals(new Move(r2)));
				}
			}
		}
		
		
	}
	
	

}
