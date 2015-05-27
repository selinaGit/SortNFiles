package dev.fun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * DocumentReader class get data from reader of one file and then split each line by space and sort it
 * Created by Selina on 5/24/15.
 */
public class DocumentReader {

    private final LinkedList<String> words;

    DocumentReader(LinkedList<String> words) {
        this.words = words;
    }

    public LinkedList<String> getWords() {
        return this.words;
    }

    /**
     *
     * Read and split and sort all number from Files
     * @param reader ex: Reader reader = new FileReader(OUTPUT_DIR+fileName)
     * @return
     * @throws java.io.IOException
     */
    static DocumentReader fromFile(Reader reader) throws IOException {
        LinkedList<String> words = new LinkedList<>();
        BufferedReader bufferReader = null;
        Stream<String> streamLines = null;

        try {
            long startTime = System.currentTimeMillis();

            bufferReader = new BufferedReader(reader);
            streamLines = bufferReader.lines();

            long readEndTime = System.currentTimeMillis();

            words = sortDocument(streamLines);

            long endTime = System.currentTimeMillis();

            System.out.println("Read a File Time = " + (readEndTime - startTime)/1000.0
                    + " Second, Sort a File Time = " + (endTime - readEndTime)/1000.0 + " Second");

        }catch(Exception e){
            e.printStackTrace();
        }
        return new DocumentReader(words);
    }

    /**
     *
     * split each line by spaces in document and parallel sort all words
     * @param streamlines
     * @return
     */
    static LinkedList<String> sortDocument(Stream<String> streamlines) {

        LinkedList words = new LinkedList<String>();

        streamlines.map(line -> line.split("\\s+")) // Stream<String[]>
                .flatMap(Arrays::stream) // Stream<String>
                .parallel()
                .sorted()
                .forEachOrdered(words::add);
        return words;
    }

}