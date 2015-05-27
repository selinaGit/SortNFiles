package fork.join;

import dev.fun.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * This is another Main entry for merge sort files, but this one do not support write files
 * because it is too slow to use fork/join to implement file writing
 */
public class MergeMain {

    public static final int READ_FILE_NUM = 10;
    public static final int RUN_REPEAT_TIME = 1;

    public static void main(String[] args) throws IOException {

        String javaVersion = Util.getVersion();
        if ( !Util.isJava8(javaVersion)) {
            System.out.println("Need Java8 JDK to Run this Project");
            System.exit(-1);
        }

        if ( args.length != 2) {
            System.out.println("Need two arguments for \"-s ./$foldDir/\" this project.");
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

            System.out.println("Do not support generate file with fork/join version, please try command below" +
                "mvn exec:java -Dexec.mainClass=\"dev.fun.Main\" -Dexec.args=\"-s ./input/\"");
        } else {

            System.out.println("Comment Lines:\n " +
                    "mvn exec:java -Dexec.mainClass=\"dev.fun.Main\" -Dexec.args=\"-g ./output/\"\n");
        }
    }


    /**
     *
     * Read files, sort all files by fork-join and merge sort all files
     * @param fileNum
     * @param foldDir
     * @throws IOException
     */
    public static void readFileAndSort(int fileNum, String foldDir) throws IOException {

        DocumentsMerger test = new DocumentsMerger();


        for(int j = 0; j < RUN_REPEAT_TIME; j++ ) {

            List<BufferedReader> buffReaders = new ArrayList<>();
            for ( int i=1; i<= fileNum; i++) {
                String fileName = String.format("%02d", i) +".txt";
                buffReaders.add(new BufferedReader(new FileReader(foldDir+fileName)));
            }

            //read all files data to memory
            long startTime = System.currentTimeMillis();
            Fold documents = Fold.fromMultiFiles(buffReaders);
            long endReadTime = System.currentTimeMillis();
            System.out.println("Read all files --fork/join took " + (endReadTime - startTime)/1000.0 + " Second");

            //sort data
            test.sortMultipleDocumentForkJoin(documents, foldDir);
            long endTime = System.currentTimeMillis();
            System.out.println("Total time --fork/join took " + (endTime - startTime)/1000.0 + " Second\n");

            for (BufferedReader reader:  buffReaders) {
                reader.close();
            }
        }
    }
}
