package com.samoloty;

import com.loty.Lotnisko;

/**
 * Abstrakcyjna klasa samolotów, posiada 4 klasy samolotów które z niej dziedziczą, nie można dodawać nowych typów
 */
public abstract class Samolot {

    private String marka;
    private double zasieg;
    private Lotnisko lokalizacja;
    private double sredniaPredkosc;
    private int maxLiczbaMiejsc;
    private double cenaPodstawowa;
    private double cenaZaKm;
    private static double licznik=0;
    private double id;
    private String stringId;
    private int stan; // 0 - niedostępny(zarezerwowany/uzywany lub w naprawie 1 - dostepny, gotowy do odlotu na lotnisku

    public Samolot(String marka, double zasieg, double sredniaPredkosc, int maxLiczbaMiejsc, double cenaPodstawowa, double cenaZaKm) {
        this.marka = marka;
        this.zasieg = zasieg;
        this.sredniaPredkosc = sredniaPredkosc;
        this.maxLiczbaMiejsc = maxLiczbaMiejsc;
        this.cenaPodstawowa = cenaPodstawowa;
        this.cenaZaKm = cenaZaKm;
        this.stan =1;
    }

    public abstract String toString();

    public int getStan() {
        return stan;
    }

    public String getMarka() {
        return marka;
    }

    public double getZasieg() {
        return zasieg;
    }

    public Lotnisko getLokalizacja() {
        return lokalizacja;
    }

    public void setLokalizacja(Lotnisko lokalizacja) {
        this.lokalizacja = lokalizacja;
        lokalizacja.getListaSamolotow().add(this);
    }

    public double getSredniaPredkosc() {
        return sredniaPredkosc;
    }

    public int getMaxLiczbaMiejsc() {
        return maxLiczbaMiejsc;
    }

    public double getCenaPodstawowa() {
        return cenaPodstawowa;
    }

    public double getCenaZaKm() {
        return cenaZaKm;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }
}
