package de.universa.blackjack.source;

public class Karten {

    private final Karte[] karten = {new Karte(2, "2"), new Karte(3, "3"), new Karte(4, "4"),
                                new Karte(5, "5"), new Karte(6, "6"), new Karte(7, "7"),
                                new Karte(8, "8"), new Karte(9, "9"), new Karte(10, "10"),
                                new Karte(10, "Bube"), new Karte(10, "Dame"), new Karte(10, "König"),
                                new Karte(11, "Ass")};

    public Karten(){ }

    public void gebeKartenaus(Spieler spieler){
        System.out.print(spieler.getName() + " hat folgende Karten: ");
       for(int kartenIndex : spieler.getErhalteneKarten()){
           System.out.print(getKarteByIndex(kartenIndex).getName() + " ");
       }
        System.out.println();
    }

    public void setzeKartenzurueck(Spieler[] spielerarray, Spieler croupier){
        for(Spieler spieler : spielerarray){
            spieler.setErhalteneKarten(new int[0]);
        }
        croupier.setErhalteneKarten(new int[0]);
    }

    public int errechneKartenWert(int[] gezogeneKartenIndex){
        int kartenWert = 0;
        int merkeWert;
        int assgemerkt = 0;
        for(int index : gezogeneKartenIndex){
            merkeWert = getKarteByIndex(index).getWert();
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

    public int errechneKartenWertCroupier(int[] gezogeneKartenIndex){
        int kartenWert = 0;
        int merkeWert;
        int assgemerkt = 0;
        for(int index : gezogeneKartenIndex){
            merkeWert = getKarteByIndex(index).getWert();
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

    private Karte getKarteByIndex(int index){ return getKarten()[index]; }
    private Karte[] getKarten(){ return karten; }
}


class Karte{
    private final String name;
    private final int wert;

    public Karte( int wert, String name) {
        this.name = name;
        this.wert = wert;
    }

    public String getName() { return name; }
    public int getWert() { return wert; }
}
