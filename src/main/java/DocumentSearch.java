package interview;

import interview.textsearch.*;
import java.io.IOException;
import java.util.Scanner;

public class DocumentSearch {

  private Searcher searcher;

  public DocumentSearch(String directoryString){
    String term, method;
    Scanner in = new Scanner(System.in);
    do {
      System.out.println("Enter Search Term: ");
      term = in.nextLine();
      System.out.println("Search Method: \n\t1) String Match:"+
                          "\n\t2) Regex: \n\t3) Indexed: ");
      method = in.nextLine();
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
