package resolution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import resolution.*;
import rubikscube.*;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

public class Solver {
	
	private static Maneuver easyManeuver( Rotation s[] ) {
		ManeuverSet m = new ManeuverSet();
		for( Rotation r : s ) {
			m.add( (new Move(r)) );
		}
		return m;
	}
	private static Maneuver easyManeuverFlipped( Rotation s[] ) {
		HashMap<Rotation, Rotation> flip = new HashMap<Rotation, Rotation>();
		flip.put(Rotation.Ri, Rotation.F);
		flip.put(Rotation.Li, Rotation.B);
		flip.put(Rotation.Fi, Rotation.L);
		flip.put(Rotation.Bi, Rotation.R);
		flip.put(Rotation.Ui, Rotation.D);
		flip.put(Rotation.Di, Rotation.U);

		flip.put(Rotation.R, Rotation.Fi);
		flip.put(Rotation.L, Rotation.Bi);
		flip.put(Rotation.F, Rotation.Li);
		flip.put(Rotation.B, Rotation.Ri);
		flip.put(Rotation.U, Rotation.Di);
		flip.put(Rotation.D, Rotation.Ui);
		
		
		
		ManeuverSet m = new ManeuverSet();
		for( Rotation r : s ) {
			m.add( (new Move(flip.get(r))) );
		}
		return m;
	}

