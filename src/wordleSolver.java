import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;


class wordleChecker {
    List<String> wordList = new ArrayList<>();
    List<String> badWordList = new ArrayList<>();


    Scanner stdin = new Scanner(System.in);
    String word, result;
    List<Character> possibleLetters = new ArrayList<Character>();
    List<Character> badLetters = new ArrayList<Character>();

    List<Character> finalWord = new ArrayList<Character>();
    boolean allGreen = false;


    /**
     * Default constructor, sets instance variables to default values
     */
    public wordleChecker() {

    }

    public wordleChecker(List<String> list) {
        wordList = list;
    }

    public void initCheck() {
        System.out.print("What word was entered? (Common starter word is Crane) \n");
        word = stdin.next();

        System.out.println("Successfully initialized word: " + word);

        System.out.print("Enter the results of the initial guess in order from first letter to last letter (B = black (not used), G = green (correct spot), Y = yellow (used, but in the wrong spot), for example: BGYYB \n");
        result = stdin.next();
        letterSorter(word, result);
        System.out.println("LetterSorter() finished");
        //checker();


        wordRemover();
        System.out.println("wordRemover() finished");

         //printPossibleWords();
         //System.out.println("\nprintPossibleWords() is finished");
        System.out.println("There are " + wordList.size() + " words in the list");

    }

    public void checker() {
        System.out.print("What is the next word entered? \n");
        word = stdin.next();

        System.out.println("Successfully initialized word: " + word);

        System.out.print("Enter the results of the initial guess in order from first letter to last letter (B = black (not used), G = green (correct spot), Y = yellow (used, but in the wrong spot), for example: BGYYB \n");
        result = stdin.next();
        letterSorter(word, result);

        while (allGreen == false) {
            checker();
        }
    }


    public void printStatus() {
        System.out.println("The possible letters so far are: ");
        for (int i = 0; i <= possibleLetters.size() - 1; i++) {
            System.out.print(possibleLetters.get(i) + " ");
        }
        System.out.println("");

        System.out.println("The bad letters so far are: ");
        for (int i = 0; i <= badLetters.size() - 1; i++) {
            System.out.print(badLetters.get(i) + " ");

        }
        System.out.println("");

        System.out.println("The word we have so far is: ");
        for (int i = 0; i <= finalWord.size() - 1; i++) {
            System.out.print(finalWord.get(i) + " ");
        }
    }

    public void printPossibleWords() {
        System.out.println("printPossibleWords() is called " + wordList.size());
        for (int i = 0; i <= wordList.size() - 1; i++) {
            if (i % 10==0){
                System.out.print("\n");
            }
            System.out.print(wordList.get(i) + " ,");
        }
    }


    public void letterSorter(String input, String result) {
        for (int i = 0; i <= input.length() - 1; i++) {
            if (result.charAt(i) == 'B' || result.charAt(i) == 'b') {

               /* for (int j = 0; j <= badLetters.size() - 1; j++){
                    if (badLetters.get(j) == input.charAt(i)){
                        System.out.println("Bad letter already exists: "+ input.charAt(i));
                    }
                    else if (j == badLetters.size() - 1 && badLetters.get(j) != input.charAt(i)){
                        badLetters.add(input.charAt(i));
                        System.out.println("Added bad letter: "+ input.charAt(i));
                    }
                } */

                badLetters.add(input.charAt(i));
                System.out.println("Added bad letter: " + input.charAt(i));
            } else if (result.charAt(i) == 'Y' || result.charAt(i) == 'y') {
                possibleLetters.add(input.charAt(i));
                System.out.println("Added possible letter: " + input.charAt(i));
            } else if (result.charAt(i) == 'G' || result.charAt(i) == 'g') {
                finalWord.add(i, input.charAt(i));
                System.out.println("Added letter " + input.charAt(i) + " to the final word");
            }
        }
        System.out.println("\nFinished looping through the results...");
        System.out.println("Successfully added all good or bad letters");
        printStatus();
    }



    public void wordRemover() {
        for (int i = 0; i < badLetters.size(); i++){ //for each character in the array
            for (int j = wordList.size() - 1 ; j >= 0; j--) { //for each word on the word list
                String s  = wordList.get(j);
                for (int k = 0; k < s.length(); k++) { //for each words letter
                    if (s.charAt(k) == badLetters.get(i)){
                        System.out.println("Removed word '" + wordList.get(j)+ "' from the list because the letter " + s.charAt(k) + " is at index " + k);
                        wordList.remove(j);
                        break;
                    }
                }
            }
        }
    }
}

public class wordleSolver{

    public static void main (String[] args){
        String line;
        int i = 0;

        List<String> wordList = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader("sgb-words.txt"))) {
            while((line= br.readLine()) != null){
                wordList.add(line);
                //System.out.println(wordList.get(i++)); //used to test if the words are correctly inputted into the array
            }
            wordleChecker wordle1 = new wordleChecker(wordList);
            wordle1.initCheck();


        }

        catch (Exception e){

        }
    }

}
