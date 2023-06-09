package ActionCommands;

import Frames.RulesFrame;
import com.company.ColorTheme;

import javax.swing.*;

public class ActionShowMoreRules extends DefaultAction implements ActionCommand {
    public ActionShowMoreRules(ColorTheme theme) {
        super(theme);
    }


    /**
     * Displays a JOptionPane showing additional rules.
     * <p>
     * Displays a new RulesFrame.
     */
    @Override
    public void execute() {
        JOptionPane.showMessageDialog(null, "If no letters are highlighted or you don't continue to next row, the word is not contained in the collection. \n" +
                "\nPressing ESC quits to main menu, this will result in a failed attempt.", "Additional rules", JOptionPane.INFORMATION_MESSAGE);

        new RulesFrame(theme);
    }

    @Override
    public String message() {
        return "More";
    }
}
