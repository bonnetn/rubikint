package IHM;

import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rendering.Java3DRenderer;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by florian on 29/01/17.
 */
public class Fenetre extends JFrame {



    public Fenetre() { // constructeur pour etablir les settings de la fenetre

        this.setTitle("Rubik'INT");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        // ajouter canvas3D du cube.

        //this.setMenuBar(createMenuBar());
        this.add("South", bouton());
        this.setVisible(true);
    }

    private JPanel bouton() {
        JPanel panel = new JPanel();

        final JButton white = new JButton("White");
        final JButton yellow = new JButton("Yellow");
        final JButton red = new JButton("Red");
        final JButton orange = new JButton("Orange");
        final JButton blue = new JButton("Blue");
        final JButton green = new JButton("Green");

        ActionListener al = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == white)
                    ;//tourne la face blanche
                if (e.getSource() == yellow)
                    ;//tourne la face jaune
                if (e.getSource() == red)
                    ;//tourne la face rouge
                if (e.getSource() == orange)
                    ;//tourne la face orange
                if (e.getSource() == blue)
                    ;//tourne la face bleue
                if (e.getSource() == green)
                    ;//tourne la face verte
            }
        };

        white.addActionListener(al);
        yellow.addActionListener(al);
        red.addActionListener(al);
        orange.addActionListener(al);
        blue.addActionListener(al);
        green.addActionListener(al);

        panel.add(white);
        panel.add(yellow);
        panel.add(red);
        panel.add(orange);
        panel.add(blue);
        panel.add(green);

        return panel;
    }


}
