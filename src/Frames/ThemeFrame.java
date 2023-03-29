package Frames;

import ActionCommands.*;
import com.company.*;

import java.awt.*;

import static com.company.Constants.*;

public class ThemeFrame extends DefaultFrame {


    // sets up the ThemeFrame with themesInfo infoLabel ActionBoxes for changing themes + menu and 3 letterBoxes displaying the color differences for each theme when selected
    // all locations and sizes are determined by constants and sizes of the components or jframe
    public ThemeFrame(ColorTheme theme) {
        super(theme, new Dimension((int) (ACTION_SIZE.getWidth() + LETTER_DISTANCE_MARGIN*3 + LETTER_SIZE.getWidth()*2), (int) (ACTION_SIZE.getHeight()*4 + LETTER_DISTANCE_MARGIN*6 + LETTER_DISTANCE_MARGIN+50)));


        int offsetActionX = LETTER_DISTANCE_MARGIN*2;

        int yOffset = LETTER_DISTANCE_MARGIN + 50;
        SavedData.addAttempt(new Attempt(CHANGE_THEME_CODE, false, theme));


        InfoLabel themesInfo = new InfoLabel("Themes", new Point((int) ((getWidth() - INFO_SIZE.getWidth()) / 2), (int) (-ACTION_SIZE.getHeight() / 8)), theme);

        pane.add(themesInfo);

        ActionBox defaultTheme = new ActionBox(new Point(offsetActionX, LETTER_DISTANCE_MARGIN + yOffset), theme, new ActionShowThemes(Constants.DEFAULT_COLOR_THEME), this);
        defaultTheme.setText("DEFAULT");
        pane.add(defaultTheme);

        ActionBox portalTheme = new ActionBox(new Point(offsetActionX, (int) (LETTER_DISTANCE_MARGIN * 2 + ACTION_SIZE.getHeight() + yOffset)), theme, new ActionShowThemes(Constants.PORTAL_COLOR_THEME), this);
        portalTheme.setText("PORTAL");
        pane.add(portalTheme);

        ActionBox colorblindTheme = new ActionBox(new Point(offsetActionX, (int) (LETTER_DISTANCE_MARGIN * 3 + ACTION_SIZE.getHeight() * 2 + yOffset)), theme, new ActionShowThemes(Constants.COLORBLIND_COLOR_THEME), this);
        colorblindTheme.setFont(new Font(colorblindTheme.getFont().getFontName(), Font.BOLD, 31));
        colorblindTheme.setText("COLORBLIND");
        pane.add(colorblindTheme);


        int offsetLetterX = (int) (getWidth() - offsetActionX - LETTER_SIZE.getWidth());
        LetterBox correctPos = new LetterBox(new Point(offsetLetterX, LETTER_DISTANCE_MARGIN + yOffset), theme);
        correctPos.setCorrectPositionBG();
        correctPos.setText("C");
        pane.add(correctPos);

        LetterBox wrongPos = new LetterBox(new Point(offsetLetterX,(int) (LETTER_DISTANCE_MARGIN * 2 + ACTION_SIZE.getHeight() + yOffset)), theme);
        wrongPos.setWrongPositionBG();
        wrongPos.setText("W");
        pane.add(wrongPos);

        LetterBox noPos = new LetterBox(new Point(offsetLetterX,(int) (LETTER_DISTANCE_MARGIN * 3 + ACTION_SIZE.getHeight() * 2 + yOffset)), theme);
        noPos.setNoPositionBG();
        noPos.setText("N");
        pane.add(noPos);




        ActionBox menu = new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionShowMenu(theme), this);
        menu.setSize(getWidth() - LETTER_DISTANCE_MARGIN*2, (int) ACTION_SIZE.getHeight());
        pane.add(menu);

    }


}
