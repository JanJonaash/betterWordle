package Frames;

import ActionCommands.ActionBox;
import ActionCommands.ActionClearData;
import ActionCommands.ActionShowStats;
import com.company.ColorTheme;
import com.company.InfoLabel;

import java.awt.*;

import static com.company.Constants.*;

public class ClearDataFrame extends DefaultFrame{
    /**
     * Sets up the ClearDataFrame
     *
     * @param theme
     * @param size
     */
    public ClearDataFrame(ColorTheme theme, Dimension size) {
        super(theme, size);


        int bottomY = (int) (getHeight() - ACTION_SIZE.getHeight() - LETTER_DISTANCE_MARGIN);
        InfoLabel info = new InfoLabel("Are you sure?", new Point(0,0), theme);
        info.setSize(getWidth(), (int) INFO_SIZE.getHeight());


        ActionBox yes = new ActionBox(new Point(LETTER_DISTANCE_MARGIN,bottomY), theme, new ActionClearData(theme),this);
        ActionBox no = new ActionBox(new Point((int) (getWidth()-ACTION_SIZE.getWidth()-LETTER_DISTANCE_MARGIN), bottomY), theme, new ActionShowStats(theme),this);
        no.setText("No");


        pane.add(info);
        pane.add(no);
        pane.add(yes);


    }
}
