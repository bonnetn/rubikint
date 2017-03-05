package ihm.frame.accueil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by florian on 04/03/17.
 */
public class AccQuit extends JPanel{

    public AccQuit() {
        this.setBounds(395,450,455,40);
        //this.setLayout(null);
        JButton accQuit = new JButton();
        Icon i = new ImageIcon("AccueilQuit.png");
        accQuit.setIcon(i);
        //setOpaque(false);
        accQuit.setBorder(null);
        accQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Quit OK");
            }
        });
        //this.setBounds(425,450,455,40);
        this.add(accQuit);
        repaint();
    }
}
