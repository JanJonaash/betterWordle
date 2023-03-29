package Frames;

import ActionCommands.ActionBox;
import ActionCommands.ActionClearData;
import ActionCommands.ActionShowMenu;
import com.company.Attempt;
import com.company.ColorTheme;
import com.company.InfoLabel;
import com.company.SavedData;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static com.company.Constants.*;

public class StatsFrame extends DefaultFrame {

    final int infoCenterX = (int) ((getWidth() - INFO_SIZE.getWidth()) / 2);
    final int infoXLeft = (int) (infoCenterX - INFO_SIZE.getWidth() / 4);
    final int infoXRight = (int) (infoCenterX + INFO_SIZE.getWidth() / 4);


    int longestStreak = 0;
    int currentStreak = 0;


    /*
    sets up the StatsFrame, calculates all wins and amount of theme changes


    calls setupAttempts()

    sets up the title,success and streaks infoLabels based on constants

    calculates the success rate and rounds it to display in the success infoLabel

    calls calculateStreaks() and displays them on the streaks infoLabel

    adds a menu actionBox
     */

    public StatsFrame(ColorTheme theme) {
        super(theme, new Dimension(600, 700));


        double wins = 0;
        int amountOfThemeChanges = 0;

        for (Attempt a : SavedData.getAttemptList()) {
            wins = (a.completed() && a.attemptAmount() != CHANGE_THEME_CODE) ? wins + 1 : wins;
            amountOfThemeChanges = (a.attemptAmount() == CHANGE_THEME_CODE) ? amountOfThemeChanges + 1 : amountOfThemeChanges;

        }


        setupAttempts();

        InfoLabel title = new InfoLabel("Your statistics", new Point(infoCenterX, 0), theme);
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 50));
        pane.add(title);


        double successRate = (wins / (SavedData.getAttemptList().size() - amountOfThemeChanges)) * 100;


        InfoLabel success = new InfoLabel("General success rate: " + Math.round(successRate) + "%", new Point(infoCenterX, 400), theme);
        success.setFont(new Font(success.getFont().getFontName(), Font.ITALIC, 30));
        pane.add(success);


        calculateStreaks();

        InfoLabel streaks = new InfoLabel("Longest streak " + longestStreak + ", Current streak " + currentStreak, new Point(infoCenterX, success.getY() + success.getHeight() / 2), theme);
        streaks.setFont(new Font(success.getFont().getFontName(), Font.ITALIC, 20));
        pane.add(streaks);

        ActionBox menuBox = new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionShowMenu(theme), this);
        menuBox.setSize((int) ((getWidth()) / 2 - LETTER_DISTANCE_MARGIN * 1.5), (int) ACTION_SIZE.getHeight());


        pane.add(menuBox);


        ActionBox clearBox = new ActionBox(new Point(getWidth() - LETTER_DISTANCE_MARGIN - menuBox.getWidth(), menuBox.getY()), theme, new ActionClearData(theme), this);
        clearBox.setSize(menuBox.getSize());
        pane.add(clearBox);

    }


    /**
     * sets up the attempt info labels via a cycle, utilising AtomicIntegers due to stream API usage
     * before the cycle starts, an atomic integer array attemptCounterStats (short form ACS) of size 6 is created
     * <p>
     * <p>
     * the cycle starts at 1 and goes to < 7, with the iteration index i
     * <p>
     * at the start of the iteration, the value of ACS with the index i-1 is created as an empty atomic integer
     * <p>
     * then the attemptList is filtered for attempts with attempt amounts being the same as i, and being completed as well.
     * And for each of those attempts the value of ACS at the index i-1 is increased by 1.
     * <p>
     * <p>
     * <p>
     * <p>
     * the x position of the infoLabel is decided by the value of i, with 1-3 being the left side constant and the rest is right side.
     * <p>
     * the y position is also determined by i, with 1;4 being the top, 2;5 being the middle and 3;6 being the bottom
     * <p>
     * then the infoLabel is created utilising the x and y for location, the i value for labeling and the ACS i-1 value for data.
     * custom font is also set.
     */


    void setupAttempts() {
        AtomicInteger[] attemptCounterStats = new AtomicInteger[6];
        for (AtomicInteger i = new AtomicInteger(1); i.intValue() < attemptCounterStats.length + 1; i.getAndIncrement()) {

            attemptCounterStats[i.intValue() - 1] = new AtomicInteger();

            SavedData.getAttemptList().
                    stream().
                    filter(x -> x.attemptAmount() == i.intValue() && x.completed()).
                    forEach(x -> attemptCounterStats[i.intValue() - 1].getAndIncrement());


            int x = (i.intValue() == 1 || i.intValue() == 2 || i.intValue() == 3) ? infoXLeft : infoXRight;

            int y = switch (i.intValue()) {

                case 1, 4 -> (int) (INFO_SIZE.getHeight() * (0.75));
                case 2, 5 -> (int) (INFO_SIZE.getHeight() * (1.5));
                case 3, 6 -> (int) (INFO_SIZE.getHeight() * (2.25));


                default -> throw new IllegalStateException("Unexpected value: " + i.intValue());
            };
            InfoLabel attemptLabel = new InfoLabel(i.intValue() + " attempts: " + attemptCounterStats[i.intValue() - 1], new Point(x, y), theme);
            attemptLabel.setFont(new Font(attemptLabel.getFont().getFontName(), Font.ITALIC, 30));
            pane.add(attemptLabel);
        }


    }


    /**
     * calculates the longest streak based on the attemptList data set using a temp variable to store the current longest streak:
     * <p>
     * <p>
     * cycles through the collection
     * if the attempt has CHANGE_THEME_CODE, the iteration is skipped
     * if the attempt is successful, tempLongest is increased by 1
     * <p>
     * if the attempt is unsuccessful or the iteration is the last one:
     * <p>
     * check if the attempt is successful (last iteration scenario), if yes, then it stores the tempLongest + 1 into the allStreaks arrayList
     * <p>
     * proceeds to compare longest with tempLongest, and the larger one is stored into the longest variable.
     * tempLongest is set to 0.
     * <p>
     * <p>
     * the global longestStreak variable is set to longest
     * the global currentStreak variable is set to the last element of the allStreaks arrayList, if not possible, it is set to 0.
     */

    void calculateStreaks() {


        int longest = -1;
        int tempLongest = 0;


        ArrayList<Integer> allStreaks = new ArrayList<>();

        for (int i = 0; i < SavedData.getAttemptList().size(); i++) {

            Attempt at = SavedData.getAttemptList().get(i);

            if (at.attemptAmount() == CHANGE_THEME_CODE) {
                continue;
            }


            if (i == SavedData.getAttemptList().size() - 1 || !at.completed()) {


                if (at.completed()) {
                    allStreaks.add(tempLongest + 1);
                }


                longest = Math.max(tempLongest, longest);

                tempLongest = 0;


            } else {

                tempLongest++;

            }


        }

        this.longestStreak = (longest != -1) ? longest : 0;
        try {
            this.currentStreak = allStreaks.get(allStreaks.size() - 1);
        } catch (Exception e) {
            this.currentStreak = 0;
        }


    }


}
