package ihm.frame.accueil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by florian on 04/03/17.
 */
public class AccCapture extends JPanel {

    public AccCapture(){

        JButton accCapture = new JButton();
        Icon i = new ImageIcon("AccueilCaptureCube.png");
        accCapture.setIcon(i);
        //setOpaque(false);
        accCapture.setBorder(null);
        accCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Capture OK");
            }
        });
        this.setBounds(450,350,341,40);
        this.add(accCapture);
    }
}
