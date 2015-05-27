package dev.fun;

import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.io.BufferedWriter;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

/**
 *
 * DocumentsMerger class is a class to merge multiple document
 */
public class FilesMerger {

    public static final String OUTPUT_FILE_NAME = "sorted-result-stream.txt";
    public static final int NUMBER_PER_LINE = 10;


    /**
     *
     * sort and write data of mulitiple documents
     * @param documents
     * @param inputDir
     */
    public void sortWriteMultipleDocument(FoldReader documents, String inputDir) {
        Path path = Paths.get(inputDir + OUTPUT_FILE_NAME);
        BufferedWriter writer = null;

        try {
            Files.deleteIfExists(path);
            writer =  Files.newBufferedWriter(path,  StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            //get all sorted data from all documents
            ArrayList<LinkedList<String>> docLists = getMultipleDocument(documents);

            //merge sort all data of ducments in ArrayList
            sortWriteMultiLists(docLists, writer, NUMBER_PER_LINE);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer!= null) {
                try {
                    writer.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * get all data from multiple documents
     * @param documents
     * @return
     */
    public ArrayList<LinkedList<String>> getMultipleDocument(FoldReader documents) {

        ArrayList<LinkedList<String>> docLists = new ArrayList<>();

        for (DocumentReader document : documents.getDocuments()) {
            LinkedList<String> words = document.getWords();
            docLists.add(words);
        }
        return docLists;
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

