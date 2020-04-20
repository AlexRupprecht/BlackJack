package de.universa.blackjack.test;

import de.universa.blackjack.source.Karten;
import de.universa.blackjack.source.Spieler;
import de.universa.blackjack.source.Spielregeln;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpielregelnTest {
    Spielregeln regeln;
    Karten karten;
    Spieler spieler;

    @Before
    public void init(){
         regeln = new Spielregeln();
         spieler = new Spieler(100, "Test");
         karten = new Karten();
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
        spieler.setErhalteneKarten(karten.getKartenTest());
        Assert.assertTrue(regeln.isAllowToPullACardPlayer(spieler, new Karten()));
    }

    @Test
    public void isNotAllowToPullACardPlayerTest(){
        spieler.setErhalteneKarten(karten.getKarten());
        Assert.assertFalse(regeln.isAllowToPullACardPlayer(spieler, new Karten()));
    }

    @Test
    public void isKartenWertUnter21Test(){
        spieler.setErhalteneKarten(karten.getKartenTest());
        Assert.assertTrue(regeln.isKartenWertUnter21(spieler.getErhalteneKarten(), karten));
    }

    @Test
    public void isNotKartenWertUnter21Test(){
        spieler.setErhalteneKarten(karten.getKarten());
        Assert.assertFalse(regeln.isKartenWertUnter21(spieler.getErhalteneKarten(), karten));
    }

    @Test
    public void isKartenWertUnter17Test(){
        spieler.setErhalteneKarten(karten.getKartenTest());
        Assert.assertTrue(regeln.isKartenWertUnter17(spieler.getErhalteneKarten(), karten));
    }

    @Test
    public void isNotKartenWertUnter17Test(){
        spieler.setErhalteneKarten(karten.getKarten());
        Assert.assertFalse(regeln.isKartenWertUnter17(spieler.getErhalteneKarten(), karten));
    }

    @Test
    public void isTripleSvenTest(){
        spieler.setErhalteneKarten(karten.getKartenTripleTest());
        Assert.assertTrue(regeln.isTripleSven(spieler.getErhalteneKarten(), karten));
    }

    @Test
    public void isNotTripleSvenTest(){
        spieler.setErhalteneKarten(karten.getKartenTest());
        Assert.assertFalse(regeln.isTripleSven(spieler.getErhalteneKarten(), karten));
    }

    @Test
    public void isAllowToDoppel(){ Assert.assertTrue(regeln.isAllowToDoppel(2)); }

    @Test
    public void isNotAllowToDoppel(){ Assert.assertFalse(regeln.isAllowToDoppel(3)); }

}
