package com.example.arranque1.appsisteme;

/**
 * Created by Arranque 1 on 24/05/2016.
 */
public class Session {
    private static Session sharedInstance = null;


    public static Session getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Session();
        }
        return sharedInstance;
    }

    public String uIdVigilado;
    public String uIdVigilante;

    public String getuIdVigilado() {
        return uIdVigilado;
    }

    public void setuIdVigilado(String uIdVigilado) {
        this.uIdVigilado = uIdVigilado;
    }

    public String getuIdVigilante() {
        return uIdVigilante;
    }

    public void setuIdVigilante(String uIdVigilante) {
        this.uIdVigilante = uIdVigilante;
    }

    public UserType uType;

    public UserType getuType() {
        return uType;
    }

    public void setuType(UserType uType) {
        this.uType = uType;
    }
}

