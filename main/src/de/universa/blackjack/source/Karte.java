package de.universa.blackjack.source;

public class Karte {
    private final String name;
    private final int wert;

    public Karte( int wert, String name) {
        this.name = name;
        this.wert = wert;
    }

    public String getName() { return name; }
    public int getWert() { return wert; }
}
