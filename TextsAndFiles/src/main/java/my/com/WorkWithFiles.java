package my.com;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WorkWithFiles {

    public final static String inputFile = "src/main/resources/Input.txt";
    public final static String outputFile = "src/main/resources/Results.txt";

    public static String readFile() {

        StringBuilder sb = new StringBuilder();
        try {
            FileReader myReader = new FileReader(inputFile);
            int character = myReader.read();
            while (character != -1) {
                sb.append((char) character);
                character = myReader.read();
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }


        return sb.toString();
    }
    public static void writeFile(String text) {
        try {
            FileWriter myWriter = new FileWriter(outputFile);
            myWriter.write(text);
            myWriter.close();
            System.out.println("Text saved successfully.");
        } catch (Exception e) {
            System.out.println("Text saving failed.");
            e.printStackTrace();
        }

    }

    public static int wordCount(String text) {
        String[] words = text.split("[\\s\n]+");
        return words.length;
    }

    public static int patternCount(String text, String regex) {
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static String[] getPattern(String text, String regex) {
        int count = patternCount(text, regex);
        String[] matchers = new String[count];
        count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            matchers[count] = text.substring(matcher.start(), matcher.end());
            count++;

        }
        return matchers;
    }

    public static int uppercaseLettersCount(String text) {
        return patternCount(text, "\\p{Lu}");
    }

    public static int lowercaseLettersCount(String text) {
        return patternCount(text, "[\\p{L}&&[^\\p{Lu}]]");
    }

    public static int blankCount(String text) {
        return patternCount(text, " ");
    }

    public static int integerCount(String text) {
        return patternCount(text, "(?<!\\.|\\d)\\b\\d+(?![.\\d])");
    }

    public static String[] integers(String text) {
        return getPattern(text, "(?<!\\.|\\d)\\b\\d+(?![.\\d])");

    }

    public static int doublesCount(String text) {
        return patternCount(text, "\\b\\d+\\.\\d*");
    }


    public static String[] doubles(String text) {
        return getPattern(text, "\\b\\d+\\.\\d*");
    }


    public static String delPattern(String text, String regex) {
        StringBuilder sb = new StringBuilder(text);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sb);

        while (matcher.find()) {
            sb.delete(matcher.start(), matcher.end());
            matcher = pattern.matcher(sb);
        }

        return sb.toString();

    }


    public static void getPatternNumber(String text, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.start() + ":" + matcher.end());
        }

    }


    public static void createFile(String filePath) {

        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File creation successful!");

            } else {
                System.out.println("File creation failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String[] Test(String text) {
        StringBuilder sb = new StringBuilder(text);
        int Index = sb.indexOf("[\"\\,\\. \\!\\?]");
        System.out.println(Index);

        return new String[]{Integer.toString(Index), "how"};
    }

    public static void printSave(StringBuilder sb, String text){
        sb.append(text);
        System.out.print(text);
    }


    public static void main(String[] args) {
        String text = readFile();
        StringBuilder sb = new StringBuilder();
        String outputText;

        outputText = "text\n---\n" + text + "\n---\n";
        printSave(sb, outputText);

        outputText = "word count: " + wordCount(text) + "\n";
        printSave(sb, outputText);

        outputText = "uppercase Letters Count: " + uppercaseLettersCount(text) + "\n";
        printSave(sb, outputText);

        outputText = "lowercase Letters Count: " + lowercaseLettersCount(text) + "\n";
        printSave(sb, outputText);

        outputText = "uppercase and lowercase Letters Count: " + (lowercaseLettersCount(text) + uppercaseLettersCount(text)) + "\n";
        printSave(sb, outputText);

        outputText = "blank Count: " + blankCount(text) + "\n";
        printSave(sb, outputText);

        outputText = "integers Count: " + integerCount(text) + "\n";
        printSave(sb, outputText);

        outputText = "integers: " + Arrays.toString(integers(text)) + "\n";
        printSave(sb, outputText);


        for (String s : integers(text)) {
            outputText = String.format("%x", Integer.parseInt(s)) + "\n";
            printSave(sb, outputText);
        }

        outputText = "double Count: " + doublesCount(text) + "\n";
        printSave(sb, outputText);

        outputText = "doubles: " + Arrays.toString(doubles(text)) + "\n";
        printSave(sb, outputText);

        for (String s : doubles(text)) {
            outputText = String.format("%.2f", Double.parseDouble(s)) + "\n";
            printSave(sb, outputText);
        }

        outputText = "dell Pattern\n---\n" + delPattern(text, "[\"\\,\\. \\!\\?]") + "\n---" + "\n";
        printSave(sb, outputText);

        writeFile(sb.toString());
        Scanner s = new Scanner(System.in);
        String word = s.nextLine();
        getPatternNumber(text, word);

        //Test(text);


    }

}
