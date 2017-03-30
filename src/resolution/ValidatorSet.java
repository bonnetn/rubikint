package resolution;

import java.util.ArrayList;

import rubikscube.AbstractRubiksCube;

public class ValidatorSet extends Validator {

	ArrayList<Validator> _subValidators;

	public void add( Validator v ) {
		_subValidators.add(v);
	}
	
	@Override
	public boolean isValid(AbstractRubiksCube cube) {
		for( Validator v : _subValidators ) {
			if( !v.isValid(cube) )
				return false;
		}
		return true;
	}

	public ValidatorSet() {
		_subValidators = new ArrayList<Validator>();
	}

}
