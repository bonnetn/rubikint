
import rendering.Java3DRenderer;
import resolution.FacetConfig;
import resolution.Procedure;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

import java.util.ArrayList;
import java.util.Arrays;

import resolution.RubiksConfiguration;
public class Test {

	public static void main( String[] args) {
		Java3DRenderer r = new Java3DRenderer();
		RubiksCube rc = new RubiksCube(); //R L U D OK
		
		rc.rotate(Rotation.R);

		rc.randomMelange();
		 r.drawItem(rc);
ArrayList<Procedure> listeProcedure= new ArrayList<Procedure>();

		//Procédure seconde couronne
		ArrayList<Rotation> rotater=new ArrayList<Rotation>(Arrays.asList(Rotation.B,Rotation.R,Rotation.Bi,Rotation.Ri,Rotation.Fi,Rotation.B,Rotation.F));
		RubiksConfiguration conf=new RubiksConfiguration(new ArrayList<FacetConfig>(Arrays.asList(new FacetConfig(1,0,Face.F,Face.R))));
		Procedure p=new Procedure(rotater,10,conf,Rotation.B);

        Procedure p2=new Procedure(p);
        p2.symetry();
        for(int i=1;i<=4;i++) {
            listeProcedure.add(new Procedure(p));
            listeProcedure.add(new Procedure(p2));
            p.translation(Face.values()[Face.F.getValue()+i]);
            p2.translation(Face.values()[Face.F.getValue()+i]);
        }
		ArrayList<Rotation> croix=new ArrayList<Rotation>(Arrays.asList(Rotation.Fi,Rotation.Li,Rotation.Bi,Rotation.L,Rotation.B,Rotation.F));
		RubiksConfiguration confcroix=new RubiksConfiguration(new ArrayList<FacetConfig>(Arrays.asList(new FacetConfig(1,0,Face.F,Face.D),new FacetConfig(1,0,Face.B,Face.D))));
		listeProcedure.add(new Procedure(croix,20,confcroix,Rotation.B));




	}
	
}
