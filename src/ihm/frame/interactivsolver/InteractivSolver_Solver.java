package ihm.frame.interactivsolver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by florian on 07/04/17.
 */
public class InteractivSolver_Solver extends JLabel {

    JButton next = new JButton();
    JButton previous = new JButton();
    JButton returne = new JButton();
    JButton solveAll = new JButton();

    int next_x=400;     int next_y=300;
    int previous_x=50; int previous_y=300;
    int returne_x=100;  int returne_y=500;
    int solveAll_x=50; int solveAll_y=400;

    public InteractivSolver_Solver(){
         final ImageIcon background = new ImageIcon("img/InteractivSolver/interactiv_solver.png");
         setIcon(background);
         setLayout(null);

         ImageIcon next1 = new ImageIcon("img/InteractivSolver/Solver/Next.png");
         ImageIcon next2 = new ImageIcon("img/InteractivSolver/Solver/Next2.png");
         ImageIcon next3 = new ImageIcon("img/InteractivSolver/Solver/Next3.png");
         ImageIcon previous1 = new ImageIcon("img/InteractivSolver/Solver/Previous.png");
         ImageIcon previous2 = new ImageIcon("img/InteractivSolver/Solver/Previous2.png");
         ImageIcon previous3 = new ImageIcon("img/InteractivSolver/Solver/Previous3.png");
         ImageIcon returne1 = new ImageIcon("img/InteractivSolver/Solver/return.png");
         ImageIcon returne2 = new ImageIcon("img/InteractivSolver/Solver/return2.png");
         ImageIcon returne3 = new ImageIcon("img/InteractivSolver/Solver/return3.png");
         ImageIcon solveAll1 = new ImageIcon("img/InteractivSolver/Solver/solveAll.png");
         ImageIcon solveAll2 = new ImageIcon("img/InteractivSolver/Solver/solveAll2.png");
         ImageIcon solveAll3 = new ImageIcon("img/InteractivSolver/Solver/solveAll3.png");

         next.setIcon(next1);
         next.setRolloverIcon(next2);
         next.setPressedIcon(next3);
         previous.setIcon(previous1);
         previous.setRolloverIcon(previous2);
         previous.setPressedIcon(previous3);
         returne.setIcon(returne1);
         returne.setRolloverIcon(returne2);
         returne.setPressedIcon(returne3);
         solveAll.setIcon(solveAll1);
         solveAll.setRolloverIcon(solveAll2);
         solveAll.setPressedIcon(solveAll3);

        next.setBounds(next_x,next_y,163,51);
        previous.setBounds(previous_x,previous_y,313,51);
        returne.setBounds(returne_x,returne_y,245,52);
        solveAll.setBounds(solveAll_x,solveAll_y,340,53);
        next.setBorder(null);
        previous.setBorder(null);
        returne.setBorder(null);
        solveAll.setBorder(null);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Next OK");
            }
        });
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Previous OK");
            }
        });
        returne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Return OK");
            }
        });
        solveAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Solve All OK");
            }
        });

        add(next);
        add(previous);
        add(returne);
        add(solveAll);
    }
    public JButton getReturne(){return returne;}
}
