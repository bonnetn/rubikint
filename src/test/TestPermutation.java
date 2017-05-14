package test;

import rubikscube.RubiksCube;
import rubikscube.enums.Color;

/**
 * Created by GRANTE Florian on 14/05/2017.
 */
public class TestPermutation {

    public static void main(String[] args)
    {
        Color[][][] testColor = new Color[6][3][3];

        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                testColor[0][i][j] = Color.RED;
            }
        }
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                testColor[1][i][j] = Color.BLUE;
            }
        }
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                testColor[2][i][j] = Color.ORANGE;
            }
        }
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                testColor[3][i][j] = Color.GREEN;
            }
        }
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                testColor[4][i][j] = Color.WHITE;
            }
        }
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                testColor[5][i][j] = Color.YELLOW;
            }
        }

        RubiksCube rubiksCube = new RubiksCube();

        rubiksCube.setRubiksCubeColor(testColor);
        int[] testPermutation = rubiksCube.getTable();

        for (int i=0; i<testPermutation.length;i++)
        {
            System.out.println(testPermutation[i]);
        }


    }
}
