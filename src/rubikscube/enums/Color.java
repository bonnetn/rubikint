package rubikscube.enums;


/*
 * Définit les couleurs possible sur chaque facette
 * Doit correspondre a l'enum face.
 */

public enum Color {
	ORANGE(3),	WHITE(0),	RED(1),	YELLOW(5),	GREEN(2),	BLUE(4), BLACK(6);

	private final int value;

	private Color(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}

