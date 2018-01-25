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
    Hashtable<String, Integer> termLocations = new Hashtable();
    List<Integer> allTermLocations = new ArrayList();

    //Need each word in needle and its start location
    Scanner scanner = new Scanner(needle);
    MatchResult result;
    while(scanner.hasNext()) {
      scanner.next();
      result = scanner.match();
      termLocations.put(result.group(), result.start());
    }

    int numOfTerms = termLocations.size();

    //look up each term in the index and subtract its location in the needle
    for (Map.Entry<String, Integer> term : termLocations.entrySet()) {
      int locationInNeedle = term.getValue();
      List<Integer> adjustedTermLocations = haystack.getWordLocations(term.getKey())
                                            .stream()
                                            .map(x -> x - locationInNeedle)
                                            .collect(Collectors.toList());
      allTermLocations.addAll(adjustedTermLocations);
    }

    System.out.println("numOfTerms: "+numOfTerms);
    System.out.println("allTermLocations: "+allTermLocations);

    //Any number present in all members of allTermLocations represents a match
    List<Integer> commonTerms = allTermLocations
                  .stream()
                  .collect(Collectors.groupingBy( c -> c, Collectors.counting()))
                  .entrySet()
                  .stream()
                  .filter( p -> p.getValue() >= numOfTerms )
                  .map( e -> e.getKey() )
                  .collect( Collectors.toList() );
    System.out.println("Common: "+commonTerms);
    return commonTerms.size();
  }
}
