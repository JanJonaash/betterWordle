package ActionCommands;

import Frames.StatsFrame;
import com.company.ColorTheme;

public class ActionShowStats extends DefaultAction implements ActionCommand{
    public ActionShowStats(ColorTheme theme) {
        super(theme);
    }

    /**
     * Displays a new StatsFrame.
     */
    @Override
    public void execute() {

        new StatsFrame(theme);
    }

    @Override
    public String message() {
        return "Stats";
    }

}
