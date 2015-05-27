package fork.join;

import dev.fun.ListCompartor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.ForkJoinPool;


/**
 *
 * FilesSortSolutionForkJoin class sort all files individully by forkJoinPool.invoke,
 * merge sort all sorted data from different files,
 * and then write date to out put file
 */
public class DocumentsMerger {

    public static final String OUTPUT_FILE_NAME = "sorted-result-fork.txt";
    public static final int NUMBER_PER_LINE = 10;

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    /**
     *
     * sort all files individully by forkJoinPool.invoke and write sorted data to file
     * @param documents
     * @param fileDir
     */
    public void sortMultipleDocumentForkJoin(Fold documents, String fileDir) {
        long time1 = System.currentTimeMillis();
        ArrayList<LinkedList<String>> docLists = forkJoinPool.invoke(new FoldTask(documents));

        long time2 = System.currentTimeMillis();

        System.out.println("Sort each file --forkJoin in " + (time2 - time1)/1000.0 + " Second");

        writeSortedData(docLists, fileDir);

        long time3 = System.currentTimeMillis();
        System.out.println("Merge Sort all files --forkJoin in " + (time3 - time2)/1000.0 + " Second");
    }


    public void writeSortedData(ArrayList<LinkedList<String>>  docLists, String fileDir) {

        Path path = Paths.get(fileDir + OUTPUT_FILE_NAME);
        BufferedWriter writer = null;

        try {
            Files.deleteIfExists(path);
            writer =  Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            sortWriteMultiLists(docLists, writer, NUMBER_PER_LINE);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * sort multiple LinkedLists and write to output file
     * @param lists
     * @param writer
     * @param wordNumPerLine : NUMBER_PER_LINE, how many word will be put into one line.
     */
    public static void sortWriteMultiLists(ArrayList<LinkedList<String>> lists, BufferedWriter writer, int wordNumPerLine){


        if (lists == null || lists.size() == 0) return;

        PriorityQueue<LinkedList<String>> heap = new PriorityQueue<>(lists.size(), new ListCompartor());

        for (LinkedList<String> list : lists) {
            if (list != null && list.size() != 0) {
                heap.add(list);
            }
        }

        try {
            StringBuffer line = new StringBuffer();
            int index = 0;
            while (!heap.isEmpty()) {

                index++;
                LinkedList<String> polledList = heap.poll();
                String value = (String) polledList.remove(0);

                if (polledList != null && (!polledList.isEmpty())) {
                    heap.add(polledList);
                }

                line.append(value);
                if ( index == wordNumPerLine) {
                    writer.write(line + "\n");
                    index = 0;
                    // This will clear the buffer
                    line.delete(0, line.length());
                } else if ( index < wordNumPerLine) {
                    line.append(" ");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
