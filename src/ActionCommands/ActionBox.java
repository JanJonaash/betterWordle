package ActionCommands;

import com.company.ColorTheme;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.company.Constants.*;

public class ActionBox extends JLabel {


   private ColorTheme theme;
   private ActionCommand command;
    public ActionBox(Point location, ColorTheme theme, ActionCommand command, JFrame targetFrame){

        this.command = command;
        this.theme = theme;
        this.setSize(ACTION_SIZE);
        this.setLocation(location);

        this.setOpaque(true);
        this.setVisible(true);

        setDefaultBG();
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setForeground(theme.foreground());

        this.setText(command.message());

        setFont(LETTER_FONT);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);



        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                targetFrame.dispose();
                command.execute();
            }

            @Override
            public void mouseExited(MouseEvent e) {
               setDefaultBG();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               setHoverBG();
            }
        });






    }
    void setDefaultBG(){
        setBorder(new MatteBorder(5, 5, 5, 5, theme.secondary()));
        setBackground(theme.letterBackground());
    }
    void setHoverBG(){
        setBorder(new MatteBorder(5, 5, 5, 5, theme.letterBackground()));
        setBackground(theme.secondary());

    }
}
