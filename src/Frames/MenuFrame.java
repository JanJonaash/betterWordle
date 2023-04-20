package Frames;

import ActionCommands.*;
import com.company.ColorTheme;
import com.company.LetterBox;

import java.awt.*;
import java.util.Random;

import static com.company.Constants.*;


public class MenuFrame extends DefaultFrame {


    LetterBox[] wordleLetters;


    /**
     * Sets up the MenuFrame.
     * <p>
     * Calls SetupWordleSign().
     * <p>
     * Adds a start ActionBox with a specific size.
     * <p>
     * Adds rules, theme, stats, quit ActionBoxes with default sizes.
     *
     * @param theme
     */
    public MenuFrame(ColorTheme theme) {

        super(theme,


                new Dimension(
                        (int) ((LETTER_DISTANCE_MARGIN) * (7) + 6 * LETTER_SIZE.getWidth()), (int) (LETTER_DISTANCE_MARGIN * 4 + LETTER_SIZE.getHeight() + ACTION_SIZE.getHeight() * 2))
        );


        setupWordleSign();


        ActionBox start = new ActionBox(new Point((int) ((getWidth() - ACTION_SIZE.getWidth()) / 2), (int) (LETTER_DISTANCE_MARGIN * 2 + LETTER_SIZE.getHeight())), theme, new ActionStartGame(theme), this);
        start.setSize((int) (LETTER_DISTANCE_MARGIN + LETTER_SIZE.getWidth() * 2), (int) (LETTER_DISTANCE_MARGIN + LETTER_SIZE.getWidth() * 2));
        start.setFont(new Font(start.getFont().getFontName(), Font.BOLD, 54));

        pane.add(start);
        pane.add(new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (LETTER_DISTANCE_MARGIN * 2 + LETTER_SIZE.getHeight())), theme, new ActionShowRules(theme), this));
        pane.add(new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (LETTER_DISTANCE_MARGIN * 3 + LETTER_SIZE.getHeight() * 2)), theme, new ActionShowThemes(theme), this));
        pane.add(new ActionBox(new Point((int) (getSize().getWidth() - LETTER_DISTANCE_MARGIN - ACTION_SIZE.getWidth()), (int) (LETTER_DISTANCE_MARGIN * 2 + LETTER_SIZE.getWidth())), theme, new ActionShowStats(theme), this));
        pane.add(new ActionBox(new Point((int) (getSize().getWidth() - LETTER_DISTANCE_MARGIN - ACTION_SIZE.getWidth()), (int) (LETTER_DISTANCE_MARGIN * 3 + LETTER_SIZE.getHeight() * 2)), theme, new ActionExitGame(theme), this));


    }


    /**
     * Creates the Wordle display as a 1 x 6 letterBox grid.
     * <p>
     * Calculates the position of the letterBoxes based on constants, sets the text as the appropriate char of the word.
     * <p>
     * <p>
     * Randomly sets the background as correct, wrong, or noPositionBackground.
     */
    void setupWordleSign() {


        LetterBox[] wordleLetters = new LetterBox[6];


        final String word = "wordle";
        for (int j = 0; j < word.length(); j++) {


            int x = (int) ((j * (LETTER_SIZE.getWidth() + LETTER_DISTANCE_MARGIN)) + LETTER_DISTANCE_MARGIN);
            int y = LETTER_DISTANCE_MARGIN;


            wordleLetters[j] = new LetterBox(new Point(x, y), theme);
            wordleLetters[j].setText(word.charAt(j));


            switch (new Random().nextInt(4)) {
                case 0 -> wordleLetters[j].setWrongPositionBG();
                case 1 -> wordleLetters[j].setCorrectPositionBG();
                default -> wordleLetters[j].setNoPositionBG();

            }


            pane.add(wordleLetters[j]);
            repaint();


        }

    }


}
