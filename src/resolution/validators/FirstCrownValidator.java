package resolution.validators;

import resolution.Validator;
import rubikscube.RubiksCube;
import rubikscube.enums.Face;

public class FirstCrownValidator extends Validator {

	int n;
	Validator validators[] = new Validator[8];
	
	public FirstCrownValidator( int numberOfFacetsThatShouldBeCorrect ) {
		n = numberOfFacetsThatShouldBeCorrect;
		int index = 0;
		for( int x=0; x<=2; x++) {
			for( int y=0; y<=2; y++ ) {
				if( x != 1 || y != 1) {
					validators[index] = new EdgePositionned(Face.U, x, y);
					index++;
				}
			}
		}
		
	}
	
	@Override
	public boolean isValid(RubiksCube cube) {
		int m = 0;
		for( Validator v : validators ) {
			if( v.isValid(cube) )
				m++;
		}
		return m>=n;
	}

	
	
}
