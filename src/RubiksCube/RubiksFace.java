package RubiksCube;

/*
 * Définit les faces du rubiks
 * Front, Up, Left, Down, Right, Back
 */

public enum RubiksFace {
	F(0),U(1),R(2),D(3),L(4),B(5);

	private final int value;

	private RubiksFace(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
