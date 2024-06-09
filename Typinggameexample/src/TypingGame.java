import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TypingGame {
    private String paragraph;
    private ArrayList<String> words;
    private int currentWordIndex;
    private double progress;
    private int timeLimit; // in seconds
    private boolean timeUp;
    private int timeRemaining;

    public TypingGame(List<String> paragraphs, int timeLimit) {
        this.paragraph = getRandomParagraph(paragraphs);
        this.words = new ArrayList<>();
        for (String word : paragraph.split(" ")) {
            words.add(word);
        }
        this.currentWordIndex = 0;
        this.progress = 0;
        this.timeLimit = timeLimit;
        this.timeUp = false;
        this.timeRemaining = timeLimit;
    }

    private String getRandomParagraph(List<String> paragraphs) {
        Random random = new Random();
        int index = random.nextInt(paragraphs.size());
        return paragraphs.get(index);
    }

    //The section where it will check if the typing is wrong
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Start typing the following paragraph:");
        System.out.println(paragraph);
        System.out.println();

        String inputLine = scanner.nextLine().trim();

        // If the input line is not empty, split it into words
        String[] inputs = !inputLine.isEmpty() ? inputLine.split("\\s+") : new String[0];

        int totalWordsAttempted = 0;
        int correctWords = 0;
        for (String input : inputs) {
            totalWordsAttempted++;
            if (checkWord(input)) {
                System.out.println("Correct!");
                correctWords++;
            }
        }

        // If the user entered no input, continue to the next iteration
        if (inputs.length == 0) {
            scanner.close();
            return;
        }

        // Calculate accuracy only if words were attempted
        double accuracy = totalWordsAttempted > 0 ? (double) correctWords / totalWordsAttempted * 100 : 100;
        System.out.printf("Accuracy: %.2f%%\n", accuracy);
        System.out.println(); // Add some space after accuracy for readability

        updateProgress();

        // Check if the user has completed typing the paragraph
        if (currentWordIndex == words.size() - 1) {
            System.out.println("Congratulations! You've completed the paragraph.");
        }

        // Do not close the scanner here
    }



    private boolean checkWord(String input) {
        return input.equals(words.get(currentWordIndex));
    }

    private void updateProgress() {
        currentWordIndex++;
        progress = (double) currentWordIndex / words.size() * 100;
    }


    //The section of the random sentences that will appear when running the code
    public static void main(String[] args) {
        List<String> paragraphs = new ArrayList<>();
        paragraphs.add("Stop and take a breath. Say 'I don't know' when you don't know.");
        paragraphs.add("Never take decisions when angry, and don't make promises when you are happy.");
        paragraphs.add("Progress is more important than perfection.");
        paragraphs.add("Practice makes perfect, so keep trying and you'll get better over time.");

        Scanner scanner = new Scanner(System.in); // Create a single Scanner object
        boolean playAgain = true;
        while (playAgain) {
            int timeLimit = 60; // 60 sec time limit
            TypingGame game = new TypingGame(paragraphs, timeLimit);

            game.start();

            // Prompt to continue or end the terminal
            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = scanner.nextLine().trim().toLowerCase();
            if (!playChoice.equals("yes")) {
                playAgain = false; // Exit the loop
            }
        }
        scanner.close(); // Close the scanner after the loop ends
    }

}


//This is the final plan due to other plans not working
//Originally, this would have more details such as a timer or more sentences. But, this is the best we can do.
