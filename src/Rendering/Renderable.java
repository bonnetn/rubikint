package Rendering;

import RubiksCube.enums.Color;
import RubiksCube.enums.Face;

public interface Renderable {

	public Color getFacetColor( Face face, int x, int y) throws IllegalArgumentException;
	
}
