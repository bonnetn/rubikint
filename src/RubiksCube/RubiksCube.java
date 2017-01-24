package RubiksCube;

abstract public class RubiksCube {
	/**
	 * Permet d'effectuer un mouvement sur le cube
	 * @param r Le mouvement de type RubiksRotation
	 * @throws IllegalArgumentException
	 */
	abstract public void rotate( RubiksRotation r ) throws IllegalArgumentException;
	
	/**
	 * Permet d'obtenir la couleur d'une facette
	 * @param face Face sur laquelle est la facette
	 * @param x Entier dans [0,2] (de la gauche vers la droite sur rubiks.png)
	 * @param y Entier dans [0,2] (du bas vers le haut sur rubiks.png)
	 * @return Couleur de la facettes
	 */
	abstract public Color getFacetColor( RubiksFace face, int x, int y) throws IllegalArgumentException;
	
	/**
	 * Permet d'obtenir un tableau avec les couleurs de la face dans l'ordre croisssant
	 * des x puis l'axe croissant des y
	 * @param face
	 * @return
	 */
	public Color[] getColorFace(RubiksFace face) {
		Color faceColors[] = new Color[9];
		
		for(int j=0; j<3; j++) 
			for(int i=0; i<3; i++) 
				faceColors[j*3+j] = getFacetColor(face, i, j);
				
		return faceColors;
		
	}
}
