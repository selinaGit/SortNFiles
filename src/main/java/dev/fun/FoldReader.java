package dev.fun;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * FoldReader class contain list of DocumentStreamReader objects
 * meaning it contain data of multiple documents
 * Created by Selina on 5/24/15.
 */
public class FoldReader {

    private final ArrayList<DocumentReader> documents;

    FoldReader(ArrayList<DocumentReader> documents) {
        this.documents = documents;
    }

    ArrayList<DocumentReader> getDocuments() {
        return this.documents;
    }

    static FoldReader fromMultiFiles(List<Reader> readers) throws IOException {
        ArrayList<DocumentReader> documents = new ArrayList<>();

        for ( Reader reader : readers) {
            documents.add(DocumentReader.fromFile(reader));
        }
        return new FoldReader(documents);
    }
}
