package rendering;

import rubikscube.enums.Color;
import rubikscube.enums.Face;

public interface Renderable {

	public Color getFacetColor( Face face, int x, int y) throws IllegalArgumentException;
	
}
