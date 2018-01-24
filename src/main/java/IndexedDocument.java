package interview;

import java.util.Hashtable;
import java.util.ArrayList;

public class IndexedDocument extends Document {
    private Hashtable<String,ArrayList<Integer>> index;
    public IndexedDocument(String title, String content) {
      super(title, content);
      createIndex();
    }
    private void createIndex() {}
}
