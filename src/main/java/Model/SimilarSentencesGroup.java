package Model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A representation of a similarity group - holds all sentences and the changing word for each
 */
public class SimilarSentencesGroup {
    private final Set<String> sentences;
    private final Set<String> diffOfSimilarity;

    public SimilarSentencesGroup() {
        sentences = new LinkedHashSet<>();
        diffOfSimilarity = new LinkedHashSet<>();
    }

    public SimilarSentencesGroup(Set<String> sentences, Set<String> diffOfSimilarity) {
        this.sentences = sentences;
        this.diffOfSimilarity = diffOfSimilarity;
    }

    /**
     * Adds a sentence with the diff word to the group
     * @param sentence
     * @param diff
     */
    public void addSentence(String sentence, String diff) {
        sentences.add(sentence);
        diffOfSimilarity.add(diff);
    }

    public Set<String> getDiffOfSimilarity() {
        return diffOfSimilarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimilarSentencesGroup group = (SimilarSentencesGroup) o;
        return sentences.equals(group.sentences) &&
                diffOfSimilarity.equals(group.diffOfSimilarity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentences, diffOfSimilarity);
    }

    @Override
    public String toString() {
        return String.join("\n", sentences) +
                "\nThe changing word was: " + diffOfSimilarity;
    }
}
