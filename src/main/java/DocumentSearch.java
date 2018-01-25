package interview;

import interview.textsearch.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.DirectoryStream;
import java.io.File;
import java.util.Scanner;
import java.nio.file.Files;

public class DocumentSearch {

  private Searcher simpleSearcher, regexSearcher, indexedSearcher;

  public DocumentSearch(String directoryString){
    String term, method;

    simpleSearcher = new SimpleSearcher();
    regexSearcher = new RegexSearcher();
    indexedSearcher = new IndexedSearcher();

    File directory = new File(directoryString);
    File[] files = directory.listFiles();

    try {
      for (File file : files) {
        String title = file.getName();
        String content = new String(Files.readAllBytes(file.toPath()));
        simpleSearcher.addText(file.getName(), content);
        regexSearcher.addText(file.getName(), content);
        indexedSearcher.addText(file.getName(), content);
      }
    } catch(IOException e){}

    Scanner in = new Scanner(System.in);
    do {
      System.out.println("Enter Search Term: ");
      term = in.nextLine();
      System.out.println("Search Method: \n\t1) String Match:"+
                          "\n\t2) Regex: \n\t3) Indexed: ");
      method = in.nextLine();
      if (method.equals("1"))
        System.out.println(simpleSearcher.search(term));
      else if (method.equals("2"))
        System.out.println(regexSearcher.search(term));
      else if (method.equals("3"))
        System.out.println(indexedSearcher.search(term));
    } while(method.equals("1") || method.equals("2") || method.equals("3"));
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Pass in argument containing directory location "+
                        "of .txt files to be searched");
    }
    else {
      DocumentSearch ds = new DocumentSearch(args[0]);
    }
  }
}
