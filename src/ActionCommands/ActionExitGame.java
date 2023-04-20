package ActionCommands;

import com.company.ColorTheme;


public class ActionExitGame extends DefaultAction implements ActionCommand {


    public ActionExitGame(ColorTheme theme) {
        super(theme);
    }

    /**
     * exits the application
     */
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String message() {
        return "Quit";
    }

}
