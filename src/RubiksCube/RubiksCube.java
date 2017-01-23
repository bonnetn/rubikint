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
	abstract public Color[] getColorFace(RubiksFace face);
}
