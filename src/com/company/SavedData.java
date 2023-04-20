package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SavedData implements Serializable {

    private static ArrayList<Attempt> attemptList;

    public static List<Attempt> getAttemptList() {
        return attemptList;
    }


    /**
     * Creates the attemptList.
     */
    public static void createAttemptList() {
        attemptList = new ArrayList<>();
    }


    /**
     * Adds an attempt to the attemptList.
     * <p>
     * Calls writeToFile().
     * @param a
     */
    public static void addAttempt(Attempt a) {


        attemptList.add(a);
        writeToFile();
    }


    /**
     * Writes a copy of the attemptList into a ser file
     */
    public static void writeToFile() {


        try {
            var fout = new FileOutputStream("data.ser");
            ObjectOutputStream oout = new ObjectOutputStream(fout);

            ArrayList<Attempt> toCopy = new ArrayList<>(attemptList);

            oout.writeObject(toCopy);

            fout.close();
            oout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * Reads the ser file and stores its data in the attemptList
     */
    public static void readFromFile() throws IOException, ClassNotFoundException {


        FileInputStream fin = new FileInputStream("data.ser");
        ObjectInputStream oin = new ObjectInputStream(fin);


        attemptList = (ArrayList<Attempt>) oin.readObject();

        fin.close();
        oin.close();


    }


    /**
     * Returns user's theme from the last "attempt", if no such attempt exist then returns the default theme
     */
    public static ColorTheme getLastTheme() {


        try {
            return attemptList.get(attemptList.size() - 1).theme();
        } catch (Exception e) {
            return Constants.DEFAULT_COLOR_THEME;
        }

    }


}




