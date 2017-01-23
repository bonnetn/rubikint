package RubiksCube;

import org.junit.Test;

/**
 * Created by shininisan on 23/01/17.
 */
public class jUnit_affichage {
    private static jUnit_affichage ourInstance = new jUnit_affichage();

    public static jUnit_affichage getInstance() {
        return ourInstance;
    }

    public jUnit_affichage() {
    }

    @Test
    public void couleurmilieu()
    {
        RubiksCubeFacetPermutation p=new RubiksCubeFacetPermutation();

        Color[] toReturn=new Color[9];
        int x=1;
        int y=2;
                toReturn[x + y*3 ]=p.getFacetColor(RubiksFace.U,x,y);
        System.out.println(toReturn[x+y*3]);

        System.out.println(p.getFacetColor(RubiksFace.U,1,1));

    }
    public void affichage()
    {

        RubiksRenderer p=new RubiksRenderer(new RubiksCubeFacetPermutation());
        p.afficheCubeFait();

    }
}
