package de.universa.blackjack.source;

import java.util.Scanner;

public class GameBase {

    private final double maxSpieleinsatz = 2500;
    private final double minSpieleinsatz = 1;
    private final int maxPlayers = 1;
    private final double maxSpieleinsatzproBox = maxSpieleinsatz*maxPlayers-500;
    private double spieleinsaetzeGelegt = 0;

    private final Spieler[] spielerArray = new Spieler[maxPlayers];
    private final Spieler croupier = new Spieler(0, "Croupier");

    private final Spielregeln regeln = new Spielregeln();
    private final Scanner scan = new Scanner(System.in);
    private final Karten karten = new Karten();

    public GameBase(){

    }

    public void start(){
        eingabeSpielerInfos();
        starteSpiel();
    }

    private void starteSpiel(){
        do{
            spieleRunden();
        }while(weiterspielen());
    }

    private void spieleRunden(){
        do{
            spieleinsaetzesetzen();
            spielRunde();
        }while (!habenAlleSpielerVerloren());
    }

    private void spielRunde(){
        int anzahlrunden;
        getKarten().setzeKartenzurueck(getSpielerArray(), getCroupier());
        getCroupier().resetSpielerErgebnisse(getSpielerArray());
        erstenZweiKartenRunden();
        anzahlrunden = 2;
        spielRundeSpieler(anzahlrunden);
        spielRundeCroupier();
        ermittleErgebnis();
        zeigeGewinnerAn();
    }

    private boolean weiterspielen(){
        boolean eingabeKorrekt = true;
        String eingabe;
        do{
            Helper.cleanBildschrim();
            if(!eingabeKorrekt){
                System.out.println("Es ist nur \"J\" oder \"N\" erlaubt!");
            }
            System.out.println("Möchten Sie weiterspielen?[j/n]:");
            eingabe = scan.next();
            eingabeKorrekt = eingabe.equalsIgnoreCase("j") || eingabe.equalsIgnoreCase("n");
        }while(!eingabeKorrekt);
        return eingabe.equalsIgnoreCase("j");
    }

    private void spielRundeSpieler(int anzahlrunden){
        for(Spieler spieler : getSpielerArray()){
            boolean spielzugBeendet = false;
            int anzahlrundeSpieler = anzahlrunden;
            do{
                if(getRegeln().isAllowToPullACardPlayer(spieler, getKarten())){
                    if(entscheideKarteZiehen(spieler)){
                        anzahlrundeSpieler++;
                        teileKarteaus(spieler, anzahlrundeSpieler);
                    }else{
                        spielzugBeendet = true;
                    }
                }else{
                    if(getKarten().errechneKartenWert(spieler.getErhalteneKarten()) == 21){
                        if(anzahlrundeSpieler == 2){
                            blackJack(spieler);
                        }else{
                            alreadyWon(spieler);
                        }
                    }else {
                        getKarten().gebeKartenaus(spieler);
                        gebeKartenWertaus(spieler);
                        System.out.println("Du bist leider ausgeschieden!");
                    }
                    Helper.warteAufEingabe();
                    spieler.setAusgeschieden(true);
                    spielzugBeendet = true;
                }
            }while(!spielzugBeendet);
        }
    }

    private void spielRundeCroupier(){
        boolean spielzugBeendet = false;
        int anzahlrundeSpieler = 2;
        teileKarteaus(getCroupier(), anzahlrundeSpieler);
        do{
            anzahlrundeSpieler++;
            if(getRegeln().isAllowToPullACardPlayer(getCroupier(), getKarten())){
                if(getRegeln().isKartenWertUnter17(getCroupier().getErhalteneKarten(), getKarten()) && anzahlrundeSpieler == 3){
                    teileKarteaus(getCroupier(), anzahlrundeSpieler);
                }else if(!getRegeln().isKartenWertUnter17(getCroupier().getErhalteneKarten(), getKarten()) && anzahlrundeSpieler == 3){
                    spielzugBeendet = true;
                }else{
                    if(entscheideKarteZiehen(getCroupier())){
                        teileKarteaus(getCroupier(), anzahlrundeSpieler);
                    }else{
                        spielzugBeendet = true;
                    }
                }
            }else{
                spielzugBeendet = true;
            }
        }while(!spielzugBeendet);
    }

    private void zeigeGewinnerAn(){
        int anzahlGewinner = 0;
        Helper.cleanBildschrim();
        System.out.println("Der Croupier hatte einen Kartenwert von: " + getKarten().errechneKartenWertCroupier(getCroupier().getErhalteneKarten()));
        System.out.println("Gewonnen hat:");
        for(Spieler spieler : spielerArray){
            if(spieler.hasGewonnen()){
                anzahlGewinner++;
                System.out.println(spieler.getName() + " mit einem Kartenwert von " + getKarten().errechneKartenWert(spieler.getErhalteneKarten()));
            }
        }
        if (anzahlGewinner == 0){
            System.out.println("niemand!");
        }
        Helper.warteAufEingabe();
    }

    private void ermittleErgebnis(){
        for(Spieler spieler : getSpielerArray()){
            if(!spieler.isAusgeschieden()){
                if(getKarten().errechneKartenWertCroupier(getCroupier().getErhalteneKarten()) > 21){
                    alreadyWon(spieler);
                }else if(getKarten().errechneKartenWertCroupier(getCroupier().getErhalteneKarten()) < getKarten().errechneKartenWert(spieler.getErhalteneKarten())){
                    alreadyWon(spieler);
                }
            }
        }
    }

