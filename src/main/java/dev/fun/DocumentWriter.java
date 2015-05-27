package dev.fun;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * 1. each file contains 10,000 text lines
 * 2. each line is a space separated list of random 10 integers
 * 3. each integer contains 8 digits
 * Created by Selina on 5/25/15.
 */
public class DocumentWriter {

    public static final int MIN_RANDOM_NUM = 10000000;
    public static final int MAX_RANDOM_NUM = 99999999;
    public static final int NUMBER_PER_LINE = 10;
    public static final int DIGIT_PER_NUMBER = 8;
    public static final int DENOMINATOR_NUM = 10000000;
    public static final int LINE_PER_FILE = 10000;


    /**
     *
     * created num files to outputDir fold
     * @param num
     * @param outputDir
     */
    public void createAllDocuments(int num, String outputDir) {

        long startTime = System.currentTimeMillis();

        //if the dir is not exist, create one
        createOutputDir(outputDir);

        for ( int i=1; i<=num; i++) {

            String fileName = String.format("%03d", i) +".txt";
            Path path = Paths.get(outputDir + fileName);
            try {
                Files.deleteIfExists(path);
                BufferedWriter writer = Files.newBufferedWriter(path,  StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                writeInputDocument(writer);
                writer.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("createAllDocuments " + num +" FILES in "+ (endTime-startTime)/1000.0 + " Second");
    }

    /**
     *
     * create a dir if it is not exist
     * @param outputDir
     */
    public void createOutputDir(String outputDir) {
        Path path = Paths.get(outputDir);
        try {
            Files.createDirectories(path);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Write one file (Documents)
     * @param writer
     */
    public void writeInputDocument(BufferedWriter writer){
        long startTime = System.currentTimeMillis();

        try {
            for (int i=0; i<LINE_PER_FILE; i++) {
                writer.write(createLine());
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {

        }
        long endTime = System.currentTimeMillis();
        System.out.println("writeInputDocument in "+ (endTime-startTime)/1000.0 + " Second");
    }

    /**
     *
     * create one line with 10 numbers, each number has 8 digit, separted by " " and end with '\n'
     * @return
     */
    public char[] createLine() {

        //the extra 1 for " " and +"\n"
        int size = NUMBER_PER_LINE * (DIGIT_PER_NUMBER + 1);
        char[] line = new char[size];
        int indexPerLine = 0;

        //create a line has 10 digits separated by space and end with '\n'
        for ( int i=0; i<NUMBER_PER_LINE; i++) {
            int randomNum = Util.getRandomNumber(MIN_RANDOM_NUM, MAX_RANDOM_NUM) ;
            indexPerLine = copyRandomNumber(line, indexPerLine, randomNum);
            line[indexPerLine++] = i==NUMBER_PER_LINE-1 ? '\n' : ' ';
        }

        return line;
    }

    /**
     *
     * copy inputNum to a char array start with index
     * @param copyToArray
     * @param index
     * @param inputNum
     * @return : new index
     */
    public int copyRandomNumber(char[] copyToArray, int index, int inputNum) {
        if (index >= copyToArray.length) {
            return index;
        }

        int divideNum = DENOMINATOR_NUM;
        for (int i=0; i<DIGIT_PER_NUMBER; i++) {
            copyToArray[index++] = (char)(inputNum /divideNum + '0');
            inputNum = divideNum == 0 ? 0 : inputNum % divideNum;
            divideNum /= 10;
        }
        return index;
    }

}
