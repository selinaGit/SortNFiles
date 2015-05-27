package dev.fun;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * This is main access for this project
 */
public class Main {

    public static final int READ_FILE_NUM = 10;
    public static final int Write_FILES_NUM = 100;

    //the number of time you like to run this project
    public static final int RUN_REPEAT_TIME = 1;

    public static void main(String[] args) throws IOException {

        String javaVersion = Util.getVersion();

        if ( !Util.isJava8(javaVersion)) {
            System.out.println("Need Java8 JDK to Run this Project");
            System.exit(-1);
        }

        if ( args.length != 2) {
            System.out.println("Need Two Arguments for this Project.");
            System.exit(-1);
        }

        final String feature = args[0];
        String foldDir = args[1];

        if (!foldDir.endsWith("/")) {
            foldDir = foldDir + "/";
        }

        if ( feature.equalsIgnoreCase("-s")) {

            System.out.println("Start to read and sort data files from 01.txt to 10.txt at " + foldDir);
            readFileAndSort(READ_FILE_NUM, foldDir);

        } else if (feature.equalsIgnoreCase("-g")) {

            System.out.println("Start to generat files from 01.txt to 100.txt at " + foldDir);
            writeFiles (Write_FILES_NUM, foldDir);
        } else {

            System.out.println("Comment Lines:\n " +
                    "mvn exec:java -Dexec.mainClass=\"dev.fun.Main\" -Dexec.args=\"-g ./output/\"\n" +
                    "mvn exec:java -Dexec.mainClass=\"dev.fun.Main\" -Dexec.args=\"-s ./input/\"");
        }
    }

    /**
     *
     * read and sort file by Java Stream, merge it and then write to a output file
     * @param fileNum
     * @param inputDir
     * @throws IOException
     */
    public static void readFileAndSort(int fileNum, String inputDir) throws IOException {
        FilesMerger test = new FilesMerger();

        for (int j=0; j < RUN_REPEAT_TIME; j++) {

            List<Reader> readers = new ArrayList<>();
            for (int i = 1; i <= fileNum; i++) {

                String fileName = String.format("%02d", i)+".txt";
                readers.add(new FileReader(inputDir + fileName));
            }

            long time1 = System.currentTimeMillis();
            FoldReader documents = FoldReader.fromMultiFiles(readers);

            long time2 = System.currentTimeMillis();

            test.sortWriteMultipleDocument(documents, inputDir);

            double mergeSortTime = (System.currentTimeMillis() - time2) / 1000.0;
            double readSortTime = (time2 - time1) / 1000.0;

            System.out.println("Read All Files and Sort Separately Time = " + readSortTime
                    + " Second\nMergeSort All Files and Write to One File Time  = " + mergeSortTime
                    + " Second\nTotal Time = " + (readSortTime + mergeSortTime) + "\n");

            for (Reader reader : readers) {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }


    /**
     *
     * generate files from 01.txt to 100.txt
     * @param fileNum
     * @param outputDir
     */
    public static void writeFiles (int fileNum, String outputDir) {
        DocumentWriter test = new DocumentWriter();
        test.createAllDocuments(fileNum, outputDir);
    }

}
