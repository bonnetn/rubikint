package rubikscube.enums;

/*
 * Définit les faces du rubiks
 * Front, Up, Left, Down, Right, Back
 */

public enum Face {
	F(1),U(0),L(2),D(3),R(4),B(5);

	private final int value;

	private  boolean notColor;

	Face(int value,boolean notColor) //Constructeur secondaire utilisé pour la résolution
	{
		this.value=value;
		this.notColor=notColor;
	}

	public boolean isNotColor() {
		return notColor;
	}

	public void setNotColor(boolean notColor) {
		this.notColor = notColor;
	}

	private Face(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
