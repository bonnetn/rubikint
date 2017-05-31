package ihm.frame.interactivsolver;

import com.jogamp.opengl.util.FPSAnimator;
import rendering.OpenGLRenderer;
import rendering.enums.Axis;
import rubikscube.enums.Rotation;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by florian on 07/04/17.
 */
public class InteractivSolver_Solver extends JLabel {

    ArrayList<Rotation> solution;
    JLabel afficheEtape;
    OpenGLRenderer renderer;
    private GLCanvas canvas;
    int indice = 0;
    int max;

    JButton next = new JButton();
    JButton previous = new JButton();
    JButton returne = new JButton();
    JButton solveAll = new JButton();


    int next_x=400;     int next_y=300;
    int previous_x=50; int previous_y=300;
    int returne_x=100;  int returne_y=500;
    int solveAll_x=50; int solveAll_y=400;

    public InteractivSolver_Solver(){

        /** ------------------------------SET INTERFACE GRAPHIQUE (BACKGROUND, BOUTON)----- -------------------------**/
         final ImageIcon background = new ImageIcon("img/InteractivSolver/interactiv_solver.png");
         setIcon(background);
         setLayout(null);

         ImageIcon next1 = new ImageIcon("img/InteractivSolver/Solver/Next.png");
         ImageIcon next2 = new ImageIcon("img/InteractivSolver/Solver/Next2.png");
         ImageIcon next3 = new ImageIcon("img/InteractivSolver/Solver/Next3.png");
         ImageIcon previous1 = new ImageIcon("img/InteractivSolver/Solver/Previous.png");
         ImageIcon previous2 = new ImageIcon("img/InteractivSolver/Solver/Previous2.png");
         ImageIcon previous3 = new ImageIcon("img/InteractivSolver/Solver/Previous3.png");
         ImageIcon returne1 = new ImageIcon("img/InteractivSolver/Solver/return.png");
         ImageIcon returne2 = new ImageIcon("img/InteractivSolver/Solver/return2.png");
         ImageIcon returne3 = new ImageIcon("img/InteractivSolver/Solver/return3.png");
         ImageIcon solveAll1 = new ImageIcon("img/InteractivSolver/Solver/solveAll.png");
         ImageIcon solveAll2 = new ImageIcon("img/InteractivSolver/Solver/solveAll2.png");
         ImageIcon solveAll3 = new ImageIcon("img/InteractivSolver/Solver/solveAll3.png");

         next.setIcon(next1);
         next.setRolloverIcon(next2);
         next.setPressedIcon(next3);
         previous.setIcon(previous1);
         previous.setRolloverIcon(previous2);
         previous.setPressedIcon(previous3);
         returne.setIcon(returne1);
         returne.setRolloverIcon(returne2);
         returne.setPressedIcon(returne3);
         solveAll.setIcon(solveAll1);
         solveAll.setRolloverIcon(solveAll2);
         solveAll.setPressedIcon(solveAll3);

        next.setBounds(next_x,next_y,163,51);
        previous.setBounds(previous_x,previous_y,313,51);
        returne.setBounds(returne_x,returne_y,245,52);
        solveAll.setBounds(solveAll_x,solveAll_y,340,53);
        next.setBorder(null);
        previous.setBorder(null);
        returne.setBorder(null);
        solveAll.setBorder(null);



/** ----------------------------------------ACTION LISTENER DES BOUTONS---------------------------------------------**/
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Next OK");
                if (indice < solution.size());
                afficheEtape.setVisible(false);
                doNextRotation();
                indice++;
                setJLabel();

            }
        });
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Previous OK");
                if( indice >0) {
                    afficheEtape.setVisible(false);
                    indice--;
                    doPreviousRotation();
                    setJLabel();
                }

            }
        });
        returne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Return OK");
            }
        });
        solveAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Solve All OK");
                int start = indice;
                for (int i=start;i<solution.size();i++)
                {
                    afficheEtape.setVisible(false);
                    doNextRotation();
                    indice++;
                    setJLabel();
                }
            }
        });

        add(next);
        add(previous);
        add(returne);
        add(solveAll);

        /**-----------------------------------AJOUT DU RENDU 3D ----------------------------------------------------**/

        renderer = new OpenGLRenderer();
        canvas = new GLCanvas();
        setJLabel();
        afficheEtape.setFont(new Font("Tahoma", Font.BOLD,25));
        afficheEtape.setForeground(Color.RED);
        afficheEtape.setBounds(50, 100, 475,200);
        afficheEtape.setVisible(true);
        add(afficheEtape);

        canvas.addGLEventListener(renderer);
        addKeyListener(new MyKeyListener(renderer));
        canvas.addKeyListener(new MyKeyListener(renderer));
        FPSAnimator animator = new FPSAnimator(canvas,60);
        animator.start();
        canvas.setBounds(600,50,600,600);
        add(canvas);
    }
    public JButton getReturne(){return returne;}


    /**--------------------------METHODE ANNEXE D AFFICHAGE DE LA SOLUTION ET DE ROTATION DU CUBE--------------------**/
    public void setJLabel(){
        if (indice == max){
            afficheEtape = new JLabel("GG (Good Game pour les nu...novice)");
        }else{

            switch(solution.get(indice)) {
                case L:
                    afficheEtape = new JLabel("Prochain mouvement : L (Left)");
                    break;
                case B:
                    afficheEtape = new JLabel("Prochain mouvement : B (Back)");
                    break;
                case R:
                    afficheEtape = new JLabel("Prochain mouvement : R (Right)");
                    break;
                case F:
                    afficheEtape = new JLabel("Prochain mouvement : F (Front)");
                    break;
                case U:
                    afficheEtape = new JLabel("Prochain mouvement : U (Up)");
                    break;
                case D:
                    afficheEtape = new JLabel("Prochain mouvement : D (Down)");
                    break;
                case Li:
                    afficheEtape = new JLabel("Prochain mouvement : L' (Left ')");
                    break;
                case Bi:
                    afficheEtape = new JLabel("Prochain mouvement : B' (Back ')");
                    break;
                case Ri:
                    afficheEtape = new JLabel("Prochain mouvement : R' (Right ')");
                    break;
                case Fi:
                    afficheEtape = new JLabel("Prochain mouvement : F' (Front')");
                    break;
                case Ui:
                    afficheEtape = new JLabel("Prochain mouvement : U' (Up ')");
                    break;
                case Di:
                    afficheEtape = new JLabel("Prochain mouvement : D' (Down ')");
                    break;
            }
        }
        afficheEtape.setFont(new Font("Tahoma", Font.BOLD,25));
        afficheEtape.setForeground(Color.RED);
        afficheEtape.setBounds(50, 100, 475,200);
        afficheEtape.setVisible(true);
        add(afficheEtape);
    }

    public void doNextRotation() {

        switch (solution.get(indice)) {
            case L:
                renderer.rotate(0, Axis.X, true);
                break;
            case B:
                renderer.rotate(2, Axis.Y, false);
                break;
            case R:
                renderer.rotate(2, Axis.X, false);
                break;
            case F:
                renderer.rotate(0, Axis.Y, true);
                break;
            case U:
                renderer.rotate(2, Axis.Z, false);
                break;
            case D:
                renderer.rotate(0, Axis.Z, true);
                break;
            case Li:
                renderer.rotate(0, Axis.X, false);
                break;
            case Bi:
                renderer.rotate(2, Axis.Y, true);
                break;
            case Ri:
                renderer.rotate(2, Axis.X, true);
                break;
            case Fi:
                renderer.rotate(0, Axis.Y, false);
                break;
            case Ui:
                renderer.rotate(2, Axis.Z, true);
                break;
            case Di:
                renderer.rotate(0, Axis.Z, false);
                break;
        }

    }

    public void doPreviousRotation()
    {
        switch (solution.get(indice)) {
            case L:
                renderer.rotate(0, Axis.X, !true);
                break;
            case B:
                renderer.rotate(2, Axis.Y, !false);
                break;
            case R:
                renderer.rotate(2, Axis.X, !false);
                break;
            case F:
                renderer.rotate(0, Axis.Y, !true);
                break;
            case U:
                renderer.rotate(2, Axis.Z, !false);
                break;
            case D:
                renderer.rotate(0, Axis.Z, !true);
                break;
            case Li:
                renderer.rotate(0, Axis.X, !false);
                break;
            case Bi:
                renderer.rotate(2, Axis.Y, !true);
                break;
            case Ri:
                renderer.rotate(2, Axis.X, !true);
                break;
            case Fi:
                renderer.rotate(0, Axis.Y, !false);
                break;
            case Ui:
                renderer.rotate(2, Axis.Z, !true);
                break;
            case Di:
                renderer.rotate(0, Axis.Z, !false);
                break;
        }

    }

    /**-------------------------CLASSE PERMETANT LA ROTATION AUTOUR DU CUBE DANS LE RENDU 3D-------------------------**/

    final class MyKeyListener extends KeyAdapter
    {
        //OpenGLRenderer renderer = new OpenGLRenderer();
        boolean isUpPressed;
        boolean isDownPressed;
        boolean isLeftPressed;
        boolean isRightPressed;

        public MyKeyListener (OpenGLRenderer r) { renderer = r ;}

        void doThat()
        {
            if(isDownPressed)
                renderer.alphaX +=2;
            if(isUpPressed)
                renderer.alphaX -=2;
            if(isLeftPressed)
                renderer.alphaY -=2;
            if(isRightPressed)
                renderer.alphaZ +=2;
        }

        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    isUpPressed = true; break;
                case KeyEvent.VK_DOWN:
                    isDownPressed = true; break;
                case KeyEvent.VK_LEFT:
                    isLeftPressed = true; break;
                case KeyEvent.VK_RIGHT:
                    isRightPressed = true; break;
            }
            doThat();
        }
        public void keyReleased(KeyEvent e){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    isUpPressed = false;
                    break;
                case KeyEvent.VK_DOWN:
                    isDownPressed = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    isRightPressed = false;
                    break;
                case KeyEvent.VK_LEFT:
                    isLeftPressed = false;
                    break;
            }
        }
    }

    public OpenGLRenderer getRenderer(){return renderer;}
    public ArrayList<Rotation> getSolution(){ return solution;}
    public void  setSolution(ArrayList<Rotation> sol){ solution = sol;}
    public void setMax(int i){max = i;}
}
