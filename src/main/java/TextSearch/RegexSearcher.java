package interview.textsearch;

import interview.IndexedDocument;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class RegexSearcher extends Searcher {

  public ArrayList<Result> search(String term) {
    //We want to use the same filter on the search terms as we used on the text
    String filteredTerm = filter.apply(term);
    ArrayList<Result> results = new ArrayList<Result>();
    for (IndexedDocument doc : texts) {
        results.add(new Result(doc.getTitle(), regexSearch(filteredTerm, doc.getContent() )));
    }
    return results;
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
