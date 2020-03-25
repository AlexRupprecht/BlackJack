package de.universa.blackjack.source;

public class Spielregeln {

    public boolean isWunschBetragGueltig(double wunschBetrag, double bereitsGesetzterWert, double maxSpieleinsatzproBox, double maxSpielEinsatzproSpieler, double minSpielEinsatzproSpieler, double spielerKontostand){
        return !ueberpruefeMaxSpieleinsatzProBoxUeberschrittenUndGebeAus(bereitsGesetzterWert, wunschBetrag, maxSpieleinsatzproBox) && !ueberpruefeMaxSpieleinsatzProSpielerUeberschrittenUndGebeAus(wunschBetrag, maxSpielEinsatzproSpieler) &&
                !ueberpruefenMinSpieleinsatzProSpielerUnterschrittenUndGebeAus(wunschBetrag, minSpielEinsatzproSpieler) && !ueberpruefeKontoStandUeberschrittenUndGebeAus(wunschBetrag, spielerKontostand);
    }

    public boolean ueberpruefeMaxSpieleinsatzProSpielerUeberschrittenUndGebeAus(double neuGestezterWert, double maxSpielEinsatzproSpieler){
        if(neuGestezterWert > maxSpielEinsatzproSpieler){
            Helper.cleanBildschrim();
            System.out.println("Der Wunschbetrag überschreitet den zulässigen Maxialeinsatz pro Spieler!");
            return true;
        }
        return false;
    }

    public boolean ueberpruefenMinSpieleinsatzProSpielerUnterschrittenUndGebeAus(double wunschBetrag, double minSpielEinsatzproSpieler){
        if(wunschBetrag < minSpielEinsatzproSpieler){
            Helper.cleanBildschrim();
            System.out.println("Der Wunschbetrag unterschreitet den zulässigen Mindesteinsatz pro Spieler!");
            return true;
        }
        return false;
    }

    public boolean ueberpruefeMaxSpieleinsatzProBoxUeberschrittenUndGebeAus(double bereitsGesetzterWert, double neuGestezterWert, double maxSetztbarerWert){
        if(maxSetztbarerWert < bereitsGesetzterWert + neuGestezterWert) {
            Helper.cleanBildschrim();
            System.out.println("Der Wunschbetrag überschreitet den zulässigen Gesamteinsatz pro Box!");
            return true;
        }
        return false;
    }

    public boolean ueberpruefeKontoStandUeberschrittenUndGebeAus(double neuGestezterWert, double spielerKontostand){
        if(neuGestezterWert > spielerKontostand){
            Helper.cleanBildschrim();
            System.out.println("Der Wunschbetrag überschreitet deinen Kontostand!");
            return true;
        }
        return false;
    }

    public boolean isAllowToPullACardPlayer(Spieler spieler, Karten karten){
        return isKartenWertUnter21(spieler.getErhalteneKarten(), karten);
    }

    public boolean isKartenWertUnter21(int[] gezogeneKarten, Karten karten){
        return karten.errechneKartenWert(gezogeneKarten) < 21;
    }

    public boolean isKartenWertUnter17(int[] gezogeneKarten, Karten karten){
        return karten.errechneKartenWertCroupier(gezogeneKarten) < 17;
    }
}
