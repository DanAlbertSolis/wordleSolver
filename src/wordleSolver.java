import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class wordleSolver {
    Scanner stdin = new Scanner(System.in);
    String initWord, result;
    List<Character> possibleLetters = new ArrayList<Character>();
    List<Character> badLetters = new ArrayList<Character>();
    Character finalWord[] = {};
    String[] sixLetterWords = new String[100];

    //Creating a JSONObject object

    Object o = new JSONParser().parse((new FileReader(file.json)));
    JSONObject jsonObject = (JSONObject) o;










    /**
     * Default constructor, sets instance variables to default values
     */
    public wordleSolver(){

    }

    public void initCheck(){
        System.out.print("What word was initially entered?");
        initWord = stdin.next();
        System.out.print("\nEnter the results of the initial guess");
        result = stdin.next();
    }

    public void jsonImporter(){

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
