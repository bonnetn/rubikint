package RubiksCube;


/*
 * Définit les couleurs possible sur chaque facette
 */

import javax.vecmath.Color3b;

public enum Color {
	ORANGE,
	WHITE,
	RED,
	YELLOW,
	GREEN,
	BLUE;

	public static Color3b givecolor(Color r) {

		Color3b result = null;
		switch (r) {

			case ORANGE:
				result = new Color3b((byte) 255, (byte) 128, (byte) 0);
				break;
			case YELLOW:
				result = new Color3b((byte) 255, (byte) 255, (byte) 0);
				break;
			case BLUE:
				result = new Color3b((byte) 0, (byte) 0, (byte) 255);
				break;
			case WHITE:
				result = new Color3b((byte) 255, (byte) 255, (byte) 255);
				break;
			case RED:
				result = new Color3b((byte) 255, (byte) 0, (byte) 0);
				break;
			case GREEN:
				result = new Color3b((byte) 0, (byte) 255, (byte) 0);
				break;

			default:
				throw new IllegalArgumentException();

		}
		return result;

	}
}