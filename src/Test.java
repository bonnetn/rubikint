
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

		//Procédure seconde couronne D'R'DRDFD'F'
		ArrayList<Rotation> rotater=new ArrayList<Rotation>(Arrays.asList(Rotation.D,Rotation.R,Rotation.Di,Rotation.Ri,Rotation.Di,Rotation.Fi,Rotation.D,Rotation.F));
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
        //croix face à face
		ArrayList<Rotation> croix=new ArrayList<Rotation>(Arrays.asList(Rotation.Fi,Rotation.Li,Rotation.Di,Rotation.L,Rotation.D,Rotation.F));
		RubiksConfiguration confcroix=new RubiksConfiguration(new ArrayList<FacetConfig>(Arrays.asList(new FacetConfig(1,0,Face.F,Face.D),new FacetConfig(1,0,Face.B,Face.D))));
		listeProcedure.add(new Procedure(croix,20,confcroix,Rotation.B));

		//croix cote à cote
		ArrayList<Rotation> croix2=new ArrayList<Rotation>(Arrays.asList(Rotation.Fi,Rotation.Di,Rotation.Li,Rotation.D,Rotation.L,Rotation.F));
		RubiksConfiguration confcroix2=new RubiksConfiguration(new ArrayList<FacetConfig>(Arrays.asList(new FacetConfig(1,0,Face.F,Face.D),new FacetConfig(1,0,Face.L,Face.D))));
		listeProcedure.add(new Procedure(croix2,20,confcroix2,Rotation.B));

		//chaise

		ArrayList<Rotation> chaise=new ArrayList<Rotation>(Arrays.asList(Rotation.Li,Rotation.D,Rotation.D,Rotation.L,Rotation.D,Rotation.Li,Rotation.D,Rotation.L));
		RubiksConfiguration confchaise=new RubiksConfiguration(new ArrayList<FacetConfig>(Arrays.asList(new FacetConfig(1,0,Face.F,Face.F),new FacetConfig(1,0,Face.R,Face.B),new FacetConfig(1,0,Face.B,Face.L),new FacetConfig(1,0,Face.L,Face.R))));
		Procedure pchaise=new Procedure(chaise,30,confchaise,Rotation.B);
		listeProcedure.add(pchaise);
		Procedure pchaisesym=new Procedure(pchaise);
		pchaisesym.symetry();
		listeProcedure.add(pchaisesym);

		//Copain
		ArrayList<Rotation> copain=new ArrayList<Rotation>(Arrays.asList(Rotation.R,Rotation.Di,Rotation.Li,Rotation.D,Rotation.Ri,Rotation.Di,Rotation.L,Rotation.D));
		RubiksConfiguration confcopain=new RubiksConfiguration(new ArrayList<FacetConfig>(Arrays.asList(new FacetConfig(1,0,Face.F,Face.F),new FacetConfig(1,0,Face.R,Face.B),new FacetConfig(1,0,Face.B,Face.L),new FacetConfig(1,0,Face.L,Face.R))));
		Procedure pcopain=new Procedure(copain,40,confcopain,Rotation.B);
		listeProcedure.add(pchaise);






	}
	
}
