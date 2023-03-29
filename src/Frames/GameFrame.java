package Frames;

import com.company.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import static com.company.Constants.*;

public class GameFrame extends DefaultFrame {


    ColorTheme theme;
    LetterBox[][] letterBoxes;
    private Point focusPoint;

    private String word;


    public static String noPositionLetters = " ";
    static String wrongPositionLetters = " ";
    static String correctPositionLetters = " ";


    /**
     * sets up the GameFrame
     * <p>
     * sets the focusPoint as 0,0
     * <p>
     * chooses a random word from selection
     * creates the 2D letterBox array as [WORD_LENGTH][ATTEMPT_AMOUNT]
     * calls setupBoxes()
     * calls addKeyReactivity()
     *
     * @param theme
     */
    public GameFrame(ColorTheme theme) {


        super(theme, new Dimension((int) ((Constants.LETTER_DISTANCE_MARGIN) * (WORD_LENGTH + 1) + WORD_LENGTH * Constants.LETTER_SIZE.getWidth()), (int) ((Constants.LETTER_DISTANCE_MARGIN) * (ATTEMPT_AMOUNT + 1) + ATTEMPT_AMOUNT * Constants.LETTER_SIZE.getHeight())));


        this.theme = theme;
        this.focusPoint = new Point(0, 0);


        word = Words.chooseRandomWord();
        System.out.println(word);
        letterBoxes = new LetterBox[WORD_LENGTH][ATTEMPT_AMOUNT];


        setUpBoxes();

        addKeyReactivity();


    }


    /**
     * adds keyRelease reactivity, with special cases:
     * <p>
     * ESC- disposes the frame, creates a MenuFrame, adds an attempt to SavedData with attemptAmount being EXIT_GAME_CODE and completed being false.
     * BACKSPACE - moves the focus point to its previous x position focusPoint(focusPoint.y,focusPoint.x-1), and sets the letterBoxes[focusPoint.x][focusPoint.y] letterBox text as ' ' (space char)
     * NON-LETTER - forced return
     * <p>
     * else:
     * <p>
     * letterBoxes[focusPoint.x][focusPoint.y] text is set as the keyChar from the keyEvent
     * <p>
     * if the focusPoint.x is equal to WORD_LENGTH - 1, then:
     * <p>
     * wodCheck() is called, if failed then focusPoint x is increased by 1 and then force returns
     * <p>
     * focusPoint is set to (0, y+1) and if y equals ATTEMPT_AMOUNT then calls exitGame(false)
     * <p>
     * <p>
     * and if the focusPoint.x is not equal to WORD_LENGTH - 1, focusPoint x is increased by 1.
     */
    void addKeyReactivity() {


        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {


                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                    new MenuFrame(theme);
                    SavedData.addAttempt(new Attempt(EXIT_GAME_CODE, false, theme));
                    return;
                }


                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && focusPoint.x != 0) {

                    focusPoint = new Point(focusPoint.x - 1, focusPoint.y);
                    letterBoxes[focusPoint.x][focusPoint.y].setText(' ');
                    repaint();


                    return;
                }

                if (!String.valueOf(e.getKeyChar()).matches("[a-zA-Z]")) {
                    return;
                }


