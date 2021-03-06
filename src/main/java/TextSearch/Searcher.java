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
* provides a method for searching them
*/
public abstract class Searcher {

  //Filter: remove all characters except alphanumerics and spaces
  private static final String FILTER_ALPHANUMERIC = "[^a-zA-Z0-9 ]";

  protected ArrayList<Document> texts;
  protected UnaryOperator<String> filter;

  public Searcher(){
    filter = (s) -> s.replaceAll(FILTER_ALPHANUMERIC,"").toLowerCase();
    texts = new ArrayList<Document>();
  }

  /**
  * Adds a new String document to the list of searchable documents
  *
  * @param title The title of new document
  * @param content The string content to be searched
  */
  public abstract void addText(String title, String content);

  /**
  * Searches the documents for <i>term</i>
  * @param term The token to search for
  * @return ArrayList of titles and the # of occurrences of <i>term</i>
  */
  public abstract ArrayList<Result> search(String term);

  public class Result implements Comparable<Result> {
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
    public String toString() {
      return title+": "+count;
    }
    public int compareTo(Result other) {
      return other.getCount() - getCount();
    }
  }
}
