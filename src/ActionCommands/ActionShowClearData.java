package ActionCommands;

import Frames.ClearDataFrame;
import com.company.ColorTheme;
import com.company.Constants.*;
import java.awt.*;

import static com.company.Constants.*;

public class ActionShowClearData extends DefaultAction implements ActionCommand{
    public ActionShowClearData(ColorTheme theme) {
        super(theme);
    }

    @Override
    public void execute() {

        new ClearDataFrame(theme, new Dimension((int) ACTION_SIZE.getWidth()*2 + LETTER_DISTANCE_MARGIN*3, (int) (INFO_SIZE.getHeight() + ACTION_SIZE.getHeight()+LETTER_DISTANCE_MARGIN)));
    }

    @Override
    public String message() {
        return "Clear";
    }
}
