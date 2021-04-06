package com.gui;

import com.klienci.Klient;
import com.klienci.exceptions.BrakKlientowBazaKlientowException;
import com.loty.Lot;
import com.loty.Lotnisko;
import com.loty.RozkladLotniska;
import com.loty.exceptions.BrakLotniskaSiecLotniskException;
import com.loty.exceptions.BrakOdlotowException;
import com.loty.exceptions.BrakPrzylotowException;
import com.samoloty.Samolot;
import com.samoloty.exceptions.BrakSamolotowBazaException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.List;

/**
 * Posiada metody statyczne zajmujące się wyszukiwarkami
 */

public abstract class Wyszukiwarki {
    static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Błąd", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Wyszukuje Samoloty pobierając filtry wyszukiwania przez 3 textfieldy w różnych konfigurcjach
     * @param textFieldMinMiejsc
     * @param textFieldMinZasieg
     * @param textFieldLotnisko
     * @param init
     * @return Zwraca wyniki wyszukiwania
     */
    static JTable wyszuajSamolot(JTextField textFieldMinMiejsc, JTextField textFieldMinZasieg, JTextField textFieldLotnisko, Init init) {
        JTable table2 = new JTable();
        if (textFieldMinZasieg.getText().length() > 0 && textFieldLotnisko.getText().length() > 0 && textFieldMinMiejsc.getText().length() > 0) {
            try {
                table2 = init.createBazaSamolotowTable(init.getBazaSamolotow().wyszukajMinZasiegLotniskoMinMiejsc(Double.parseDouble(textFieldMinZasieg.getText()), textFieldLotnisko.getText(), Double.parseDouble(textFieldMinMiejsc.getText())));
                table2.tableChanged(null);
            } catch (BrakSamolotowBazaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
            }
        }// zasieg miejsca
        else if (textFieldMinZasieg.getText().length() > 0 && textFieldMinMiejsc.getText().length() > 0) {
            try {
                table2 = init.createBazaSamolotowTable(init.getBazaSamolotow().wyszukajMinZasiegMinMiejsca(Double.parseDouble(textFieldMinZasieg.getText()), Double.parseDouble(textFieldMinMiejsc.getText())));
                table2.tableChanged(null);
            } catch (BrakSamolotowBazaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
            }
        }// zasieg lotnisko
        else if (textFieldMinZasieg.getText().length() > 0 && textFieldLotnisko.getText().length() > 0) {
            try {
                table2 = init.createBazaSamolotowTable(init.getBazaSamolotow().wyszukajMinZasiegLotnisko(Double.parseDouble(textFieldMinZasieg.getText()), textFieldLotnisko.getText()));
                table2.tableChanged(null);
            } catch (BrakSamolotowBazaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
            }
        }// lotnisko miejsca
        else if (textFieldLotnisko.getText().length() > 0 && textFieldMinMiejsc.getText().length() > 0) {
            try {
                table2 = init.createBazaSamolotowTable(init.getBazaSamolotow().wyszukajLotniskoMinMiejsca(textFieldLotnisko.getText(), Double.parseDouble(textFieldMinMiejsc.getText())));
                table2.tableChanged(null);
            } catch (BrakSamolotowBazaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
            }
        }// miejsca
        else if (textFieldMinMiejsc.getText().length() > 0) {
            try {
                table2 = init.createBazaSamolotowTable(init.getBazaSamolotow().wyszukajMinMiejsca(Double.parseDouble(textFieldMinMiejsc.getText())));
                table2.tableChanged(null);
            } catch (BrakSamolotowBazaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
            }
        }// zasieg
        else if (textFieldMinZasieg.getText().length() > 0) {
            try {
                table2 = init.createBazaSamolotowTable(init.getBazaSamolotow().wyszukajMinZasieg(Double.parseDouble(textFieldMinZasieg.getText())));
                table2.tableChanged(null);
            } catch (BrakSamolotowBazaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
            }
        }// lotnisko
        else if (textFieldLotnisko.getText().length() > 0) {
            try {
                table2 = init.createBazaSamolotowTable(init.getBazaSamolotow().wyszukajLotnisko(textFieldLotnisko.getText()));
                table2.tableChanged(null);
            } catch (BrakSamolotowBazaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return table2;
    }

    /**
     * Wyszukuje lotnisko na podstawie nazwy
     * @param textFieldLotniskaLotnisko
     * @param szukajSamolotowCheckBox
     * @param comboxSamoloty2
     * @param table3
     * @param init
     */

    public static void wyszukajLotnisko(JTextField textFieldLotniskaLotnisko, JCheckBox szukajSamolotowCheckBox, JComboBox comboxSamoloty2, JTable table3, Init init) {
        if (textFieldLotniskaLotnisko.getText().length() > 0 && !szukajSamolotowCheckBox.isSelected()) {
            try {
                table3 = init.createSiecLotniskTable(init.getSiecLotnisk().wyszukajLotniskoPoNazwie(textFieldLotniskaLotnisko.getText()));
                table3.tableChanged(null);
            } catch (BrakLotniskaSiecLotniskException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak lotnisk", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (textFieldLotniskaLotnisko.getText().length() > 0 && szukajSamolotowCheckBox.isSelected())
            try {
                table3 = init.createSiecLotniskTable(init.getSiecLotnisk().wyszukajLotnisko(textFieldLotniskaLotnisko.getText(), ((Samolot) comboxSamoloty2.getModel().getSelectedItem()).getMarka()));
                table3.tableChanged(null);
            } catch (BrakLotniskaSiecLotniskException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak lotnisk", JOptionPane.INFORMATION_MESSAGE);
            }
        else if (textFieldLotniskaLotnisko.getText().length() == 0 && szukajSamolotowCheckBox.isSelected()) {
            try {
                table3 = init.createSiecLotniskTable(init.getSiecLotnisk().wyszukajLotniskoPoMarce(((Samolot) comboxSamoloty2.getModel().getSelectedItem()).getMarka()));
                table3.tableChanged(null);
            } catch (BrakLotniskaSiecLotniskException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak lotnisk", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     *  Wyszukuje klienta po nazwie (Panel Klienci)
     * @param init
     * @param table1
     * @param textFieldWyszukiwarka
     */
    static void wyszukajKlientNazwa(Init init, JTable table1, JTextField textFieldWyszukiwarka ) {
        try {
            List<Klient> b = init.getBazaKlientow().wyszukajKlientNazwa(textFieldWyszukiwarka.getText());
            table1 = init.createBazaKlientowTable(b);
            table1.tableChanged(null);
        } catch (BrakKlientowBazaKlientowException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *  Wyszukuje klienta po Id (Panel Klienci)
     * @param init
     * @param table1
     * @param textFieldWyszukiwarka
     */
    static void wyszukajKlientId(Init init, JTable table1, JTextField textFieldWyszukiwarka ) {
        try {
            Klient b = init.getBazaKlientow().wyszukajKlientId(textFieldWyszukiwarka.getText());
            List<Klient> bList = new ArrayList<>(1);
            table1 = init.createBazaKlientowTable(bList);
            table1.tableChanged(null);
        } catch (BrakKlientowBazaKlientowException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Wyszukuje loty, data musi być podana i checkbox data musi być zaznaczony aby wyszukać najbliższe loty od podanej daty
     * @param table4
     * @param dataCheckBox
     * @param comboxDocelowe
     * @param init
     * @param comboBoxRok
     * @param comBoxMiesiac
     * @param comboxDzien
     * @param comboxGodzina
     * @param comboxMinuty
     * @param comBoxRozklad
     * @param odlotyCheckBox
     * @param przylotyCheckBox
     * @return zwraca posortowaną tablcę
     * @throws BrakLotniskaSiecLotniskException
     */

    static JTable wyszukajLoty(JTable table4, JCheckBox dataCheckBox, JComboBox comboxDocelowe, Init init, JComboBox comboBoxRok, JComboBox comBoxMiesiac, JComboBox comboxDzien, JComboBox comboxGodzina, JComboBox comboxMinuty, JComboBox comBoxRozklad, JCheckBox odlotyCheckBox, JCheckBox przylotyCheckBox) throws BrakLotniskaSiecLotniskException {
        List<Lot> posortowane = new ArrayList<>();

        RozkladLotniska rozklad = init.getSiecLotnisk().getLotnisko((String) comBoxRozklad.getModel().getSelectedItem()).getRozklad();

        Calendar data = new GregorianCalendar(Integer.parseInt((String)comboBoxRok.getModel().getSelectedItem()), Integer.parseInt((String)comBoxMiesiac.getModel().getSelectedItem())-1, Integer.parseInt((String)comboxDzien.getModel().getSelectedItem()), Integer.parseInt((String)comboxGodzina.getModel().getSelectedItem()), Integer.parseInt((String)comboxMinuty.getModel().getSelectedItem()));

        if(dataCheckBox.isSelected() && comboxDocelowe.getModel().getSelectedItem().equals("ALL")){

            if(odlotyCheckBox.isSelected()){
                posortowane.addAll(rozklad.wyszukajNajblizszeOdloty(data.getTime()));
                table4=init.createRozkladTable(posortowane, 1);
            }
            else if(przylotyCheckBox.isSelected()){
                posortowane.addAll(rozklad.wyszukajNajblizszePrzyloty(data.getTime()));
                table4=init.createRozkladTable(posortowane, 2);
            }
        }
        else if(dataCheckBox.isSelected() && !comboxDocelowe.getModel().getSelectedItem().equals("ALL")){
            try{
                if(odlotyCheckBox.isSelected()){
                    posortowane.addAll(rozklad.wyszukajNajblizszeOdloty(data.getTime(), rozklad.szukajOdlotyDoB((String) comboxDocelowe.getSelectedItem(), init.getSiecLotnisk())));
                    table4=init.createRozkladTable(posortowane, 1);
                }
                else if(przylotyCheckBox.isSelected()){
                    posortowane.addAll(rozklad.wyszukajNajblizszePrzyloty(data.getTime(), rozklad.szukajPrzylotyZ((String) comboxDocelowe.getSelectedItem(), init.getSiecLotnisk())));
                    table4=init.createRozkladTable(posortowane, 2);
                }
            }
            catch(BrakOdlotowException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak Odlotow", JOptionPane.ERROR_MESSAGE);
            }
            catch(BrakPrzylotowException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Brak przylotow", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(!dataCheckBox.isSelected() && comboxDocelowe.getModel().getSelectedItem().equals("ALL")){
            if(odlotyCheckBox.isSelected()){
                posortowane.addAll(rozklad.getListaodlotow());
                table4=init.createRozkladTable(posortowane, 1);
            }
            else if(przylotyCheckBox.isSelected()){
                posortowane.addAll(rozklad.getListaprzylotow());
                table4=init.createRozkladTable(posortowane, 2);
            }
        }
        else if(!dataCheckBox.isSelected() && !comboxDocelowe.getModel().getSelectedItem().equals("ALL")){
            if(odlotyCheckBox.isSelected()){
                try {
                    posortowane.addAll(rozklad.szukajOdlotyDoB((String) comboxDocelowe.getModel().getSelectedItem(), init.getSiecLotnisk()));
                    table4=init.createRozkladTable(posortowane, 1);
                } catch (BrakOdlotowException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Brak Odlotow", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(przylotyCheckBox.isSelected()){
                try {
                    posortowane.addAll(rozklad.szukajPrzylotyZ((String) comboxDocelowe.getModel().getSelectedItem(), init.getSiecLotnisk()));
                    table4=init.createRozkladTable(posortowane, 2);
                } catch (BrakPrzylotowException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Brak przylotow", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Zadenif", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return table4;
    }

    /**
     *  Wyszukuje Bilety, a właściwie połączenia między dwoma lotniskami
     * @param comBoxOdlotBilety
     * @param comBoxLotDoBilety
     * @param table5
     * @param init
     * @return zwraca tabelę
     */
    static JTable wyszukajBilety(JComboBox comBoxOdlotBilety, JComboBox comBoxLotDoBilety, JTable table5, Init init) {
        try{
            Lotnisko lotniskoA = init.getSiecLotnisk().getLotnisko((String) comBoxOdlotBilety.getModel().getSelectedItem());
            Lotnisko lotniskoB = init.getSiecLotnisk().getLotnisko((String) comBoxLotDoBilety.getModel().getSelectedItem());
            table5 = init.createBiletyTable(lotniskoA, lotniskoB);
            return table5;
        }
        catch(BrakLotniskaSiecLotniskException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Bład", 0);
        }
        return table5;
    }
}
