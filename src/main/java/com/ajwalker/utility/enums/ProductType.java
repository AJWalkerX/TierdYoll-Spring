package com.ajwalker.utility.enums;

public enum ProductType {
    // Women Clothing
    WOMEN_DRESS("Dress"),
    WOMEN_SHIRT("Shirt"),

    // Women's Shoes
    WOMEN_SHOES_HEELS("Heeled Shoes"),
    WOMEN_SHOES_SNEAKER("Sneaker"),

    // Men's Clothing
    MEN_TSORT("T-shirt"),
    MEN_SHIRT("Shirt"),

    // Men's Shoes
    MEN_SNEAKERS_SPOR("Sneakers"),
    MEN_SHOES_DAYWEAR("Casual Shoes");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
