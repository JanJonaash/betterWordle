package Frames;

import ActionCommands.*;
import com.company.*;

import java.awt.*;

import static com.company.Constants.*;

public class ThemeFrame extends DefaultFrame {


    /**
     * Sets up the ThemeFrame
     * <p>
     * calls SavedData.addAttempt(), with CHANGE_THEME_CODE, false completion and theme.
     * <p>
     * Adds themesInfo InfoLabel.
     * <p>
     * Adds defaultTheme, portalTheme, colorblindTheme ActionBoxes.
     * <p>
     * Adds correctPos, wrongPos and noPos LetterBoxes.
     * <p>
     * Adds a menu ActionBox.
     *
     * @param theme
     */
    public ThemeFrame(ColorTheme theme) {
        super(theme, new Dimension((int) (ACTION_SIZE.getWidth() + LETTER_DISTANCE_MARGIN * 3 + LETTER_SIZE.getWidth() * 2), (int) (ACTION_SIZE.getHeight() * 4 + LETTER_DISTANCE_MARGIN * 6 + LETTER_DISTANCE_MARGIN + 50)));


        int offsetActionX = LETTER_DISTANCE_MARGIN * 2;
        int offsetLetterX = (int) (getWidth() - offsetActionX - LETTER_SIZE.getWidth());
        int yOffset = LETTER_DISTANCE_MARGIN + 50;


        SavedData.addAttempt(new Attempt(CHANGE_THEME_CODE, false, theme));


        InfoLabel themesInfo = new InfoLabel("Themes", new Point((int) ((getWidth() - INFO_SIZE.getWidth()) / 2), (int) (-ACTION_SIZE.getHeight() / 8)), theme);


        ActionBox defaultTheme = new ActionBox(new Point(offsetActionX, LETTER_DISTANCE_MARGIN + yOffset), theme, new ActionShowThemes(Constants.DEFAULT_COLOR_THEME), this);
        defaultTheme.setText("DEFAULT");


        ActionBox portalTheme = new ActionBox(new Point(offsetActionX, (int) (LETTER_DISTANCE_MARGIN * 2 + ACTION_SIZE.getHeight() + yOffset)), theme, new ActionShowThemes(Constants.PORTAL_COLOR_THEME), this);
        portalTheme.setText("PORTAL");


        ActionBox colorblindTheme = new ActionBox(new Point(offsetActionX, (int) (LETTER_DISTANCE_MARGIN * 3 + ACTION_SIZE.getHeight() * 2 + yOffset)), theme, new ActionShowThemes(Constants.COLORBLIND_COLOR_THEME), this);
        colorblindTheme.setFont(new Font(colorblindTheme.getFont().getFontName(), Font.BOLD, 31));
        colorblindTheme.setText("COLORBLIND");


        LetterBox correctPos = new LetterBox(new Point(offsetLetterX, LETTER_DISTANCE_MARGIN + yOffset), theme);
        correctPos.setCorrectPositionBG();
        correctPos.setText("C");


        LetterBox wrongPos = new LetterBox(new Point(offsetLetterX, (int) (LETTER_DISTANCE_MARGIN * 2 + ACTION_SIZE.getHeight() + yOffset)), theme);
        wrongPos.setWrongPositionBG();
        wrongPos.setText("W");


        LetterBox noPos = new LetterBox(new Point(offsetLetterX, (int) (LETTER_DISTANCE_MARGIN * 3 + ACTION_SIZE.getHeight() * 2 + yOffset)), theme);
        noPos.setNoPositionBG();
        noPos.setText("N");


        ActionBox menu = new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionShowMenu(theme), this);
        menu.setSize(getWidth() - LETTER_DISTANCE_MARGIN * 2, (int) ACTION_SIZE.getHeight());


        pane.add(themesInfo);

        pane.add(defaultTheme);
        pane.add(portalTheme);
        pane.add(colorblindTheme);


        pane.add(correctPos);
        pane.add(wrongPos);
        pane.add(noPos);

        pane.add(menu);

    }


}
