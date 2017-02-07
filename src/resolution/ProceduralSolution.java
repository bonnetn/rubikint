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
    private int priority; //désigne l'étape à laquelle on est: On ne regardera pas les procédures de priorité plus faible
    public ProceduralSolution(RubiksCube cube,int priority)
    {
        this.cube=cube;
        this.priority = priority;
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
    }
    //On retourne la priorité la plus faible, supérieure à la priorité actuelle, dont une procédure peut être utilisée
    public Procedure selectProcedure(ArrayList <Procedure> procs)
    {
        for(Procedure procedure:procs)
        {
            if(procedure.getPriority()>=this.priority)
            {

                    if(procedure.getConfig().match(cube))
                    {
                        return procedure;
                    }

            }
        }
        return null;
    }

}
