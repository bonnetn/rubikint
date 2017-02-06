package resolution;

import java.util.ArrayList;

import rubikscube.RubiksCube;

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
            if(cube.getFacetColor(facette.getFace(),facette.getX(),facette.getY())!=facette.getCouleur())
            {
                return false;
            }

        }
    return true;
    }
    public RubiksConfiguration copyAndChangeFace()
    {

    }
}
