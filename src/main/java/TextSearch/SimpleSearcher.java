package interview.textsearch;

import java.util.ArrayList;
import interview.IndexedDocument;
import interview.Document;

/*
* Simple match-based searching. Searching string length m for instances
* of string length n takes O(m*n) time.
*/
public class SimpleSearcher extends Searcher {

  public ArrayList<Result> search(String term) {
    //We want to use the same filter on the search terms as we used on the text
    String filteredTerm = filter.apply(term);
    ArrayList<Result> results = new ArrayList<Result>();
    for (Document doc : texts) {
        results.add(new Result(doc.getTitle(), simpleSearch(filteredTerm, doc.getContent() )));
    }
    return results;
  }

  public void addText(String title, String content) {
    texts.add(new Document(title, filter.apply(content)));
  }

  private static int simpleSearch(String needle, String haystack) {
     int count = 0;
     for(int i = 0; i <= haystack.length() - needle.length(); i++) {
         if(haystack.substring(i, i+needle.length()).equals(needle)) {
             count++;
         }
     }
     return count;
   }
}
