import org.junit.*;
import static org.junit.Assert.*;
import interview.textsearch.*;
import interview.*;
import java.util.List;
import java.util.Map;

public class RegexSearcherTest {

  RegexSearcher searcher;

  @Before
  public void setUp() {
    searcher = new RegexSearcher();
  }

  @Test
  public void testSimpleSearchSingleText() {
    searcher.addText("title1","term1 term2 term3 term1");
    List<Searcher.Result> searchResult = searcher.search("term1");
    assertEquals(1, searchResult.size());
    assertEquals(2, searchResult.get(0).getCount());
  }

  @Test
  public void testSimpleSearchMultipleTexts() {
    searcher.addText("title1","term1 term2 term3 term1");
    searcher.addText("title2","term1 term2 term3 anotherterm");
    List<Searcher.Result> searchResult = searcher.search("term1");
    assertEquals(2, searchResult.size());
    assertEquals(2, searchResult.get(0).getCount());
    assertEquals(1, searchResult.get(1).getCount());
  }

  @Test
  public void testCaseInsensitive() {
    searcher.addText("title1","term1 term2 term3 teRM1");
    searcher.addText("title2","Term1 term2 term3 anotherterm");
    List<Searcher.Result> searchResult = searcher.search("term1");
    assertEquals(2, searchResult.size());
    assertEquals(2, searchResult.get(0).getCount());
    assertEquals(1, searchResult.get(1).getCount());
  }

  @Test
  public void testIgnoresPunctuation() {
    searcher.addText("title1","term1, term2 term3, teRM1.");
    searcher.addText("title2","Ter-m1 term2 ter,,m3 anotherterm");
    List<Searcher.Result> searchResult = searcher.search("term1");
    assertEquals(2, searchResult.size());
    assertEquals(2, searchResult.get(0).getCount());
    assertEquals(1, searchResult.get(1).getCount());
  }
}
