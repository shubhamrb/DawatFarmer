package com.dawat.farmer.mamits.model;

public class Tabs {
    String name;
    int icon;

    public Tabs() {
    }

    public Tabs(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
