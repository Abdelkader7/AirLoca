package com.example.airloca;

import com.example.airloca.Entities.Favoris;
import com.example.airloca.Entities.Personne;

public class Session {

    private static Personne personneConnected;
    private static Favoris favoris;

    public static Personne getPersonneConnected() {
        if(personneConnected == null)
        {
            personneConnected = new Personne();
        }
        return personneConnected;
    }

    public static void setPersonneConnected(Personne personneConnected) {

        Session.personneConnected = personneConnected;
    }

    public static boolean IsConnected(){
        return personneConnected != null;
    }
}
