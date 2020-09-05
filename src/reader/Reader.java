package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public String sourceText;

    public Reader() {
    }

    /** @param sourcePath specifies the path to the input
     *  reads the text from input file
     */
    public void read(String sourcePath){
        try (BufferedReader in = new BufferedReader(new FileReader(sourcePath))) {
            int ch;
            StringBuilder sourceTextBuffer = new StringBuilder();
            while ((ch = in.read()) != -1) {
                sourceTextBuffer.append((char) ch);
            }
            this.sourceText = sourceTextBuffer.toString();
        } catch (FileNotFoundException e) {
            System.out.println("The file that was specified can not be found!");
        } catch (IOException e) {
            System.out.println("There was some error while reading from the file!");
        }
    }
}

