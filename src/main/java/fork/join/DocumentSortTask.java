package fork.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

/**
 *
 * DocumentSortTask class run task for sort data at a document
 */
class DocumentSortTask extends RecursiveTask<LinkedList> {
    private final Document document;

    DocumentSortTask(Document document) {
        super();
        this.document = document;
    }

    /**
     *
     * split each line in document and parallel sort all words
     * @param document
     * @return
     */
    LinkedList<String> sortDocument(Document document) {

        LinkedList<String> lines = document.getLines();

        ArrayList<String> words = new ArrayList<>();

        for (String line : lines) {
            for (String word :line.split("\\s+")) {
                words.add(word);
            }
        }
        Collections.sort(words);

        return new LinkedList(words);
    }

    @Override
    protected LinkedList<String> compute() {
        return sortDocument(document);
    }
}