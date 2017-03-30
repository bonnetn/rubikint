package resolution;

import rubikscube.enums.Color;

import java.util.ArrayList;

import rubikscube.AbstractRubiksCube;

public class ValidatorCorner extends Validator {

	@Override
	public boolean isValid(AbstractRubiksCube cube) {
		ArrayList<Color> colors = new ArrayList<Color>();
		
		int perm[] = cube.getPermutationTable();
		colors.add( cube.getColorPermutation(perm[5]));
		colors.add( cube.getColorPermutation(perm[23]));
		colors.add( cube.getColorPermutation(perm[24]));
		
		if( !colors.contains(Color.RED) ||  !colors.contains(Color.GREEN) ||  !colors.contains(Color.YELLOW) ) {
			return false;
		}
		
		colors = new ArrayList<Color>();
		
		colors.add( cube.getColorPermutation(perm[7]));
		colors.add( cube.getColorPermutation(perm[26]));
		colors.add( cube.getColorPermutation(perm[37]));
		
		if( !colors.contains(Color.RED) ||  !colors.contains(Color.BLUE) ||  !colors.contains(Color.YELLOW) ) {
			return false;
		}
		

		colors = new ArrayList<Color>();
		
		colors.add( cube.getColorPermutation(perm[31]));
		colors.add( cube.getColorPermutation(perm[39]));
		colors.add( cube.getColorPermutation(perm[45]));
		
		if( !colors.contains(Color.ORANGE) ||  !colors.contains(Color.BLUE) ||  !colors.contains(Color.YELLOW) ) {
			return false;
		}
		

		colors = new ArrayList<Color>();
		
		colors.add( cube.getColorPermutation(perm[29]));
		colors.add( cube.getColorPermutation(perm[21]));
		colors.add( cube.getColorPermutation(perm[47]));
		
		if( !colors.contains(Color.ORANGE) ||  !colors.contains(Color.GREEN) ||  !colors.contains(Color.YELLOW) ) {
			return false;
		}
		
		return true;
		
		
		
		
		
			
	}

}
