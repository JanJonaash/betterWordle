package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class Words {


    public static ArrayList<String> wordSet = new ArrayList<>();




    // loads all the words into a wordSet arraylist while also making them uppercase
    static void loadWords() throws IOException {




          BufferedReader  br = new BufferedReader(new FileReader("src/com/company/words.txt"));

            String line = "";

            while ((line = br.readLine()) != null){

                wordSet.add(line.toUpperCase());
            }
            br.close();




    }


    //returns a random word from the wordSet collection
    public static String chooseRandomWord(){



        return Words.wordSet.get(new Random().nextInt(Words.wordSet.size() - 1));
    }
}
