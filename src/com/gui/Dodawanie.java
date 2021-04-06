package com.gui;

import com.klienci.BazaKlientow;
import com.klienci.Klient;
import com.klienci.exceptions.BrakBiletowBazaKlientowException;
import com.klienci.exceptions.BrakKlientowBazaKlientowException;
import com.loty.Lot;
import com.loty.Lotnisko;
import com.loty.SiecLotnisk;
import com.loty.Trasa;
import com.loty.exceptions.BrakLotniskaSiecLotniskException;
import com.loty.exceptions.BrakOdlotowException;
import com.loty.exceptions.LotException;
import com.loty.exceptions.ZlaTrasaException;
import com.samoloty.BazaSamolotow;
import com.samoloty.Samolot;
import com.samoloty.exceptions.BrakSamolotowBazaException;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *  Klasa posiadająca metody dodawania lotów i biletów do list i tabel
 */
public class Dodawanie {
    SiecLotnisk siecLotnisk;
    BazaSamolotow bazaSamolotow;
    BazaKlientow bazaKlientow;
    Init init;

    public Dodawanie(SiecLotnisk siecLotnisk, BazaSamolotow bazaSamolotow, BazaKlientow bazaKlientow, Init init) {
        this.siecLotnisk = siecLotnisk;
        this.bazaSamolotow = bazaSamolotow;
        this.bazaKlientow = bazaKlientow;
        this.init = init;
    }

    /**
     * Dodaje Lot do tablicy i rozkładu, pobiera jako parametry datę, lotniska, samolot
     * @param table4
     * @param comboxGodzina2
     * @param comboxminuta2
     * @param comboxdzien2
     * @param comboxmiesiac2
     * @param comboxrok2
     * @param comboxNoweLotniskoStart
     * @param comboxNoweLotniskoCel
     * @param comBoxSamolotIdLot
     * @return tablicaLotow
     */
    public JTable dodajLot(JTable table4, JComboBox comboxGodzina2, JComboBox comboxminuta2, JComboBox comboxdzien2, JComboBox comboxmiesiac2, JComboBox comboxrok2, JComboBox comboxNoweLotniskoStart, JComboBox comboxNoweLotniskoCel, JSpinner comBoxSamolotIdLot) {

        Calendar data = new GregorianCalendar(Integer.parseInt((String)comboxrok2.getModel().getSelectedItem()),
                Integer.parseInt((String)comboxmiesiac2.getModel().getSelectedItem()),
                Integer.parseInt((String)comboxdzien2.getModel().getSelectedItem()),
                Integer.parseInt((String)comboxGodzina2.getModel().getSelectedItem()),
                Integer.parseInt((String)comboxminuta2.getModel().getSelectedItem()));

        try {
            Lotnisko lotniskoA = siecLotnisk.getLotnisko((String) comboxNoweLotniskoStart.getModel().getSelectedItem());
            Lotnisko lotniskoB = siecLotnisk.getLotnisko((String) comboxNoweLotniskoCel.getModel().getSelectedItem());
            Samolot samolot = bazaSamolotow.getSamolotPoStringID(String.valueOf((int)comBoxSamolotIdLot.getModel().getValue()*1.0));
            if(samolot.getStan()==0){
                JOptionPane.showMessageDialog(null, "Samolot jest już zarezerwowany.", "Samolot", 0);
                return table4;
            }

            Lot lot = new Lot(new Trasa(lotniskoA, lotniskoB, siecLotnisk), samolot, data.getTime());
            lotniskoA.getRozklad().dodajLot(lot);
            lotniskoB.getRozklad().dodajLot(lot);

            table4 =  init.createRozkladTable(lotniskoA.getRozklad().szukajOdlotyDoB(lotniskoB.getNazwa(), siecLotnisk), 1);
            JOptionPane.showMessageDialog(null, "Pomyslnie dodano lot nr "+lot.getNumerLotu()+" Odlot o "+lot.getDataOdlotu().toString()+" Czasu lokalnego ", " Powodzenie ", 1);
            return table4;

        } catch (BrakLotniskaSiecLotniskException e) {
            JOptionPane.showMessageDialog(null, "Lotnisko nie istnieje w bazie", e.getMessage(), 0); e.printStackTrace();
        } catch (BrakSamolotowBazaException e) {
            JOptionPane.showMessageDialog(null, "Samolot nie istnieje w bazie", e.getMessage(), 0); e.printStackTrace();
        } catch (LotException e) {
            JOptionPane.showMessageDialog(null, "Za mały zasięg samolotu", e.getMessage(), 0);
            e.printStackTrace();
        } catch (ZlaTrasaException e) {
            JOptionPane.showMessageDialog(null, "Lotnisko startowe == lotnisko docelowe", e.getMessage(), 0); e.printStackTrace();
        } catch (BrakOdlotowException e) {
            JOptionPane.showMessageDialog(null, "Błąd nie dodano lotniska", e.getMessage(), 0); e.printStackTrace();
        }


        return table4;
    }

    /**
     * Dodaj bilety do tablicy i do listy biletów obiektu 'Lot'
     * @param table5
     * @param liczbaBiletowSpinner
     * @param comBoxOdlotBilety
     * @param stringIdKlient
     * @throws BrakBiletowBazaKlientowException rzuca ten wyjątek, gdy wszystkie bilety są wyprzedane
     */

    public void dodajBilety(JTable table5, JSpinner liczbaBiletowSpinner, JComboBox comBoxOdlotBilety, JTextField stringIdKlient) throws BrakBiletowBazaKlientowException {
        int liczbaBiletow = (int)liczbaBiletowSpinner.getValue();
        Lot lot;
        Lotnisko lotnisko;
        Klient klient;
        try {
            lotnisko = init.getSiecLotnisk().getLotnisko((String) comBoxOdlotBilety.getModel().getSelectedItem());
            lot = lotnisko.getRozklad().getLot((int) (table5.getModel().getValueAt(table5.getSelectedRow(), 0)));
            if(liczbaBiletow < 0 || liczbaBiletow > lot.getWolneMiejsca())
                throw new BrakBiletowBazaKlientowException("Przekroczono limit miejsc");

            klient = init.getBazaKlientow().wyszukajKlientId(stringIdKlient.getText());
            for(int i=0; i < liczbaBiletow; i++){
                lot.rezerwujBilet(klient);
            }
            JOptionPane.showMessageDialog(null, "Pomyślnie zarezerwowano "+liczbaBiletow+" przez "+klient.getStringID(), "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (BrakLotniskaSiecLotniskException | BrakOdlotowException | BrakKlientowBazaKlientowException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Błąd", 0); e.printStackTrace();
            e.printStackTrace();
        }


    }
}
