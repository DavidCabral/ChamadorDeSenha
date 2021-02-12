package com.github.david;

public enum Alerta {
    AIRPORT("alerta/airport-bingbong"),
    DING_DONG("alerta/ding-dong"),
    DOORBELL("alerta/doorbell-bingbong"),
    EKIGA_VM("alerta/ekiga-vm"),
    INFOBLEEP("alerta/infobleep"),
    QUITO("alerta/quito-mariscal-sucre"),
    TOYDOOR("alerta/toydoorbell");

    private final String path;

    Alerta(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
