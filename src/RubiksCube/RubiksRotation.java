package RubiksCube;

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

public enum RubiksRotation {
	U, Ui,
	D, Di,
	R, Ri,
	L, Li,
	F, Fi, 
	B, Bi;
}
