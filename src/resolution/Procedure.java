package resolution;

import java.lang.reflect.Array;
import java.util.ArrayList;

import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

/**
 * Created by shininisan on 26/01/17.
 */
public class Procedure {
    private ArrayList<Rotation> proc;
    private int priority; // On utilisera les procédures de plus haute priorité en premier
    private ArrayList <RubiksConfiguration> configs;
    public Procedure(ArrayList<Rotation> rot,int priority,ArrayList <RubiksConfiguration> configs)
    {
        this.proc=rot;
        this.priority=priority;
        this.configs=configs;
    }

    /**
     *
     * @param FaceEnFace
     */
    public void symetry(Face FaceEnFace)
    {

    }

    public ArrayList<RubiksConfiguration> getConfigs() {
        return configs;
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
