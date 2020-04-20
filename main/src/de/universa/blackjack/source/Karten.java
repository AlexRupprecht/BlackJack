package de.universa.blackjack.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Karten {

    private final Karte[] karten = {new Karte(2, "2"), new Karte(3, "3"), new Karte(4, "4"),
                                new Karte(5, "5"), new Karte(6, "6"), new Karte(7, "7"),
                                new Karte(8, "8"), new Karte(9, "9"), new Karte(10, "10"),
                                new Karte(10, "Bube"), new Karte(10, "Dame"), new Karte(10, "König"),
                                new Karte(11, "Ass")};
    private final List<Karte> kartenDeck = new ArrayList<>();

    private final Karte[] kartenTest = {new Karte(7, "7"), new Karte(7, "7")};
    private final Karte[] kartenTripleTest = {new Karte(7, "7"), new Karte(7, "7"), new Karte(7, "7")};

    public Karten(){ }

    public void gebeKartenaus(Spieler spieler){
        System.out.print(spieler.getName() + " hat folgende Karten: ");
       for(Karte karte : spieler.getErhalteneKarten()){
           System.out.print(karte.getName() + " ");
       }
        System.out.println();
    }

    public void erstellekartenDeck(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < getKarten().length; k++){
                    getKartenDeck().add(getKarten()[k]);
                }
            }
        }
        Collections.shuffle(getKartenDeck());
    }

    public void setzeKartenzurueck(Spieler[] spielerarray, Spieler croupier){
        for(Spieler spieler : spielerarray){
            spieler.setErhalteneKarten(new Karte[0]);
        }
        croupier.setErhalteneKarten(new Karte[0]);
    }

    public int errechneKartenWert(Karte[] gezogeneKartenIndex){
        int kartenWert = 0;
        int merkeWert;
        int assgemerkt = 0;
        for(Karte karte : gezogeneKartenIndex){
            merkeWert = karte.getWert();
            if(merkeWert == 11){
                assgemerkt++;
            }
            kartenWert = kartenWert + merkeWert;
        }
        if(assgemerkt > 0){
                if(kartenWert > 21){ kartenWert = kartenWert - 10; }
        }
        return kartenWert;
    }

    public int errechneKartenWertCroupier(Karte[] gezogeneKartenIndex){
        int kartenWert = 0;
        int merkeWert;
        int assgemerkt = 0;
        for(Karte karte : gezogeneKartenIndex){
            merkeWert = karte.getWert();
            if(merkeWert == 11){
                assgemerkt++;
            }
            kartenWert = kartenWert + merkeWert;
        }
        if(assgemerkt > 0){
            if(kartenWert > 21){
                    for(int i = 0; i < assgemerkt; i++) {
                        kartenWert = kartenWert - 10;
                    }
            }
        }
        return kartenWert;
    }

    public boolean isTripleSevenVorhanden(Karte[] gezogenKartenIndex){
        int counter = 0;
        for(Karte karte : gezogenKartenIndex){
            if(karte.getWert() == 7){
                counter++;
            }
        }
        return counter == 3;
    }


    public Karte getNextKarte(){
        Karte nextKarte = getKartenDeck().get(0);
        getKartenDeck().remove(0);
        return nextKarte;
    }
    public Karte[] getKarten(){ return karten; }
    public List<Karte> getKartenDeck() { return kartenDeck; }
    public Karte[] getKartenTest() { return kartenTest; }
    public Karte[] getKartenTripleTest() { return kartenTripleTest; }
}
class Karte {
    private final String name;
    private final int wert;

    public Karte( int wert, String name) {
        this.name = name;
        this.wert = wert;
    }

    public String getName() { return name; }
    public int getWert() { return wert; }
}