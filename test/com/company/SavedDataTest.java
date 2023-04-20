package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavedDataTest {


    @Test
    void addAttemptTest(){

        SavedData.createAttemptList();

        SavedData.addAttempt(new Attempt(1,true, Constants.DEFAULT_COLOR_THEME));

        assertEquals(1, SavedData.getAttemptList().size());
    }


    @Test
    void lastColorThemeTest(){

        SavedData.createAttemptList();

        SavedData.addAttempt(new Attempt(1,true, Constants.DEFAULT_COLOR_THEME));
        SavedData.addAttempt(new Attempt(1,true, Constants.COLORBLIND_COLOR_THEME));
        SavedData.addAttempt(new Attempt(1,true, Constants.DEFAULT_COLOR_THEME));
        SavedData.addAttempt(new Attempt(1,true, Constants.PORTAL_COLOR_THEME));

        assertEquals(Constants.PORTAL_COLOR_THEME, SavedData.getLastTheme());


    }
}