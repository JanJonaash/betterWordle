package Frames;

import com.company.Attempt;
import com.company.Constants;
import com.company.SavedData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatsFrameTest {





    @Test
    void successRateTest_onlyThemeChanges() {
        SavedData.createAttemptList();
        StatsFrame frame = new StatsFrame(Constants.DEFAULT_COLOR_THEME);

        SavedData.addAttempt(new Attempt(Constants.CHANGE_THEME_CODE, false, frame.theme));
        SavedData.addAttempt(new Attempt(Constants.CHANGE_THEME_CODE, false, frame.theme));


        assertEquals(0, frame.calculateSuccessRate());


    }


    @Test
    void successRateTest_emptyList() {
        SavedData.createAttemptList();
        StatsFrame frame = new StatsFrame(Constants.DEFAULT_COLOR_THEME);


        assertEquals(0, frame.calculateSuccessRate());


    }

    @Test
    void successRateTest_onlyWins() {
        SavedData.createAttemptList();
        StatsFrame frame = new StatsFrame(Constants.DEFAULT_COLOR_THEME);

        SavedData.addAttempt(new Attempt(1, true, frame.theme));
        SavedData.addAttempt(new Attempt(1, true, frame.theme));
        SavedData.addAttempt(new Attempt(1, true, frame.theme));
        SavedData.addAttempt(new Attempt(1, true, frame.theme));
        SavedData.addAttempt(new Attempt(1, true, frame.theme));
        SavedData.addAttempt(new Attempt(1, true, frame.theme));


        assertEquals(100, frame.calculateSuccessRate());


    }

    @Test
    void successRateTest_realScenario() {
        SavedData.createAttemptList();
        StatsFrame frame = new StatsFrame(Constants.DEFAULT_COLOR_THEME);

        SavedData.addAttempt(new Attempt(1, true, frame.theme));
        SavedData.addAttempt(new Attempt(1, false, frame.theme));
        SavedData.addAttempt(new Attempt(1, false, frame.theme));
        SavedData.addAttempt(new Attempt(Constants.CHANGE_THEME_CODE, true, frame.theme));
        SavedData.addAttempt(new Attempt(Constants.CHANGE_THEME_CODE, true, frame.theme));
        SavedData.addAttempt(new Attempt(Constants.CHANGE_THEME_CODE, true, frame.theme));


        assertEquals(33, frame.calculateSuccessRate());


    }

}