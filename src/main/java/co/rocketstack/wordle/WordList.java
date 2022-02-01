package co.rocketstack.wordle;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Character.isUpperCase;
import static java.util.Collections.unmodifiableList;

@Component
public class WordList {
  private final List<String> wordList;

  public WordList() {
    List<String> wordList = new ArrayList<>();
    try {
      Resource resource = new ClassPathResource("english3.txt");
      new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))
          .lines()
          .filter(word -> !isUpperCase(word.charAt(0)))
          .filter(word -> !word.contains("'"))
          .filter(word -> word.length() == 5)
          .forEach(wordList::add);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.wordList = unmodifiableList(wordList);
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

  public List<String> not(String letters) {
    Pattern regex = Pattern.compile("[" + letters + "]");
    return wordList.stream().filter(regex.asPredicate().negate()).collect(Collectors.toList());
  }

  public List<String> not(String letters, List<String> wordList) {
    Pattern regex = Pattern.compile("[" + letters + "]");
    return wordList.stream().filter(regex.asPredicate().negate()).collect(Collectors.toList());
  }
}
