package de.universa.blackjack.test;

import de.universa.blackjack.source.Karte;
import de.universa.blackjack.source.Karten;
import de.universa.blackjack.source.Spieler;
import de.universa.blackjack.source.Spielregeln;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpielregelnTest {
    Spielregeln regeln;
    @Before
    public void init(){
         regeln = new Spielregeln();
    }

    @Test
    public void isWunschBetragGueltigTest(){  Assert.assertTrue(regeln.isWunschBetragGueltig(150, 150,1000,999,5, 1500)); }

    @Test
    public void isNichtWunschBetragGueltigTest(){  Assert.assertFalse(regeln.isWunschBetragGueltig(150, 150,1000,999,998, 1500)); }

    @Test
    public void ueberpruefeMaxSpieleinsatzProSpielerUeberschrittenUndGebeAusTest(){ Assert.assertTrue(regeln.ueberpruefeMaxSpieleinsatzProSpielerUeberschrittenUndGebeAus(150, 100)); }

    @Test
    public void ueberpruefeMaxSpieleinsatzProSpielerNichtUeberschrittenUndGebeAusTest(){ Assert.assertFalse(regeln.ueberpruefeMaxSpieleinsatzProSpielerUeberschrittenUndGebeAus(100, 150)); }

    @Test
    public void ueberpruefenMinSpieleinsatzProSpielerUnterschrittenUndGebeAusTest(){ Assert.assertTrue(regeln.ueberpruefenMinSpieleinsatzProSpielerUnterschrittenUndGebeAus(15, 25)); }

    @Test
    public void ueberpruefenMinSpieleinsatzProSpielerNichtUnterschrittenUndGebeAusTest(){ Assert.assertFalse(regeln.ueberpruefenMinSpieleinsatzProSpielerUnterschrittenUndGebeAus(35, 25)); }

    @Test
    public void ueberpruefeMaxSpieleinsatzProBoxUeberschrittenUndGebeAusTest(){ Assert.assertTrue(regeln.ueberpruefeMaxSpieleinsatzProBoxUeberschrittenUndGebeAus(100, 50, 149)); }

    @Test
    public void ueberpruefeMaxSpieleinsatzProBoxNichtUeberschrittenUndGebeAusTest(){ Assert.assertFalse(regeln.ueberpruefeMaxSpieleinsatzProBoxUeberschrittenUndGebeAus(100, 50, 150)); }

    @Test
    public void ueberpruefeKontoStandUeberschrittenUndGebeAusTest(){ Assert.assertTrue(regeln.ueberpruefeKontoStandUeberschrittenUndGebeAus(150, 149));}

    @Test
    public void ueberpruefeKontoStandNichtUeberschrittenUndGebeAusTest(){ Assert.assertFalse(regeln.ueberpruefeKontoStandUeberschrittenUndGebeAus(149, 150));}

    @Test
    public void isAllowToPullACardPlayerTest(){
        Karte[] erhalteneKarten = {new Karte(5, "5"), new Karte(6, "6")};
        Spieler spieler = new Spieler(erhalteneKarten);
        Assert.assertTrue(regeln.isAllowToPullACardPlayer(spieler, new Karten()));
    }

    @Test
    public void isNotAllowToPullACardPlayerTest(){
        Karte[] erhalteneKarten = {new Karte(10, "10"), new Karte(9, "9"), new Karte(10, "10")};
        Spieler spieler = new Spieler(erhalteneKarten);
        Assert.assertFalse(regeln.isAllowToPullACardPlayer(spieler, new Karten()));
    }

    @Test
    public void isKartenWertUnter21Test(){
        Karte[] erhalteneKarten = {new Karte(7, "7"), new Karte(7, "7")};
        Karten karten = new Karten();
        Assert.assertTrue(regeln.isKartenWertUnter21(erhalteneKarten, karten));
    }

    @Test
    public void isNotKartenWertUnter21Test(){
        Karte[] erhalteneKarten = {new Karte(10, "Bube"), new Karte(9, "9"), new Karte(10, "Bube")};
        Karten karten = new Karten();
        Assert.assertFalse(regeln.isKartenWertUnter21(erhalteneKarten, karten));
    }

    @Test
    public void isKartenWertUnter17Test(){
        Karte[] erhalteneKarten = {new Karte(5, "5"), new Karte(6, "6")};
        Karten karten = new Karten();
        Assert.assertTrue(regeln.isKartenWertUnter17(erhalteneKarten, karten));
    }

    @Test
    public void isNotKartenWertUnter17Test(){
        Karte[] erhalteneKarten = {new Karte(9, "9"), new Karte(9, "9")};
        Karten karten = new Karten();
        Assert.assertFalse(regeln.isKartenWertUnter17(erhalteneKarten, karten));
    }

}
