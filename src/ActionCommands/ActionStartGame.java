package ActionCommands;

import Frames.GameFrame;
import com.company.ColorTheme;

public class ActionStartGame extends DefaultAction implements ActionCommand{


    public ActionStartGame(ColorTheme theme) {
        super(theme);
    }



    @Override
    public void execute() {
        new GameFrame(theme);
    }

    @Override
    public String message() {
        return "Start";
    }

}
