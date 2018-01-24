package interview;

import java.util.Hashtable;
import java.util.ArrayList;

public class Document {
    private String title, content;
    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
      return title;
    }
    public String getContent() {
      return content;
    }
}