	public ArrayList<Rotation> solve( RubiksCube rb ) throws NoSolutionFound {
	

		BFSSearcher s = new BFSSearcher();
		ValidatorSet v = new ValidatorSet();
		ManeuverSet solution = new ManeuverSet();
		
		Maneuver atomicManeuvers[] = { 
				new Move(Rotation.U),
				new Move(Rotation.D),
				new Move(Rotation.R),
				new Move(Rotation.L),
				new Move(Rotation.F),
				new Move(Rotation.B),
				new Move(Rotation.Ui),
				new Move(Rotation.Di),
				new Move(Rotation.Ri),
				new Move(Rotation.Li),
				new Move(Rotation.Fi),
				new Move(Rotation.Bi),
				
		};
		
		Rotation belgeRotRight[] = {Rotation.Di,Rotation.Ri,Rotation.D,Rotation.R,Rotation.D,Rotation.F,Rotation.Di,Rotation.Fi};
		Maneuver belgeRight = easyManeuver(belgeRotRight);
		Rotation belgeRotLeft[] = {Rotation.D,Rotation.L,Rotation.Di,Rotation.Li,Rotation.Di,Rotation.Fi,Rotation.D,Rotation.F};
		Maneuver belgeLeft = easyManeuver(belgeRotLeft);
		
		Maneuver belgeManuvers[] = {
				new Move(Rotation.D),
				new Move(Rotation.Di),
				
				belgeRight,
				belgeRight.rotateAroundUDAxis(1),
				belgeRight.rotateAroundUDAxis(3),
				belgeRight.rotateAroundUDAxis(4),
				belgeLeft,
				belgeLeft.rotateAroundUDAxis(1),
				belgeLeft.rotateAroundUDAxis(3),
				belgeLeft.rotateAroundUDAxis(4),
				
		};
		
		Rotation FURURFRot[] = {Rotation.F,Rotation.R,Rotation.U,Rotation.Ri,Rotation.Ui,Rotation.Fi};
		Maneuver FURURF = easyManeuverFlipped(FURURFRot);
		
		Rotation FRURUFRot[] = {Rotation.F,Rotation.R,Rotation.U,Rotation.Ri,Rotation.Ui,Rotation.Fi};
		Maneuver FRURUF = easyManeuverFlipped(FRURUFRot);
		
		Maneuver yellowCrossManeuvers[] = {
				FRURUF,
				FRURUF.rotateAroundUDAxis(2),
				FURURF,
				FURURF.rotateAroundUDAxis(1),
				FURURF.rotateAroundUDAxis(2),
				FURURF.rotateAroundUDAxis(3),				
		};
		
		Rotation chaiseRot[] = {Rotation.R,Rotation.U,Rotation.U,Rotation.Ri,Rotation.Ui,Rotation.R,Rotation.Ui,Rotation.Ri};
		Maneuver chaise = easyManeuverFlipped(chaiseRot);
		Maneuver chaiseManeuvers[] = {
				new Move(Rotation.D),
				new Move(Rotation.Di),
				
				chaise,
				chaise.rotateAroundUDAxis(1),
				chaise.rotateAroundUDAxis(2),
				chaise.rotateAroundUDAxis(3),
		};
		
		Rotation cornerPosRot[] = {Rotation.Li,Rotation.U,Rotation.R,Rotation.Ui,Rotation.L,Rotation.U,Rotation.Ri,Rotation.Ui};
		Maneuver cornerPos = easyManeuverFlipped(cornerPosRot);
		Maneuver cornerPosManeuvers[] = {
				cornerPos,
				cornerPos.rotateAroundUDAxis(1),
				cornerPos.rotateAroundUDAxis(2),
				cornerPos.rotateAroundUDAxis(3),				
		};
		
		Rotation cornerPlaceRot[] = {Rotation.R,Rotation.U,Rotation.U,Rotation.Ri,Rotation.Ui,Rotation.R,Rotation.Ui,Rotation.Ri,Rotation.Li,Rotation.U,Rotation.U,Rotation.L,Rotation.U,Rotation.Li,Rotation.U,Rotation.L};
		Maneuver cornerPlace = easyManeuverFlipped(cornerPlaceRot);
		Maneuver cornerPlaceManeuvers[] = {
				cornerPlace,
				cornerPlace.rotateAroundUDAxis(1),
				cornerPlace.rotateAroundUDAxis(2),
				cornerPlace.rotateAroundUDAxis(3),	
		};

		ValidatorSet crown3_corners_cross = new ValidatorSet();
		crown3_corners_cross.add(new ValidatorFacet(25));
		crown3_corners_cross.add(new ValidatorFacet(27));
		crown3_corners_cross.add(new ValidatorFacet(30));
		crown3_corners_cross.add(new ValidatorFacet(28));
		

		ValidatorSet crown3_corners_placed = new ValidatorSet();
		crown3_corners_placed.add(new ValidatorFacet(24));
		crown3_corners_placed.add(new ValidatorFacet(26));
		crown3_corners_placed.add(new ValidatorFacet(29));
		crown3_corners_placed.add(new ValidatorFacet(31));
		
		
		
		
		
		LinkedList<Maneuver[]> stepsManeuvers = new LinkedList<Maneuver[]>();
		LinkedList<Validator>  stepsValidator = new LinkedList<Validator>();
		
		
		int crown1_cross[] = {9,11,12,14,8,10,13,15};
		for( int i : crown1_cross ) {
			stepsManeuvers.add(atomicManeuvers);
			stepsValidator.add(new ValidatorFacet(i));
		}
		
		int crown2_corners[] = {20,4,36,44};
		for( int i : crown2_corners ) {
			stepsManeuvers.add(belgeManuvers);
			stepsValidator.add(new ValidatorFacet(i));
		}
		
		stepsManeuvers.add(yellowCrossManeuvers);
		stepsValidator.add(new ValidatorCross(Face.D, Color.YELLOW));
		
		stepsManeuvers.add(chaiseManeuvers);
		stepsValidator.add(crown3_corners_cross);
		
		stepsManeuvers.add(cornerPosManeuvers);
		stepsValidator.add(new ValidatorCorner());
		
		stepsManeuvers.add(cornerPlaceManeuvers);
		stepsValidator.add(crown3_corners_placed);
		
		
		while(stepsValidator.size() > 0) {
		
			Maneuver localSolution;
			
			v.add(stepsValidator.poll());
			try {
				localSolution = s.getSolution( rb, v, stepsManeuvers.poll() );
			} catch( NoSolutionFound nop ) {
				throw nop;
			}
			
			localSolution.apply(rb);
			solution.add(localSolution);
			
			int size = stepsManeuvers.size();

			if(size>=12)
				System.out.println("1ere couronne: croix (" + size + ")");
			else if(size >=12-4)
				System.out.println("1ere couronne: coins (" + size + ")");
			else if(size >=12-4-4)
				System.out.println("2eme couronne: coins (" + size + ")");
			else if(size >= 12-4-4-1)
				System.out.println("3eme couronne: faire croix (" + size + ")");
			else if(size >= 12-4-4-1-1)
				System.out.println("3eme couronne: corners (" + size + ")");
			else if(size >= 12-4-4-1-1-1)
				System.out.println("3eme couronne: placer coins (" + size + ")");
			else if(size >= 12-4-4-1-1-1)
				System.out.println("3eme couronne: tourner coins (" + size + ")");

		}
		
		solution.getInverse().apply(rb);
		
		return solution.getRotationList();

		
		
		
	}
		
	

}