                letterBoxes[focusPoint.x][focusPoint.y].setText(e.getKeyChar());
                if (focusPoint.x == WORD_LENGTH - 1) {

                    if (!wordCheck()) {
                        focusPoint = new Point(focusPoint.x + 1, focusPoint.y);
                        return;
                    }

                    focusPoint = new Point(0, focusPoint.y + 1);
                    if (focusPoint.y == ATTEMPT_AMOUNT) {

                        exitGame(false);
                    }


                } else {

                    focusPoint = new Point(focusPoint.x + 1, focusPoint.y);
                }


            }
        });
    }


    //


    /**
     * creates the playing field as a ATTEMPT_AMOUNT x WORD_LENGTH letterBox grind (Y x X), calculates the position of the letterBoxes based on constants,
     * adds them to the pane and adds them to the two-dimensional letterBox array ([X][Y])
     */
    void setUpBoxes() {


        for (int i = 0; i < ATTEMPT_AMOUNT; i++) {
            for (int j = 0; j < WORD_LENGTH; j++) {


                int x = (int) ((j * (Constants.LETTER_SIZE.getWidth() + Constants.LETTER_DISTANCE_MARGIN)) + Constants.LETTER_DISTANCE_MARGIN);
                int y = (int) ((i * (Constants.LETTER_SIZE.getHeight() + Constants.LETTER_DISTANCE_MARGIN)) + Constants.LETTER_DISTANCE_MARGIN);


                letterBoxes[j][i] = new LetterBox(new Point(x, y), theme);
                pane.add(letterBoxes[j][i]);
                repaint();


            }
        }


    }


    //

    /**
     * builds the word from each letterBox put together, then checks if the Word.wordSet contains said word.
     * <p>
     * If no, returns false, if yes, continues to a colorChange, returns true and if the userword is the supposed word, then calls exitGame(true), and returns true
     *
     * @return
     */
    boolean wordCheck() {


        String userWord = "";

        for (int i = 0; i < WORD_LENGTH; i++) {

            userWord += letterBoxes[i][focusPoint.y].getText();
        }

        if (!Words.wordSet.contains(userWord)) {
            return false;
        }


        colorChange(userWord);


        if (userWord.equalsIgnoreCase(word)) {

            exitGame(true);

        }

        return true;
    }


    /**
     * Sets all letters in a focusPoint.y row to a no position background at first, then checks for wrong positions.
     * <p>
     * <br>
     * If a wrong position letter occurs, the LetterBox is set to a wrong position background, and the letter character is added to the static wrongPositionLetters variable.
     * <p>
     * If a wrong position letter reocurrs per userword, it is ignored and left as "no position".
     * <p>
     * <br>
     * At the end checks for correct positions, changing the appropriate labels to a correct position background, and adding the correct letters to the static correctPositionLetters variable.
     * <p>
     * <br>
     * Finally, adds all background-wise unchanged LetterBoxes letters to the static noPositionLetters variable
     *
     * @param userWord
     */
    void colorChange(String userWord) {

        for (int l = 0; l < WORD_LENGTH; l++) {
            letterBoxes[l][focusPoint.y].setNoPositionBG();

        }
        String usedWrongPositionChars = " ";
        for (int k = 0; k < WORD_LENGTH; k++) {


            if (usedWrongPositionChars.contains("" + userWord.charAt(k))) {
                System.out.println("continued");
                continue;
            }
            if (word.contains("" + userWord.charAt(k)) && !(userWord.charAt(k) == word.charAt(k))) {


                letterBoxes[k][focusPoint.y].setWrongPositionBG();
                usedWrongPositionChars += letterBoxes[k][focusPoint.y].getText();
                wrongPositionLetters += letterBoxes[k][focusPoint.y].getText();

            }


        }

        for (int j = 0; j < WORD_LENGTH; j++) {


            if (userWord.charAt(j) == word.charAt(j)) {
                letterBoxes[j][focusPoint.y].setCorrectPositionBG();
                correctPositionLetters += letterBoxes[j][focusPoint.y].getText();
            }

            if (letterBoxes[j][focusPoint.y].getBackground() == theme.noPositionBackground()) {
                noPositionLetters += letterBoxes[j][focusPoint.y].getText();

            }

        }


    }


    private Timer exitTimer = null;


    // disposes the JFrame, creates a GameOverFrame with the completion parameter, adds the attempt to saved data which is then serialized
    // all with a 100 ms delay
    //a null check is used for double game-end prevention
    void exitGame(boolean completed) {
        if (exitTimer == null) {
            exitTimer = new Timer();
            exitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    dispose();
                    new GameOverFrame(word, theme, completed).setVisible(true);
                    SavedData.addAttempt(new Attempt(focusPoint.y, completed, theme));

                }
            }, 100);
        }


    }


}

