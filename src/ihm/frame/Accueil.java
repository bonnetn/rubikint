package ihm.frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by florian on 02/03/17.
 */
public class Accueil extends JButton{

    //JButton bouton = new JButton("");
    private Image img;

    public Accueil(){

        Icon i = new ImageIcon("AccueilBackground.png");
        this.setIcon(i);


        //add(bouton);

    }

    //public JButton getBouton(){ return bouton;}
}
