package resolution;

import java.util.ArrayList;
import java.util.Stack;

import rubikscube.RubiksCube;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

public class BFSSearcher {
	private RubiksCube cube;
	private ArrayList< Rotation > movements;
	private Stack< Rotation > path;
	public ArrayList<Validator> v;
	
	boolean goToDepth( int depth)
	{
		
		if(depth == 0) {
			for( Validator validator : v) {
				if( ! validator.isValid(cube) ) {
					return false;
				}
			}
			return true;
		} else {
			for( Rotation r : movements ) {
				
				if( !path.empty() && Rotation.getOpposite(r) == path.peek())
					continue;
				
				path.add(r);
				cube.rotate(r);			
				if(goToDepth(depth-1))
					return true;
				cube.rotate(Rotation.getOpposite(r));
				path.pop();
			}
			return false;
		}
	}
	
	public boolean getSolution() {
		for( int i=0; i<10; i++) {
			//System.out.println("depth " + i);
			if(goToDepth(i))
				return true;
			
		}
		return false;
	}
	
	public BFSSearcher( RubiksCube c, ArrayList< Rotation > r,ArrayList<Validator> val) {
		cube = c;
		movements = r;
		path = new Stack<Rotation>();
		v = val;
	}
	
}
