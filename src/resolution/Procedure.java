package resolution;

import java.lang.reflect.Array;
import java.util.ArrayList;

import rubikscube.RubiksCube;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

/**
 * Created by shininisan on 26/01/17.
 */
public class Procedure {
    private ArrayList<Rotation> proc;
    private int priority; // On utilisera les procédures de plus haute priorité en premier
    private RubiksConfiguration config;
    public Procedure(ArrayList<Rotation> rot,int priority,RubiksConfiguration config)
    {
        this.proc=rot;
        this.priority=priority;
        this.config=config;

    }

    /**
     *Permet une translation par rapport à un de 4 coté (ni le dessus ni le dessous)
     * Une translation DOIT etre effectuee APRES une symetry.
     * @param faceNouvelle
     */
    public void translation(Face faceNouvelle)
    {
        config.translation(faceNouvelle);


        for(int i=0;i<proc.size();i++)
        {
            this.proc.set(i,Rotation.values()[(proc.get(i).getValue()+(faceNouvelle.getValue()-Face.F.getValue())*2)%8]);
        }



    }

    /**
     * Permet de symetriser par rapport a un axe vertical.
     * DOIT etre utilisé AVANT toute translation
     */
    public void symetry()
    {
        //On symétrise les rotations: L <=> R et pour le reste A<=>Ai
        for(int i=0;i<proc.size();i++)
        {
            config.symetry();

            if(this.proc.get(i)==Rotation.R)
            {

                this.proc.set(i,Rotation.L);

            }
            else if(this.proc.get(i)==Rotation.Ri)
            {

                this.proc.set(i,Rotation.Li);

            }
            else if(this.proc.get(i)==Rotation.L)
            {

                this.proc.set(i,Rotation.R);

            }
            else if(this.proc.get(i)==Rotation.Li)
            {

                this.proc.set(i,Rotation.Ri);

            }
            else
            {
                int dif=(proc.get(i).getValue()%2)*2-1;
                this.proc.set(i,Rotation.values()[proc.get(i).getValue()-dif]); // on ajoute 1 au pairs, on soustrait 1 aux impairs
            }

        }
    }

    public RubiksConfiguration getConfig() {
        return config;
    }

    public ArrayList<Rotation> getProc() {
        return proc;
    }

    public int getPriority() {
        return priority;
    }

    public void setProc(ArrayList<Rotation> proc) {
        this.proc = proc;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
