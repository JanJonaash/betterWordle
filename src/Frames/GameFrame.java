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
    public static String wrongPositionLetters = " ";
    public static String correctPositionLetters = " ";


    /**
     * Sets up the GameFrame.
     * <p>
     * Sets the focusPoint as 0;0.
     * <p>
     * Chooses a random word from selection.
     * <p>
     * Creates the 2D letterBox array as [WORD_LENGTH][ATTEMPT_AMOUNT].
     * <p>
     * Calls setupBoxes().
     * <p>
     * Calls addKeyReactivity().
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
     * Adds a KeyListener to the JFrame with a KeyRelease reactivity.
     * <p>
     * <p>
     * <p>
     * ESCAPE:
     * <p>
     * Disposes the JFrame.
     * <p>
     * Creates a new MenuFrame.
     * <p>
     * Saves the attempt to SavedData with false completion and EXIT_GAME_CODE as attempt amount.
     * <p>
     * returns.
     * <p>
     * <p>
     * <p>
     * BACKSPACE:
     * <p>
     * Calls moveFocusPoint(true)
     * <p>
     * letterBoxes[focusPoint x][focusPoint y] text is to EMPTY_CHAR
     * <p>
     * returns.
     * <p>
     * <p>
     * <p>
     * NON-LETTER:
     * <p>
     * returns.
     * <p>
     * <p>
     * <p>
     * letterBoxes[focusPoint x][focusPoint y] text is to e.getKeyChar().
     * <p>
     * Calls moveFocusPoint(false)
     */
    void addKeyReactivity() {


        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {


                switch (e.getKeyCode()) {

                    case KeyEvent.VK_ESCAPE -> {

                        dispose();
                        new MenuFrame(theme);
                        SavedData.addAttempt(new Attempt(EXIT_GAME_CODE, false, theme));
                        return;
                    }

                    case KeyEvent.VK_BACK_SPACE -> {

                        moveFocusPoint(true);
                        letterBoxes[(int) focusPoint.getX()][(int) focusPoint.getY()].setText(EMPTY_CHAR);
                        repaint();


                        return;

                    }

                }

                if (!String.valueOf(e.getKeyChar()).matches("[a-zA-Z]")) {
                    return;
                }


                letterBoxes[(int) focusPoint.getX()][(int) focusPoint.getY()].setText(e.getKeyChar());
                moveFocusPoint(false);


            }
        });
    }

    /**
     * focusPoint is set to focusPoint x-1 ; focusPoint y, if x is bigger than 0. If not, it stays the same.
     * <p>
     * returns.
     * <p>
     * <p>
     * If focusPoint x is equal to WORD_LENGTH-1:
     *
     * <p>
     * - wordCheck() is called, and if it fails:
     * <p>
     * -- focusPoint is set to focusPoint x+1 ; focusPoint y.
     * <p>
     * -- returns.
     * <p>
     * - focusPoint is set to 0 ; focusPoint y+1
     * <p>
     * - If focusPoint x is equal to ATTEMPT_AMOUNT:
     * <p>
     * -- Calls exitGame(false)
     * <p>
     * <p>
     * else:
     * <p>
     * - focusPoint is set to focusPoint x+1 ; focusPoint y
     *
     * @param backward
     */
    void moveFocusPoint(boolean backward) {


        if (backward) {
            focusPoint = ((int) focusPoint.getX() != 0) ? new Point((int) focusPoint.getX() - 1, (int) focusPoint.getY()) : new Point((int) focusPoint.getX(), (int) focusPoint.getY());
            return;

        }


        if ((int) focusPoint.getX() == WORD_LENGTH - 1) {

            if (!wordCheck()) {
                focusPoint = new Point((int) focusPoint.getX() + 1, (int) focusPoint.getY());
                return;
            }

            focusPoint = new Point(0, (int) focusPoint.getY() + 1);
            if ((int) focusPoint.getY() == ATTEMPT_AMOUNT) {

                exitGame(false);
            }


        } else {

            focusPoint = new Point((int) focusPoint.getX() + 1, (int) focusPoint.getY());
        }


    }


    /**
     * Creates the playing field as a ATTEMPT_AMOUNT x WORD_LENGTH letterBox grind.
     * <p>
     * Calculates the position of the letterBoxes based on constants.
     * <p>
     * Adds them to the pane and to the two-dimensional letterBox array [X][Y]
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


    /**
     * Builds the word from each letterBox in a row put together, then checks if the Words.wordList contains said word.
     * <p>
     * If no, returns false.
     * <p>
     * Calls colorChange().
     * <p>
     * If the userword is the supposed word, then calls exitGame(true).
     * <p>
     * Returns true.
     *
     * @return
     */
    boolean wordCheck() {


        String userWord = "";

        for (int i = 0; i < WORD_LENGTH; i++) {
            userWord += letterBoxes[i][(int) focusPoint.getY()].getText();
        }

        if (!Words.wordList.contains(userWord)) {
            return false;
        }


        colorChange(userWord);


        if (userWord.equalsIgnoreCase(word)) {

            exitGame(true);

        }

        return true;
    }


    /**
     * Sets all letters in a focusPoint y row to a no position background at first, then checks for wrong positions.
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
     * Finally, adds all background-wise unchanged LetterBoxes letters to the static noPositionLetters variable.
     *
     * @param userWord
     */
    void colorChange(String userWord) {

        for (int l = 0; l < WORD_LENGTH; l++) {
            letterBoxes[l][(int) focusPoint.getY()].setNoPositionBG();

        }
        String usedWrongPositionChars = " ";
        for (int k = 0; k < WORD_LENGTH; k++) {


            if (usedWrongPositionChars.contains("" + userWord.charAt(k))) {
                System.out.println("continued");
                continue;
            }
            if (word.contains("" + userWord.charAt(k)) && !(userWord.charAt(k) == word.charAt(k))) {


                letterBoxes[k][(int) focusPoint.getY()].setWrongPositionBG();
                usedWrongPositionChars += letterBoxes[k][(int) focusPoint.getY()].getText();
                wrongPositionLetters += letterBoxes[k][(int) focusPoint.getY()].getText();

            }


        }

        for (int j = 0; j < WORD_LENGTH; j++) {


            if (userWord.charAt(j) == word.charAt(j)) {
                letterBoxes[j][(int) focusPoint.getY()].setCorrectPositionBG();
                correctPositionLetters += letterBoxes[j][(int) focusPoint.getY()].getText();
            }

            if (letterBoxes[j][(int) focusPoint.getY()].getBackground() == theme.noPositionBackground()) {
                noPositionLetters += letterBoxes[j][(int) focusPoint.getY()].getText();

            }

        }


    }


    private Timer exitTimer = null;


    /**
     * Goes through a null check for exitTimer (double game-end prevention).
     * <p>
     * Schedules a one-time TimerTask with 100ms delay, which:
     * <p>
     * - Disposes the JFrame.
     * <p>
     * - Creates a GameOverFrame.
     * <p>
     * - Calls SavedData.addAttempt() with focusPoint.getY(), theme and completed as params.
     *
     * @param completed
     */
    void exitGame(boolean completed) {
        if (exitTimer == null) {
            exitTimer = new Timer();
            exitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    dispose();
                    new GameOverFrame(word, theme, completed);
                    SavedData.addAttempt(new Attempt((int) focusPoint.getY(), completed, theme));

                }
            }, 100);
        }


    }


}

