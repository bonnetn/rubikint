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
    
    public void symetrizeFaceCote(RubiksCube cube,Face face)
    {

        for (FacetConfig x:facetConfig)
        {
            int numFace=(x.getFace().getValue()-face.getValue())%4+Face.F.getValue(); //décalage lié a l'enum
            x.changeFace(cube,Face.values()[numFace]);
        }


    }
}
