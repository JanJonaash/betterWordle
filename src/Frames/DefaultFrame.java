package Frames;

import com.company.ColorTheme;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public abstract class DefaultFrame extends JFrame {
  protected   JLayeredPane pane;
    protected ColorTheme theme;




    // sets up the DefaultFrame with a layeredPane, clears the static GameFrame noPositionLetters variable
    public DefaultFrame(ColorTheme theme, Dimension size){
        this.theme = theme;
        pane = new JLayeredPane();

        this.setSize(size);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocation(0, 0);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        this.setContentPane(pane);
        pane.setSize(getSize());
        pane.setLocation(0, 0);
        pane.setBorder(new MatteBorder(4, 4, 4, 4, theme.secondary()));
        pane.setBackground(theme.frameBackground());
        pane.setOpaque(true);

        GameFrame.noPositionLetters = " ";
    }

}
