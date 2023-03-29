package com.company;

import Frames.GameFrame;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static com.company.Constants.*;

public class LetterBox extends JLabel {


    private final int borderSize = 4;
    private ColorTheme theme;


    //sets up the letterBox, makes it default background
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


    //sets the text as the uppercase version of the parameter, then changes the background if it is contained in the noPositionLetters collection, or if the parameter is a whitespace char
    public void setText(char c) {


        if (GameFrame.noPositionLetters.contains(String.valueOf(c).toUpperCase())) {

            setNoPositionBG();
        }
        if (c == ' ') {

            setDefaultBG();
        }


        setText(String.valueOf(c).toUpperCase());
        repaint();


    }


    //sets the correctPositiontBackground along with appropriate border
    public void setCorrectPositionBG() {


        setBackground(theme.correctPositionBackground());
        setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, theme.correctPositionBackground()));

    }

    //sets the wrongPositionBackground along with appropriate border
    public void setWrongPositionBG() {


        setBackground(theme.wrongPositionBackground());
        setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, theme.wrongPositionBackground()));

    }

    //sets the noPositiontBackground along with appropriate border
    public void setNoPositionBG() {


        setBackground(theme.noPositionBackground());
        setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, theme.noPositionBackground()));

    }

    //sets the defaultBackground along with appropriate border
    public void setDefaultBG() {
        setBackground(theme.letterBackground());
        setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, theme.secondary()));
    }


}
