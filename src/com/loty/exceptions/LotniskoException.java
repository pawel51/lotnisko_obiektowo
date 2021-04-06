package com.loty.exceptions;

public class LotniskoException extends Exception{
    private String nazwa = "OK";
    private double wsp = 1;
    public LotniskoException(String msg){super(msg);}
    public LotniskoException(String msg, String nazwa){
        super(msg);
        this.nazwa = nazwa;
    }
    public LotniskoException(String msg, double wsp) {
        super(msg);
        this.wsp = wsp;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getWsp() {
        return wsp;
    }

    public void setWsp(double wsp) {
        this.wsp = wsp;
    }
}

