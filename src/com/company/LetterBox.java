package com.company;

import Frames.GameFrame;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static com.company.Constants.*;

public class LetterBox extends JLabel {


    private final int borderSize = 4;
    private ColorTheme theme;


    /**
     * Sets up the LetterBox.
     * calls setDefaultBG().
     *
     * @param location
     * @param theme
     */
    public LetterBox(Point location, ColorTheme theme) {


        this.theme = theme;

        this.setSize(LETTER_SIZE);
        this.setLocation(location);
        this.setOpaque(true);
        this.setForeground(theme.foreground());

        setDefaultBG();
        this.setVisible(true);
        this.setText("");

        setFont(LETTER_FONT);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);


    }


    /**
     * If noPositionLetters contain the uppercase version of the char:
     * <p>
     * - Calls setNoPositionBG().
     * <p>
     * If the char is EMPTY_CHAR:
     * <p>
     * - Calls setDefaultBG().
     * <p>
     * Sets the text as the uppercase version of the char.
     *
     * @param c
     */
    public void setText(char c) {


        if (GameFrame.noPositionLetters.contains(String.valueOf(c).toUpperCase())) {

            setNoPositionBG();
        }
        if (c == EMPTY_CHAR) {

            setDefaultBG();
        }


        setText(String.valueOf(c).toUpperCase());
        repaint();


    }


    /**
     * Sets the background and border as correctPositionBackground.
     */
    public void setCorrectPositionBG() {


        setBackground(theme.correctPositionBackground());
        setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, theme.correctPositionBackground()));

    }

    /**
     * Sets the background and border as wrongPositionBackground.
     */
    public void setWrongPositionBG() {


        setBackground(theme.wrongPositionBackground());
        setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, theme.wrongPositionBackground()));

    }

    /**
     * Sets the background and border as noPositionBackground.
     */
    public void setNoPositionBG() {


        setBackground(theme.noPositionBackground());
        setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, theme.noPositionBackground()));

    }

    /**
     * Sets the background as letterBackground.
     * <p>
     * Sets the border to secondary.
     */
    public void setDefaultBG() {
        setBackground(theme.letterBackground());
        setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, theme.secondary()));
    }


}
