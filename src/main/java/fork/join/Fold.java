package fork.join;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * class MultiDocuments contain all Document objects
 * Created by Selina on 5/26/15.
 */
public class Fold {

    private final ArrayList<Document> documents;

    public Fold(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public ArrayList<Document> getDocuments() {
        return this.documents;
    }

    public static Fold fromMultiFiles(List<BufferedReader> buffReaders) throws IOException {
        ArrayList<Document> documents = new ArrayList<>();

        for (BufferedReader reader : buffReaders) {
            documents.add(Document.fromFile(reader));
        }
        return new Fold(documents);
    }
}