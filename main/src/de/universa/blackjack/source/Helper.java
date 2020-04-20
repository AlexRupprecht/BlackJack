package de.universa.blackjack.source;

import java.io.IOException;

@SuppressWarnings("ALL")
public class Helper {

    public static void cleanBildschrim(){
        for(int i = 0; i < 70; i++)
            System.out.println();
    }

    public static void warteAufEingabe(){
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
