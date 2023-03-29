package com.company;

import java.awt.*;
import java.io.Serializable;

public record ColorTheme (
        Color primary,
        Color secondary,
        Color frameBackground,
        Color letterBackground,
        Color foreground,
        Color wrongPositionBackground,
        Color noPositionBackground,
        Color correctPositionBackground) implements Serializable{
}
