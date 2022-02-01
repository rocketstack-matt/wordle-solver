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

  @RequestMapping("/contains/{letters}/and/{letters2}")
  List<String> contains(@PathVariable String letters, @PathVariable String letters2) {
    return wordList.contains(letters2, contains(letters));
  }

  @RequestMapping("/contains/{letters}/and/{letters2}/and/{letters3}")
  List<String> contains(
      @PathVariable String letters, @PathVariable String letters2, @PathVariable String letters3) {
    return wordList.contains(letters3, contains(letters, letters2));
  }

  @RequestMapping("/contains/{letters}/and/{letters2}/and/{letters3}/and/{letters4}")
  List<String> contains(
      @PathVariable String letters,
      @PathVariable String letters2,
      @PathVariable String letters3,
      @PathVariable String letters4) {
    return wordList.contains(letters4, contains(letters, letters2, letters3));
  }

  @RequestMapping("/contains/{letters}/and/{letters2}/and/{letters3}/and/{letters4}/and/{letters5}")
  List<String> contains(
      @PathVariable String letters,
      @PathVariable String letters2,
      @PathVariable String letters3,
      @PathVariable String letters4,
      @PathVariable String letters5) {
    return wordList.contains(letters5, contains(letters, letters2, letters3, letters4));
  }

  @RequestMapping("/match/{pattern}")
  List<String> match(@PathVariable String pattern) {
    return wordList.match(pattern);
  }

  @RequestMapping("/match/{pattern}/contains/{letters}")
  List<String> matchAndContain(@PathVariable String pattern, @PathVariable String letters) {
    return wordList.contains(letters, match(pattern));
  }

  @RequestMapping("/match/{pattern}/contains/{letters}/and/{letters2}")
  List<String> matchAndContain(
      @PathVariable String pattern, @PathVariable String letters, @PathVariable String letters2) {
    return wordList.contains(letters2, matchAndContain(pattern, letters));
  }

  @RequestMapping("/match/{pattern}/contains/{letters}/and/{letters2}/and/{letters3}")
  List<String> matchAndContain(
      @PathVariable String pattern,
      @PathVariable String letters,
      @PathVariable String letters2,
      @PathVariable String letters3) {
    return wordList.contains(letters3, matchAndContain(pattern, letters, letters2));
  }
}
