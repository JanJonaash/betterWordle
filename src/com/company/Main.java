package com.company;

import Frames.MenuFrame;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            Words.loadWords(); //https://github.com/tabatkins/wordle-list/blob/main/words source of all possible words
            SavedData.createAttemptList();
            SavedData.readFromFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        new MenuFrame(SavedData.getLastTheme());

    }
}
