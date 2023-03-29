package ActionCommands;

import Frames.MenuFrame;
import com.company.ColorTheme;

public class ActionShowMenu extends DefaultAction implements ActionCommand{
    public ActionShowMenu(ColorTheme theme) {
        super(theme);
    }

    @Override
    public void execute() {
        new MenuFrame(theme);
    }

    @Override
    public String message() {
        return "Menu";
    }
}
