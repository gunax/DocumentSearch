package interview.textsearch;

import interview.IndexedDocument;
import interview.Document;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

/*
* Regex-based searching. Searching string length m for instances
* of string length n takes O(m+n) time.
*/
public class RegexSearcher extends Searcher {

  public ArrayList<Result> search(String term) {
    //We want to use the same filter on the search terms as we used on the text
    String filteredTerm = filter.apply(term);
    ArrayList<Result> results = new ArrayList<Result>();
    for (Document doc : texts) {
        results.add(new Result(doc.getTitle(), regexSearch(filteredTerm, doc.getContent() )));
    }
    return results;
  }

  public void addText(String title, String content) {
    texts.add(new Document(title, filter.apply(content)));
  }

  private static int regexSearch(String needle, String haystack) {
     int count = 0;
     Pattern pattern = Pattern.compile(needle);
     Matcher matcher = pattern.matcher(haystack);
     int from = 0;
     while(matcher.find(from)) {
       count++;
       from = matcher.start() + 1;
     }
     return count;
   }
}
