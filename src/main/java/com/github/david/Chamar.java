package com.github.david;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Chamar {

    private void playList(List<MediaPlayer> list) {
        MediaPlayer player = list.get(0);
        player.play();
        player.setOnError(() -> {
            System.out.println("Media error occurred: " + player.getError());
            com.sun.javafx.application.PlatformImpl.exit();
        });
        player.setOnEndOfMedia(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                list.remove(0);
                if (list.isEmpty()) {
                    com.sun.javafx.application.PlatformImpl.exit();
                    return;
                }
                playList(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void play(String... files) {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
            final List<MediaPlayer> list = new ArrayList<>();
            for (String f : files) {
                list.add(createPlayer(f));
            }
            playList(list);
        });
    }

    public void senha(Alerta alerta, Voz voz, String senha, String guiche) {
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

    public void senha(String senha, String guiche) {
        senha(Alerta.AIRPORT, Voz.FEMININO, senha, guiche);
    }

    private String getCaminho(Voz voz, String v) {
        return voz.getPath() + v.toLowerCase();
    }

    private MediaPlayer createPlayer(String f) {
        URL resource = getClass().getResource("/media/" + f + ".mp3");
        Media media = new Media(resource.toString());
        return new MediaPlayer(media);
    }

}
