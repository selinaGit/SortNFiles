package fork.join;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

/**
 *
 * MultiDocumentsTask run all tasks
 */
class FoldTask extends RecursiveTask<ArrayList> {
    private final Fold documents;

    FoldTask(Fold documents) {
        super();
        this.documents = documents;
    }

    @Override
    protected ArrayList<LinkedList<String>> compute() {

        ArrayList<LinkedList<String>> docLists = new ArrayList<>();
        LinkedList<RecursiveTask<LinkedList>> forks = new LinkedList<>();

        for (Document document : documents.getDocuments()) {
            DocumentSortTask task = new DocumentSortTask(document);
            forks.add(task);
            task.fork();
        }
        for (RecursiveTask<LinkedList> task : forks) {
            docLists.add(task.join());
        }
        return docLists;
    }
}