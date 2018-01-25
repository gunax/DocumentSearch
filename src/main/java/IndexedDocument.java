package interview;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
* Text document with Hashtable-based location index of each word
*/
public class IndexedDocument extends Document {

    private Hashtable<String, ArrayList<Integer>> index;
    public IndexedDocument(String title, String content) {
      super(title, content);
      createIndex(content);
    }

    /**
    * Get the ArrayList of int-locations of appearances of <i>word</i>
    * @return ArrayList<Integer> containing ordered list of start
    * locations of <i>word</i>
    */
    public ArrayList<Integer> getWordLocations(String word) {
      ArrayList<Integer> locations = index.get(word);

      //It is easier in Searcher if we return an empty list than null
      if (locations == null) {
        locations = new ArrayList<Integer>();
      }
      return locations;
    }

    private void createIndex(String content) {
      index = new Hashtable<String, ArrayList<Integer>>();

      //Scan and index each word in content
      Scanner scanner = new Scanner(content);
      MatchResult result;
      while(scanner.hasNext()) {
        scanner.next();
        result = scanner.match();
        addWord(result.group(), result.start());
      }
    }

    private void addWord(String word, int location) {
      ArrayList<Integer> locationList = index.get(word);
      if (locationList == null) {
        locationList = new ArrayList<Integer>();
        index.put(word, locationList);
      }
      locationList.add(location);
    }
}
