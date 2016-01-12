package paint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Paint {

    private static PPMImage image;

    public static void main(String[] args) {

        String input = "400 300\n"
                + "rect 0 0 255 0 0 300 400\n"
                + "line 255 255 255 0 0 299 399\n"
                + "line 255 255 255 299 0 0 399\n"
                + "rect 200 200 0 100 150 100 100\n"
                + "point 0 0 0 150 200";

        /*String[] commands = breakDownInput(input);
        image = readHeader(commands[0]);
        readCommands(commands);*/
        
        readInputFromFile();

        try {
            new PPMWriter(image.toString()).writeFile("image");
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    private static void readInputFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            image = readHeader(br.readLine());

            String currLine;
            while ((currLine = br.readLine()) != null) {
                readCommand(currLine);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static String[] breakDownInput(String input) {
        return input.split("\n");
    }

    private static PPMImage readHeader(String header) {
        String[] headerArr = header.split(" ");
        return new PPMImage(Integer.parseInt(headerArr[0]), Integer.parseInt(headerArr[1]));
    }

    private static void readCommand(String command) {
        String[] commArr = command.split(" ");
        switch (commArr[0]) {
            case "point":
                image.drawPixel(Integer.parseInt(commArr[1]), //red
                        Integer.parseInt(commArr[2]), //green
                        Integer.parseInt(commArr[3]), //blue
                        Integer.parseInt(commArr[5]), //x coord
                        Integer.parseInt(commArr[4]));        //y coord
                break;
            case "rect":
                image.drawRectangle(Integer.parseInt(commArr[1]), //red
                        Integer.parseInt(commArr[2]), //green
                        Integer.parseInt(commArr[3]), //blue
                        Integer.parseInt(commArr[5]), //starting x coord
                        Integer.parseInt(commArr[4]), //starting y coord
                        Integer.parseInt(commArr[7]), //height
                        Integer.parseInt(commArr[6]));       //width
                break;
            case "line":
                image.drawLine(Integer.parseInt(commArr[1]), //red
                        Integer.parseInt(commArr[2]), //green
                        Integer.parseInt(commArr[3]), //blue
                        Integer.parseInt(commArr[5]), //starting x coord
                        Integer.parseInt(commArr[4]), //starting y coord
                        Integer.parseInt(commArr[7]), //ending x coord
                        Integer.parseInt(commArr[6]));       //ending y coord
                break;
        }
    }

    private static void readCommands(String[] commands) {
        for (int i = 1; i < commands.length; i++) {
            readCommand(commands[i]);
        }
    }

}
