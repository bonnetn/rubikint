package ihm.frame.interactivsolver;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import rubikscube.enums.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import static rubikscube.enums.Color.*;

/**
 * Created by florian on 07/04/17.
 */
/* SETLABEL pour afficher quel face est capture et affichage de la
derniere face capture

methode pour passer des couleurs au permutation.
 */

public class InteractivSolver_capture extends JLabel{


    int faceCapture = 0;
    Color[][][] faceColor = new Color[6][3][3];
    BufferedImage img;

    Webcam webcam;
    WebcamPanel panel;
    JButton done = new JButton();
    JButton next = new JButton();
    JButton previous = new JButton();
    JButton shoot = new JButton();
    JLayeredPane kamera = new JLayeredPane();
    Color testcolor;
    Placement_Facette placement_facette = new Placement_Facette(java.awt.Color.BLACK);
    drawFacette drawFacette = new drawFacette();
    int done_x=100;     int done_y=500;
    int next_x=400;     int next_y=400;
    int previous_x=50; int previous_y=400;
    int shoot_x=100;    int shoot_y=300;

    public InteractivSolver_capture(){
        final ImageIcon background = new ImageIcon("img/InteractivSolver/interactiv_solver.png");
        setIcon(background);
        setLayout(null);

        ImageIcon done1 = new ImageIcon("img/InteractivSolver/Capture/Done.png");
        ImageIcon done2 = new ImageIcon("img/InteractivSolver/Capture/Done2.png");
        ImageIcon done3 = new ImageIcon("img/InteractivSolver/Capture/Done3.png");
        ImageIcon next1 = new ImageIcon("img/InteractivSolver/Capture/Next.png");
        ImageIcon next2 = new ImageIcon("img/InteractivSolver/Capture/Next2.png");
        ImageIcon next3 = new ImageIcon("img/InteractivSolver/Capture/Next3.png");
        ImageIcon previous1 = new ImageIcon("img/InteractivSolver/Capture/Previous.png");
        ImageIcon previous2 = new ImageIcon("img/InteractivSolver/Capture/Previous2.png");
        ImageIcon previous3 = new ImageIcon("img/InteractivSolver/Capture/Previous3.png");
        ImageIcon shoot1 = new ImageIcon("img/InteractivSolver/Capture/Shoot.png");
        ImageIcon shoot2 = new ImageIcon("img/InteractivSolver/Capture/Shoot2.png");
        ImageIcon shoot3 = new ImageIcon("img/InteractivSolver/Capture/Shoot3.png");

        done.setIcon(done1);
        done.setRolloverIcon(done2);
        done.setPressedIcon(done3);
        next.setIcon(next1);
        next.setRolloverIcon(next2);
        next.setPressedIcon(next3);
        previous.setIcon(previous1);
        previous.setRolloverIcon(previous2);
        previous.setPressedIcon(previous3);
        shoot.setIcon(shoot1);
        shoot.setRolloverIcon(shoot2);
        shoot.setPressedIcon(shoot3);

        done.setBounds(done_x,done_y,169,54);
        next.setBounds(next_x,next_y,163,51);
        previous.setBounds(previous_x,previous_y,313,51);
        shoot.setBounds(shoot_x,shoot_y,217,53);
        done.setBorder(null);
        next.setBorder(null);
        previous.setBorder(null);
        shoot.setBorder(null);

        kamera.setBounds(600,50,640,480);
        kamera.setVisible(true);
        kamera.setOpaque(false);


        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Done OK");
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Next OK");
                if (faceCapture <5)
                {
                    faceCapture++;
                }
                System.out.println(faceCapture);

            }
        });
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Previous OK");
                if (faceCapture >0)
                {
                    faceCapture--;

                }
                System.out.println(faceCapture);
            }
        });
        shoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Shoot OK");
                setFace();
                for (int i=0;i<3;i++)
                {
                    for (int j=0;j<3;j++)
                    {
                        System.out.println(faceColor[faceCapture][i][j].getValue());
                    }

                }
                Thread threadDrawFace = new Thread(new ThreadDrawFace());
                threadDrawFace.start();
                drawFacette.repaint();
            }
        });
        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        panel = new WebcamPanel(webcam);
        panel.setMirrored(true);
        panel.setVisible(true);
        panel.setBounds(0,0,640,480);
        placement_facette.setBounds(0,0,640,480);
        placement_facette.setVisible(true);
        placement_facette.setOpaque(false);
        kamera.add(panel,new Integer(0));
        kamera.add(placement_facette,new Integer(1));

        add(done);
        add(next);
        add(previous);
        add(shoot);
        add(kamera);
    }
    public JButton getDone(){return done;}

    public void setFace()
    {
        if (faceCapture<6)
        {
            img = Webcam.getDefault().getImage();
            for (int i=0;i<3;i++) {

                for (int j = 0; j < 3; j++) {
                    faceColor[faceCapture][i][j] = defineColor(readColor(i,j,img));
                }
            }
        }



    }

    public float[] readColor(int i,int j, BufferedImage img)
    {
        java.awt.Color PixelColor;
        int x=0; int y=0; int R=0; int G=0; int B=0;
        if (i==0) {x = 215;}
        if (i==1) {x = 315;}
        if (i==2) {x = 415;}
        if (j==0) {y = 135;}
        if (j==1) {y = 235;}
        if (j==2) {y = 335;}

        for (int h=0;h<5;h++)
        {
            for (int l=0;l<5;l++)
            {
               PixelColor = new java.awt.Color(img.getRGB(x+l,y+h));
               R+=PixelColor.getRed();
               G+=PixelColor.getGreen();
               B+=PixelColor.getBlue();

            }
        }
        R/=25;
        G/=25;
        B/=25;
        float[] HSV = new float[3];
        java.awt.Color.RGBtoHSB(R,G,B,HSV);
        return HSV;
    }


    public Color defineColor(float[] rgb)
    {
        float H=rgb[0];
        float S=rgb[1];
        float V=rgb[2];
        if (H >= 0.9f) //OK
        {
            return RED;
        }
        if (H < 0.13f)
        {
            return ORANGE;
        }
        if ( H < 0.50f && H >=0.30f)
        {
            return Color.GREEN;
        }
        if (H < 0.90f && H >=0.50f)
        {
            if (S <= 0.30)
            {
                return WHITE;
            }else{
                return BLUE;
            }

        }
        if (H >= 0.13f && H < 0.30f)
        {
            return Color.YELLOW;
        }
        return Color.BLACK;

     }

    public class Placement_Facette extends JPanel
    {
        java.awt.Color couleur_centrale;
        public Placement_Facette(java.awt.Color color)
        {
            couleur_centrale = color;
        }
        public void paintComponent(Graphics g)
        {
            g.setColor(couleur_centrale);
            g.fillOval(310,230,20,20);
            g.setColor(java.awt.Color.black);
            g.fillOval(210,130,20,20);
            g.fillOval(310,130,20,20);
            g.fillOval(410,130,20,20);
            g.fillOval(210,230,20,20);
            g.fillOval(410,230,20,20);
            g.fillOval(210,330,20,20);
            g.fillOval(310,330,20,20);
            g.fillOval(410,330,20,20);

        }
    }

    public class drawFacette extends JPanel
    {
        int y = 0;
        public drawFacette(){};
        public void paintComponent(Graphics ge)
        {
            for (int indice=0;indice<=faceCapture;indice++) {
                int x = indice*150 + 50;
                for (int i=0;i<3;i++) {
                    for (int j = 0; j < 3; j++) {
                        switch (faceColor[indice][j][i]) {
                            case BLUE:
                                ge.setColor(java.awt.Color.BLUE);
                                break;
                            case GREEN:
                                ge.setColor(java.awt.Color.GREEN);
                                break;
                            case ORANGE:
                                ge.setColor(java.awt.Color.GRAY);
                                break;
                            case RED:
                                ge.setColor(java.awt.Color.RED);
                                break;
                            case WHITE:
                                ge.setColor(java.awt.Color.WHITE);
                                break;
                            case YELLOW:
                                ge.setColor(java.awt.Color.YELLOW);
                                break;
                        }
                        ge.fillRect(x+33*j,y+33*i,30,30);
                    }
                }
            }

        }
    }

    public class ThreadDrawFace implements Runnable
    {
        @Override
        public void run()
        {
            drawFacette = new drawFacette();
            drawFacette.setBounds(0,580,1280,200);
            drawFacette.setVisible(true);
            drawFacette.setOpaque(false);
            drawFacette.repaint();
            add(drawFacette);
        }
    }

    public void setInstruction()
    {
        switch (faceCapture)
        {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    public Color[][][] getFaceColor() {return faceColor;}
}
