package co.rocketstack.wordle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Application {
  private final WordList wordList;

  @Autowired
  public Application(WordList wordList) {
    this.wordList = wordList;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RequestMapping(value = "/contains/{letters}", method = RequestMethod.GET)
  List<String> contains(@PathVariable String letters) {
    return wordList.contains(letters);
  }

  @RequestMapping(value = "/contains/{letters}/not/{theseLetters}", method = RequestMethod.GET)
  List<String> containsNot(@PathVariable String letters, @PathVariable String theseLetters) {
    return wordList.contains(letters, not(theseLetters));
  }

  @RequestMapping(value = "/match/{pattern}", method = RequestMethod.GET)
  List<String> match(@PathVariable String pattern) {
    return wordList.match(pattern);
  }

  @RequestMapping(value = "/match/{pattern}/not/{theseLetters}", method = RequestMethod.GET)
  List<String> matchNot(@PathVariable String pattern, @PathVariable String theseLetters) {
    return wordList.not(theseLetters, wordList.match(pattern));
  }

  @RequestMapping(value = "/match/{pattern}/contains/{letters}", method = RequestMethod.GET)
  List<String> matchAndContain(@PathVariable String pattern, @PathVariable String letters) {
    return wordList.contains(letters, match(pattern));
  }

  @RequestMapping(value = "/match/{pattern}/contains/{letters}/not/{theseLetters}", method = RequestMethod.GET)
  List<String> matchContainNot(
      @PathVariable String pattern, @PathVariable String letters, @PathVariable String theseLetters) {
    return wordList.not(theseLetters, matchAndContain(pattern, letters));
  }

  @RequestMapping(value = "/not/{letters}", method = RequestMethod.GET)
  List<String> not(@PathVariable String letters) {
    return wordList.not(letters);
  }
}
