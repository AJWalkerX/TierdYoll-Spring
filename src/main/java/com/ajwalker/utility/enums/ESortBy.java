package com.ajwalker.utility.enums;

public enum ESortBy {
    PRICE("price"),
    RATING("rating"),
    NAME("name"),
    CATEGORY("category");

    private final String field;

    ESortBy(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
