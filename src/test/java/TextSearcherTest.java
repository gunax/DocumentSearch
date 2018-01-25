import org.junit.*;
import static org.junit.Assert.*;
import interview.textsearch.*;
import interview.*;
import java.util.List;
import java.util.Map;

public class TextSearcherTest {

  SimpleSearcher simpleSearcher;
  RegexSearcher regexSearcher;
  //indexedSearcher;

  @Before
  public void setUp() {
    simpleSearcher = new SimpleSearcher();
    regexSearcher = new RegexSearcher();
  }

  @Test
  public void testSimpleSearchSingleText() {
    simpleSearcher.addText("title1","term1 term2 term3 term1");
    List<Searcher.Result> searchResult = simpleSearcher.search("term1");
    assertEquals(1, searchResult.size());
    assertEquals(2, searchResult.get(0).getCount());
  }

  @Test
  public void testSimpleSearchMultipleTexts() {
    simpleSearcher.addText("title1","term1 term2 term3 term1");
    simpleSearcher.addText("title2","term1 term2 term3 anotherterm");
    List<Searcher.Result> searchResult = simpleSearcher.search("term1");
    assertEquals(2, searchResult.size());
    assertEquals(2, searchResult.get(0).getCount());
    assertEquals(1, searchResult.get(1).getCount());
  }

  @Test
  public void testCanRegexSearch() {
    regexSearcher.addText("title1","term1 term2 term3 term1");
    List<Searcher.Result> searchResult = regexSearcher.search("term1");
    assertEquals(1, searchResult.size());
    assertEquals(2, searchResult.get(0).getCount());
  }

  @Test
  public void testCanRegexSearchMultipleTexts() {
    regexSearcher.addText("title1","term1 term2 term3 term1");
    regexSearcher.addText("title2","term1 term2 term3 anotherterm");
    List<Searcher.Result> searchResult = regexSearcher.search("term1");
    assertEquals(2, searchResult.size());
    assertEquals(2, searchResult.get(0).getCount());
    assertEquals(1, searchResult.get(1).getCount());
  }
}
