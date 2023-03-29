package Frames;

import ActionCommands.*;
import com.company.ColorTheme;
import com.company.InfoLabel;
import com.company.LetterBox;

import java.awt.*;

import static com.company.Constants.*;

public class GameOverFrame extends DefaultFrame {




    LetterBox[] letterBoxes;
    ColorTheme theme;

    private String word;

    // sets up the GameOverFrame with a infoLabel with text reflecting the completion status, adds a Restart and Menu actionBoxes
    // calls setupBoxes()
    public GameOverFrame(String word, ColorTheme theme, boolean completed) {


        super(theme, new Dimension(

                (int) ((LETTER_DISTANCE_MARGIN) * (WORD_LENGTH + 1) + WORD_LENGTH * LETTER_SIZE.getWidth()), (int) (LETTER_DISTANCE_MARGIN * 3 + ACTION_SIZE.getHeight()/2 + LETTER_SIZE.getHeight()*2)
        ));
        this.word = word;
        this.theme = theme;
        letterBoxes = new LetterBox[WORD_LENGTH];

        String message = (completed) ? "Word guessed" : "Guesses failed";
        InfoLabel completionLabel = new InfoLabel(message,new Point((int) (getWidth()-INFO_SIZE.getWidth())/2, -(int) ACTION_SIZE.getHeight()/6 ), theme);
        completionLabel.setFont(new Font(completionLabel.getFont().getFontName(), Font.BOLD, 40));
        pane.add(completionLabel);

        ActionBox restart = new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionRestartGame(theme), this);
        restart.setSize((int) ( (getWidth())/2-LETTER_DISTANCE_MARGIN*1.5), (int) ACTION_SIZE.getHeight());



        ActionBox menu = new ActionBox(new Point((int) (getWidth() - restart.getWidth() - LETTER_DISTANCE_MARGIN), (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionShowMenu(theme), this);
        menu.setSize(restart.getSize());



        pane.add(restart);
        pane.add(menu);
        setUpBoxes();
    }



    // creates the end word display as a 1 x WORD_LENGTH letterBox grind (X), calculates the position of the letterBoxes based on constants,
    // adds them to the pane and adds them to the  letterBox array
    // sets a box to a no position background at first, then changes to a wrong position background if appropriate, then correct position background if appropriate.

    //clears the GameFrame static noPositionLetters, wrongPositionLetters and correctPosition variables

    void setUpBoxes() {


        for (int j = 0; j < WORD_LENGTH; j++) {


            int x = (int) ((j * (LETTER_SIZE.getWidth() + LETTER_DISTANCE_MARGIN)) + LETTER_DISTANCE_MARGIN);
            int y = (int) (getHeight()-LETTER_DISTANCE_MARGIN*2-LETTER_SIZE.getHeight()- ACTION_SIZE.getHeight());


            letterBoxes[j] = new LetterBox(new Point(x, y), theme);
            letterBoxes[j].setText(word.charAt(j));

            letterBoxes[j].setNoPositionBG();


            if (GameFrame.wrongPositionLetters.contains("" + (word.charAt(j)))) {
                letterBoxes[j].setWrongPositionBG();
            }

            if (GameFrame.correctPositionLetters.contains("" + (word.charAt(j)))) {
                letterBoxes[j].setCorrectPositionBG();
            }






            pane.add(letterBoxes[j]);
            repaint();


        }
        GameFrame.wrongPositionLetters = "";
        GameFrame.noPositionLetters = "";
        GameFrame.correctPositionLetters = "";

    }


}
