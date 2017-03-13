package capture;


import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_videoio.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2HSV;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import static org.bytedeco.javacpp.opencv_videoio.*;


/**
 * Created by florian on 09/03/17.
 */

// OUVRE DEUX FENETRE, LA CAM PUIS UNE CAM AVEC FILTRE COULEUR, ICI LA COULEUR BLEU EST FILTRE
    // FONCTIONNE TRES BIEN, COMMENT PASSER DE CE FILTRE A UNE DETECTION DANS L ESPACE D UNE COULEUR PUIS D EN DEDUIRE LA CONFIGURATION DU CUBE
    // NECESSITERAIS D AFFICHER LA CAM AVEC UN CALQUE DE PETIT CARRE DESSUS, ZONE DANS LAQUELLE ON FERAIT LA DETECTION DE COULEURS.
    //EN FAISANT UNE MOYENNE SUR LES PIXELS PRESENT DANS LE PETIT CUBE EN QUESTION, ON POURRAIT FACILEMENT DETERMINER LES COULEURS.
public class test2 {

    public static void main(String[] args) {

        IplImage img1, imghsv, imgbin;
        imghsv = cvCreateImage(cvSize(640,480), 8, 3);
        imgbin = cvCreateImage(cvSize(640,480), 8, 1);

        CvCapture capture1 = cvCreateCameraCapture(CV_CAP_ANY);
        int i =1;

        while(i==1) {


            img1 = cvQueryFrame(capture1);
            if (img1 == null)break;

            cvCvtColor(img1, imghsv, CV_BGR2HSV);
            CvScalar minc = cvScalar(95, 150, 75, 0), maxc = cvScalar(145, 255, 255, 0);
            cvInRangeS(imghsv, minc, maxc, imgbin);

            cvShowImage("color", img1);
            cvShowImage("Binary", imgbin);
            char c =(char) cvWaitKey(15);
            if (c == 'q') break;
        }

       // cvReleaseImage(img1);
        cvReleaseImage(imghsv);
        cvReleaseImage(imgbin);
        cvReleaseCapture(capture1);
    }
}
