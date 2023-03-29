package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.Constants.INFO_SIZE;
import static com.company.Constants.LETTER_FONT;

public class InfoLabel extends JLabel {


    //sets up the infoLabel with basic attributes, either from constants or constructor parameters
    public InfoLabel(String text, Point location, ColorTheme theme){

        this.setSize(INFO_SIZE);
        this.setLocation(location);
        this.setText(text);
        this.setFont(LETTER_FONT);
        this.setForeground(theme.foreground());
        this.setVisible(true);
        this.setFont(new Font(LETTER_FONT.getFontName(), Font.BOLD, 34));




        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);





    }
}
