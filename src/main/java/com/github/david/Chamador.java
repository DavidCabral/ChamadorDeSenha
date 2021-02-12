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

    private void play(List<String> files) throws JavaLayerException {
        for (String f : files) {
            createPlayer(f).play();
        }
    }

    public void chamar() {

        if (senha == null || senha.isEmpty()) {
            throw new RuntimeException("A Senha não foi informada!");
        }

        List<String> list = new ArrayList<>();
        if (alerta != null) {
            //adiciona o alerta
            list.add(alerta.getPath());
        }

        //adiciona a senha
        list.add(getCaminho("senha"));

        //para chamar senha
        criarListaLetra(list, senha);

        if (guiche != null && !guiche.isEmpty()) {
            //adiciona o guiche
            list.add(getCaminho("guiche"));
            //para chamar guiche
            criarListaLetra(list, guiche);
        }
        try {
            play(list);
        } catch (JavaLayerException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    private void criarListaLetra(List<String> list, String senha) {
        String[] arr = senha.split("");
        for (String letra : arr) {
            list.add(getCaminho(letra));
        }
    }

    private String getCaminho(String v) {
        return voz.getPath() + v.toLowerCase();
    }

    private Player createPlayer(String f) throws JavaLayerException {
        return new Player(getClass().getResourceAsStream("/media/" + f + ".mp3"));
    }

}
