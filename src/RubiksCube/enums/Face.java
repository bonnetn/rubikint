package RubiksCube.enums;

/*
 * Définit les faces du rubiks
 * Front, Up, Left, Down, Right, Back
 */

public enum Face {
	F(0),U(1),L(2),D(3),R(2),B(5);

	private final int value;

	private Face(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
