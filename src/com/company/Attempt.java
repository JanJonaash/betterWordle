package com.company;

import java.io.Serializable;

public record Attempt(int attemptAmount, boolean completed, ColorTheme theme) implements Serializable {
}
