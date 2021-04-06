package com.klienci;

public class Firma extends Klient {
    private String nazwa;
    private String nrKrs;

    public Firma(String nazwa, String nrKrs) {
        super(nrKrs);
        this.nazwa = nazwa;
        this.nrKrs = nrKrs;
    }
    public String getStringID(){
        return this.nazwa;
    }


}
