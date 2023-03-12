//crane -> seats -> email
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;


class wordleChecker {
    List<String> wordList = new ArrayList<>();
    List<String> badWordList = new ArrayList<>();
    public boolean running = true;

    HashMap<Character,Integer> dict = new HashMap<Character, Integer>();


    Character[] finWord = new Character[5];
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
        //checker();

        wordRemover();
        System.out.println("wordRemover() finished");

        while (running){
            checker();
        }

    }

    public void checker() {

        System.out.print("What is the next word entered? \n");
        word = stdin.next();
        System.out.println("Successfully initialized word: " + word);
        System.out.print("Enter the results of the initial guess in order from first letter to last letter (B = black (not used), G = green (correct spot), Y = yellow (used, but in the wrong spot), for example: BGYYB \n");
        result = stdin.next();
        letterSorter(word, result);
        wordRemover();
        System.out.println("Is the word finished? Y/N");
        String yn = stdin.next();
        if (yn.equals("Y") || yn.equals("y")){
                running = false;
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
        for (int i = 0; i <= finWord.length - 1; i++) {
            System.out.print(finWord[i]);
        }
        System.out.println();
    }

    public void printPossibleWords() {
        for (int i = 0; i <= wordList.size() - 1; i++) {
            if (i % 10==0){
                System.out.print("\n");
            }
            System.out.print(wordList.get(i) + " ,");
        }
        System.out.println("There are " + wordList.size() + " words");
    }


    public void letterSorter(String input, String result) {
        for (int i = 0; i <= result.length() - 1; i++) {
            if (result.charAt(i) == 'B' || result.charAt(i) == 'b') { //checks if letter is black
                if (badLetters.contains(input.charAt(i)) || possibleLetters.contains(input.charAt(i))){
                    System.out.println("Letter " + input.charAt(i) + " skipped");
                }
                else {
                    badLetters.add(input.charAt(i));
                    System.out.println("Added bad letter: " + input.charAt(i));
                }

            }
            else if (result.charAt(i) == 'Y' || result.charAt(i) == 'y') {
                if (possibleLetters.contains(input.charAt(i))){
                    System.out.println("Letter " + input.charAt(i) + " skipped");
                }
                else {
                    possibleLetters.add(input.charAt(i));
                    System.out.println("Added possible letter: " + input.charAt(i));
                }
            }
            else{
                finWord[i] = input.charAt(i);
                //finalWord.add(i, input.charAt(i));
                dict.put(input.charAt(i),i);//key = char, value = index
                System.out.println("Added letter " + input.charAt(i) + " to the final word at index " + i);

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
                        System.out.println("Removed word '" + wordList.get(j)+ "' from the list because the letter " + s.charAt(k) + " is at index " + k + " and is equal to badLetter " + badLetters.get(i));
                        wordList.remove(j);
                        break;
                    }
                }
            }
        }

        for (Map.Entry <Character, Integer> entry : dict.entrySet()){
            System.out.println("Map remover is entered 1");
            for (int a = wordList.size() - 1 ; a >= 0; a--){
                String s  = wordList.get(a);
                if (s.charAt(entry.getValue()) != entry.getKey()){
                    System.out.println("Map remover is entered 2, removed word " + wordList.get(a) + " because " + s.charAt(entry.getValue()) + " doesnt equal " + entry.getKey());
                    wordList.remove(a);
                }
                        /*for (int b = 0; b < s.length(); b++){ //for each words letter
                            if (s.charAt(b) != entry.getKey()){
                                wordList.remove(a);
                                break;
                            }
                        }*/
            }
        }

        printPossibleWords();

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
