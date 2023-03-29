package ActionCommands;

import Frames.StatsFrame;
import com.company.ColorTheme;
import com.company.SavedData;

import javax.swing.*;

public class ActionClearData extends DefaultAction implements ActionCommand{
    public ActionClearData(ColorTheme theme) {
        super(theme);
    }

    @Override
    public void execute() {


       int choice =  JOptionPane.showConfirmDialog(null, "Are you sure?","Permanent data deletion", JOptionPane.YES_NO_OPTION);


        if (choice == JOptionPane.YES_OPTION) {
            SavedData.createAttemptList();
            SavedData.writeToFile();
        }
        new StatsFrame(theme);

    }

    @Override
    public String message() {
        return "Clear";
    }
}
