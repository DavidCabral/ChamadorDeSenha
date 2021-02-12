package com.github.david;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Chamar {

    private void playList(List<Player> list) throws JavaLayerException {
        for (Player p : list) {
            p.play();
        }
    }

    private void play(String... files) throws JavaLayerException {
        final List<Player> list = new ArrayList<>();
        for (String f : files) {
            list.add(createPlayer(f));
        }
        playList(list);
    }

    public void senha(Alerta alerta, Voz voz, String senha, String guiche) throws JavaLayerException {
        List<String> list = new ArrayList<>();
        //adiciona o alerta
        list.add(alerta.getPath());

        //adiciona a senha
        list.add(getCaminho(voz, "senha"));

        //para chamar senha
        String[] arr = senha.split("");
        for (String letra : arr) {
            list.add(getCaminho(voz, letra));
        }
        //adiciona o guiche
        list.add(getCaminho(voz, "guiche"));

        //para chamar guiche
        String[] g = guiche.split("");
        for (String letra : g) {
            list.add(getCaminho(voz, letra));
        }

        play(list.toArray(new String[0]));

    }

    public void senha(String senha, String guiche) throws JavaLayerException {
        senha(Alerta.AIRPORT, Voz.FEMININO, senha, guiche);
    }

    private String getCaminho(Voz voz, String v) {
        return voz.getPath() + v.toLowerCase();
    }

    private Player createPlayer(String f) throws JavaLayerException {
        return new Player(getClass().getResourceAsStream("/media/" + f + ".mp3"));
    }


}
