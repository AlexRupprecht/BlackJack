package de.universa.blackjack.source;

public class Spieler {

    private double kontostand;
    private double gewinn = 0;
    private String name;
    private int[] erhalteneKarten;
    private double gestezterWert;
    private boolean ausgeschieden = false;
    private boolean gewonnen = false;

    public Spieler(double kontostand, String name){
        this.kontostand = kontostand;
        this.name = name;
    }

    public void resetSpielerErgebnisse(Spieler[] spielerArray){
        for(Spieler spieler : spielerArray){
            spieler.setAusgeschieden(false);
            spieler.setGewonnen(false);
        }
    }

    public Spieler(int[] erhalteneKarten){ this.erhalteneKarten = erhalteneKarten; }
    public double getKontostand() {
        return kontostand;
    }
    public int[] getErhalteneKarten() { return erhalteneKarten; }
    public void setErhalteneKarten(int[] erhalteneKarten) { this.erhalteneKarten = erhalteneKarten; }
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
}
