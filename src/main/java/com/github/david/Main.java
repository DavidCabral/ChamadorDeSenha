package com.github.david;

import javazoom.jl.decoder.JavaLayerException;

public class Main {

    public static void main(String[] args) {
        try {
            new Chamar().senha("P0004", "05");
            new Chamar().senha(Alerta.DING_DONG, Voz.MASCULINO, "P0004", "05");
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        //new Chamar().senha(Alerta.DING_DONG, Voz.MASCULINO, "P0004", "05");
    }

}
