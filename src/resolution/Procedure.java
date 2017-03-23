package resolution;

import java.util.ArrayList;

import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

/**
 * Created by shininisan on 26/01/17.
 */
public class Procedure {
    private ArrayList<Rotation> proc;
    private  Rotation fallbackOption;
    private int priority; // On utilisera les procédures de plus haute priorité en premier
    private RubiksConfiguration config;
    private Validator validator;

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(RubiksConfiguration validator) {
        this.validator = validator;
    }

    public Procedure(ArrayList<Rotation> rot, int priority, RubiksConfiguration config, Rotation fallback,Validator finalState)
    {
        this.proc=rot;
        this.priority=priority;
        this.config=config;
        this.fallbackOption=fallback;
        this.validator =finalState;

    }
    public Procedure(Procedure p)
    {
        this.proc=new ArrayList<Rotation>(p.getProc());
        this.priority=p.getPriority();
        this.config=new RubiksConfiguration(p.getConfig());
        this.fallbackOption=p.getFallbackOption();
        this.validator =p.getValidator();
    }
    public Procedure invertedRotation()
    {
        Procedure p=new Procedure(this);
        for(int i=0;i<p.proc.size();i++)
        {

            int dif=(p.proc.get(i).getValue()%2)*2-1;
            p.proc.set(i,Rotation.values()[p.proc.get(i).getValue()-dif]); // on ajoute 1 au pairs, on soustrait 1 aux impairs

        }
        return p;
    }
    public Rotation getFallbackOption() {
        return fallbackOption;
    }

    public void setFallbackOption(Rotation fallbackOption) {
        this.fallbackOption = fallbackOption;
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

                this.proc.set(i,Rotation.Li);

            }
            else if(this.proc.get(i)==Rotation.Ri)
            {

                this.proc.set(i,Rotation.L);

            }
            else if(this.proc.get(i)==Rotation.L)
            {

                this.proc.set(i,Rotation.Ri);

            }
            else if(this.proc.get(i)==Rotation.Li)
            {

                this.proc.set(i,Rotation.R);

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
