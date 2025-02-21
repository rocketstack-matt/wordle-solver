package co.rocketstack.wordle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {
    private final WordList wordList;
    private final BuildProperties buildProperties;

    @Autowired
    public Application(WordList wordList, BuildProperties buildProperties) {
        this.wordList = wordList;
        this.buildProperties = buildProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/version")
    public Map<String, String> version() {
        return Map.of("version", buildProperties.getVersion());
    }

    @GetMapping("/contains/{letters}")
    List<String> contains(@PathVariable String letters) {
        return wordList.contains(letters);
    }

    @GetMapping("/contains/{letters}/not/{theseLetters}")
    List<String> containsNot(@PathVariable String letters, @PathVariable String theseLetters) {
        return wordList.contains(letters, wordList.not(theseLetters));
    }

    @GetMapping("/match/{pattern}")
    List<String> match(@PathVariable String pattern) {
        return wordList.match(pattern);
    }

    @GetMapping("/match/{pattern}/not/{theseLetters}")
    List<String> matchNot(@PathVariable String pattern, @PathVariable String theseLetters) {
        return wordList.not(theseLetters, wordList.match(pattern));
    }

    @GetMapping("/match/{pattern}/contains/{letters}")
    List<String> matchAndContain(@PathVariable String pattern, @PathVariable String letters) {
        return wordList.contains(letters, match(pattern));
    }

    @GetMapping("/match/{pattern}/contains/{letters}/not/{theseLetters}")
    List<String> matchContainNot(
            @PathVariable String pattern, @PathVariable String letters, @PathVariable String theseLetters) {
        return wordList.not(theseLetters, matchAndContain(pattern, letters));
    }

    @GetMapping("/not/{letters}")
    List<String> not(@PathVariable String letters) {
        return wordList.not(letters);
    }
}
