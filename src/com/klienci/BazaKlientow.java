package com.klienci;

import com.klienci.exceptions.BrakKlientowBazaKlientowException;
import com.klienci.exceptions.KlientJuzIstniejeKlientException;
import com.klienci.exceptions.NazwaZajetaBazaKlientoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa posiadająca jeden obiekt przechowujący informacje o wszystkich klientach oraz metody zarządzające nimi
 */
public class BazaKlientow {
    List<Klient> listaKlientow;

    public BazaKlientow(int rozmiar) {
        this.listaKlientow = new ArrayList<>(rozmiar);
    }

    public List<Klient> getListaKlientow() {
        return listaKlientow;
    }

    public void setListaKlientow(List<Klient> listaKlientow) {
        this.listaKlientow = listaKlientow;
    }

    /**
     * Dodaje i sprawdza czy dany klient może być dodany do bazy
     * @param klient
     * @throws NazwaZajetaBazaKlientoException
     * @throws KlientJuzIstniejeKlientException
     */
    public void addKlient(Klient klient) throws NazwaZajetaBazaKlientoException, KlientJuzIstniejeKlientException {
        for(Klient k  : this.listaKlientow){
            if(k .getStringID().equals(klient.getStringID())){
                throw new NazwaZajetaBazaKlientoException("Nazwa Klienta jest zajeta");
            }
        }
        for(Klient k : this.listaKlientow){
            if(k.getId().equals(klient.getId())){
                throw new KlientJuzIstniejeKlientException("Klient jest juz w bazie");
            }
        }
        listaKlientow.add(klient);
    }

    public void usunKlient(String id){
        listaKlientow.removeIf(klient -> klient.getId().equals(id));
    }

    /**
     * Wyszukuje klienta po nazwie
     * @param nazwa
     * @return zwraca jednoelementową listę
     * @throws BrakKlientowBazaKlientowException
     */

    public List<Klient> wyszukajKlientNazwa(String nazwa) throws BrakKlientowBazaKlientowException {
        List<Klient> zwracane = new ArrayList<>(10);
        for(Klient k : listaKlientow){
            if(k.getStringID().contains(nazwa)){
                zwracane.add(k);
            }
        }
        if(zwracane.isEmpty())
            throw new BrakKlientowBazaKlientowException("Brak klienta o podanej nazwie na liscie");
        else
            return zwracane;
    }

    /**
     * Wyszukuje Klienta po numerze id (String)
     * @param nr
     * @return Zwraca obiekt klienta
     * @throws BrakKlientowBazaKlientowException
     */
    public Klient wyszukajKlientId(String nr) throws BrakKlientowBazaKlientowException {

        for (Klient k : listaKlientow) {
            if (k.getId().contains(nr)) {
                return k;
            }
        }
        throw new BrakKlientowBazaKlientowException("Brak klienta o podanym id");
    }
}
