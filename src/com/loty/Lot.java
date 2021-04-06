package com.loty;

import com.klienci.Bilet;
import com.klienci.Klient;
import com.loty.exceptions.LotException;
import com.samoloty.Samolot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Klasa lotu, tworząca zaplanowane loty, na podstawie obiektów klas Lotnisko, Samolot. Przechowuje listę kupionych biletów i zarządza nią
 */
public class Lot {
    private Trasa trasa;
    private Samolot samolot;
    private List<Bilet> kupione;
    private int wolneMiejsca;
    private static int licznik=0;
    private int numerLotu = licznik++;
    private Date dataOdlotu;
    private Date dataPrzylotu;
    private long czasLotu;
    private int rodzajlotu; //1 - odlot 2 - przylot
    private double cenaEko;
    private double cenaBiznes;

    public Lot(Trasa trasa, Samolot samolot, Date dOdlotu) throws LotException {
        if(samolot.getZasieg() < trasa.getOdeglosc()){
            throw new LotException("Za maly zasieg samolotu");
        }

        //if(dOdlotu.getTime() < trasa.getLotniskoA().getKalendarz().getTimeInMillis()){
           // throw new LotException("Podana data Odlotu juz minela");
        //}
        this.trasa = trasa;
        this.samolot = samolot;
        this.dataOdlotu = dOdlotu;
        this.kupione = new ArrayList<>(samolot.getMaxLiczbaMiejsc());
        this.czasLotu = (long) (trasa.getOdeglosc()/samolot.getSredniaPredkosc()*60*60*1000);
        this.dataPrzylotu = new Date();
        this.dataPrzylotu.setTime(dOdlotu.getTime() - trasa.getLotniskoA().getZone().getOffset(dOdlotu.getTime()) + trasa.getLotniskoB().getZone().getOffset(dOdlotu.getTime()) + czasLotu);
        this.cenaEko = obliczCena();
        this.cenaBiznes = this.cenaEko*2;
        kupione = new ArrayList<>();
        wolneMiejsca = samolot.getMaxLiczbaMiejsc();

    }

    /**
     * Rezerwuje bilet (jedno miejsce) na konto danego klienta
     * @param k klient
     */
    public void rezerwujBilet(Klient k){
        Bilet bilet = new Bilet(this, k);
        this.kupione.add(bilet);
        k.getKupioneBilety().add(bilet);
        this.wolneMiejsca--;
    }

    private double obliczCena(){
        double cena = this.samolot.getCenaPodstawowa() +this.samolot.getCenaZaKm()*this.trasa.getOdeglosc();
        return cena;
    }

    public int getWolneMiejsca() {
        return wolneMiejsca;
    }

    public double getCena() {
        return cenaEko;
    }

    public Trasa getTrasa() {
        return trasa;
    }

    public Samolot getSamolot() {
        return samolot;
    }

    public void setSamolot(Samolot samolot) {
        this.samolot = samolot;
    }

    public List<Bilet> getKupione() {
        return kupione;
    }

    public int getNumerLotu() {
        return numerLotu;
    }

    public Date getDataOdlotu() {
        return dataOdlotu;
    }

    public void setDataOdlotu(Date nowaDataOdlotu) {
        long opoznienie = nowaDataOdlotu.getTime()-this.dataOdlotu.getTime();
        this.dataOdlotu = nowaDataOdlotu;
        this.dataPrzylotu.setTime(dataPrzylotu.getTime()+opoznienie);
    }

    public Date getDataPrzylotu() {
        return dataPrzylotu;
    }

    public double getCzasLotu() {
        return czasLotu;
    }


}
