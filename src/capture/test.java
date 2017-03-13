package capture;

/**
 * Created by florian on 09/03/17.
 */


// LANCE LA CAM SANS ENREGISTRER
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.opencv_videoio.*;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacv.*;

import javax.swing.*;

import java.awt.*;

import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_videoio.*;


public class test {

    JLabel label = new JLabel();
    JFrame frame = new JFrame();

    public test(){
        frame.setTitle("Rubik'INT");
        frame.setSize(1280, 755); //tester et aprouve pour BACKGROUND VISIBLE
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());


        CvCapture capture = cvCreateCameraCapture(CV_CAP_ANY);
        IplImage frames;
        cvNamedWindow("Video",CV_WINDOW_AUTOSIZE);
        //frames=cvQueryFrame(capture);


        for(;;){
            frames = cvQueryFrame(capture);
            if (frames == null){ System.out.println("ERROR : NO Video File");break;}

            cvShowImage("Video",frames);
            char c = (char) cvWaitKey(30);

            if(c=='q'){ cvReleaseCapture(capture); cvDestroyWindow("Video");}
        }


    }

    public static void main(String[] args){

        test test = new test();
    }
}
