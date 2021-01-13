package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A representation of each line in the input file
 */
public class Sentence {
    private final String rawSentence;
    private final List<String> words;

    public Sentence(String sentence) throws Exception {
        rawSentence = sentence;
        String[] words = sentence.split(" ");
        if (words.length < 2) {
            throw new Exception("Not much data in sentence " + sentence);
        }

        // Verify that the input is in correct format
        LocalDateTime.parse(words[0] + " " + words[1], DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
        this.words = Arrays.asList(words).subList(2, words.length);
    }

    public List<String> getWords() {
        return words;
    }

    public String getRawSentence() {
        return rawSentence;
    }

    public String getRawSentenceWithoutDateTime() {
        return String.join(" ", this.words);
    }

    /**
     * @param pattern
     * @return the changing word if the sentence is similar to the pattern, null otherwise
     */
    public String getDiffOfSimilarity(String pattern) throws Exception {
        String[] wordsInPattern = pattern.split(" ");
        if (getWords().size() != wordsInPattern.length) {
            throw new Exception("The pattern is not matching the sentence");
        }

        List<String> diff = words.stream().filter(s -> !Arrays.asList(wordsInPattern).contains(s)).collect(Collectors.toList());
        if (diff.size() != 1) {
            throw new Exception("The pattern is not matching the sentence");
        }

        return diff.get(0);
    }
}
