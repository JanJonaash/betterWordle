package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SavedData implements Serializable {

    private static ArrayList<Attempt> attemptList;

    public static List<Attempt> getAttemptList() {
        return attemptList;
    }


    // creates the attemptList
    public static void createAttemptList() {
        attemptList = new ArrayList<>();
    }


    //adds an attempt to the attemptList, calls the writeToFile method
    public static void addAttempt(Attempt a) {


        attemptList.add(a);
        writeToFile();
    }




    //writes a copy of the attemptList into a ser file
    public static void writeToFile() {


        try {
            FileOutputStream fout = new FileOutputStream("data.ser");
            ObjectOutputStream oout = new ObjectOutputStream(fout);

            ArrayList<Attempt> toCopy = new ArrayList<>(attemptList);

            oout.writeObject(toCopy);

            fout.close();
            oout.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //reads the ser file and stores its data in the attemptList
    public static void readFromFile() {


        try {
            FileInputStream fin = new FileInputStream("data.ser");
            ObjectInputStream oin = new ObjectInputStream(fin);


            attemptList = (ArrayList<Attempt>) oin.readObject();

            fin.close();
            oin.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }


    }


    // returns user's theme from the last "attempt", if no such attempt exist then returns the default theme
    public static ColorTheme getLastTheme() {


        try {
            return attemptList.get(attemptList.size() - 1).theme();
        } catch (Exception e) {
            return Constants.DEFAULT_COLOR_THEME;
        }

    }


}




