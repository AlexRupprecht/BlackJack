package de.universa.blackjack.source;

public class Spieler {

    private double kontostand;
    private double gewinn = 0;
    private final String name;
    private Karte[] erhalteneKarten;
    private double gestezterWert;
    private boolean ausgeschieden = false;
    private boolean gewonnen = false;
    private boolean verdoppelt = false;

    public Spieler(double kontostand, String name){
        this.kontostand = kontostand;
        this.name = name;
    }

    public void resetSpielerErgebnisse(Spieler[] spielerArray){
        for(Spieler spieler : spielerArray){
            spieler.setAusgeschieden(false);
            spieler.setGewonnen(false);
            spieler.setVerdoppelt(false);
        }
    }

    public double getKontostand() {
        return kontostand;
    }
    public Karte[] getErhalteneKarten() { return erhalteneKarten; }
    public void setErhalteneKarten(Karte[] erhalteneKarten) { this.erhalteneKarten = erhalteneKarten; }
    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }
    public double getGewinn() {
        return gewinn;
    }
    public void setGewinn(double gewinn) {
        this.gewinn = gewinn;
    }
    public boolean isAusgeschieden() { return ausgeschieden;  }
    public boolean hasGewonnen() { return gewonnen; }
    public void setGewonnen(boolean gewonnen) { this.gewonnen = gewonnen; }
    public void setAusgeschieden(boolean ausgeschieden) { this.ausgeschieden = ausgeschieden;  }
    public String getName() {
        return name;
    }
    public double getGestezterWert() {
        return gestezterWert;
    }
    public void setGestezterWert(double gestezterWert) {
        this.gestezterWert = gestezterWert;
    }
    public void setVerdoppelt (boolean verdoppelt){ this.verdoppelt = verdoppelt; }
    public boolean hasVerdoppelt() { return verdoppelt; }
}
