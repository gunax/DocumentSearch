package interview;

import java.util.Hashtable;
import java.util.ArrayList;

public class IndexedDocument {
    private String title, content;
    private Hashtable<String,ArrayList<Integer>> index;
    public IndexedDocument(String title, String content) {
        this.title = title;
        this.content = content;
        createIndex();
    }
    private void createIndex() {}

    public String getTitle() {
      return title;
    }
    public String getContent() {
      return content;
    }
}
