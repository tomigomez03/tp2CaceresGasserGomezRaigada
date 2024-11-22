package com.tuti.desi.enums;

public enum Calzada {
    TIERRA("Tierra"),
    RIPIO("Ripio"),
    ASFALTO("Asfalto");
  

    private final String displayName;

    Calzada(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}