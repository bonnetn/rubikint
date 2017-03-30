package resolution;

import java.util.ArrayList;

import rubikscube.AbstractRubiksCube;
import rubikscube.enums.Rotation;

abstract public class Maneuver {
	
	abstract public Maneuver getInverse();
	abstract public void apply( AbstractRubiksCube c );
	abstract public int size();
	abstract public Maneuver rotateAroundUDAxis( int angle );
	abstract public ArrayList<Rotation> getRotationList();

}
