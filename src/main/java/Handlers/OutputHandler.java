package Handlers;

import Model.SimilarSentencesGroup;

import java.util.List;

public interface OutputHandler {
    void handle(List<SimilarSentencesGroup> groups);
}
