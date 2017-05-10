package ihm.frame.interactivsolver;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import jogamp.nativewindow.windows.PIXELFORMATDESCRIPTOR;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.opencv_videoio.*;
import org.bytedeco.javacv.*;
import rubikscube.enums.Color;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.file.Watchable;

import static org.bytedeco.javacpp.opencv_videoio.*;

/**
 * Created by florian on 07/04/17.
 */


public class InteractivSolver_capture extends JLabel{


    int faceCapture = 0;
    Color[][] faceColor = new Color[6][9];
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
            }
        });
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Previous OK");
                setFace();
                for (int i=0;i<9;i++)
                {
                    System.out.println(faceColor[0][i].getValue());
                }

            }
        });
        shoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Shoot OK");
                //setFace();
                //for (int i=0;i<9;i++)
                //{
                //    System.out.println(faceColor[0][i].getValue());
                //}
                /*
                System.out.println(java.awt.Color.YELLOW);
                System.out.println(java.awt.Color.WHITE);
                System.out.println(java.awt.Color.ORANGE);
                System.out.println(java.awt.Color.RED);
                System.out.println(java.awt.Color.BLUE);
                System.out.println(java.awt.Color.GREEN);
                */

                BufferedImage img = Webcam.getDefault().getImage();
                float[] rgb = readColor(0,img);
                System.out.println("Face HG");
                System.out.println(rgb[0]);
                //System.out.println(rgb[1]);
                //System.out.println(rgb[2]);
                rgb = readColor(1,img);
                System.out.println("Face HM");
                System.out.println(rgb[0]);
                //System.out.println(rgb[1]);
                //System.out.println(rgb[2]);
                rgb = readColor(2,img);
                System.out.println("Face HD");
                System.out.println(rgb[0]);
                //System.out.println(rgb[1]);
                //System.out.println(rgb[2]);
                rgb = readColor(3,img);
                System.out.println("Face MG");
                System.out.println(rgb[0]);
                //System.out.println(rgb[1]);
                //System.out.println(rgb[2]);
                rgb = readColor(4,img);
                System.out.println("Face MM");
                System.out.println(rgb[0]);
                //System.out.println(rgb[1]);
                //System.out.println(rgb[2]);
                rgb = readColor(5,img);
                System.out.println("Face MD");
                System.out.println(rgb[0]);
                //System.out.println(rgb[1]);
                //System.out.println(rgb[2]);
                rgb = readColor(6,img);
                System.out.println("Face BG");
                System.out.println(rgb[0]);
                //System.out.println(rgb[1]);
                //System.out.println(rgb[2]);
                rgb = readColor(7,img);
                System.out.println("Face BM");
                System.out.println(rgb[0]);
                //System.out.println(rgb[1]);
                //System.out.println(rgb[2]);
                rgb = readColor(8,img);
                System.out.println("Face BD");
                System.out.println(rgb[0]);
                //System.out.println(rgb[1]);
                //System.out.println(rgb[2]);

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
            for (int j=0;j<9;j++)
            {
                faceColor[faceCapture][j] = defineColor(readColor(j,img));
            }
            //faceCapture ++;
        }



    }

    public float[] readColor(int j, BufferedImage img)
    {
        java.awt.Color PixelColor;
        int x=0; int y=0; int R=0; int G=0; int B=0;
        if (j==0) {x = 215; y = 135;}
        if (j==1) {x = 315; y = 135;}
        if (j==2) {x = 415; y = 135;}
        if (j==3) {x = 215; y = 235;}
        if (j==4) {x = 315; y = 235;}
        if (j==5) {x = 415; y = 235;}
        if (j==6) {x = 215; y = 335;}
        if (j==7) {x = 315; y = 335;}
        if (j==8) {x = 415; y = 335;}

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
            return Color.RED;
        }
        if (H < 0.12f)
        {
            return Color.ORANGE;
        }
        if ( H < 0.50f && H >=0.30f)
        {
            return Color.GREEN;
        }
        if (H < 0.90f && H >=0.50f)
        {
            if (S <= 0.30)
            {
                return Color.WHITE;
            }else{
                return Color.BLUE;
            }

        }
        if (H >= 0.12f && H < 0.30f)
        {
            return Color.YELLOW;
        }
        return Color.BLACK;

    }
        /* SEUIL COULEUR A TESTER
        R > 100 && G < 50 && B < 50 ==> RED
        R > 100 && G > 60 && B < 50 ==> ORANGE
        R < 35  && G > 70 && 30 < B < 80 ==> GREEN
        R < 30  && G > 50 && B > 110 ==> BLUE
        R > 110 && G > 110 && 51 < B < 109 ==> YELLOW
        R > 110 && G > 110 && B > 110 ==> WHITE
 */

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
}
