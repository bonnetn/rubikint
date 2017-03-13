/*
import rendering.Java3DRenderer;
import resolution.FacetConfig;
import resolution.Procedure;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

import java.util.ArrayList;
import resolution.RubiksConfiguration;
public class Test {

	public static void main( String[] args) {
		Java3DRenderer r = new Java3DRenderer();
		RubiksCube rc = new RubiksCube(); //R L U D OK
		
		rc.rotate(Rotation.R);

		rc.randomMelange();
		 r.drawItem(rc);


		//Procédure seconde couronne
		ArrayList<Rotation> rotater=new ArrayList<Rotation>();
		rotater.add(Rotation.B);
		rotater.add(Rotation.R);
		rotater.add(Rotation.Bi);
		rotater.add(Rotation.Ri);
		rotater.add(Rotation.Fi);
		rotater.add(Rotation.B);
		rotater.add(Rotation.F);

		ArrayList<FacetConfig> confl= new ArrayList<FacetConfig>();
		confl.add(new FacetConfig(1,0,Face.F,Face.R));
		RubiksConfiguration conf=new RubiksConfiguration(confl);
		Procedure p=new Procedure(rotater,0,conf);
//	p.translation(Face.R);
		p.symetry();
		System.out.println(p);


	}
	

}

*/
