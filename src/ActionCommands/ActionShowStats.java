package ActionCommands;

import Frames.StatsFrame;
import com.company.ColorTheme;

import java.awt.*;

public class ActionShowStats extends DefaultAction implements ActionCommand{
    public ActionShowStats(ColorTheme theme) {
        super(theme);
    }

    @Override
    public void execute() {

        new StatsFrame(theme);
    }

    @Override
    public String message() {
        return "Stats";
    }

}
