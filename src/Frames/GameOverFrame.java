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

    /**
     * Sets up the GameFrame
     * <p>
     * Decides the message displayed on completionLabel
     * <p>
     * Adds a completionLabel InfoLabel, restart ActionBox, menu ActionBox
     * <p>
     * calls setupBoxes()
     * <p>
     * calls clearPositionVariables()
     *
     * @param word
     * @param theme
     * @param completed
     */
    public GameOverFrame(String word, ColorTheme theme, boolean completed) {


        super(theme, new Dimension(

                (int) ((LETTER_DISTANCE_MARGIN) * (WORD_LENGTH + 1) + WORD_LENGTH * LETTER_SIZE.getWidth()), (int) (LETTER_DISTANCE_MARGIN * 3 + ACTION_SIZE.getHeight() / 2 + LETTER_SIZE.getHeight() * 2)
        ));
        this.word = word;
        this.theme = theme;
        letterBoxes = new LetterBox[WORD_LENGTH];

        String message = (completed) ? "Word guessed" : "Guesses failed";

        InfoLabel completionLabel = new InfoLabel(message, new Point((int) (getWidth() - INFO_SIZE.getWidth()) / 2, -(int) ACTION_SIZE.getHeight() / 6), theme);
        completionLabel.setFont(new Font(completionLabel.getFont().getFontName(), Font.BOLD, 40));


        ActionBox restart = new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionRestartGame(theme), this);
        restart.setSize((int) ((getWidth()) / 2 - LETTER_DISTANCE_MARGIN * 1.5), (int) ACTION_SIZE.getHeight());


        ActionBox menu = new ActionBox(new Point((int) (getWidth() - restart.getWidth() - LETTER_DISTANCE_MARGIN), (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionShowMenu(theme), this);
        menu.setSize(restart.getSize());


        pane.add(completionLabel);
        pane.add(restart);
        pane.add(menu);
        setUpBoxes();
        clearPositionVariables();
    }

    /**
     * Creates the word display as a 1 x WORD_LENGTH letterBox grid.
     * <p>
     * Calculates the position of the letterBoxes based on constants, sets the text as the appropriate char of the word.
     * <p>
     * Sets each box to a noPositionBackground.
     * <p>
     * Checks if wrongPositionLetters contain the char, changes the background to wrongPositionBackground if yes.
     * <p>
     * Checks if correctPositionLetters contain the char, changes the background to correctPositionBackground if yes.
     */
    void setUpBoxes() {


        for (int j = 0; j < WORD_LENGTH; j++) {


            int x = (int) ((j * (LETTER_SIZE.getWidth() + LETTER_DISTANCE_MARGIN)) + LETTER_DISTANCE_MARGIN);
            int y = (int) (getHeight() - LETTER_DISTANCE_MARGIN * 2 - LETTER_SIZE.getHeight() - ACTION_SIZE.getHeight());


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


    }

    /**
     * Clears the GameFrame static noPositionLetters, wrongPositionLetters and correctPosition variables.
     */
    void clearPositionVariables() {
        GameFrame.wrongPositionLetters = "";
        GameFrame.noPositionLetters = "";
        GameFrame.correctPositionLetters = "";

    }


}
