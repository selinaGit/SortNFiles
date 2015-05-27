package fork.join;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 *
 * Document class contain all data for one file
 */
public class Document {

    private final LinkedList<String> lines;

    public Document(LinkedList<String> lines) {
        this.lines = lines;
    }

    public LinkedList<String> getLines() {
        return this.lines;
    }

    public static Document fromFile(BufferedReader reader) throws IOException {

        LinkedList<String> lines = new LinkedList<>();

        try {
            long startTime = System.currentTimeMillis();

            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Read a File Time = " + (endTime - startTime)/1000.0 + " Second");

        }catch(Exception e){
            e.printStackTrace();
        }

        return new Document(lines);
    }
}
