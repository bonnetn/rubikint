package resolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import rubikscube.AbstractRubiksCube;
import rubikscube.enums.Rotation;


public class Move extends Maneuver {

	private static final Map<Rotation, Rotation> inverseLookup;
	static {
		HashMap<Rotation, Rotation> hm = new HashMap<Rotation, Rotation>();
		hm.put(Rotation.U, Rotation.Ui);
		hm.put(Rotation.D, Rotation.Di);
		hm.put(Rotation.R, Rotation.Ri);
		hm.put(Rotation.L, Rotation.Li);
		hm.put(Rotation.F, Rotation.Fi);
		hm.put(Rotation.B, Rotation.Bi);

		hm.put(Rotation.Ui, Rotation.U);
		hm.put(Rotation.Di, Rotation.D);
		hm.put(Rotation.Ri, Rotation.R);
		hm.put(Rotation.Li, Rotation.L);
		hm.put(Rotation.Fi, Rotation.F);
		hm.put(Rotation.Bi, Rotation.B);
		
		inverseLookup = Collections.unmodifiableMap(hm);
	}
	
	private static final Map<Rotation, Rotation> rotationLookup;
	static {
		HashMap<Rotation, Rotation> hm = new HashMap<Rotation, Rotation>();
		hm.put(Rotation.U, Rotation.U);
		hm.put(Rotation.D, Rotation.D);
		hm.put(Rotation.R, Rotation.B);
		hm.put(Rotation.L, Rotation.F);
		hm.put(Rotation.F, Rotation.R);
		hm.put(Rotation.B, Rotation.L);

		hm.put(Rotation.Ui, Rotation.Ui);
		hm.put(Rotation.Di, Rotation.Di);
		hm.put(Rotation.Ri, Rotation.Bi);
		hm.put(Rotation.Li, Rotation.Fi);
		hm.put(Rotation.Fi, Rotation.Ri);
		hm.put(Rotation.Bi, Rotation.Li);
		
		rotationLookup = Collections.unmodifiableMap(hm);
	}
	
	private Rotation _rotation;
	
	@Override
	public boolean equals(Object obj) {
		if( getClass() != obj.getClass())
			return super.equals(obj);
				
		return ((Move) obj)._rotation == _rotation;
	}

	public Move( Rotation r ) {
		_rotation = r;
	}
	
	@Override
	public
	Maneuver getInverse() {
		return new Move(inverseLookup.get(_rotation));
	}

	@Override
	public void apply(AbstractRubiksCube c) {
		c.rotate(_rotation);
	}

	@Override
	public String toString() {
		return _rotation.toString();
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public Maneuver rotateAroundUDAxis(int angle) {
		if(angle == 0)
			return new Move(_rotation);
		
		Maneuver rot = new Move(rotationLookup.get(_rotation));
		return rot.rotateAroundUDAxis(angle-1);
	}

	@Override
	public ArrayList<Rotation> getRotationList() {
		ArrayList<Rotation> result = new ArrayList<Rotation>();
		result.add(_rotation);
		return result;
	}

}
