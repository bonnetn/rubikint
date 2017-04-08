package ihm.frame.interactivsolver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by florian on 07/04/17.
 */
public class InteractivSolver_capture extends JLabel{

    JButton done = new JButton();
    JButton next = new JButton();
    JButton previous = new JButton();
    JButton shoot = new JButton();
    int done_x=100;     int done_y=500;
    int next_x=400;     int next_y=400;
    int previous_x=50; int previous_y=400;
    int shoot_x=100;    int shoot_y=300;


    public InteractivSolver_capture(){
        final ImageIcon background = new ImageIcon("img/InteractivSolver/interactiv_solver.png");
        setIcon(background);
        setLayout(null);

        ImageIcon done1 = new ImageIcon("img/InteractivSolver/Capture/Done.png");
        ImageIcon done2 = new ImageIcon("img/InteractivSolver/Capture/Done2.png");
        ImageIcon done3 = new ImageIcon("img/InteractivSolver/Capture/Done3.png");
        ImageIcon next1 = new ImageIcon("img/InteractivSolver/Capture/Next.png");
        ImageIcon next2 = new ImageIcon("img/InteractivSolver/Capture/Next2.png");
        ImageIcon next3 = new ImageIcon("img/InteractivSolver/Capture/Next3.png");
        ImageIcon previous1 = new ImageIcon("img/InteractivSolver/Capture/Previous.png");
        ImageIcon previous2 = new ImageIcon("img/InteractivSolver/Capture/Previous2.png");
        ImageIcon previous3 = new ImageIcon("img/InteractivSolver/Capture/Previous3.png");
        ImageIcon shoot1 = new ImageIcon("img/InteractivSolver/Capture/Shoot.png");
        ImageIcon shoot2 = new ImageIcon("img/InteractivSolver/Capture/Shoot2.png");
        ImageIcon shoot3 = new ImageIcon("img/InteractivSolver/Capture/Shoot3.png");

        done.setIcon(done1);
        done.setRolloverIcon(done2);
        done.setPressedIcon(done3);
        next.setIcon(next1);
        next.setRolloverIcon(next2);
        next.setPressedIcon(next3);
        previous.setIcon(previous1);
        previous.setRolloverIcon(previous2);
        previous.setPressedIcon(previous3);
        shoot.setIcon(shoot1);
        shoot.setRolloverIcon(shoot2);
        shoot.setPressedIcon(shoot3);

        done.setBounds(done_x,done_y,169,54);
        next.setBounds(next_x,next_y,163,51);
        previous.setBounds(previous_x,previous_y,313,51);
        shoot.setBounds(shoot_x,shoot_y,217,53);
        done.setBorder(null);
        next.setBorder(null);
        previous.setBorder(null);
        shoot.setBorder(null);

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Done OK");
            }
        });
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
        shoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Shoot OK");
            }
        });

        add(done);
        add(next);
        add(previous);
        add(shoot);
    }

    public JButton getDone(){return done;}
}
