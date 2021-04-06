package com.loty;

import com.samoloty.Samolot;

import java.util.*;

/**
 * Lotnisko, oprócz nazwy posiada współrzędne, strefę czasową, datę, listę samolotów uziemionych oraz rozkład
 */
public class Lotnisko {
    private String nazwa;
    private double wspN;
    private double wspE;
    private List<Samolot> listaSamolotow;
    private Calendar kalendarz;
    private TimeZone zone;
    private RozkladLotniska rozklad;

    /**
     * Tworzy obiekt lotnisko, ustawia datę, oblicza różnice UTC, tworzy pusty rozkład
     * @param nazwa
     * @param wspN
     * @param wspE
     * @param timeZone
     */
    public Lotnisko(String nazwa, double wspN, double wspE, String timeZone) {
        this.nazwa = nazwa;
        this.wspN = wspN;
        this.wspE = wspE;
        this.listaSamolotow = new ArrayList<>(10);
        this.zone = TimeZone.getTimeZone(timeZone); // nazwa zone'a jest typu "Europe/Warsaw"
        this.kalendarz = new GregorianCalendar(2020, 5, 8, 11, 0);
        this.kalendarz.add(Calendar.MILLISECOND, zone.getRawOffset());
        this.rozklad = new RozkladLotniska(this, timeZone);
    }
    public Lotnisko(Lotnisko stareLotnisko){
        this.nazwa = stareLotnisko.getNazwa();
        this.wspN = stareLotnisko.getWspN();
        this.wspE = stareLotnisko.getWspE();
        this.listaSamolotow = new ArrayList<>(10);
        this.zone = TimeZone.getTimeZone(stareLotnisko.getZone().getID()); // nazwa zone'a jest typu "Europe/Warsaw"
        this.kalendarz = new GregorianCalendar(zone, Locale.FRANCE);
        kalendarz.set(2020, 6, 3, 12, 0);
        kalendarz.add(Calendar.MILLISECOND, zone.getRawOffset());
        this.rozklad = new RozkladLotniska(this, stareLotnisko.getZone().getID());
    }
    @Override
    public String toString(){
        return this.nazwa;
    }
    public void dodajSamolot(Samolot samolot){
        this.listaSamolotow.add(samolot);
    }
    public void usunSamolot(Samolot samolot) throws NoSuchElementException{
        if(listaSamolotow.remove(samolot))
            return;
        else
            throw new NoSuchElementException("Brak samolotu na lotnisku");
    }

    public List<Samolot> getListaSamolotow() {
        return listaSamolotow;
    }


    public TimeZone getZone() {
        return zone;
    }


    public RozkladLotniska getRozklad() {
        return rozklad;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getWspN() {
        return wspN;
    }


    public double getWspE() {
        return wspE;
    }

    public Calendar getKalendarz() {
        return kalendarz;
    }

}
