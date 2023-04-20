package com.company;

import Frames.GameFrame;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.company.Constants.EMPTY_CHAR;
import static org.junit.jupiter.api.Assertions.*;

class LetterBoxTest {


    @Test
    void testNoPositionBackgroundChange() {


        ColorTheme testTheme = Constants.DEFAULT_COLOR_THEME;
        LetterBox l = new LetterBox(new Point(0, 0), testTheme);

        GameFrame.noPositionLetters = "CBD";

        l.setText('C');

        assertEquals(testTheme.noPositionBackground(), l.getBackground());


    }


    @Test
    void testDefaultBackgroundChange() {


        ColorTheme testTheme = Constants.PORTAL_COLOR_THEME;
        LetterBox l = new LetterBox(new Point(0, 0), testTheme);

        GameFrame.noPositionLetters = "ABC";

        l.setText('H');

        assertEquals(testTheme.letterBackground(), l.getBackground());


    }


    @Test
    void testEmptyCharDefaultBackgroundChange() {


        ColorTheme testTheme = Constants.PORTAL_COLOR_THEME;
        LetterBox l = new LetterBox(new Point(0, 0), testTheme);

        GameFrame.noPositionLetters = "ABC";
        l.setText('A');

        l.setText(EMPTY_CHAR);

        assertEquals(testTheme.letterBackground(), l.getBackground());


    }

}