package com.loty;

import com.loty.exceptions.BrakLotniskaSiecLotniskException;
import com.loty.exceptions.BrakOdlotowException;
import com.loty.exceptions.BrakPrzylotowException;

import java.util.*;

/**
 * Jest komponentem Lotniska, jest jeden rozkład dla każdego lotniska
 */
public class RozkladLotniska {
        private Lotnisko lotnisko;
        private List<Lot> listaLotow;
        private List<Lot> listaodlotow;
        private List<Lot> listaprzylotow;


        public RozkladLotniska(Lotnisko lotnisko, String zone){
            listaprzylotow = new ArrayList<>(3);
            listaodlotow = new ArrayList<>(3);

            this.lotnisko = lotnisko;
            listaLotow = new ArrayList<>(3);

        }

    /**
     * Dodaje lot do listy odlotów lub przylotów zależy od kolejności lotnisk
     * @param lot
     */
    public void dodajLot(Lot lot){
            listaLotow.add(lot);
            if(lot.getTrasa().getLotniskoA().getNazwa().equals(this.lotnisko.getNazwa())){
                listaodlotow.add(lot);
            }
            if(lot.getTrasa().getLotniskoB().getNazwa().equals(this.lotnisko.getNazwa())){
                listaprzylotow.add(lot);
            }
        }

    public Lot getLot(int numerLotu) throws BrakOdlotowException {
            for(Lot lot : listaodlotow){
                if(lot.getNumerLotu() == numerLotu)
                    return lot;
            }
            throw new BrakOdlotowException("Brak odlotu");
    }

    /**
     * Wyszukuje najbliższe odloty
     * @param date
     * @return lista Lotów
     */
    public List<Lot> wyszukajNajblizszeOdloty(Date date){

        class NajblizszaDataComparator implements Comparator<Lot>{

            @Override
            public int compare(Lot lot1, Lot lot2) {
                Date d1 = lot1.getDataOdlotu();
                Date d2 = lot2.getDataOdlotu();
                if(Math.abs(d1.getTime() - date.getTime()) > Math.abs(d2.getTime() - date.getTime()))
                    return 1;
                else
                    return -1;
            }
        }

        listaodlotow.sort(new NajblizszaDataComparator());

        return this.listaodlotow;

    }

    /**
     * Wyszukuje najbliższe Odloty do jakiegoś miasta
     * @param date
     * @param odlotyDoB
     * @return lista lotów
     */

    public List<Lot> wyszukajNajblizszeOdloty(Date date, List<Lot> odlotyDoB){

        class NajblizszaDataComparator implements Comparator<Lot>{

            @Override
            public int compare(Lot lot1, Lot lot2) {
                Date d1 = lot1.getDataOdlotu();
                Date d2 = lot2.getDataOdlotu();
                if(Math.abs(d1.getTime() - date.getTime()) > Math.abs(d2.getTime() - date.getTime()))
                    return 1;
                else
                    return -1;
            }
        }

        odlotyDoB.sort(new NajblizszaDataComparator());

        return this.listaodlotow;
    }

    /**
     * Wyszukuje najbliższe przyloty
     * @param date
     * @return lista lotów
     */
    public List<Lot> wyszukajNajblizszePrzyloty(Date date){

        class NajblizszaDataComparator implements Comparator<Lot>{

            @Override
            public int compare(Lot lot1, Lot lot2) {
                Date d1 = lot1.getDataOdlotu();
                Date d2 = lot2.getDataOdlotu();
                if(Math.abs(d1.getTime() - date.getTime()) > Math.abs(d2.getTime() - date.getTime()))
                    return 1;
                else if(Math.abs(d1.getTime() - date.getTime()) == Math.abs(d2.getTime() - date.getTime()))
                    return 0;
                else
                    return -1;
            }
        }
        listaprzylotow.sort(new NajblizszaDataComparator());
        return this.listaprzylotow;
    }

    /**
     * Wyszukuje najbliższe przyloty z jakiegoś miasta
     * @param date
     * @param przylotyZ
     * @return
     */
    public List<Lot> wyszukajNajblizszePrzyloty(Date date, List<Lot> przylotyZ){

        class NajblizszaDataComparator implements Comparator<Lot>{

            @Override
            public int compare(Lot lot1, Lot lot2) {
                Date d1 = lot1.getDataOdlotu();
                Date d2 = lot2.getDataOdlotu();
                if(Math.abs(d1.getTime() - date.getTime()) > Math.abs(d2.getTime() - date.getTime()))
                    return 1;
                else if(Math.abs(d1.getTime() - date.getTime()) == Math.abs(d2.getTime() - date.getTime()))
                    return 0;
                else
                    return -1;
            }
        }
        przylotyZ.sort(new NajblizszaDataComparator());
        return this.listaprzylotow;
    }

    /**
     * W liście odlotów wyszukuje odloty do danego miasta
     * @param nazwaLotniskaB
     * @param siecLotnisk
     * @return lista lotów
     * @throws BrakLotniskaSiecLotniskException
     * @throws BrakOdlotowException
     */
    public List<Lot> szukajOdlotyDoB( String nazwaLotniskaB, SiecLotnisk siecLotnisk) throws BrakLotniskaSiecLotniskException, BrakOdlotowException {

            siecLotnisk.contains(nazwaLotniskaB);

            List<Lot> zwracane = new ArrayList<>(10);


            Lotnisko lotniskoB = siecLotnisk.getLotnisko((nazwaLotniskaB));
            for(Lot lot : this.listaodlotow){
                if(lot.getTrasa().getLotniskoB().getNazwa().equals(lotniskoB.getNazwa())){
                    zwracane.add(lot);
                }
            }
            if(zwracane.isEmpty())
                throw new BrakOdlotowException("Brak odlotow do Lotniska"+nazwaLotniskaB);
            return zwracane;
    }

    /**
     * Wyszukuje na liście przylotów loty z jakiegoś miasta
     * @param nazwaLotniskaA
     * @param siecLotnisk
     * @return lista lotów
     * @throws BrakLotniskaSiecLotniskException
     * @throws BrakPrzylotowException
     */
    public List<Lot> szukajPrzylotyZ(String nazwaLotniskaA, SiecLotnisk siecLotnisk) throws BrakLotniskaSiecLotniskException, BrakPrzylotowException {
        siecLotnisk.contains(nazwaLotniskaA);

        List<Lot> zwracane = new ArrayList<>(10);
        Lotnisko lotniskoA = siecLotnisk.getLotnisko(nazwaLotniskaA);

        for(Lot lot : this.listaprzylotow){
            if(lot.getTrasa().getLotniskoA().getNazwa().equals(lotniskoA.getNazwa())){
                zwracane.add(lot);
            }
        }
        if(zwracane.isEmpty())
            throw new BrakPrzylotowException("Brak przylotow z Lotniska"+nazwaLotniskaA);
        return zwracane;
    }


    public Lotnisko getLotnisko() {
        return lotnisko;
    }

    public void setLotnisko(Lotnisko lotnisko) {
        this.lotnisko = lotnisko;
    }

    public List<Lot> getListaodlotow() {
        return listaodlotow;
    }

    public List<Lot> getListaprzylotow() {
        return listaprzylotow;
    }


}
