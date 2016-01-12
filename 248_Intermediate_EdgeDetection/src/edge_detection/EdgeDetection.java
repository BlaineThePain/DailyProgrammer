package edge_detection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EdgeDetection {

    private static PPMImage imgIn;
    private static PPMImage imgOut;

    public static void main(String[] args) {
        
        String filename = "steam";
        
        try {
            readImageFromFile(filename + ".ppm");
            imgOut = imgIn.toGreyScale();
            printImageToFile(filename + "_00_Greyscale.ppm");
            imgOut = imgIn.avgBlur(2, 10);
            printImageToFile(filename + "_01_Blurred.ppm");
            imgOut = imgOut.edgeSobel();
            printImageToFile(filename + "_02_EdgedSobel.ppm");
            imgOut = CannyEdge.nonMaxSuppression(imgOut);
            printImageToFile(filename + "_03_EdgedCannyMinSupp.ppm");
            imgOut = CannyEdge.doubleThresholding(imgOut, 0.2, 0.7);
            printImageToFile(filename + "_04_EdgedCannyDoubleT.ppm");
        } catch (IOException ex) {
            System.out.println(ex);
        }    
    }

    private static void readImageFromFile(String filePath) throws IOException {
        
        Scanner reader = new Scanner(new File(filePath));
        
        reader.next();
        int width = reader.nextInt();
        int height = reader.nextInt();
        imgIn = new PPMImage(width, height);
        reader.next();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imgIn.drawPixel(reader.nextInt(), //red
                        reader.nextInt(),  //green
                        reader.nextInt(),  //blue
                        x, y);  //coord
            }
        }
    }
    
    private static void printImageToFile(String filePath) throws IOException {
        
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(
                        new File(filePath)));
        
        bw.write(imgOut.toString());
        
    }

}
