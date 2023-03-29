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

    //sets up the info display letter boxes and appropriate infoBoxes (rules) based on the heights and sizes of both the frame anc each component
    //alters the letterBox rules to make them smaller and correctly offset
    //adds a menu actionBox
    //calls the setupBoxes method
    public RulesFrame(ColorTheme theme) {
        super(theme, new Dimension((int) ((Constants.LETTER_DISTANCE_MARGIN) * (WORD_LENGTH + 1) + WORD_LENGTH * Constants.LETTER_SIZE.getWidth()),
                (int) (INFO_SIZE.getHeight() + LETTER_SIZE.getHeight()*4 + LETTER_DISTANCE_MARGIN*8 + ACTION_SIZE.getHeight())));


        int xOffsetBox = LETTER_DISTANCE_MARGIN * 4;
        int xOffsetRule = (int) (LETTER_DISTANCE_MARGIN * 6 + LETTER_SIZE.getWidth());
        InfoLabel firstRule = new InfoLabel("Guess a 5 letter word", new Point(0, 0), theme);
        pane.add(firstRule);

        LetterBox correctBox = new LetterBox(new Point(xOffsetBox, (int) (LETTER_SIZE.getHeight() * 2 + LETTER_DISTANCE_MARGIN * 2)), theme);
        correctBox.setCorrectPositionBG();
        correctBox.setText("-");
        pane.add(correctBox);

        InfoLabel secondRule = new InfoLabel(" Correct position", new Point(xOffsetRule, (int) (LETTER_SIZE.getHeight() * 2 + LETTER_DISTANCE_MARGIN * 2)), theme);
        pane.add(secondRule);


        LetterBox wrongBox = new LetterBox(new Point(xOffsetBox, (int) (LETTER_SIZE.getHeight() * 3 + LETTER_DISTANCE_MARGIN * 3)), theme);
        wrongBox.setWrongPositionBG();
        wrongBox.setText("-");
        pane.add(wrongBox);

        InfoLabel thirdRule = new InfoLabel(" Wrong position", new Point(xOffsetRule, (int) (LETTER_SIZE.getHeight() * 3 + LETTER_DISTANCE_MARGIN * 3)), theme);
        pane.add(thirdRule);


        LetterBox noBox = new LetterBox(new Point(xOffsetBox, (int) (LETTER_SIZE.getHeight() * 4 + LETTER_DISTANCE_MARGIN * 4)), theme);
        noBox.setNoPositionBG();
        noBox.setText("-");
        pane.add(noBox);

        InfoLabel fourthRule = new InfoLabel(" Not contained in the word", new Point(xOffsetRule, (int) (LETTER_SIZE.getHeight() * 4 + LETTER_DISTANCE_MARGIN * 4)), theme);
        pane.add(fourthRule);



        alterRules(secondRule);
        alterRules(thirdRule);
        alterRules(fourthRule);

        setUpBoxes();

        ActionBox menuBox =

                new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionShowMenu(theme), this);




        menuBox.setSize((int) ( (getWidth())/2-LETTER_DISTANCE_MARGIN*1.5), (int) ACTION_SIZE.getHeight());


        pane.add(menuBox);

        ActionBox moreRulesBox = new ActionBox(new Point(getWidth()- LETTER_DISTANCE_MARGIN - menuBox.getWidth(), menuBox.getY()),theme,new ActionShowMoreRules(theme),this);
       moreRulesBox.setSize(menuBox.getSize());
        pane.add(moreRulesBox);
    }


    /**
     sets up the 5 animation letterBoxes, based on array index, offsets and common y.
     calls startAnimation()
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


        startAnimation();


    }


    /**
     starts and repeats an animation once per 5s
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
     resets all the letterBoxes, generates a random word, generates a random typing speed,
     proceeds to type letter by letter, simulating gameplay, at the end makes all the letterBoxes a random background(correct, no, and wrong position)
     */
    void animate() {


        Timer animation = new Timer();
        currentWordOnDisplay = Words.chooseRandomWord();


        for (LetterBox l : letterBoxes) {

            l.setDefaultBG();
            l.setText(" ");
        }


        Random r = new Random();
        int typeSpeed = (r.nextInt(2) + 1) * 450;

        animation.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                letterBoxes[letterBoxIndex].setText(currentWordOnDisplay.charAt(letterBoxIndex));
                letterBoxIndex++;


                if (letterBoxIndex == letterBoxes.length) {
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
     alters the infobox slightly to make it fit more (used for rules)
     * @param l
     */
    void alterRules(InfoLabel l) {

        l.setHorizontalAlignment(JLabel.LEFT);
        l.setFont(new Font(l.getFont().getFontName(), Font.ITALIC, 30));
    }
}
