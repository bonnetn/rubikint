package ihm.frame.accueil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by florian on 03/03/17.
 */
public class AccBackground extends JPanel { //background de Accueil

    public AccBackground(){

        ImageIcon image = new ImageIcon("AccueilBackground.png");
        JLabel label = new JLabel("",image,JLabel.CENTER);
        //BorderLayout();
        add(label/*,BorderLayout.CENTER*/);
        this.setBounds(0,0,1280,720);
    }

}