    private void teileKarteaus(Spieler spieler, int anzahlRunde){
        Karte[] kartenerhalten = new Karte[anzahlRunde];
        int zaehler = 0;
        if(anzahlRunde != 1) {
            for (Karte karte : spieler.getErhalteneKarten()) {
                    kartenerhalten[zaehler] = karte;
                    zaehler++;
            }
        }
        if(getKarten().getKartenDeck().isEmpty()){
            getKarten().erstellekartenDeck();
        }
        kartenerhalten[zaehler] = getKarten().getNextKarte();
        spieler.setErhalteneKarten(kartenerhalten);
    }

    private void erstenZweiKartenRunden(){
        for(int i = 0; i < 2; i++){
            for(Spieler spieler : getSpielerArray()){
                teileKarteaus(spieler, i+1);
            }
        }
        teileKarteaus(getCroupier(), 1);
    }

    private boolean habenAlleSpielerVerloren(){
        boolean habenAlleSpielerVerloren = true;
        for(Spieler spieler : getSpielerArray()){
            if(spieler.getKontostand() > 0 && habenAlleSpielerVerloren){
                habenAlleSpielerVerloren = false;
            }
        }
        return habenAlleSpielerVerloren;
    }

    private void blackJack(Spieler spieler){
        alreadyWon(spieler);
        System.out.println("Winner Winner Chicken Dinner!");
    }
    private void alreadyWon(Spieler spieler){
        spieler.setKontostand(spieler.getKontostand() + spieler.getGestezterWert());
        spieler.setGewinn(spieler.getGewinn() + spieler.getGestezterWert());
        spieler.setGewonnen(true);
        getKarten().gebeKartenaus(spieler);
        gebeKartenWertaus(spieler);
    }

    private void gebeKartenWertaus(Spieler spieler){
        System.out.println("Dein Kartenwert beträgt: " + getKarten().errechneKartenWert(spieler.getErhalteneKarten()));
    }

    //Eingaben
    private boolean entscheideKarteZiehen(Spieler spieler){
        boolean eingabeKorrekt = true;
        String eingabe;
        do{
            if(!eingabeKorrekt){
                System.out.println("Es ist nur \"H\" oder \"S\" erlaubt!");
            }
            if(!spieler.equals(getCroupier())) {
                getKarten().gebeKartenaus(getCroupier());
            }
            getKarten().gebeKartenaus(spieler);
            gebeKartenWertaus(spieler);
            System.out.println();
            System.out.println("Möchtest du noch einmal ziehen[h] oder es sein lassen[s]:");
            eingabe = scan.next();
            eingabeKorrekt = eingabe.equalsIgnoreCase("h") || eingabe.equalsIgnoreCase("s");

        }while(!eingabeKorrekt);
        return eingabe.equalsIgnoreCase("h");
    }

    private void spieleinsaetzesetzen(){
        for(Spieler spieler : getSpielerArray()){
            boolean eingabeKorrekt;
            double zusetzenderWert;
            Helper.cleanBildschrim();
            do{
                System.out.println(spieler.getName() + " bitte gebe den Betrag ein, welchen du setzen möchtest: (Kontostand " + spieler.getKontostand() + "€)");
                zusetzenderWert = scan.nextDouble();
                eingabeKorrekt = getRegeln().isWunschBetragGueltig(zusetzenderWert, getSpieleinsaetzeGelegt(), getMaxSpieleinsatzproBox(), getMaxSpieleinsatz(), getMinSpieleinsatz(), spieler.getKontostand());
            }while(!eingabeKorrekt);
            setSpieleinsaetzeGelegt(getSpieleinsaetzeGelegt() + zusetzenderWert);
            spieler.setGestezterWert(zusetzenderWert);
            spieler.setKontostand(spieler.getKontostand() - spieler.getGestezterWert());
            Helper.cleanBildschrim();
        }
    }

    private void eingabeSpielerInfos(){
        for(int i = 0; i < getMaxPlayers(); i++){
            String name;
            Helper.cleanBildschrim();
            System.out.println("Spieler " + (i+1) + " bitte gebe deinen Namen ein:");
            name = scan.next();
            getSpielerArray()[i] = new Spieler(eingabeKontostand(), name);
        }
    }

    private double eingabeKontostand(){
        boolean eingabeKorrekt = true;
        double kontostand;
        do{
            if(!eingabeKorrekt){
                Helper.cleanBildschrim();
                System.out.println("Dein eingebener Betrag ist zu niedrig zum spielen!");
            }
            System.out.println("Bitte gebe dein Betrag ein, mit welchem du spielen möchtest:");
            kontostand = scan.nextDouble();
            eingabeKorrekt = !(kontostand < 1);
        }while(!eingabeKorrekt);
        return kontostand;
    }

    //Getter und Setter
    private int getKarteID(){
        return (int) ((Math.random() * 12) + 1);
    }
    public double getMaxSpieleinsatzproBox() {
        return maxSpieleinsatzproBox;
    }
    public double getMaxSpieleinsatz() {
        return maxSpieleinsatz;
    }
    public double getMinSpieleinsatz() {
        return minSpieleinsatz;
    }
    public double getSpieleinsaetzeGelegt() {
        return spieleinsaetzeGelegt;
    }
    public void setSpieleinsaetzeGelegt(double spieleinsaetzeGelegt) { this.spieleinsaetzeGelegt = spieleinsaetzeGelegt; }
    public int getMaxPlayers() {
        return maxPlayers;
    }
    public Spieler[] getSpielerArray() {
        return spielerArray;
    }
    public Spieler getCroupier() {
        return croupier;
    }
    public Karten getKarten() {
        return karten;
    }
    public Spielregeln getRegeln(){
        return regeln;
    }
}