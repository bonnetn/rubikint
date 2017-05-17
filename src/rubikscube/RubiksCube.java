package rubikscube;
import java.lang.reflect.Array;
import java.util.ArrayList;
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

	private ArrayList<Corner> capturedCorner = new ArrayList<>();
	private ArrayList<Edge>   capturedEdge = new ArrayList<>();
	private ArrayList<Corner> vanillaCorner = new ArrayList<>();
	private ArrayList<Edge>   vanillaEdge = new ArrayList<>();
	private ArrayList<ArrayList<Integer>>  settingPermutation = new ArrayList<>();
	public ArrayList<Integer> indexToReach = new ArrayList<>();
	public ArrayList<Integer> actualIndex = new ArrayList<>();



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
		rubiksPermutations = new int[6*8];
		for( int i=0; i<6*8; i++)
			rubiksPermutations[i] = i;
	}


	public void setPermutationTable(int[] perm)
	{
		rubiksPermutations = perm;
	}

	public void setRubiksCubeColor(Color[][][] color)
	{
		ColorToCubiesColor(color);
		setPermutationTable();
	}

	public int[] getTable() {return rubiksPermutations;}

	public void ColorToCubiesColor(Color[][][] color) // definis les differents coin et arrete capture
	{
		// Defini les coins dans cet ordre : ULF,URF,ULB,URB,DLF,DRF,DLB,DRB  F : 0 , R : 1 , B : 2, L : 3, U : 4, D : 5
		//ULF 4 3 0

		capturedCorner.add(new Corner(new Facette(color[4][0][0],13),new Facette(color[3][2][2],18),new Facette(color[0][0][2],0)));
		//URF 4 1 0
		capturedCorner.add(new Corner(new Facette(color[4][2][0],15),new Facette(color[1][0][2],32),new Facette(color[0][2][2],2)));
		//ULB 4 3 2
		capturedCorner.add(new Corner( new Facette(color[4][0][2],8),new Facette(color[3][0][2],16),new Facette(color[2][2][2],42)));
		//URB 4 1 2
		capturedCorner.add(new Corner( new Facette(color[4][2][2],10),new Facette(color[1][2][2],34), new Facette(color[2][0][2],40)));
		//DLF 5 3 0
		capturedCorner.add( new Corner( new Facette(color[5][0][2],24), new Facette(color[3][2][0],23), new Facette(color[0][0][0],5)));
		//DRF
		capturedCorner.add(new Corner(new Facette(color[5][2][2],26),new Facette(color[1][0][0],37),new Facette(color[0][2][0],7)));
		//DLB
		capturedCorner.add(new Corner(new Facette(color[5][0][0],29),new Facette(color[3][0][0],21), new Facette(color[2][2][0],47)));
		//DRB
		capturedCorner.add(new Corner(new Facette(color[5][2][0],31), new Facette(color[1][2][0],39),new Facette(color[2][0][0],45)));

		// Arrete : UF , UR , UB , UL , DF , DR , DB , DL , FR , RB , BL , LF
		//UF 4 0
		capturedEdge.add(new Edge(new Facette(color[4][1][0],14),new Facette(color[0][1][2],1)));
		//UR 4 1
		capturedEdge.add(new Edge(new Facette(color[4][2][1],12),new Facette(color[1][1][2],33)));
		//UB 4 2
		capturedEdge.add(new Edge(new Facette(color[4][1][2],9), new Facette(color[2][1][2],41)));
		//UL 4 3
		capturedEdge.add(new Edge(new Facette(color[4][0][1],11), new Facette(color[3][1][2],17)));

		//DF 5 0
		capturedEdge.add(new Edge(new Facette(color[5][1][2],25), new Facette(color[0][1][0],6)));
		//DR 5 1
		capturedEdge.add(new Edge(new Facette(color[5][2][1],28),new Facette(color[1][1][0],38)));
		//DB 5 2
		capturedEdge.add(new Edge(new Facette(color[5][1][0],30),new Facette(color[2][1][0],46)));
		//DL 5 3
		capturedEdge.add(new Edge(new Facette(color[5][0][1],27),new Facette(color[3][1][0],22)));

		//FR 0 1
		capturedEdge.add(new Edge(new Facette(color[0][2][1],4),new Facette(color[1][0][1],35)));
		//RB 1 2
		capturedEdge.add(new Edge(new Facette(color[1][2][1],36),new Facette(color[2][0][1],43)));
		//BL 2 3
		capturedEdge.add(new Edge(new Facette(color[2][2][1],44),new Facette(color[3][0][1],29)));
		//LF 3 0
		capturedEdge.add(new Edge(new Facette(color[3][2][1],20),new Facette(color[0][0][1],3)));

		//SET DES VANILLA CORNER ET EDGE QUI CONTIENT LES PERMID DES FACETTES.
		vanillaCorner.add(new Corner(new Facette(getColorPermutation(13),13),new Facette(getColorPermutation(18),18),new Facette(getColorPermutation(0),0)));
		vanillaCorner.add(new Corner(new Facette(getColorPermutation(15),15),new Facette(getColorPermutation(32),32),new Facette(getColorPermutation(2),2)));
		vanillaCorner.add(new Corner( new Facette(getColorPermutation(8),8),new Facette(getColorPermutation(16),16),new Facette(getColorPermutation(42),42)));
		vanillaCorner.add(new Corner( new Facette(getColorPermutation(10),10),new Facette(getColorPermutation(34),34), new Facette(getColorPermutation(40),40)));
		vanillaCorner.add( new Corner( new Facette(getColorPermutation(24),24), new Facette(getColorPermutation(23),23), new Facette(getColorPermutation(5),5)));
		vanillaCorner.add(new Corner(new Facette(getColorPermutation(26),26),new Facette(getColorPermutation(37),37),new Facette(getColorPermutation(7),7)));
		vanillaCorner.add(new Corner(new Facette(getColorPermutation(29),29),new Facette(getColorPermutation(21),21), new Facette(getColorPermutation(47),47)));
		vanillaCorner.add(new Corner(new Facette(getColorPermutation(31),31), new Facette(getColorPermutation(39),39),new Facette(getColorPermutation(45),45)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(14),14),new Facette(getColorPermutation(1),1)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(12),12),new Facette(getColorPermutation(33),33)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(9),9), new Facette(getColorPermutation(41),41)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(11),11), new Facette(getColorPermutation(17),17)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(25),25), new Facette(getColorPermutation(6),6)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(28),28),new Facette(getColorPermutation(38),38)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(30),30),new Facette(getColorPermutation(46),46)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(27),27),new Facette(getColorPermutation(22),22)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(4),4),new Facette(getColorPermutation(35),35)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(36),36),new Facette(getColorPermutation(43),43)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(44),44),new Facette(getColorPermutation(29),29)));
		vanillaEdge.add(new Edge(new Facette(getColorPermutation(20),20),new Facette(getColorPermutation(3),3)));

	}

	public void setPermutationTable()
	{
		for (Corner captured : capturedCorner)
		{
			for (Corner vanilla : vanillaCorner)
			{
				if (vanilla.color.contains(captured.color.get(0))&&vanilla.color.contains(captured.color.get(1))&&vanilla.color.contains(captured.color.get(2)))
				{
					for (int i=0;i<2;i++)
					{
						for (int j=0;j<2;j++)
						{
							if (captured.color.get(i) == vanilla.color.get(j))
							{
								indexToReach.add(vanilla.permId.get(j));
								actualIndex.add(captured.permId.get(i));
							}

						}
					}
				}

			}
		}

		for (Edge captured : capturedEdge)
		{
			for (Edge vanilla : vanillaEdge)
			{
				if (vanilla.color.contains(captured.color.get(0))&&vanilla.color.contains(captured.color.get(1)))
				{
					for (int i=0;i<2;i++)
					{
						for (int j=0;j<2;j++)
						{
							indexToReach.add(vanilla.permId.get(j));
							actualIndex.add(captured.permId.get(i));
						}
					}
				}
			}
		}


		for (int indice=0;indice<indexToReach.size();indice++)
		{
			rubiksPermutations[actualIndex.get(indice)] = indexToReach.get(indice);

		}


	}



	public class Facette
	{
		int posPerm;
		Color color;
		Facette(Color color,int pos)
		{
			this.color=color;
			this.posPerm=pos;
		}
	}

	public class Corner
	{
		ArrayList<Color> color = new ArrayList<>();
		ArrayList<Integer> permId = new ArrayList<>();
		Corner(Facette c1,Facette c2,Facette c3)
		{
			this.color.add(c1.color);
			this.color.add(c2.color);
			this.color.add(c3.color);
			this.permId.add(c1.posPerm);
			this.permId.add(c2.posPerm);
			this.permId.add(c3.posPerm);
		}
	}

	public class Edge
	{
		ArrayList<Color> color = new ArrayList<>();
		ArrayList<Integer> permId = new ArrayList<>();
		Edge(Facette c1,Facette c2)
		{
			this.color.add(c1.color);
			this.color.add(c2.color);
			this.permId.add(c2.posPerm);
			this.permId.add(c2.posPerm);
		}
	}




}
