package paint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PPMWriter {

    private final String toBeWritten;
    
    public PPMWriter(String toBeWritten) {
        this.toBeWritten = toBeWritten;
    }

    public void writeFile(String fileName) throws IOException {
        
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(
                        new File(fileName + ".ppm")));
        
        bw.write(toBeWritten);
        
        bw.close();

    }

}
