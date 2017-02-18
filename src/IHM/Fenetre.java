/*
package IHM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rubikscube.RubiksCube;
import rubikscube.enums.Rotation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by florian on 29/01/17.
 */

/*
public class Fenetre{

    Java3DRenderer r = new Java3DRenderer();
    RubiksCube cube = new RubiksCube();



    public Fenetre() { // constructeur pour etablir les settings de la fenetre
        JFrame frame = new JFrame();

        frame.setTitle("Rubik'INT");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());


        frame.add("Center",cube());

        //this.setMenuBar(createMenuBar());
        frame.add("South", bouton());
        frame.setVisible(true);
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
                if (e.getSource() == white){
                    cube.rotate(Rotation.U);

                }
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

    private JPanel cube(){
        JPanel panel = new JPanel(new GridLayout());
        //panel.setLayout(new BorderLayout());
        //Java3DRenderer r = new Java3DRenderer();
        //RubiksCube cube = new RubiksCube();
        cube.rotate(Rotation.Ui);
        cube.rotate(Rotation.Ui);
        r.drawItem(cube);
        panel.add(r.getCanvas3D());

        return panel;
    }
}

*/
