package test;

import com.jogamp.opengl.util.FPSAnimator;
import ihm.frame.interactivsolver.InteractivSolver_Solver;
import rendering.OpenGLRenderer;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;

import javax.media.opengl.GL;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.RuleBasedCollator;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

/**
 * Created by GRANTE Florian on 14/05/2017.
 */
public class TestPermutation {

    OpenGLRenderer renderer = new OpenGLRenderer();
    GLCanvas canvas;
    Color[][][] testColor;
    public TestPermutation() {

        CardLayout cl = new CardLayout(); // layout manager permetant de switch entre differentes scene
        JFrame frame = new JFrame();

        frame.setTitle("Rubik'INT");
        frame.setSize(1280, 755); //tester et aprouve pour BACKGROUND VISIBLE
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setLayout(null);
        renderer = new OpenGLRenderer();
        canvas = new GLCanvas();
        canvas.addGLEventListener(renderer);
        FPSAnimator animator = new FPSAnimator(canvas,60);
        animator.start();
        canvas.setBounds(600,50,600,600);
        frame.add(canvas);
        canvas.setVisible(true);
        frame.setVisible(true);


    }

    public static void main(String[] args) {

        Color[][][] testColor = new Color[6][3][3];
        /*
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j==2)
                {
                    testColor[0][i][j] = Color.BLUE;
                }else {
                    testColor[0][i][j] = Color.RED;
                }

            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j==2)
                {
                    testColor[1][i][j] = Color.ORANGE;
                }else {
                    testColor[1][i][j] = Color.BLUE;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j==2)
                {
                    testColor[2][i][j] = Color.GREEN;
                }else {
                    testColor[2][i][j] = Color.ORANGE;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j==2)
                {
                    testColor[3][i][j] = Color.RED;
                }else {
                    testColor[3][i][j] = Color.GREEN;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                testColor[5][i][j] = Color.YELLOW;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                testColor[4][i][j] = Color.WHITE;
            }
        }
*/
        testColor[0][0][0] = Color.ORANGE;
        testColor[0][0][1] = Color.RED;
        testColor[0][0][2] = Color.YELLOW;
        testColor[0][1][0] = Color.YELLOW;
        testColor[0][1][1] = Color.RED;
        testColor[0][1][2] = Color.WHITE;
        testColor[0][2][0] = Color.YELLOW;
        testColor[0][2][1] = Color.YELLOW;
        testColor[0][2][2] = Color.WHITE;

        testColor[1][0][0] = Color.GREEN;
        testColor[1][0][1] = Color.ORANGE;
        testColor[1][0][2] = Color.BLUE;
        testColor[1][1][0] = Color.RED;
        testColor[1][1][1] = Color.BLUE;
        testColor[1][1][2] = Color.YELLOW;
        testColor[1][2][0] = Color.BLUE;
        testColor[1][2][1] = Color.YELLOW;
        testColor[1][2][2] = Color.WHITE;

        testColor[2][0][0] = Color.ORANGE;
        testColor[2][0][1] = Color.GREEN;
        testColor[2][0][2] = Color.GREEN;
        testColor[2][1][0] = Color.BLUE;
        testColor[2][1][1] = Color.ORANGE;
        testColor[2][1][2] = Color.GREEN;
        testColor[2][2][0] = Color.GREEN;
        testColor[2][2][1] = Color.BLUE;
        testColor[2][2][2] = Color.BLUE;

        testColor[3][0][0] = Color.YELLOW;
        testColor[3][0][1] = Color.RED;
        testColor[3][0][2] = Color.WHITE;
        testColor[3][1][0] = Color.WHITE;
        testColor[3][1][1] = Color.GREEN;
        testColor[3][1][2] = Color.ORANGE;
        testColor[3][2][0] = Color.GREEN;
        testColor[3][2][1] = Color.GREEN;
        testColor[3][2][2] = Color.RED;

        testColor[4][0][0] = Color.BLUE;
        testColor[4][0][1] = Color.WHITE;
        testColor[4][0][2] = Color.RED;
        testColor[4][1][0] = Color.GREEN;
        testColor[4][1][1] = Color.WHITE;
        testColor[4][1][2] = Color.ORANGE;
        testColor[4][2][0] = Color.ORANGE;
        testColor[4][2][1] = Color.BLUE;
        testColor[4][2][2] = Color.RED;

        testColor[5][0][0] = Color.ORANGE;
        testColor[5][0][1] = Color.BLUE;
        testColor[5][0][2] = Color.WHITE;
        testColor[5][1][0] = Color.ORANGE;
        testColor[5][1][1] = Color.YELLOW;
        testColor[5][1][2] = Color.RED;
        testColor[5][2][0] = Color.YELLOW;
        testColor[5][2][1] = Color.WHITE;
        testColor[5][2][2] = Color.RED;

        //RubiksCube rubiksCube = new RubiksCube();
        //rubiksCube.randomMelange();
        //int[] beforeTest = rubiksCube.getTable();
        //for (int i =0; i<48;i++)
        //{
        //    System.out.println(beforeTest[i]);
        //}
        //rubiksCube.setRubiksCubeColor(testColor);

        for (int indice =0;indice<6;indice++)
        {
            for (int i =0;i<3;i++)
            {
                for (int j = 0;j<3;j++)
                {
                    System.out.println(testColor[indice][i][j]);
                }
            }
        }
        TestPermutation test = new TestPermutation();

        OpenGLRenderer renderer = test.renderer;
        RubiksCube rubiksCube = renderer.getCube();
        renderer.setRubiksCubecolor(testColor);
        int[] testPermutation = rubiksCube.getTable();
        for (int i = 0; i < testPermutation.length; i++) {
            System.out.println(testPermutation[i]);
        }
        ArrayList<Color> testColor2 = new ArrayList<>();


    }

    public void setRubisCubeColor(Color[][][] color) { renderer.setRubiksCubecolor(color);}
}
