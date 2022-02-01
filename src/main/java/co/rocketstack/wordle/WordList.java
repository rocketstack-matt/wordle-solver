package co.rocketstack.wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Character.isUpperCase;
import static java.util.Collections.unmodifiableList;

public class WordList {
  private final List<String> wordList;

  public WordList() {
    List<String> wordList = new ArrayList<>();
    try {
      Files.lines(Paths.get("src/main/resources/english3.txt"))
          .filter(word -> !isUpperCase(word.charAt(0)))
          .filter(word -> word.length() == 5)
          .forEach(wordList::add);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.wordList = unmodifiableList(wordList);
    System.out.println(wordList.size());
  }

  public List<String> match(String pattern) {
    Pattern regex = Pattern.compile(pattern);
    return wordList.stream().filter(regex.asPredicate()).collect(Collectors.toList());
  }

  public List<String> contains(String letters) {
    Pattern regex = Pattern.compile("(" + letters + ")");
    return wordList.stream().filter(regex.asPredicate()).collect(Collectors.toList());
  }

  public List<String> contains(String letters, List<String> wordList) {
    Pattern regex = Pattern.compile("(" + letters + ")");
    return wordList.stream().filter(regex.asPredicate()).collect(Collectors.toList());
  }
}
