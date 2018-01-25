package interview.textsearch;

import interview.IndexedDocument;
import interview.Document;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.regex.MatchResult;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;

/*
* Hashtable-based index searching. Searching string length m for instances
* of string length n takes O(n) time.
*/
public class IndexedSearcher extends Searcher{

  public ArrayList<Result> search(String term) {
    //We want to use the same filter on the search terms as we used on the text
    String filteredTerm = filter.apply(term);
    ArrayList<Result> results = new ArrayList<Result>();
    for (Document doc : texts) {
      results.add(new Result(doc.getTitle(), indexedSearch(filteredTerm, (IndexedDocument) doc )));
    }
    return results;
  }

  public void addText(String title, String content) {
    texts.add(new IndexedDocument(title, filter.apply(content)));
  }

  /* Given a String containing n space-separated words, and their locations in the haystack, determine
  * if all n occur in sequence in the haystack by examining their location indexes
  */
  private static int indexedSearch(String needle, IndexedDocument haystack) {
    Hashtable<String, Integer> termLocations = new Hashtable<String,Integer>();
    List<Integer> allTermLocations = new ArrayList<Integer>();

    //map each word in the needle to its location in the needle
    Scanner scanner = new Scanner(needle);
    MatchResult result;
    while(scanner.hasNext()) {
      scanner.next();
      result = scanner.match();
      termLocations.put(result.group(), result.start());
    }

    //look up each term in the index and subtract its location in the needle
    for (Map.Entry<String, Integer> term : termLocations.entrySet()) {
      int locationInNeedle = term.getValue();
      List<Integer> adjustedTermLocations = haystack.getWordLocations(term.getKey())
                                            .stream()
                                            .map(x -> x - locationInNeedle)
                                            .collect(Collectors.toList());
      allTermLocations.addAll(adjustedTermLocations);
    }

    int numOfTerms = termLocations.size();
    //count how many locations are in every list--that's a match
    return findDuplicateTerms(allTermLocations, numOfTerms).size();
  }

  //Return list of terms occurring at least n times in the given list
  private static List<Integer> findDuplicateTerms(List<Integer> list, int n) {
          return list.stream()
                  .collect( Collectors.groupingBy( c -> c, Collectors.counting()) )
                  .entrySet()
                  .stream()
                  .filter( p -> p.getValue() >= n )
                  .map( e -> e.getKey() )
                  .collect( Collectors.toList() );
  }
}
