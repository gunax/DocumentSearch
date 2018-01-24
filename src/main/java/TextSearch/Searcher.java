package interview.textsearch;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.Hashtable;
import java.util.function.UnaryOperator;
import interview.IndexedDocument;
import interview.Document;

/**
* Holds a list of texts to be searched, and
* provides methods for searching them
*/
public abstract class Searcher {

  //Default filter: remove all characters except alphanumerics and spaces
  private static final String FILTER_DEFAULT = "[^a-zA-Z0-9 ]";

  protected ArrayList<Document> texts;
  protected UnaryOperator<String> filter;

  public Searcher(){
    filter = (s) -> s.replaceAll(FILTER_DEFAULT,"");
    texts = new ArrayList<Document>();
  }

  /**
  * Adds a new String document to the list of searchable documents
  *
  * @param title The title of new document
  * @param content The string content to be searched
  */
  abstract void addText(String title, String content);

  abstract ArrayList<Result> search(String term);

  public class Result {
    private String title;
    private int count;
    public Result(String title, int count) {
      this.title = title;
      this.count = count;
    }
    public int getCount() {
      return count;
    }
    public String getTitle() {
      return title;
    }
  }
}
