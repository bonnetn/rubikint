package rubikscube.enums;

/*
 * 
 * Definit les mouvements possibles sur le rubiks selon la notation
 * de Singmaster.
 * U <=> Up
 * D <=> Down
 * L <=> Left
 * R <=> Right
 * F <=> Front
 * B <=> Back
 * 
 * Ui <=> Up'
 * etc.
 */

public enum Rotation {
	L(0), Li(1),
	B(2), Bi(3),
	R(4), Ri(5),
	F(6), Fi(7),
	U(8), Ui(9),
	D(10), Di(11);

	private  int value;
	private Rotation(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public static Rotation getOpposite( Rotation r ) {
		if(r.getValue() % 2 == 0)
			return Rotation.values()[r.getValue()+1];
		else
			return Rotation.values()[r.getValue()-1];
		
	}
}
