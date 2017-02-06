package IHM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rendering.Java3DRenderer;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;
import rubikscube.enums.Face;
import rubikscube.enums.Rotation;

/**
 * Created by florian on 29/01/17.
 */
public class Fenetre extends JFrame implements ActionListener {

    private JPanel bouton_graphique = new JPanel();
    private JButton bouton = new JButton("test");

    public Fenetre() { // constructeur pour etablir les settings de la fenetre
        this.setTitle("Rubik'INT");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ajout bouton
        bouton_graphique.add(bouton);
        this.setContentPane(bouton_graphique);
        this.setVisible(true);
        bouton.addActionListener(this); // la fenetre écoute le bouton pour interragir lors du clique
    }

    @Override
    public void actionPerformed(ActionEvent e) { // rendu cube 3D quand appuie sur bouton
        Java3DRenderer r = new Java3DRenderer();
        RubiksCube rc = new RubiksCube(); //R L U D OK
        rc.rotate(Rotation.U);
        Color m=rc.getFacetColor(Face.R,0,0);
        m=rc.getFacetColor(Face.R,0,1);
        m=rc.getFacetColor(Face.R,0,2);
        m=rc.getFacetColor(Face.R,1,0);
        m=rc.getFacetColor(Face.R,1,1);
        m=rc.getFacetColor(Face.R,1,2);
        m=rc.getFacetColor(Face.R,2,0);
        m=rc.getFacetColor(Face.R,2,1);
        m=rc.getFacetColor(Face.R,2,2);
        r.drawItem(rc);
    }

    public static void main(String[] args) {
        Fenetre fenetre = new Fenetre();

    }


}
