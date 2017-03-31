/*
package resolution;

import rubikscube.RubiksCube;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

import java.util.ArrayList;

/**
 * Created by shininisan on 26/01/17.
 * Implémente une résolution par utilisation de procédures.
 */
/*
public class ProceduralSolver {
    private RubiksCube cube;
    private int priority;//désigne l'étape à laquelle on est: On ne regardera pas les procédures de priorité plus faible
    private ArrayList<Procedure> listeProcedure;
    private ArrayList<Rotation> historique;
    private Validator validator;

    public ArrayList<Procedure> getListeProcedure() {
        return listeProcedure;
    }

    public ArrayList<Rotation> getHistorique() {
        return historique;
    }

    public void setHistorique(ArrayList<Rotation> historique) {
        this.historique = historique;
    }

    public void setListeProcedure(ArrayList<Procedure> listeProcedure) {
        this.listeProcedure = listeProcedure;
    }

    public ProceduralSolver(RubiksCube cube, int priority, ArrayList<Procedure> listeProcedure)
    {
        this.cube=cube;
        this.priority = priority;
        this.listeProcedure=listeProcedure;
        this.historique= new ArrayList<Rotation>();
    }
    public ProceduralSolver(RubiksCube cube)
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

    public int maxPrio()
    {
        if(listeProcedure.size()==0)
            System.out.println("la liste de procédure est vide dans max"); // Ok
        int i=listeProcedure.get(0).getPriority();
        for (Procedure x: listeProcedure)
        {
            if (x.getPriority()>i)
            {
                i=x.getPriority();
            }

        }
    return i;
    }

    public ArrayList<Procedure> subPriority()
    {
        ArrayList<Procedure> o=new ArrayList<Procedure>();
        for (Procedure x:listeProcedure)
        {
            if(x.getPriority()==this.priority)
            {
                o.add(x);
            }
        }
    return o;
    }
    public void resolve()
    {
        this.priority=10;
        int max=maxPrio();
        while(this.priority<=max)
        {
            boolean validate=false;
            while(!validate)
            {
                Procedure toUse=selectProcedure().invertedRotation();
                this.useProcedure(toUse);
                validate=isValid();
            }
            priority+=10;
        }
    }
    public boolean isValid()
    {
        System.out.println("U");
        this.cube.printFace(Face.U);
        System.out.println("F");
        this.cube.printFace(Face.F);
        System.out.println("L");
        this.cube.printFace(Face.L);
        System.out.println("R");
        this.cube.printFace(Face.R);
        System.out.println("B");
        this.cube.printFace(Face.B);
        for (Procedure x:listeProcedure) { // REUTILISER SUBPRIORITY QUAND DEBUGGE
            if(!x.getValidator().isValid(this.cube))
            {
                return false;
            }
        }
        return true;
    }
    /**
     *Effectue les rotations de la Procédure
     * @param proc
     */
/*
    public void useProcedure(Procedure proc)
    {
        for(Rotation x:proc.getProc())
        {
            cube.rotate(x);
        }
        historique.addAll(proc.getProc());
    }
    //On retourne la priorité la plus faible, supérieure à la priorité actuelle, dont une procédure peut être utilisée
    public Procedure selectProcedure()
    {
        for(Procedure procedure:this.getListeProcedure())
        {
            if(procedure.getPriority()>=this.priority && ! procedure.getValidator().isValid(cube)) //On cherche a isValid la prio si on est pas déjà dans l'état final de la procedure. On utilise la fallback option si on ne trouve pas
            {
                int i=0;
                boolean trouve= procedure.getConfig().isValid(cube);
                    while(!trouve && i<4)
                    {

                        cube.rotate(procedure.getFallbackOption());
                        trouve= procedure.getConfig().isValid(cube);
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
*/
