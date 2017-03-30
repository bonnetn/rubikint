package resolution;

import java.util.LinkedList;

import rubikscube.AbstractRubiksCube;

public class BFSSearcher {
	
	private final static int MAX_DEPTH = 10;
	
	private AbstractRubiksCube _rubiks;
	private Validator _validator;
	private Maneuver[] _maneuvers;
	private LinkedList<Maneuver> _currentSolution;
	
	private LinkedList<Maneuver> findSolution( int depth ) throws NoSolutionFound {
		LinkedList<Maneuver> solution;
		if( depth > 0 ) {
			depth--;
			for( Maneuver m : _maneuvers ) {

				_currentSolution.addLast(m);
				m.apply(_rubiks);
				try {
					return findSolution( depth );
				}
				catch(NoSolutionFound s) {
					
				}
				finally {
					m.getInverse().apply(_rubiks);
					_currentSolution.pollLast();
				}
			}
			throw new NoSolutionFound();
		} else {
			if(_validator.isValid(_rubiks)) {
				return (LinkedList<Maneuver>) (_currentSolution.clone());
			} else {
				throw new NoSolutionFound();
			}
				
		}
		
	}
	
	public Maneuver getSolution( AbstractRubiksCube rc, Validator v, Maneuver[] m )
	throws NoSolutionFound
	{
	
		_rubiks = rc;
		_validator = v;
		_maneuvers = m;
		_currentSolution = new LinkedList<Maneuver>();
		
		for( int depth = 0; depth < MAX_DEPTH; depth++) {
			try {
				LinkedList<Maneuver> s = findSolution( depth );
				ManeuverSet mSet = new ManeuverSet();
				while(s.size() > 0 )
					mSet.add(s.pollFirst());
				return mSet;
					
			}  
			catch(NoSolutionFound s) {
				
			}
		}
		
		throw new NoSolutionFound();
	}
	
	public BFSSearcher() {
		
	}
	
}
