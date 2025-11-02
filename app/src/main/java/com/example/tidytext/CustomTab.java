package com.example.tidytext;

public class CustomTab {
    private String name;
    private String rule;
    private boolean isDefault;

    public CustomTab(String name, String rule, boolean isDefault) {
        this.name = name;
        this.rule = rule;
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public String getRule() {
        return rule;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
