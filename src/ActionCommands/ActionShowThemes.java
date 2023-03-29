package ActionCommands;

import Frames.ThemeFrame;
import com.company.ColorTheme;

public class ActionShowThemes extends DefaultAction implements ActionCommand{
    public ActionShowThemes(ColorTheme theme) {
        super(theme);
    }


    @Override
    public void execute() {

        new ThemeFrame(theme);
    }

    @Override
    public String message() {
        return "Themes";
    }
}
