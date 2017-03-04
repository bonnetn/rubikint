package ihm.frame;

import ihm.frame.accueil.AccBackground;
import ihm.frame.accueil.AccCapture;
import ihm.frame.accueil.AccQuit;
import ihm.frame.accueil.AccRandomCube;

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
public class Accueil extends JLayeredPane{

    public Accueil(){
        setLayout(null);
        setBorder(null);
        setBounds(0,0,1280,720);

        AccBackground accBackground = new AccBackground();
        AccRandomCube accRandomCube = new AccRandomCube();
        AccCapture accCapture = new AccCapture();
        AccQuit accQuit = new AccQuit();

        add(accRandomCube,1);
        add(accCapture,1);
        add(accQuit,1);
        updateUI();
        add(accBackground,0);



    }
}