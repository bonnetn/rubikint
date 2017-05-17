package test;

import com.jogamp.opengl.util.FPSAnimator;
import ihm.frame.interactivsolver.InteractivSolver_Solver;
import rendering.OpenGLRenderer;
import rubikscube.RubiksCube;
import rubikscube.enums.Color;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

/**
 * Created by GRANTE Florian on 14/05/2017.
 */
public class TestPermutation {

    OpenGLRenderer renderer;
    private static GLCanvas canvas = new GLCanvas();

    public static void main(String[] args) {
        Color[][][] testColor = new Color[6][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    testColor[0][i][j] = Color.GREEN;
                } else {
                    testColor[0][i][j] = Color.RED;
                }

            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    testColor[0][i][j] = Color.RED;
                } else {
                    testColor[0][i][j] = Color.BLUE;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    testColor[0][i][j] = Color.BLUE;
                } else {
                    testColor[0][i][j] = Color.ORANGE;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    testColor[0][i][j] = Color.ORANGE;
                } else {
                    testColor[0][i][j] = Color.GREEN;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                testColor[4][i][j] = Color.WHITE;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                testColor[5][i][j] = Color.YELLOW;
            }
        }

        RubiksCube rubiksCube = new RubiksCube();

        rubiksCube.setRubiksCubeColor(testColor);
        int[] testPermutation = rubiksCube.getTable();
        System.out.println("La table de permutation");
        for (int i = 0; i < testPermutation.length; i++) {
            System.out.println(testPermutation[i]);
        }
        System.out.println("Table de permutation fini");
        System.out.println("Taille de ");
        System.out.println(rubiksCube.indexToReach.size());
        System.out.println(rubiksCube.actualIndex.size());
    }
}
