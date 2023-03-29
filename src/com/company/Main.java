package com.company;

import Frames.GameFrame;
import Frames.MenuFrame;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            Words.loadWords(); //https://github.com/tabatkins/wordle-list/blob/main/words source of all possible words
        } catch (IOException e) {
            e.printStackTrace();
        }
        SavedData.createAttemptList();
        SavedData.readFromFile();



        new MenuFrame(SavedData.getLastTheme());
/*
        if(SavedData.getLastTheme() == null){
            new MenuFrame(Constants.DEFAULT_COLOR_THEME);
        }else{

            new MenuFrame(SavedData.getLastTheme());
        }
*/

    }
}
