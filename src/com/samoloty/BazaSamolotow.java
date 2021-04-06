package com.samoloty;

import com.loty.Lotnisko;
import com.samoloty.exceptions.BrakSamolotowBazaException;

import java.util.ArrayList;
import java.util.List;

public class BazaSamolotow {
    private List<Samolot> listaSamolotow;
    public BazaSamolotow(int rozmiar){
        listaSamolotow = new ArrayList<>(rozmiar);
    }

    public void add(Samolot samolot, Lotnisko lotnisko){
        samolot.setLokalizacja(lotnisko);
        samolot.setId(listaSamolotow.size()+1);
        samolot.setStringId(String.valueOf(samolot.getId()));
        listaSamolotow.add(samolot);
    }
    public Samolot getSamolotPoStringID(String id) throws BrakSamolotowBazaException {
        for(Samolot s : listaSamolotow) {
            if (s.getStringId().equals(id)) {
                return s;
            }
        }
        throw new BrakSamolotowBazaException("Brak samolotu o podanym id: "+id);
    }

    public void addList(List<Samolot> listaSamolotow){
        this.listaSamolotow.addAll(listaSamolotow);
    }

    public List<Samolot> getListaSamolotow() {
        return listaSamolotow;
    }

    public List<Samolot> wyszukajMinZasieg(double minZasieg) throws BrakSamolotowBazaException {
        List<Samolot> zwracane = new ArrayList<>(3);
        for(Samolot samolot : listaSamolotow){
            if(samolot.getZasieg() >= minZasieg)
                zwracane.add(samolot);
        }
        if(!zwracane.isEmpty())
            return zwracane;
        else
            throw new BrakSamolotowBazaException("Brak samolotow o podanym zasiegu w Bazie");
    }
    public List<Samolot> wyszukajMinZasiegLotnisko(double minZasieg, String lotnisko) throws BrakSamolotowBazaException{
        List<Samolot> zwracane = new ArrayList<>(3);
        for(Samolot samolot : listaSamolotow){
            if(samolot.getZasieg() >= minZasieg && samolot.getLokalizacja().getNazwa().contains(lotnisko))
                zwracane.add(samolot);
        }
        if(!zwracane.isEmpty())
            return zwracane;
        else
            throw new BrakSamolotowBazaException("Brak samolotow o podanym zasiegu na lotnisku");
    }
    public List<Samolot> wyszukajMinZasiegLotniskoMinMiejsc(double minZasieg, String lotnisko, double minMiejsc) throws BrakSamolotowBazaException{
        List<Samolot> zwracane = new ArrayList<>(3);
        for(Samolot samolot : listaSamolotow){
            if(samolot.getZasieg() >= minZasieg && samolot.getLokalizacja().getNazwa().contains(lotnisko) && samolot.getMaxLiczbaMiejsc() >= minMiejsc)
                zwracane.add(samolot);
        }
        if(!zwracane.isEmpty())
            return zwracane;
        else
            throw new BrakSamolotowBazaException("Brak samolotow o podanym zasiegu i liczbie miejsc na lotnisku");
    }
    public List<Samolot> wyszukajMinMiejsca(double minMiejsc) throws BrakSamolotowBazaException {
        List<Samolot> zwracane = new ArrayList<>(10);
        for(Samolot samolot : listaSamolotow){
            if(samolot.getMaxLiczbaMiejsc() >= minMiejsc)
                zwracane.add(samolot);
        }
        if(!zwracane.isEmpty())
            return zwracane;
        else
            throw new BrakSamolotowBazaException("Brak samolotow o podanej liczbie miejsc w Bazie");
    }
    public List<Samolot> wyszukajLotniskoMinMiejsca( String lotnisko, double minMiejsc) throws BrakSamolotowBazaException {
        List<Samolot> zwracane = new ArrayList<>(10);
        for(Samolot samolot : listaSamolotow){
            if(samolot.getMaxLiczbaMiejsc() >= minMiejsc && samolot.getLokalizacja().getNazwa().contains(lotnisko))
                zwracane.add(samolot);
        }
        if(!zwracane.isEmpty())
            return zwracane;
        else
            throw new BrakSamolotowBazaException("Brak samolotow o podanej liczbie miejsc na Lotnisku");
    }
    public List<Samolot> wyszukajMinZasiegMinMiejsca(double minMiejsc, double minZasieg) throws BrakSamolotowBazaException {
        List<Samolot> zwracane = new ArrayList<>(10);
        for(Samolot samolot : listaSamolotow){
            if(samolot.getMaxLiczbaMiejsc() >= minMiejsc && samolot.getZasieg() >= minZasieg)
                zwracane.add(samolot);
        }
        if(!zwracane.isEmpty())
            return zwracane;
        else
            throw new BrakSamolotowBazaException("Brak samolotow o podanej liczbie miejsc i zasiegu w Bazie");
    }
    public List<Samolot> wyszukajLotnisko(String lotnisko) throws BrakSamolotowBazaException{
        List<Samolot> zwracane = new ArrayList<>(3);
        for(Samolot samolot : listaSamolotow){
            if(samolot.getLokalizacja().getNazwa().contains(lotnisko))
                zwracane.add(samolot);
        }
        if(!zwracane.isEmpty())
            return zwracane;
        else
            throw new BrakSamolotowBazaException("Brak samolotow na lotnisku");
    }

    public void usun(Double id ){
        listaSamolotow.removeIf(n -> n.getId() == id);
    }
    public List<Samolot> wyszukajMarka(String marka) throws BrakSamolotowBazaException {
        List<Samolot> zwracane = new ArrayList<>(10);
        for(Samolot s : listaSamolotow){
            if(s.getMarka().equals(marka)){
                zwracane.add(s);
            }
        }
        if(zwracane.isEmpty()){
            throw new BrakSamolotowBazaException("Brak samolotow o podanej marce w bazie");
        }
        else
            return zwracane;

    }





























}
