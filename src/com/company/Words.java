package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class Words {


    public static ArrayList<String> wordList = new ArrayList<>();


    /**
     * Reads the words.txt file and stores an uppercase version of the word in the wordList.
     *
     * @throws IOException
     */
    static void loadWords() throws IOException {


        BufferedReader br = new BufferedReader(new FileReader("src/com/company/words.txt"));

        String line = "";

        while ((line = br.readLine()) != null) {

            wordList.add(line.toUpperCase());
        }
        br.close();


    }


    /**
     * returns a random word from the wordList collection
     */
    public static String chooseRandomWord() {


        return Words.wordList.get(new Random().nextInt(Words.wordList.size() - 1));
    }
}
