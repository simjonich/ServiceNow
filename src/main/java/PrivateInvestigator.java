import Model.Sentence;
import Model.SimilarSentencesGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Investigates the sentences by prepare a map of patterns with a special character for each word.
 * Assumption, % is a special character that can't be used as word (can be replaced by other special character if required).
 * For example, the sentence "Naomi is getting into the car" will produce 6 patterns as following:
 *   "% is getting into the car",
 *   "Naomi % getting into the car",
 *   "Naomi is % into the car",
 *   "Naomi is getting % the car",
 *   "Naomi is getting into % car",
 *   "Naomi is getting into the %"
 * For each pattern check if the pattern is already in the map and if so
 * add the sentence to his group and add the changing word to the diffOfSimilarity set.
 * At the end removes group with one sentence.
 */
public class PrivateInvestigator {

    public static final String SPECIAL_CHARACTER = "%";

    /**
     * Investigates all string patterns
     * @param input a list of sentences to investigate
     * @return a list of similarity groups
     * @throws Exception in case the data is not in a valid format
     */
    public List<SimilarSentencesGroup> investigate(List<String> input) throws Exception {
        Map<String, SimilarSentencesGroup> map = new HashMap<>();
        for (String string : input) {
            Sentence sentence = new Sentence(string);
            List<String> patterns = buildPatterns(sentence);
            for(String pattern : patterns) {
                SimilarSentencesGroup group;
                if (!map.containsKey(pattern)) {
                    group = new SimilarSentencesGroup();
                } else {
                    group = map.get(pattern);
                }

                String diff = sentence.getDiffOfSimilarity(pattern);
                group.addSentence(sentence.getRawSentence(), diff);
                map.put(pattern, group);
            }
        }

        return reduceSingleGroups(map);
    }

    /**
     * Removes groups with one sentence
     * @param map pattern -> similarity group
     * @return a list of similarity groups
     */
    private List<SimilarSentencesGroup> reduceSingleGroups(Map<String, SimilarSentencesGroup> map) {
        return map.values().stream().filter(group -> group.getDiffOfSimilarity().size() > 1).collect(Collectors.toList());
    }

    /**
     * Builds patterns - replaces each word in sentence with "%" (assuming a special character,
     * can be replaced to other special character if required).
     * @param sentence
     * @return a list of all patterns for the sentence
     */
    private List<String> buildPatterns(Sentence sentence) {
        List<String> patterns = new ArrayList<>();
        String rawSentenceWithoutDateTime = sentence.getRawSentenceWithoutDateTime();
        for (String word : sentence.getWords()) {
            // Replace only the whole words
            String pattern = rawSentenceWithoutDateTime.replaceAll("\\b" + word + "\\b", SPECIAL_CHARACTER);
            patterns.add(pattern);
        }

        return patterns;
    }
}
