package rubikscube;
import java.util.concurrent.ThreadLocalRandom;


import rendering.Cube;
import rendering.Renderable;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

/**
 * Fonction représentant le RubiksCube
 * @author pulp
 *
 */
public class RubiksCube extends AbstractRubiksCube implements Renderable {

	/**
	 * Le rubiks est modélisé comme un ensemble de permuatations, 
	 * voir rubiks.png pour les détails des index.
	 */

	private int rubiksPermutations[];
	
	/**
	 * Les permutations sous formes canoniques pour chacun des mouvements 
	 * (notation Singmaster)
	 */
	private static final int permF[][] = {{0, 2, 7, 5}, {1, 4, 6, 3}, {13, 32, 26, 23}, {14, 35, 25, 20}, {15, 37, 24, 18}};
	private static final int permB[][] = {{40, 42, 47, 45}, {41, 44, 46, 43}, {8, 21, 31, 34}, {9, 19, 30, 36}, {10, 16, 29, 39}};
	private static final int permL[][] = {{16, 18, 23, 21}, {17, 20, 22, 19}, {0, 24, 47, 8}, {3, 27, 44, 11}, {5, 29, 42, 13}};
	private static final int permR[][] = {{32, 34, 39, 37}, {33, 36, 38, 35}, {2, 10, 45, 26}, {4, 12, 43, 28}, {7, 15, 40, 31}};
	private static final int permU[][] = {{8, 10, 15, 13}, {9, 12, 14, 11}, {0, 16, 40, 32}, {1, 17, 41, 33}, {2, 18, 42, 34}};
	private static final int permD[][] = {{24, 26, 31, 29}, {25, 28, 30, 27}, {5, 37, 45, 21}, {6, 38, 46, 22}, {7, 39, 47, 23}};

	/**
	 * Permet de convertir une position sous la forme (x,y) avec x,y€[0,2]² dans le système d'index (voir rubiks.png)
	 */
	private static final int posToID[][] = { {5,3,0}, {6,-1,1}, {7,4,2}};
	
	
	/**
	 * Applique une permutation sur le rubiks cube
	 * @param p la permutation sous la forme cannonique { {cycle1},{cycle2},...}
	 */
	private void _applyPermutation( int p[][] ) {
		
		for( int cycle[] : p ) {
			int first = rubiksPermutations[cycle[0]];
			for(int i=0; i<cycle.length-1; i++) {
				rubiksPermutations[cycle[i]] = rubiksPermutations[cycle[i+1]];
				
			}
			rubiksPermutations[cycle[cycle.length-1]] = first;
		}
		
	}
	
	/**
	 * Permet d'obtenir la couleur associée à un index dans la liste permutée (voir rubiks.png)	
	 * @param value Index
	 * @return Retourne la couleur associée à cet index
	 * @throws IllegalArgumentException
	 */
	private Color _getColor(int value) throws IllegalArgumentException {
		int i = value/8;
		
		switch(i) {
			case 0: return Color.RED;
			case 1: return Color.WHITE;
			case 2: return Color.GREEN;
			case 3: return Color.YELLOW;
			case 4: return Color.BLUE;
			case 5: return Color.ORANGE;
		}
		throw new IllegalArgumentException();
	}
	
	@Override
	public Color getFacetColor( Face face, int x, int y)  throws IllegalArgumentException {
		
		if( x < 0 || x > 2 || y < 0 || y > 2)
			throw new IllegalArgumentException();
		
		if( x==1 && y==1)
			return _getColor(face.ordinal()*8);
		
		return _getColor(rubiksPermutations[face.ordinal() * 8 + posToID[x][y]]);
	}
	
	/**
	 * Affiche une face dans la console
	 * @param face Face à afficher
	 */
	public void printFace( Face face ) {
		for(int i=0; i<3; i++) {
			String line = "|";
			for(int j=0; j<3; j++) {

				line += getFacetColor(face,j,2-i).name().charAt(0);
					
				if(j!=2)
					line+=",";
			}
			line += "|";
			System.out.println(line);
		}
	}

	@Override
	public void rotate( Rotation r )  throws IllegalArgumentException {
		switch( r ) {
		
		case Ui:
			_applyPermutation(permU);
			break;
		case Di:
			_applyPermutation(permD);
			break;
		case Fi:
			_applyPermutation(permF);
			break;
		case Bi:
			_applyPermutation(permB);
			break;
		case Li:
			_applyPermutation(permL);
			break;
		case Ri:
			_applyPermutation(permR);
			break;
		
		// La permutation inverse revient à faire 3 fois la directe
		case U:
			_applyPermutation(permU);
			_applyPermutation(permU);
			_applyPermutation(permU);
			
			break;
		case D:
			_applyPermutation(permD);
			_applyPermutation(permD);
			_applyPermutation(permD);
			
			break;
		case F:
			_applyPermutation(permF);
			_applyPermutation(permF);
			_applyPermutation(permF);
			
			break;
		case B:
			_applyPermutation(permB);
			_applyPermutation(permB);
			_applyPermutation(permB);
			
			break;
		case L:
			_applyPermutation(permL);
			_applyPermutation(permL);
			_applyPermutation(permL);
			
			break;
		case R:
			_applyPermutation(permR);
			_applyPermutation(permR);
			_applyPermutation(permR);

			break;
			
		default:
			throw new IllegalArgumentException();
		}
		
	}
	public void randomMelange()
	{
		for(int i=0;i<60;i++)
		{
			int rot=ThreadLocalRandom.current().nextInt(0, 11 + 1);
			this.rotate(Rotation.values()[rot]);
		}
	}
	
	/**
	 * Constructeur
	 */
	public RubiksCube() {
		
		// Initialise la liste qui sera permutée
		rubiksPermutations = new int[6*9];
		for( int i=0; i<6*9; i++) 
			rubiksPermutations[i] = i;
	}

}
