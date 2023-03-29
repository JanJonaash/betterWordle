package Frames;

import ActionCommands.*;
import com.company.ColorTheme;
import com.company.Constants;
import com.company.LetterBox;

import java.awt.*;
import java.util.Random;


import static com.company.Constants.ACTION_SIZE;
import static com.company.Constants.LETTER_DISTANCE_MARGIN;
import static com.company.Constants.LETTER_SIZE;


public class MenuFrame extends DefaultFrame {


    LetterBox[] wordleLetters;



    //sets up the MenuFrame with Rules, Stats, Themes, Quit and Start actionBoxes, Start being a different size
    //calls setupWordleSign()
    public MenuFrame(ColorTheme theme) {

        super(theme,


                new Dimension(
                        (int) ((LETTER_DISTANCE_MARGIN) * (7) + 6 *LETTER_SIZE.getWidth()), (int) (LETTER_DISTANCE_MARGIN*4 + LETTER_SIZE.getHeight()+ ACTION_SIZE.getHeight()*2))
                );



        setupWordleSign();





        ActionBox start = new ActionBox(new Point ((int) ((getWidth()- ACTION_SIZE.getWidth())/2), (int) (LETTER_DISTANCE_MARGIN*2+ LETTER_SIZE.getHeight()) ), theme, new ActionStartGame(theme), this);

        start.setSize((int) (LETTER_DISTANCE_MARGIN+ LETTER_SIZE.getWidth()*2), (int) (LETTER_DISTANCE_MARGIN+ LETTER_SIZE.getWidth()*2));

        start.setFont(new Font(start.getFont().getFontName(), Font.BOLD, 54));
        pane.add(start);

        pane.add(new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (LETTER_DISTANCE_MARGIN*2+ LETTER_SIZE.getHeight())),theme,new ActionShowRules(theme),this));
        pane.add(new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (LETTER_DISTANCE_MARGIN*3+ LETTER_SIZE.getHeight()*2)),theme,new ActionShowThemes(theme),this));
        pane.add(new ActionBox(new Point((int) (getSize().getWidth()-LETTER_DISTANCE_MARGIN-ACTION_SIZE.getWidth()), (int) (LETTER_DISTANCE_MARGIN*2+ LETTER_SIZE.getWidth())),theme,new ActionShowStats(theme),this));
        pane.add(new ActionBox(new Point((int) (getSize().getWidth()-LETTER_DISTANCE_MARGIN-ACTION_SIZE.getWidth()),(int) (LETTER_DISTANCE_MARGIN*3+ LETTER_SIZE.getHeight()*2)),theme,new ActionExitGame(theme),this));





    }





    // creates the wordle sign as a 1 x WORD_LENGTH letterBox grind (X), calculates the position of the letterBoxes based on constants,
    //adds them to the pane and adds them to the  letterBox array
    // sets each letter as random background (wrong, correct and no position)

    void setupWordleSign(){


        LetterBox[] wordleLetters = new LetterBox[6];


      final   String word = "wordle";
        for (int j = 0; j < word.length(); j++) {


                int x = (int) ((j * (LETTER_SIZE.getWidth() + LETTER_DISTANCE_MARGIN)) + LETTER_DISTANCE_MARGIN);
                int y = LETTER_DISTANCE_MARGIN;


                wordleLetters[j]= new LetterBox(new Point(x, y), theme);
                wordleLetters[j].setText(word.charAt(j));



                switch (new Random().nextInt(4)){
                    case 0->  wordleLetters[j].setWrongPositionBG();
                    case 1-> wordleLetters[j].setCorrectPositionBG();
                    default -> wordleLetters[j].setNoPositionBG();

                }



                pane.add(wordleLetters[j]);
                repaint();






        }

    }




}
