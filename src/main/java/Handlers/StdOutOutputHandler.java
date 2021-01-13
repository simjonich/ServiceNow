package Handlers;

import Model.SimilarSentencesGroup;

import java.util.List;

/**
 * Prints the output to standard output
 */
public class StdOutOutputHandler implements OutputHandler {
    @Override
    public void handle(List<SimilarSentencesGroup> groups) {
        for (SimilarSentencesGroup group : groups) {
            System.out.println(group);
        }
    }
}
