package com.example.evchargingstationapi.enums;

public enum PowerLevel {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String label;

    PowerLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

