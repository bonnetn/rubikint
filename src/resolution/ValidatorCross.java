package resolution;

import rubikscube.AbstractRubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;

public class ValidatorCross extends Validator {

	Face _face;
	Color _color;
	
	public ValidatorCross( Face f, Color c ) {
		_face  = f;
		_color = c;
	}
	
	@Override
	public boolean isValid(AbstractRubiksCube cube) {
		return cube.getFacetColor(_face, 1, 0) == _color && 
			   cube.getFacetColor(_face, 0, 1) == _color && 
			   cube.getFacetColor(_face, 1, 2) == _color && 
			   cube.getFacetColor(_face, 2, 1) == _color;
				
	}

}
