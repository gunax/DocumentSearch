package interview;

import interview.textsearch.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.DirectoryStream;
import java.io.File;
import java.util.Scanner;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

/**
* DocumentSearch takes a directory containing space-separated .txt documents
* and allows the user to seach them for occurrences of a given String
*/
public class DocumentSearch {

  private Searcher simpleSearcher, regexSearcher, indexedSearcher;

  public DocumentSearch(String directoryString){
    String term, method;

    simpleSearcher = new SimpleSearcher();
    regexSearcher = new RegexSearcher();
    indexedSearcher = new IndexedSearcher();

    File directory = new File(directoryString);
    File[] files = directory.listFiles();

    //load files as Strings in the given directory, skipping non-.txt
    try {
      for (File file : files) {
        String title = file.getName();
        if (!title.endsWith(".txt")) {
          continue;
        }
        String content = new String(Files.readAllBytes(file.toPath()));
        simpleSearcher.addText(file.getName(), content);
        regexSearcher.addText(file.getName(), content);
        indexedSearcher.addText(file.getName(), content);
      }
    } catch(IOException e){
      System.out.println("Could not read directory or file inside"+directoryString);
      e.printStackTrace();
      System.exit(1);
    }

    Scanner in = new Scanner(System.in);

    //Loop until user enters invalid method
    do {
      term = queryTerm(in);
      method = queryMethod(in);
      long timeBefore = System.currentTimeMillis();
      switch(method){
        case "1":
          printResults(simpleSearcher.search(term));
          break;
        case "2":
          printResults(regexSearcher.search(term));
          break;
        case "3":
          printResults(indexedSearcher.search(term));
          break;
        }
      System.out.println("Your search took "+(System.currentTimeMillis()-timeBefore)+"ms.");
    } while(method.equals("1") || method.equals("2") || method.equals("3"));
  }

  private static String queryMethod(Scanner in) {
    System.out.println("Search Method: \n\t1) String Match:"+
                        "\n\t2) Regex: \n\t3) Indexed: ");
    return in.nextLine();
  }

  private static String queryTerm(Scanner in) {
    System.out.println("Enter Search Term: ");
    return in.nextLine();
  }

  private static void printResults(List<Searcher.Result> results) {
    Collections.sort(results);
    for (Searcher.Result result : results) {
      System.out.println(result);
    }
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
