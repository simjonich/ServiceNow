import Handlers.FileInputHandler;
import Handlers.InputHandler;
import Handlers.OutputHandler;
import Handlers.StdOutOutputHandler;
import Model.SimilarSentencesGroup;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputHandler inputHandler = new FileInputHandler();
        try {
            List<String> input = inputHandler.handle();
            PrivateInvestigator privateInvestigator = new PrivateInvestigator();
            List<SimilarSentencesGroup> output = privateInvestigator.investigate(input);
            OutputHandler outputHandler = new StdOutOutputHandler();
            outputHandler.handle(output);
        } catch (Exception e) {
            System.out.println("Failed in " + e);
        }
    }
}
