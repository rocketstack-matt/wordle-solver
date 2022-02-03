package co.rocketstack.wordle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class Application {
  private final WordList wordList;

  @Autowired
  public Application(WordList wordList) {
    this.wordList = wordList;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RequestMapping("/contains/{letters}")
  List<String> contains(@PathVariable String letters) {
    return wordList.contains(letters);
  }

  @RequestMapping("/contains/{letters}/not/{theseLetters}")
  List<String> containsNot(@PathVariable String letters, @PathVariable String theseLetters) {
    return wordList.contains(letters, not(theseLetters));
  }

  @RequestMapping("/match/{pattern}")
  List<String> match(@PathVariable String pattern) {
    return wordList.match(pattern);
  }

  @RequestMapping("/match/{pattern}/not/{theseLetters}")
  List<String> matchNot(@PathVariable String pattern, @PathVariable String theseLetters) {
    return wordList.not(theseLetters, wordList.match(pattern));
  }

  @RequestMapping("/match/{pattern}/contains/{letters}")
  List<String> matchAndContain(@PathVariable String pattern, @PathVariable String letters) {
    return wordList.contains(letters, match(pattern));
  }

  @RequestMapping("/match/{pattern}/contains/{letters}/not/{theseLetters}")
  List<String> matchContainNot(
      @PathVariable String pattern, @PathVariable String letters, @PathVariable String theseLetters) {
    return wordList.not(theseLetters, matchAndContain(pattern, letters));
  }

  @RequestMapping("/not/{letters}")
  List<String> not(@PathVariable String letters) {
    return wordList.not(letters);
  }
}
