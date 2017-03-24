package resolution.validators;

import resolution.Validator;
import rubikscube.RubiksCube;
import rubikscube.enums.Face;

public class EdgePositionned extends Validator {
	
	private Face _f;
	private int _x,_y;
	
	public EdgePositionned( Face f, int x, int y ) {
		_x = x;
		_y = y;
		_f = f;
	}

	public boolean isValid( RubiksCube cube )
	{
		return cube.isAtRightLocation(_f, _x, _y);
	}
	
}
