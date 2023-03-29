package ActionCommands;

import Frames.GameFrame;
import com.company.ColorTheme;

public class ActionRestartGame extends DefaultAction implements ActionCommand{


    public ActionRestartGame(ColorTheme theme) {
        super(theme);
    }

    @Override
    public void execute() {
        new GameFrame(theme);
    }

    @Override
    public String message() {
        return "Restart";
    }

}
