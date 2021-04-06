package com.loty;

import com.loty.exceptions.BrakLotniskaSiecLotniskException;
import com.loty.exceptions.LotniskoException;
import com.samoloty.Samolot;

import java.util.*;

/**
 * Klasa posiadająca jeden obiekt przechowujący informacje o wszystkich lotniskach oraz metody zarządzające nimi
 */
public class SiecLotnisk {
    private List<Lotnisko> listaLotnisk;

    public SiecLotnisk(int rozmiar){
        listaLotnisk = new ArrayList<>(rozmiar);
    }

    public Lotnisko getLotnisko(String nazwa) throws BrakLotniskaSiecLotniskException {
        for(Lotnisko l : listaLotnisk){
            if(l.getNazwa().equals(nazwa)){
                return l ;
            }
        }
        throw new BrakLotniskaSiecLotniskException("Brak Lotniska o podanej nazwie");
    }


    public List<Lotnisko> getListaLotnisk() {
        return listaLotnisk;
    }


    public void add(Lotnisko lotnisko) throws LotniskoException {
        for(Lotnisko l : listaLotnisk) {
            if (l.getNazwa().equals(lotnisko.getNazwa())) {
                throw new LotniskoException("Nazwa juz jest uzyta", lotnisko.getNazwa());
            }
        }
        if(lotnisko.getWspN() > 90 || lotnisko.getWspN() < -90 ){
            throw new LotniskoException("Sprawdz wspolrzedne N", lotnisko.getWspN());
        }
        if(lotnisko.getWspE() > 180 || lotnisko.getWspE() < -180){
            throw new LotniskoException("Sprawdz wspolrzedne E", lotnisko.getWspE());
        }
        listaLotnisk.add(lotnisko);
    }
    public void add(String nazwaLotniska, double wspN, double wspE, String timeZone) throws LotniskoException {
        boolean poprawnaNazwa = false;
        for(Lotnisko lotnisko : listaLotnisk){
            if(lotnisko.getNazwa().equals(nazwaLotniska)){
                poprawnaNazwa=true;
            }
        }
        if(poprawnaNazwa=false){
            throw new LotniskoException("Nazwa użytkownika jest już zajęta ");
        }

        for(Lotnisko l : listaLotnisk) {
            if (l.getNazwa().equals(nazwaLotniska)) {
                throw new LotniskoException("Nazwa juz jest uzyta", nazwaLotniska);
            }
        }

        if(wspN > 90 || wspN < -90 ){
            throw new LotniskoException("Sprawdz wspolrzedne N", wspN);
        }
        if(wspE > 180 || wspE < -180){
            throw new LotniskoException("Sprawdz wspolrzedne E",wspE);
        }
        Lotnisko lotnisko = new Lotnisko(nazwaLotniska, wspN, wspE, timeZone);
        listaLotnisk.add(lotnisko);

    }
    public boolean contains(String nazwa) throws BrakLotniskaSiecLotniskException {
        for(Lotnisko l : listaLotnisk){
            if(l.getNazwa().equals(nazwa)){
                return true;
            }
        }
        throw new BrakLotniskaSiecLotniskException("Brak lotniska o podanej nazwie w bazie :\n-nazwa: "+nazwa);
    }

    /**
     * Wyszukuje lotnisko, na którym obecne są samoloty danej marki
     * @param marka
     * @return zwraca liste samolotów
     * @throws BrakLotniskaSiecLotniskException
     */

    public List<Lotnisko> wyszukajLotniskoPoMarce(String marka) throws BrakLotniskaSiecLotniskException {
        List<Lotnisko> zwracane = new ArrayList<>(10);
        for(Lotnisko lotnisko : listaLotnisk){
            for(Samolot samolot : lotnisko.getListaSamolotow()){
                if(samolot.getMarka().contains(marka)) {
                    zwracane.add(lotnisko);
                    break;
                }
            }
        }
        if(zwracane.isEmpty())
            throw new BrakLotniskaSiecLotniskException("Brak lotniska w Sieci, na ktorym bylyby samoloty o podanej marce");
        else
            return zwracane;
    }

    /**
     * wyszukuje lotnisko po nazwielotniska
     * @param nazwa
     * @return zwraca liste lotnisk
     * @throws BrakLotniskaSiecLotniskException
     */
    public List<Lotnisko> wyszukajLotniskoPoNazwie(String nazwa) throws BrakLotniskaSiecLotniskException {
        List<Lotnisko> zwracane = new ArrayList<>(10);
        for(Lotnisko lotnisko : listaLotnisk){
            if(lotnisko.getNazwa().contains(nazwa))
                zwracane.add(lotnisko);
        }
        if(zwracane.isEmpty())
            throw new BrakLotniskaSiecLotniskException("Brak lotniska o podanej nazwie");
        else
            return zwracane;
    }

    /**
     * Wyszukuje Lotnisko po  nazwie, ale musi znajdować się na nim samolot danej marki
     * @param nazwa
     * @param marka
     * @return
     * @throws BrakLotniskaSiecLotniskException
     */
    public List<Lotnisko>wyszukajLotnisko(String nazwa, String marka) throws BrakLotniskaSiecLotniskException {
        List<Lotnisko> zwracane = new ArrayList<>(10);
        for(Lotnisko lotnisko : listaLotnisk){
            for(Samolot samolot : lotnisko.getListaSamolotow()){
                if(samolot.getMarka().contains(marka) && lotnisko.getNazwa().contains(nazwa))
                    zwracane.add(lotnisko);
            }
        }
        if(zwracane.isEmpty())
            throw new BrakLotniskaSiecLotniskException("Brak lotniska o podanej nazwie w Sieci, na ktorym bylyby samoloty o podanej marce");
        else
            return zwracane;
    }

}









