package co.rocketstack.wordle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Application {
  WordList wordList = new WordList();

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RequestMapping("/hello/{name}")
  String hello(@PathVariable String name) {
    return "Hello " + name;
  }

  @RequestMapping("/hello/{name}/{surname}")
  String hello(@PathVariable String name, @PathVariable String surname) {
    return hello(name) + " " + surname;
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
}
