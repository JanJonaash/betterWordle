package com.company;

import java.awt.*;

public abstract class Constants {


    public static final int  WORD_LENGTH = 5;
    public static final int  ATTEMPT_AMOUNT = 6;

    public static final Dimension LETTER_SIZE = new Dimension(120, 120);
    public static final int LETTER_DISTANCE_MARGIN = 15;
    static final String GLOBAL_FONT_NAME = "Arial";
    public static final Font LETTER_FONT = new Font(GLOBAL_FONT_NAME, Font.BOLD, 40);

    public static final Dimension ACTION_SIZE = new Dimension((int) (2 * LETTER_SIZE.getWidth() + LETTER_DISTANCE_MARGIN), 120);

    public static final Dimension INFO_SIZE = new Dimension((int) (int) ((Constants.LETTER_DISTANCE_MARGIN) * (WORD_LENGTH + 1) + WORD_LENGTH * Constants.LETTER_SIZE.getWidth()), (int) LETTER_SIZE.getHeight());



    public  static final ColorTheme PORTAL_COLOR_THEME = new ColorTheme(
            new Color(207, 207, 207),
            new Color(190, 190, 190),
            new Color(207, 207, 207),
            new Color(234, 234, 234),
            Color.black,
            Color.orange,
            Color.white,
            Color.blue);

    public  static final  ColorTheme DEFAULT_COLOR_THEME = new ColorTheme(
            Color.black,
            new Color(48, 48, 48),
            Color.black,
            Color.darkGray,
            Color.white,
            new Color(128, 128, 0),
            Color.gray,
            new Color(0, 128, 0));


    public  static final ColorTheme COLORBLIND_COLOR_THEME = new ColorTheme(
            Color.gray,
            new Color(96,96,96),
            Color.gray,
            Color.lightGray,
            new Color(96,96,96),
            Color.white,
            Color.white,
            Color.white);




    public static final int EXIT_GAME_CODE = -1;
    public static final int CHANGE_THEME_CODE = -2;
    public static final char EMPTY_CHAR = ' ';


}
