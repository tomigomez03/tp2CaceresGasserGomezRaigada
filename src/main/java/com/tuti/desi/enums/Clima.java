package com.tuti.desi.enums;

public enum Clima {
    OPTIMO("Optimo"),
    LLUVIA("Lluvia"),
    TORMENTA("Tormenta"),
    NIEVE_INTENSA("Nieve Intensa");

    private final String displayName;

    Clima(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}