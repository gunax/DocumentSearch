import org.junit.*;
import static org.junit.Assert.*;
import interview.textsearch.*;
import interview.*;
import java.util.ArrayList;

public class IndexedDocumentTest {

  IndexedDocument doc;

  @Test
  public void testSingleOccurrence() {
    doc = new IndexedDocument("title","some content here");
    assertEquals(1, doc.getWordLocations("some").size());
    assertEquals(1, doc.getWordLocations("content").size());
    assertEquals(1, doc.getWordLocations("here").size());
  }

  @Test
  public void testMultipleOccurrence() {
    doc = new IndexedDocument("title2","some content here and some here");
    assertEquals(2, doc.getWordLocations("some").size());
    assertEquals(1, doc.getWordLocations("content").size());
    assertEquals(2, doc.getWordLocations("here").size());
  }

  @Test
  public void testMultipleOccurrenceValues() {
    doc = new IndexedDocument("title3","this and this not this");
    ArrayList<Integer> loc = doc.getWordLocations("this");
    assertEquals(0, (int) loc.get(0));
    assertEquals(9, (int) loc.get(1));
    assertEquals(18, (int) loc.get(2));
  }

  @Test
  public void testReturnsEmptyList() {
    doc = new IndexedDocument("title4","here is another content string");
    assertNotNull(doc.getWordLocations("giraffe"));
    assertEquals(0, doc.getWordLocations("giraffe").size());
  }
}
