import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String sentence = "This is a sentence to split at each space and then add to array list";

        // Create an ArrayList to store the words
        ArrayList<String> wordsList = new ArrayList<>();

        // Split the sentence into words and add them to the ArrayList
        String[] wordsArray = sentence.split(" ");
        for (int i = 0; i < wordsArray.length; i++) {
            wordsList.add(wordsArray[i]);
        }

        // Print ArrayList
        System.out.println("Words in the ArrayList:");
        for (int i = 0; i < wordsList.size(); i++) {
            System.out.println(wordsList.get(i));
        }

        // Number of words in the sentence
        System.out.println("Number of words: " + wordsList.size());

        // Number of characters in the sentence
        int charCount = sentence.length();
        System.out.println("Number of characters: " + charCount);

        // Find unique words
        ArrayList<String> uniqueWords = new ArrayList<>();
        for (int i = 0; i < wordsList.size(); i++) {
            String word = wordsList.get(i);
            if (!uniqueWords.contains(word)) {
                uniqueWords.add(word);
            }
        }

        // Print unique words
        System.out.println("Unique words in the sentence:");
        for (int i = 0; i < uniqueWords.size(); i++) {
            System.out.println(uniqueWords.get(i));

        }
    }
}