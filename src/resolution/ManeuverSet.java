package resolution;

import java.util.ArrayList;

import rubikscube.AbstractRubiksCube;
import rubikscube.enums.Rotation;

public class ManeuverSet extends Maneuver {

	ArrayList<Maneuver> _subManeuvers;
	
	public ManeuverSet() {
		_subManeuvers = new ArrayList<Maneuver>();
	}
	
	public void add( Maneuver m ) {
		if(m.size()>0)
			_subManeuvers.add(m);
	}
	
	@Override
	public Maneuver getInverse() {
		ManeuverSet invert = new ManeuverSet();
		for (int i = _subManeuvers.size()-1; i >= 0 ; i--) {
			invert.add( _subManeuvers.get(i).getInverse() );
		}
		return invert;
	}

	@Override
	public void apply(AbstractRubiksCube c) {
		for( Maneuver m : _subManeuvers ) {
			m.apply(c);
		}
	}

	@Override
	public String toString() {

		if(_subManeuvers.size() == 0)
			return "";
		
		String str = "";
		for( Maneuver m : _subManeuvers ) {
			
			str += m.toString() + ";";
		}
	
		return str.substring(0, str.length() - 1);
	}

	@Override
	public int size() {
		int s = 0;
		for( Maneuver m : _subManeuvers ) {
			s += m.size();
		}
		return s;
	}

	@Override
	public Maneuver rotateAroundUDAxis(int angle) {
		ManeuverSet rotated = new ManeuverSet();
		for ( Maneuver m : _subManeuvers ) {
			rotated.add( m.rotateAroundUDAxis(angle) );
		}
		return rotated;
	}

	@Override
	public ArrayList<Rotation> getRotationList() {
		ArrayList<Rotation> result = new ArrayList<Rotation>();
		for( Maneuver m : _subManeuvers) {
			for( Rotation rot : m.getRotationList() ) {
				result.add(rot);
			}
		}
		return result;
	}

}
