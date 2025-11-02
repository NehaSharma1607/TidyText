package com.example.tidytext;

import java.io.Serializable;
import java.util.Objects;

public class Tab implements Serializable {
    private String name;
    private String rules; // Comma-separated rules (keywords)

    public Tab(String name, String rules) {
        this.name = name;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tab tab = (Tab) o;
        return Objects.equals(name, tab.name) && Objects.equals(rules, tab.rules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rules);
    }
}
