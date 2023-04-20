package ActionCommands;

import Frames.RulesFrame;
import com.company.ColorTheme;


public class ActionShowRules extends DefaultAction implements ActionCommand{



    public ActionShowRules(ColorTheme theme) {
        super(theme);

    }

    /**
     * Displays a new RulesFrame.
     */
    @Override
    public void execute() {
        new RulesFrame(theme);
    }

    @Override
    public String message() {
        return "Rules";
    }



}
