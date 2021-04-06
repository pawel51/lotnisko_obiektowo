package com.klienci;

import java.util.ArrayList;
import java.util.List;

/**
 *  Obiekty tej klasy są klientem indywidualnym lub firmą
 */
public abstract class Klient {
    protected List<Bilet> kupioneBilety;
    protected String id;
    public Klient(){}
    public Klient(String nr) {

        this.id = nr;
        this.kupioneBilety = new ArrayList<>(2);
    }
    public abstract String getStringID();

    public List<Bilet> getKupioneBilety() {
        return kupioneBilety;
    }


    public String getId() {
        return id;
    }





}
