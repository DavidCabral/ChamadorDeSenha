package com.github.david;

public enum Voz {
    FEMININO("voz/fem/", "Feminino"),
    MASCULINO("voz/masculino/", "Masculino");

    private final String descricao;
    private final String path;

    Voz(String path, String descricao) {
        this.path = path;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPath() {
        return path;
    }
}
