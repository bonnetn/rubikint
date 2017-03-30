package resolution;

import rubikscube.AbstractRubiksCube;

public class ValidatorFacet extends Validator {
	
	private int _facet;
	
	public ValidatorFacet( int facet ) throws IllegalArgumentException {
		
		if( facet < 0 || facet > 47 )
			throw new IllegalArgumentException();
		
		_facet = facet;
	}

	@Override
	public boolean isValid(AbstractRubiksCube cube) {
		return cube.getPermutationTable()[_facet] == _facet;
	}

}
