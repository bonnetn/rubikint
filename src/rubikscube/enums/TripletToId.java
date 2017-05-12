package rubikscube.enums;

import java.util.List;

/**
 * Created by shininisan on 12.05.17.
 */
public class TripletToId {
    //but: créer le tableau 3 couleurs=> id
    //on prend un rubiks fini, on regarde les angles, on utilise 3 fois getcolor et getID
    public Color[] colors;
    public int[] id;
    public TripletToId(Color[] colors, int[] id)
    {
        this.colors=colors;
        this.id=id;
    }

    /*
        public int[] getIdFromColor(Color[] colors, TripletToId[] triplets)
        {
            boolean trouve=false;
            int i=0;
            while(!trouve && triplets.length>i )
            {
                for(Color color:colors)
                {
                    if(Arrays.asList(triplets[i]).contains(yourValue)

                    color )
                }
            }
        }
    */
    public static TripletToId[] createTab() {


        TripletToId[] tab = new TripletToId[8];
        //coins blancs
        tab[0].colors = new Color[]{Color.WHITE, Color.RED, Color.BLUE};
        tab[0].id = new int[]{15, 2, 32};
        tab[1].colors = new Color[]{Color.WHITE, Color.RED, Color.GREEN};
        tab[1].id = new int[]{13, 0, 18};
        tab[2].colors = new Color[]{Color.WHITE, Color.ORANGE, Color.GREEN};
        tab[2].id = new int[]{8, 42, 16};
        tab[3].colors = new Color[]{Color.WHITE, Color.BLUE, Color.ORANGE};
        tab[3].id = new int[]{10, 34, 40};
//coins jaunes
        tab[4].colors = new Color[]{Color.YELLOW, Color.RED, Color.BLUE};
        tab[4].id = new int[]{26, 7, 37};
        tab[5].colors = new Color[]{Color.YELLOW, Color.RED, Color.GREEN};
        tab[5].id = new int[]{24, 5, 23};
        tab[6].colors = new Color[]{Color.YELLOW, Color.ORANGE, Color.GREEN};
        tab[6].id = new int[]{29, 47, 21};
        tab[7].colors = new Color[]{Color.YELLOW, Color.BLUE, Color.ORANGE};
        tab[7].id = new int[]{10, 34, 40};
        //Arretes blanches
        tab[8].colors = new Color[]{Color.WHITE, Color.RED};
        tab[8].id = new int[]{14, 1};
        tab[9].colors = new Color[]{Color.WHITE, Color.BLUE};
        tab[9].id = new int[]{12, 33};
        tab[10].colors = new Color[]{Color.WHITE, Color.GREEN};
        tab[10].id = new int[]{11, 17};
        tab[11].colors = new Color[]{Color.WHITE, Color.ORANGE};
        tab[11].id = new int[]{9, 41};
        //Arretes jaunes
        tab[12].colors = new Color[]{Color.YELLOW, Color.RED};
        tab[12].id = new int[]{25, 6};
        tab[13].colors = new Color[]{Color.YELLOW, Color.BLUE};
        tab[13].id = new int[]{28, 38};
        tab[14].colors = new Color[]{Color.YELLOW, Color.GREEN};
        tab[14].id = new int[]{27, 22};
        tab[15].colors = new Color[]{Color.YELLOW, Color.ORANGE};
        tab[15].id = new int[]{30, 46};
        //4 restantes
        tab[16].colors = new Color[]{Color.GREEN, Color.RED};
        tab[16].id = new int[]{20, 3};
        tab[17].colors = new Color[]{Color.RED, Color.BLUE};
        tab[17].id = new int[]{4, 35};
        tab[18].colors = new Color[]{Color.BLUE, Color.ORANGE};
        tab[18].id = new int[]{36, 43};
        tab[19].colors = new Color[]{Color.ORANGE, Color.GREEN};
        tab[19].id = new int[]{44, 29};
        return tab;
    }

    public static TripletToId[] withPermut(TripletToId[] tab) {
        TripletToId[] tab2 = new TripletToId[6*tab.length];
        for (int i=0;i<6*tab.length;i++) {
            //les combinaisons possibles
            tab2[6*i]=tab[i];

            tab2[6*i+1]=new TripletToId(
                    new Color[]{tab[i].colors[0],tab[i].colors[2],tab[i].colors[1]},
                    new int[]{tab[i].id[0],tab[i].id[2],tab[i].id[1]});
            tab2[6*i+2]=new TripletToId(
                    new Color[]{tab[i].colors[1],tab[i].colors[0],tab[i].colors[2]},
                    new int[]{tab[i].id[1],tab[i].id[0],tab[i].id[2]});
            tab2[6*i+3]=new TripletToId(
                    new Color[]{tab[i].colors[1],tab[i].colors[2],tab[i].colors[0]},
                    new int[]{tab[i].id[1],tab[i].id[2],tab[i].id[0]});
            tab2[6*i+4]=new TripletToId(
                    new Color[]{tab[i].colors[2],tab[i].colors[0],tab[i].colors[1]},
                    new int[]{tab[i].id[2],tab[i].id[0],tab[i].id[1]});
            tab2[6*i+5]=new TripletToId(
                    new Color[]{tab[i].colors[2],tab[i].colors[1],tab[i].colors[0]},
                    new int[]{tab[i].id[2],tab[i].id[1],tab[i].id[0]});

        }
return tab;
    }
}
