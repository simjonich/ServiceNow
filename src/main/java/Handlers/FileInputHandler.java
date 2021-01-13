package Handlers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles input from resource file
 */
public class FileInputHandler implements InputHandler {
    public List<String> handle() throws Exception {
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.getClass().getResource("/").getPath() + "input.csv");
            Scanner scanner = new Scanner(fis);
            List<String> input = new ArrayList<>();
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }

            return input;
        } catch (FileNotFoundException e) {
            throw new Exception("No input file provided");
        }
    }
}
