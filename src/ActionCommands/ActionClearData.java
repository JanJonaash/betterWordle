package ActionCommands;

import Frames.StatsFrame;
import com.company.ColorTheme;
import com.company.SavedData;

public class ActionClearData extends DefaultAction implements ActionCommand {
    public ActionClearData(ColorTheme theme) {
        super(theme);
    }


    /**
     * Shows a JOptionPane asking the user to confirm the data deletion.
     * <p>
     * If yes is chosen, creates an empty attemptList, which is then saved to the ser file.
     * <p>
     * Creates a new StatsFrame.
     */
    @Override
    public void execute() {


        SavedData.createAttemptList();
        SavedData.writeToFile();

        new StatsFrame(theme);


    }

    @Override
    public String message() {
        return "Yes";
    }
}
