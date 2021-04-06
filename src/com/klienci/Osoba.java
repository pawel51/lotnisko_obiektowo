package com.klienci;

public class Osoba extends Klient {
    private String imie;
    private String nazwisko;
    private String pesel;

    public Osoba(String imie, String nazwisko, String pesel) {
        super(pesel);
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }
    public String getStringID(){
        return getImie()+" "+getNazwisko();
    }
    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

}
