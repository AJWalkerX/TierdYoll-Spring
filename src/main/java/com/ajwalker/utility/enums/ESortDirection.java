package com.ajwalker.utility.enums;

public enum ESortDirection {
    ARTAN("ASC"),
    AZALAN("DESC");

    private final String direction;

    ESortDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
