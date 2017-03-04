package ihm.frame.accueil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by florian on 03/03/17.
 */
public class AccRandomCube extends JPanel {

    public AccRandomCube() {
        //this.setSize(600,30);
        //this.setLayout(null);
        JButton accRandom = new JButton();
        accRandom.setBackground(Color.BLACK);
        Icon i = new ImageIcon("AccueilSolveRandom.png");
        accRandom.setIcon(i);
        //setOpaque(true);
        accRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Random OK");
            }
        });
        this.setBounds(400,250,455,40);
        this.add(accRandom);
    }
}
