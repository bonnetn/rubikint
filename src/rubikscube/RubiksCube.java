package rubikscube;
import java.util.concurrent.ThreadLocalRandom;


import rendering.Renderable;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;
import rubikscube.enums.TripletToId;

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
	public Color getColorPermutation(int value) throws IllegalArgumentException {
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
			return getColorPermutation(face.ordinal()*8);

		return getColorPermutation(rubiksPermutations[face.ordinal() * 8 + posToID[x][y]]);
	}

	/**
	 * Retourne l'id associé a une position
	 * @param face
	 * @param x
	 * @param y
	 * @return
	 */
	public int getFacetId(Face face, int x, int y)
	{
		if( x < 0 || x > 2 || y < 0 || y > 2)
			throw new IllegalArgumentException();
		if( x==1 && y==1)
			return face.ordinal()*8;
		return rubiksPermutations[face.ordinal() * 8 + posToID[x][y]];
	}

	Color corners_colors[][] = {
			{Color.WHITE,Color.RED,Color.GREEN},
			{Color.WHITE,Color.RED,Color.BLUE},
			{Color.WHITE,Color.ORANGE,Color.GREEN},
			{Color.WHITE,Color.ORANGE,Color.BLUE},
			{Color.YELLOW,Color.RED,Color.GREEN},
			{Color.YELLOW,Color.RED,Color.BLUE},
			{Color.YELLOW,Color.ORANGE,Color.GREEN},
			{Color.YELLOW,Color.ORANGE,Color.BLUE},
			{Color.WHITE,Color.RED},
			{Color.WHITE,Color.GREEN},
			{Color.WHITE,Color.BLUE},
			{Color.WHITE,Color.ORANGE},
			{Color.YELLOW,Color.RED},
			{Color.YELLOW,Color.GREEN},
			{Color.YELLOW,Color.BLUE},
			{Color.YELLOW,Color.ORANGE},
			{Color.GREEN,Color.RED},
			{Color.RED,Color.BLUE},
			{Color.BLUE,Color.ORANGE},
			{Color.ORANGE,Color.GREEN}
	};

	int corners_permid[][] = {
			{13,0,18},
			{15,2,32},
			{8,42,16},
			{10,40,34},
			{24,5,23},
			{26,7,37},
			{29,47,21},
			{31,45,39},
			{14,1},
			{11,17},
			{12,33},
			{9,41},
			{25,6},
			{27,22},
			{28,38},
			{30,46},
			{20,3},
			{4,35},
			{36,43},
			{44,29}
	};

	Face corners_face[][] = {
			{Face.F, Face.L, Face.D},
			{Face.F, Face.R, Face.D},
			{Face.F, Face.R, Face.U},
			{Face.F, Face.L, Face.U},

			{Face.B, Face.L, Face.D},
			{Face.B, Face.R, Face.D},
			{Face.B, Face.R, Face.U},
			{Face.B, Face.L, Face.U},
			{Face.F,Face.U},
			{Face.B,Face.U},
			{Face.L,Face.U},
			{Face.R,Face.U},
			{Face.B,Face.D},
			{Face.F,Face.D},
			{Face.L,Face.D},
			{Face.R,Face.D},
			{Face.F,Face.L},
			{Face.R,Face.F},
			{Face.B,Face.R},
			{Face.L,Face.B}
	};

	int corners_pos[][][]={
		{ {0,0}, {2,0}, {0,2} },
		{ {2,0}, {0,0}, {2,2} },
		{ {2,2}, {0,2}, {2,0} },
		{ {0,2}, {2,2}, {0,0} },

		{ {2,0}, {0,0}, {0,0} },
		{ {0,0}, {2,0}, {2,0} },
		{ {0,2}, {2,2}, {2,2} },
		{ {2,2}, {0,2}, {0,2} },
		{{1,2},{1,0}},
		{{1,2},{1,2}},
		{{1,2},{0,1}},
		{{1,2},{2,1}},
		{{1,0},{1,0}},
		{{1,0},{1,2}},
		{{1,0},{0,1}},
		{{1,0},{2,1}},
		{{0,1},{2,1}},
		{{0,1},{2,1}},
		{{0,1},{2,1}},
		{{0,1},{2,1}}

	};

	private int faceToFlorianID( Face f ) {
		switch(f) {
			case F:
				return 0;
			case R:
				return 1;
			case B:
				return 2;
			case L:
				return 3;
			case U:
				return 4;
			case D:
				return 5;
			default:
				return 1000;
		}
	}


	public int getCubieID( Face f, int x, int y) {

		// On parcours tous les cubies possibles
		for(int i=0; i<corners_face.length; i++) {

			// Premierement on check si ce cubie a une facette sur la face concernee
			boolean hasFace = false;
			int faceID = 0;
			for( int j=0; j<corners_face[i].length; j++) {
				Face faceCorner = corners_face[i][j];
				if(faceCorner == f) {
					hasFace = true;
					faceID = j;
				}
			}
			if(!hasFace) {
				continue;
			}

			// Puis on check la pos
			boolean hasPos = false;
			for( int tuple[] : corners_pos[faceID] ) {
				if(tuple[0] == x && tuple[1] == y) {
					hasPos = true;
				}
			}
			if(hasFace) {
				// on sait qu'on appartient au cubie i
				return i;
			}

		}
		return 999;
	}

	public Color getFlorianColor( Color config[][][], Face f, int x, int y) {
		int faceId = faceToFlorianID(f);
		return config[faceId][x][y];

	}

	public int[] getPermutationTableFromConfig( Color[][][] configColor ) {

		int[] perm = new int[6*9];
		for( Face f : Face.values() ) {
			for( int x=0; x<=2; x++) {
				for( int y=0; y<=2; y++) {
					if(x != 1 || y != 1) {
						// Pour tout cubie (sauf les centres)

						// On prend l'ID du cubie.
						int cubieID = getCubieID( f, x, y );

						// On prend la couleurs de la facette
						Color c = getFlorianColor(configColor, f, x, y);

						// Et on prend l'id correspondant
						for( int i=0; i<3; i++) {
							// si le ieme est de la bonne couleur, on prend le i dans le tableau des perm
							if(corners_colors[cubieID][i] == c) {
								int perm2 = corners_permid[cubieID][i]; // il est sur permid[i]
								int pos = getFacetId(f, x, y); // il devrait etre a sa pos
								perm[pos] = perm2; // donc on le met dans le tableau des perm
							}
						}
					}
				}
			}
		}
		return perm;
	}



	public boolean isAtRightLocation( Face face, int x, int y ) throws IllegalArgumentException {
		/*
		 * return true if the facet is at its right location on the cube.
		 */
		if( x < 0 || x > 2 || y < 0 || y > 2)
			throw new IllegalArgumentException();
		int id = face.ordinal() * 8 + posToID[x][y];

		return rubiksPermutations[id] == id;
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
		System.out.println("");
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

	public int[] getPermutationTable() {
		return rubiksPermutations.clone();
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


	public void setPermutationTable(int[] perm)
	{
		rubiksPermutations = perm;
	}

	public void setRubiksCubeColor(Color[][][] color)
	{
		setPermutationTable(getPermutationTableFromConfig(color));
	}

	public int[] getTable() {return rubiksPermutations;}

}
