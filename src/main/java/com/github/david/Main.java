package com.github.david;

public class Main {

    public static void main(String[] args) {
        Chamador.novaChamada()
                .comSenha("P0004")
                .comGuiche("05")
                .chamar();

        Chamador.novaChamada()
                .comAlerta(Alerta.DING_DONG)
                .comVoz(Voz.MASCULINO)
                .comSenha("I0004")
                .comGuiche("05")
                .chamar();

    }

}
