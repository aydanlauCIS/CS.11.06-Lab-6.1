import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class AdventureTime {

    /** This is the main method and it is where you will test your implementations for challengeOne, challengeTwo, etc.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String testfile = "inputOneTwo.txt";
        int challengeOneAnswer = challengeOne(testfile);
        int challengeTwoAnswer = challengeTwo(testfile);

        String testfiletwo = "inputThreeFour.txt";
        int challengeThreeAnswer = challengeThree(testfiletwo);
        int challengeFourAnswer = challengeFour(testfiletwo);

        writeFileAllAnswers("AdventureTime.txt", challengeOneAnswer, challengeTwoAnswer, challengeThreeAnswer, challengeFourAnswer);
    }
    /** TODO 1
     *
     * Challenge 1
     *
     * @param fileName
     * @return Answer to Challenge 1
     * @throws IOException
     */
    public static int challengeOne(String fileName) throws IOException {
        int[] depths = readFile(fileName);
        int array_length = depths.length;

        int increased = 0;
        for (int i = 1; i < array_length; i++) {
            if (depths[i] - depths[i - 1] > 0) {
                increased++;
            }
        }

        return increased;
    }

    /** TODO 2
     *
     * Challenge 2
     *
     * @param fileName
     * @return Answer to Challenge 2
     * @throws FileNotFoundException
     */
    public static int challengeTwo(String fileName) throws FileNotFoundException {
        int[] depths = readFile(fileName);
        int array_length = depths.length;

        int prev_sum = depths[0] + depths[1] + depths[2];
        int curr_sum, increased = 0;
        for (int i = 1; i < array_length - 2; i++) {
            curr_sum = depths[i] + depths[i + 1] + depths[i + 2];
            if (curr_sum > prev_sum) {
                increased++;
            }
            prev_sum = curr_sum;
        }

        return increased;
    }

    /** TODO 3
     *
     * Challenge 3
     *
     * @param fileName
     * @return Answer to Challenge 3
     * @throws FileNotFoundException
     */
    public static int challengeThree(String fileName) throws FileNotFoundException {
        String[][] instructions = readFileTwo(fileName);
        int x = 0, y = 0;
        for (String[] inst: instructions) {
            String dir = inst[0];
            int mag = Integer.parseInt(inst[1]);
            switch (dir) {
                case "forward" -> x += mag;
                case "down" -> y += mag;
                case "up" -> y -= mag;
            }
        }

        return x * y;
    }

    /** TODO 4
     *
     * Challenge 4
     *
     * @param filename
     * @return Answer to Challenge 4
     * @throws FileNotFoundException
     */
    public static int challengeFour(String filename) throws FileNotFoundException {
        String[][] instructions = readFileTwo(filename);
        int x = 0, y = 0, aim = 0;
        for (String[] inst: instructions) {
            String dir = inst[0];
            int mag = Integer.parseInt(inst[1]);

            switch (dir) {
                case "forward" -> {
                    x += mag;
                    y += aim * mag;
                }
                case "up" -> aim -= mag;
                case "down" -> aim += mag;
            }
        }

        return x * y;
    }

    /** This method will write the values passed as challengeOne, challengeTwo, challengeThree, and challengeFour to a text file.
     * Do not edit this method.
     */
    private static void writeFileAllAnswers(String outPutFilename, int challengeOne, int challengeTwo, int challengeThree, int challengeFour) throws IOException {
        File file = new File(outPutFilename);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("Challenge 1: " + "\t" + challengeOne + "\n");
        bufferedWriter.write("Challenge 2: " + "\t" + challengeTwo + "\n");
        bufferedWriter.write("Challenge 3: " + "\t" + challengeThree + "\n");
        bufferedWriter.write("Challenge 4: " + "\t" + challengeFour + "\n");
        bufferedWriter.close();
    }

    /** This method will read the values in inputFilename into an array of integers, which it will return.
     * Do not edit this method.
     */
    private static int[] readFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        int[] data = new int[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextInt();
        }
        scanner.close();
        return data;
    }

    private static String[][] readFileTwo(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        int[] data = new int[numberOfLinesInFile];

        String[][] instructions = new String[numberOfLinesInFile][2];
        int index = 0;
        while (scanner.hasNextLine()) {
            instructions[index++] = scanner.nextLine().split(" ");
        }
        scanner.close();
        return instructions;
    }

    /** This method will count the number of lines in a text file, which it will return.
     * Do not edit this method.
     */
    private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }

}