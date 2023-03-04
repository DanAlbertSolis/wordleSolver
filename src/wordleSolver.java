import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.Scanner;

import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


class wordleChecker {
    List<List<String>> wordList = new ArrayList<>();

    Scanner stdin = new Scanner(System.in);
    String initWord, result;
    List<Character> possibleLetters = new ArrayList<Character>();
    List<Character> badLetters = new ArrayList<Character>();
    Character finalWord[] = {};
    String[] sixLetterWords = new String[100];



    /**
     * Default constructor, sets instance variables to default values
     */
    public wordleChecker() {

    }

    public wordleChecker(List<List<String>> listArg) {
    wordList = listArg;
    }

    public void initCheck(){
        System.out.print("What word was initially entered?\n");
        initWord = stdin.next();
        System.out.print("\nEnter the results of the initial guess");
        result = stdin.next();
    }


    public void checker(String input){
        for (int i = 0; i <= input.length(); i++){
            if (input.charAt(i) == 'B' || input.charAt(i) == 'b'){
                badLetters.add(input.charAt(i));
            }
            if (input.charAt(i) == 'Y' || input.charAt(i) == 'y'){
                possibleLetters.add(input.charAt(i));
            }
            if (input.charAt(i) == 'G' || input.charAt(i) == 'g'){
                finalWord[i] = input.charAt(i);
            }
        }
    }

    public void sorter(){

    }

    public void displayWords(){

    }


}

public class wordleSolver{

    public static void main (String[] args){

        List<List<String>> wordList = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader("fiveletterwords.txt"))) {
            List<String> lines = new ArrayList<String>();

            while((String))

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                wordList.add(Arrays.asList(values));
            }

            wordleChecker wordle1 = new wordleChecker(wordList);
            wordle1.initCheck();
            System.out.println(wordList.get(0));
        }

        catch (Exception e){

        }
    }

}
