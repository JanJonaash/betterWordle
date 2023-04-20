package Frames;

import ActionCommands.ActionBox;
import ActionCommands.ActionShowMenu;
import ActionCommands.ActionShowMoreRules;
import com.company.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.company.Constants.*;

public class RulesFrame extends DefaultFrame {


    LetterBox[] letterBoxes;


    /**
     * Sets up the RulesFrame.
     * <p>
     * Adds 4 rule infoBoxes.
     * <p>
     * Adds LetterBoxes displaying correctPosition, wrongPosition and noPosition backgrounds.
     * <p>
     * Adds a menu ActionBox.
     * <p>
     * Adds a moreRules ActionBox.
     * <p>
     * Calls alterRules() with params: secondRule, thirdRule, fourthRule.
     * <p>
     * Calls setupBoxes()
     * <p>
     * Calls startAnimation()
     *
     * @param theme
     */
    public RulesFrame(ColorTheme theme) {
        super(theme, new Dimension((int) ((Constants.LETTER_DISTANCE_MARGIN) * (WORD_LENGTH + 1) + WORD_LENGTH * Constants.LETTER_SIZE.getWidth()),
                (int) (INFO_SIZE.getHeight() + LETTER_SIZE.getHeight() * 4 + LETTER_DISTANCE_MARGIN * 8 + ACTION_SIZE.getHeight())));


        int xOffsetBox = LETTER_DISTANCE_MARGIN * 4;
        int xOffsetRule = (int) (LETTER_DISTANCE_MARGIN * 6 + LETTER_SIZE.getWidth());

        InfoLabel firstRule = new InfoLabel("Guess a 5 letter word", new Point(0, 0), theme);


        LetterBox correctBox = new LetterBox(new Point(xOffsetBox, (int) (LETTER_SIZE.getHeight() * 2 + LETTER_DISTANCE_MARGIN * 2)), theme);
        correctBox.setCorrectPositionBG();
        correctBox.setText("-");


        InfoLabel secondRule = new InfoLabel(" Correct position", new Point(xOffsetRule, (int) (LETTER_SIZE.getHeight() * 2 + LETTER_DISTANCE_MARGIN * 2)), theme);


        LetterBox wrongBox = new LetterBox(new Point(xOffsetBox, (int) (LETTER_SIZE.getHeight() * 3 + LETTER_DISTANCE_MARGIN * 3)), theme);
        wrongBox.setWrongPositionBG();
        wrongBox.setText("-");


        InfoLabel thirdRule = new InfoLabel(" Wrong position", new Point(xOffsetRule, (int) (LETTER_SIZE.getHeight() * 3 + LETTER_DISTANCE_MARGIN * 3)), theme);


        LetterBox noBox = new LetterBox(new Point(xOffsetBox, (int) (LETTER_SIZE.getHeight() * 4 + LETTER_DISTANCE_MARGIN * 4)), theme);
        noBox.setNoPositionBG();
        noBox.setText("-");


        InfoLabel fourthRule = new InfoLabel(" Not contained in the word", new Point(xOffsetRule, (int) (LETTER_SIZE.getHeight() * 4 + LETTER_DISTANCE_MARGIN * 4)), theme);


        ActionBox menuBox = new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionShowMenu(theme), this);
        menuBox.setSize((int) ((getWidth()) / 2 - LETTER_DISTANCE_MARGIN * 1.5), (int) ACTION_SIZE.getHeight());

        ActionBox moreRulesBox = new ActionBox(new Point(getWidth() - LETTER_DISTANCE_MARGIN - menuBox.getWidth(), menuBox.getY()), theme, new ActionShowMoreRules(theme), this);
        moreRulesBox.setSize(menuBox.getSize());


        pane.add(firstRule);
        pane.add(secondRule);
        pane.add(thirdRule);
        pane.add(fourthRule);

        pane.add(correctBox);
        pane.add(wrongBox);
        pane.add(noBox);

        pane.add(menuBox);
        pane.add(moreRulesBox);


        alterRules(secondRule, thirdRule, fourthRule);

        setUpBoxes();
        startAnimation();


    }


    /**
     * Creates the word display as a 1 x WORD_LENGTH letterBox grid.
     * <p>
     * Calculates the position of the letterBoxes based on constants.
     */


    void setUpBoxes() {


        letterBoxes = new LetterBox[WORD_LENGTH];

        for (int j = 0; j < WORD_LENGTH; j++) {


            int x = (int) ((j * (LETTER_SIZE.getWidth() + Constants.LETTER_DISTANCE_MARGIN)) + Constants.LETTER_DISTANCE_MARGIN);
            int y = (int) LETTER_SIZE.getHeight();


            letterBoxes[j] = new LetterBox(new Point(x, y), theme);
            pane.add(letterBoxes[j]);
            repaint();


        }


    }


    /**
     * Calls animate() every 5s
     */

    void startAnimation() {

        Timer animationRepetition = new Timer();

        animationRepetition.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {

                        animate();

                    }
                }, 0, 5000


        );
    }

    /**
     * Chooses a random word to display.
     * <p>
     * Sets each letterBox's text to EMPTY_CHAR.
     * <p>
     * Generates a random typing speed.
     * <p>
     * Schedules a periodic TimerTask with period length of typeSpeed, which:
     * <p>
     * - Sets the letterBoxes[letterBoxIndex] text to a char at the same position in currentWordOnDisplay.
     * <p>
     * - Increases the letterBoxIndex.
     * <p>
     * - If letterBoxIndex equals WORD_LENGTH:
     * <p>
     * -- Cancels the animation timer.
     * <p>
     * -- Sets the letterBoxIndex to 0
     * <p>
     * -- Randomly sets all the letterBoxes to correct, wrong, or noPositionBackground.
     */
    void animate() {


        Timer animation = new Timer();
        currentWordOnDisplay = Words.chooseRandomWord();


        for (LetterBox l : letterBoxes) {

            l.setText(EMPTY_CHAR);
        }


        Random r = new Random();
        int typeSpeed = (r.nextInt(2) + 1) * 450;

        animation.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                letterBoxes[letterBoxIndex].setText(currentWordOnDisplay.charAt(letterBoxIndex));
                letterBoxIndex++;


                if (letterBoxIndex == WORD_LENGTH) {
                    animation.cancel();
                    letterBoxIndex = 0;

                    for (LetterBox l : letterBoxes) {

                        switch (new Random().nextInt(4)) {
                            case 0 -> l.setWrongPositionBG();
                            case 1 -> l.setCorrectPositionBG();
                            default -> l.setNoPositionBG();

                        }
                    }


                }


            }
        }, 0, typeSpeed);


    }


    private int letterBoxIndex = 0;
    private String currentWordOnDisplay = "";


    /**
     * Alters the InfoLabels by changing their horizontalAlignment and font.
     *
     * @param labels
     */
    void alterRules(InfoLabel... labels) {


        for (InfoLabel l : labels) {
            l.setHorizontalAlignment(JLabel.LEFT);
            l.setFont(new Font(l.getFont().getFontName(), Font.ITALIC, 30));
        }

    }
}
