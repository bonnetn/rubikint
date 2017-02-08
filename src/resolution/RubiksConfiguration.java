package resolution;

import java.util.ArrayList;

import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;

/**
 * Created by shininisan on 26/01/17.
 Cube dont seules quelques facettes sont précisées et décrivent un état du cube néceessaire pour lancer une procédure
 */
public class RubiksConfiguration {
    private ArrayList<FacetConfig> facetConfig;
    public RubiksConfiguration(ArrayList<FacetConfig> facetConfig)
    {
        this.facetConfig=facetConfig;
    }

    public ArrayList<FacetConfig> getFacetConfig() {
        return facetConfig;
    }

    public void setFacetConfig(ArrayList<FacetConfig> facetConfig) {
        this.facetConfig = facetConfig;
    }

    public boolean match(RubiksCube cube)
    {
        for (FacetConfig facette:this.facetConfig)
        {
            if(cube.getFacetColor(facette.getFace(),facette.getX(),facette.getY())!= Color.values()[facette.getCouleurFaceCorrespondante().getValue()])
            {
                return false;
            }

        }
    return true;
    }
    
    public void translation(Face face)
    {

        for (FacetConfig x:facetConfig)
        {
            int numFace=(face.getValue()-x.getFace().getValue())%4+Face.F.getValue(); //décalage lié a l'enum
            x.changeFace(Face.values()[numFace]);
        }


    }
    public void symetry()
    {
        for (int i=0;i<facetConfig.size();i++)
        {
            facetConfig.get(i).setX(2-facetConfig.get(i).getX()); // On inverse les x
           if( facetConfig.get(i).getCouleurFaceCorrespondante()==Face.R) {
               facetConfig.get(i).setCouleurFaceCorrespondante(Face.L);
           }
           else if( facetConfig.get(i).getCouleurFaceCorrespondante()==Face.L) {
               facetConfig.get(i).setCouleurFaceCorrespondante(Face.R);
           }
        }
        
    }
}
