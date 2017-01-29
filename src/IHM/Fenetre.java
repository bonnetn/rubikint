package IHM;

import javax.swing.JFrame;

/**
 * Created by florian on 29/01/17.
 */
public class Fenetre extends JFrame {

    public Fenetre() { // constructeur pour etablir les settings de la fenetre
        this.setTitle("Rubik'INT");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Fenetre fenetre = new Fenetre();
    }
}
