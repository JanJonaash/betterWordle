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


    private int longestStreak = 0;
    private int currentStreak = 0;
    private double successRate;

    public double getSuccessRate() {
        return successRate;
    }

    /**
     * Sets up the StatsFrame.
     * <p>
     * Calls setupAttempts().
     * <p>
     * Calls calculateSuccessRate() and stores the result.
     * <p>
     * Calls calculateStreaks().
     * <p>
     * Adds title, success and streak InfoLabels.
     * <p>
     * Adds menu, clear ActionBoxes.
     * <p>
     */

    public StatsFrame(ColorTheme theme) {
        super(theme, new Dimension(600, 700));


        setupAttempts();
        successRate = calculateSuccessRate();
        calculateStreaks();


        InfoLabel title = new InfoLabel("Your statistics", new Point(infoCenterX, 0), theme);
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 50));


        InfoLabel success = new InfoLabel("General success rate: " + successRate + "%", new Point(infoCenterX, 400), theme);
        success.setFont(new Font(success.getFont().getFontName(), Font.ITALIC, 30));


        InfoLabel streaks = new InfoLabel("Longest streak " + longestStreak + ", Current streak " + currentStreak, new Point(infoCenterX, success.getY() + success.getHeight() / 2), theme);
        streaks.setFont(new Font(success.getFont().getFontName(), Font.ITALIC, 20));


        ActionBox menuBox = new ActionBox(new Point(LETTER_DISTANCE_MARGIN, (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN)), theme, new ActionShowMenu(theme), this);
        menuBox.setSize((int) ((getWidth()) / 2 - LETTER_DISTANCE_MARGIN * 1.5), (int) ACTION_SIZE.getHeight());


        ActionBox clearBox = new ActionBox(new Point(getWidth() - LETTER_DISTANCE_MARGIN - menuBox.getWidth(), menuBox.getY()), theme, new ActionClearData(theme), this);
        clearBox.setSize(menuBox.getSize());


        pane.add(title);
        pane.add(success);
        pane.add(streaks);

        pane.add(menuBox);
        pane.add(clearBox);


    }


    /**
     * Calculates amount of wins and theme changes from the attemptList.
     * <p>
     * Divides wins by the difference of the attemptList size and amount of theme changes.
     * <p>
     * Multiplies the result by 100.
     *
     * @return
     */
    public double calculateSuccessRate() {


        double wins = 0;
        int amountOfThemeChanges = 0;

        for (Attempt a : SavedData.getAttemptList()) {
            wins = (a.completed() && a.attemptAmount() != CHANGE_THEME_CODE) ? wins + 1 : wins;
            amountOfThemeChanges = (a.attemptAmount() == CHANGE_THEME_CODE) ? amountOfThemeChanges + 1 : amountOfThemeChanges;

        }

        return Math.round ((wins / (SavedData.getAttemptList().size() - amountOfThemeChanges)) * 100);
    }

    /**
     * Creates AtomicInteger array to store statistics.
     * <p>
     * Calculates all the 1-6 completed attempts in the attemptList, stores them in the array.
     * <p>
     * Sets up the attemptLabels based on the number they represent (position, text)
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
     * Calculates the longest streak based on the attemptList data set using a temp variable to store the current longest streak:
     * <p>
     * <p>
     * <p>
     * Cycles through the collection.
     * <p>
     * If the attempt has CHANGE_THEME_CODE, the iteration is skipped.
     * <p>
     * If the attempt is successful, tempLongest is increased by 1.
     * <p>
     * If the attempt is unsuccessful or the iteration is the last one:
     * <p>
     * - Checks if the attempt is successful (last iteration scenario), if yes:
     * <p>
     * -- Stores the tempLongest + 1 into the allStreaks arrayList.
     * <p>
     * -- Proceeds to compare longest with tempLongest+1, and the larger one is stored into the longest variable.
     * <p>
     * - if not:
     * <p>
     * -- Proceeds to compare longest with tempLongest, and the larger one is stored into the longest variable.
     * <p>
     * tempLongest is set to 0.
     * <p>
     * <p>
     * The global longestStreak is set to longest, if longest is not -1. If yes, It is set to 0.
     * <p>
     * The global currentStreak is set to the last element of the allStreaks arrayList, if not possible, it is set to 0.
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
                    longest = Math.max(tempLongest + 1, longest);
                } else {
                    longest = Math.max(tempLongest, longest);
                }


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
