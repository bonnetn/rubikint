package resolution;

import rubikscube.RubiksCube;
import rubikscube.enums.Rotation;

import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * Created by shininisan on 26/01/17.
 * Implémente une résolution par utilisation de procédures.
 */
public class ProceduralSolution {
    private RubiksCube cube;
    private int priority;//désigne l'étape à laquelle on est: On ne regardera pas les procédures de priorité plus faible

    private ArrayList<Rotation> historique;
    public ProceduralSolution(RubiksCube cube,int priority)
    {
        this.cube=cube;
        this.priority = priority;
        this.historique= new ArrayList<Rotation>();
    }
    public ProceduralSolution(RubiksCube cube)
    {
        this.cube=cube;
        this.priority = 0;
        this.historique= new ArrayList<Rotation>();
    }

    public int getPriority() {
        return priority;
    }

    public RubiksCube getCube() {
        return cube;
    }

    public void setCube(RubiksCube cube) {
        this.cube = cube;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     *Effectue les rotations de la Procédure
     * @param proc
     */

    public void useProcedure(Procedure proc)
    {
        for(Rotation x:proc.getProc())
        {
            cube.rotate(x);
        }
        historique.addAll(proc.getProc());
    }
    //On retourne la priorité la plus faible, supérieure à la priorité actuelle, dont une procédure peut être utilisée
    public Procedure selectProcedure(ArrayList <Procedure> procs)
    {
        for(Procedure procedure:procs)
        {
            if(procedure.getPriority()>=this.priority && ! procedure.getFinalState().match(cube)) //On cherche a match la prio si on est pas déjà dans l'état final de la procedure. On utilise la fallback option si on ne trouve pas
            {
                int i=0;
                boolean trouve= procedure.getConfig().match(cube);
                    while(!trouve && i<4)
                    {

                        cube.rotate(procedure.getFallbackOption());
                        trouve= procedure.getConfig().match(cube);
                        i++;

                    }

                    if(trouve) //On ajoute a l'historique i fois le mouvement de fallback
                    {
                        if(i==3)
                        {
                           int diff=(procedure.getFallbackOption().getValue()%2)*2-1;
                            historique.add(Rotation.values()[procedure.getFallbackOption().getValue()-diff]); //si on tourne 3 fois c'est le mvt inverse
                        }
                        for (int k=0;k<i;k++)
                        {
                            historique.add(procedure.getFallbackOption());
                        }


                    }
                return procedure;
            }
        }
        return null;
    }

}
