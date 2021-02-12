package com.github.david;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Chamador {

    private Voz voz;
    private String senha;
    private String guiche;
    private Alerta alerta;

    private Chamador() {

    }

    public static Chamador novaChamada() {
        return (new Chamador()).comVoz(Voz.FEMININO).comAlerta(Alerta.AIRPORT);
    }

    public Chamador comVoz(Voz voz) {
        this.voz = voz;
        return this;
    }

    public Chamador comAlerta(Alerta alerta) {
        this.alerta = alerta;
        return this;
    }

    public Chamador comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Chamador comGuiche(String guiche) {
        this.guiche = guiche;
        return this;
    }

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

    public void chamar() {

        if (senha == null || senha.isEmpty()) {
            throw new RuntimeException("Informe a Senha");
        }


        List<String> list = new ArrayList<>();
        if (alerta != null) {
            //adiciona o alerta
            list.add(alerta.getPath());
        }

        //adiciona a senha
        list.add(getCaminho(voz, "senha"));

        //para chamar senha
        criarListaLetra(list, senha);


        if (guiche != null && !guiche.isEmpty()) {
            //adiciona o guiche
            list.add(getCaminho(voz, "guiche"));
            //para chamar guiche
            criarListaLetra(list, guiche);
        }

        try {
            play(list.toArray(new String[0]));
        } catch (JavaLayerException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    private void criarListaLetra(List<String> list, String senha) {
        String[] arr = senha.split("");
        for (String letra : arr) {
            list.add(getCaminho(voz, letra));
        }
    }


    private String getCaminho(Voz voz, String v) {
        return voz.getPath() + v.toLowerCase();
    }

    private Player createPlayer(String f) throws JavaLayerException {
        return new Player(getClass().getResourceAsStream("/media/" + f + ".mp3"));
    }


}
