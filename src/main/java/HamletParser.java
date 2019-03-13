import javafx.scene.shape.PathElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thook on 10/7/15.
 */
public class HamletParser {

    private String hamletData;
    private final Pattern HAMLET_PATTERN = Pattern.compile("\\bHamlet\\b");
    private final Pattern HORATIO_PATTERN = Pattern.compile("\\bHoratio\\b");
    private final String HAMLET_REPLACEMENT = "Leon";
    private final String HORATIO_REPLACEMENT = "Tariq";

    public HamletParser(){
        this.hamletData = loadFile();
    }

    private String loadFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("hamlet.txt").getFile());
        StringBuilder result = new StringBuilder();

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return result.toString();
    }

    private void writeOutFile(String fileName, String output) {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getHamletData(){
        return hamletData;
    }

    public String replaceBoth(String replaceIn) {
        return replaceHamlet(replaceHoratio(replaceIn));
    }

    public int findHamlet(String findIn) {
        return matchPattern(HAMLET_PATTERN, findIn);
    }

    public int findHoratio(String findIn) {
        return matchPattern(HORATIO_PATTERN, findIn);
    }

    private int matchPattern(Pattern regex, String findIn) {
        Matcher regexMatcher = regex.matcher(findIn);
        return getCountOfMatches(regexMatcher);
    }

    private int getCountOfMatches(Matcher regexMatcher) {
        int count = 0;
        while (regexMatcher.find()) {
            count++;
        }
        return count;
    }

    public String replaceHamlet(String replaceIn) {
        return replacePattern(HAMLET_PATTERN, HAMLET_REPLACEMENT, replaceIn);
    }

    public String replaceHoratio(String replaceIn) {
        return replacePattern(HORATIO_PATTERN, HORATIO_REPLACEMENT, replaceIn);
    }

    private String replacePattern(Pattern regex, String replacement, String replaceIn) {
        Matcher regexMatcher = regex.matcher(replaceIn);
        return regexMatcher.replaceAll(replacement);
    }

    public static void main(String[] args) {
        HamletParser hamletParser = new HamletParser();
        hamletParser.writeOutFile("replaced.txt", hamletParser.replaceBoth(hamletParser.getHamletData()));
    }

}
