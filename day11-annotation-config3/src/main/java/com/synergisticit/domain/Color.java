package com.synergisticit.domain;

public class Color {
    private int red;
    private int blue;
    private int green;
    
    Color() {
    }
    
    Color(int red, int blue, int green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    @Override
    public String toString() {
        return "Color [red=" + red + ", blue=" + blue + ", green=" + green + "]";
    }
    
}
