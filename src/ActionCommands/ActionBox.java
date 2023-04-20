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

    private final int borderWidth = 5;

    /**
     * Sets up the ActionBox based on location, preset size, visibility, cursor
     * <p>
     * Sets the text as the ActionCommand message.
     * <p>
     * Sets font as the LETTER_FONT.
     * <p>
     * Sets the foreground as the color theme foreground.
     * <p>
     * Calls setDefaultBG().
     * <p>
     * Sets vertical and horizontal text alignment as JLabel.CENTER.
     * <p>
     * Calls addMouseReactivity(targetFrame).
     *
     * @param location
     * @param theme
     * @param command
     * @param targetFrame
     */
    public ActionBox(Point location, ColorTheme theme, ActionCommand command, JFrame targetFrame) {

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


        addMouseReactivity(targetFrame);


    }


    /**
     * Adds a MouseListener to the ActionBox.
     * <p>
     * MousePressed - dispose the targetFrame, executes the ActionCommand.
     * <p>
     * MouseEntered - calls setHoverBG().
     * <p>
     * MouseExited - calls setDefaultBG().
     *
     * @param targetFrame to dispose
     */
    void addMouseReactivity(JFrame targetFrame) {


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


    /**
     * Sets the outline as the secondary color of the color theme, with borderWidth as the outline width all around
     * <p>
     * Sets the background as the letterBackground color of the color theme.
     */
   public void setDefaultBG() {
        setBorder(new MatteBorder(borderWidth, borderWidth, borderWidth, borderWidth, theme.secondary()));
        setBackground(theme.letterBackground());
    }

    /**
     * Sets the outline as the letterBackground color of the color theme, with borderWidth as the outline width all around
     * <p>
     * Sets the background as the secondary color of the color theme.
     */
    public void setHoverBG() {
        setBorder(new MatteBorder(borderWidth, borderWidth, borderWidth, borderWidth, theme.letterBackground()));
        setBackground(theme.secondary());

    }
}
