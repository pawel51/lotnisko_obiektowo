package com.gui;

import com.klienci.*;
import com.klienci.exceptions.KlientJuzIstniejeKlientException;
import com.klienci.exceptions.NazwaZajetaBazaKlientoException;
import com.loty.*;
import com.loty.exceptions.BrakLotniskaSiecLotniskException;
import com.loty.exceptions.LotException;
import com.loty.exceptions.LotniskoException;
import com.loty.exceptions.ZlaTrasaException;
import com.samoloty.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 * Inicjuje tabelę oraz posiada metody wywoływane do updatu danych dabeli
 */
class Init {
    private  SiecLotnisk siecLotnisk;
    private  BazaSamolotow bazaSamolotow;
    private  BazaKlientow bazaKlientow;

    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JTable table4;
    private JTable table5;

    /**
     * Inicjuje tabele
     * @param tab1 klienci
     * @param tab2 samoloty
     * @param tab3 lotniska
     * @param tab4 loty
     * @param tab5 bilety
     */
    Init(JTable tab1, JTable tab2, JTable tab3, JTable tab4, JTable tab5){

        table1 = tab1;
        table2 = tab2;
        table3 = tab3;
        table4 = tab4;
        table5 = tab5;

        siecLotnisk = new SiecLotnisk(10);
        bazaSamolotow = new BazaSamolotow(30);
        bazaKlientow = new BazaKlientow(10);
        //klienci
        try{
            bazaKlientow.addKlient(new Firma("Beco", "153622"));
            bazaKlientow.addKlient(new Firma("CD", "2346545"));
            bazaKlientow.addKlient(new Firma("Finlandia", "859234"));
            bazaKlientow.addKlient(new Firma("Drone", "00012312"));
            bazaKlientow.addKlient(new Osoba("Pawel", "Szapiel", "99052802852"));
            bazaKlientow.addKlient(new Osoba("Natalia", "Nowak","01111002876"));
            bazaKlientow.addKlient(new Osoba("Anna", "Jablonowska", "72072605432"));

        }
        catch(NazwaZajetaBazaKlientoException | KlientJuzIstniejeKlientException e){
            e.printStackTrace();
        }

        //lotniska
        try{
            Lotnisko Warszawa = new Lotnisko("Warsaw", 52.23, 21, "Europe/Warsaw");
            siecLotnisk.add(Warszawa);
            Lotnisko Moskwa = new Lotnisko("Moscow", 55.75, 37.62, "Europe/Moscow");
            siecLotnisk.add(Moskwa);
            Lotnisko Monako = new Lotnisko("Monaco", 43.73, 7.41, "Europe/Monaco");
            siecLotnisk.add(Monako);
            Lotnisko Kair = new Lotnisko("Cairo", 30.06, 31.24, "Africa/Cairo");
            siecLotnisk.add(Kair);
            Lotnisko NowyJork = new Lotnisko("New_York", 40.71, -74.00, "America/New_York");
            siecLotnisk.add(NowyJork);
        }
        catch(LotniskoException e){
            e.printStackTrace();
        }
        RozkladLotniska r = siecLotnisk.getListaLotnisk().get(0).getRozklad();
        //samoloty
        for (int i = 0; i < 10; i++) {
            bazaSamolotow.add(new Boeing747(), siecLotnisk.getListaLotnisk().get(i%5));

            bazaSamolotow.add(new Boeing737(), siecLotnisk.getListaLotnisk().get(i%5));

            bazaSamolotow.add(new Embraer100(), siecLotnisk.getListaLotnisk().get(i%5));

            bazaSamolotow.add(new Embraer300(), siecLotnisk.getListaLotnisk().get(i%5));

        }
        //loty
        Calendar data = new GregorianCalendar(2020, 5,7, 8, 0);
        for(Lotnisko lotniskoA : siecLotnisk.getListaLotnisk()){
            for(Lotnisko lotniskoB : siecLotnisk.getListaLotnisk()){
                if(! (lotniskoA.getNazwa().equals(lotniskoB.getNazwa())) ){
                    Trasa trasa = null;
                    try {
                        trasa = new Trasa(lotniskoA, lotniskoB, siecLotnisk);
                    } catch (ZlaTrasaException e) {
                        e.printStackTrace();
                    }
                    for(Samolot samolot : lotniskoA.getListaSamolotow()){
                        if(samolot.getStan() == 1){
                            try {
                                Lot lot = new Lot(trasa, samolot, data.getTime());
                                lotniskoA.getRozklad().dodajLot(lot);
                                lotniskoB.getRozklad().dodajLot(lot);
                                data.add(Calendar.MINUTE, 30);
                            } catch (LotException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }


        table1=this.createBazaKlientowTable(2); table1.tableChanged(null);
        table2=this.createBazaSamolotowTable(); table2.tableChanged(null);
        table3=this.createSiecLotniskTable(); table3.tableChanged(null);
        table4=this.createRozkladTable(1); table4.tableChanged(null);
        try {
            this.table5 =this.createBiletyTable(siecLotnisk.getLotnisko("Warsaw"), siecLotnisk.getLotnisko("Monaco"));
        } catch (BrakLotniskaSiecLotniskException e) {
            e.printStackTrace();
        }
        this.table5.tableChanged(null);


    }

    /**
     * Tworzy tablicę klientów wyświetlającą informacje: "Numer id", "Nazwa", "Liczba kupionych biletów"
     * @param b
     * @return
     */
    JTable createBazaKlientowTable(List<Klient> b){
        String[] nazwyKolumn = new String[] {"Numer id", "Nazwa", "Liczba kupionych"};
        DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0 );

        table1.setModel(model);
        table1.createDefaultColumnsFromModel();

        for(Klient klient : b){
            model.addRow(new Object[] {
                    klient.getId(),
                    klient.getStringID(),
                    klient.getKupioneBilety().size() });
        }
        return table1;

    }
    JTable createBazaKlientowTable(int x){
        String[] nazwyKolumn = new String[] {"Numer id", "Nazwa", "Liczba kupionych"};
        DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0 );

        table1.setModel(model);
        table1.createDefaultColumnsFromModel();

        if(x == 2){
            for(Klient klient : bazaKlientow.getListaKlientow()){
                model.addRow(new Object[] {
                        klient.getId(),
                        klient.getStringID(),
                        klient.getKupioneBilety().size() });
            }
            return table1;
        }

        if(x == 1){
            for(Klient klient : bazaKlientow.getListaKlientow()){
                if(klient instanceof Firma){
                    model.addRow(new Object[] {
                            klient.getId(),
                            klient.getStringID(),
                            klient.getKupioneBilety().size() });
                }
            }
            return table1;
        }

        if(x == 0){
            for(Klient klient : bazaKlientow.getListaKlientow()){
                if(klient instanceof Osoba){
                    model.addRow(new Object[] {
                            klient.getId(),
                            klient.getStringID(),
                            klient.getKupioneBilety().size() });
                }
            }
        }
        return table1;
    }

    /**
     * Tworzy liste samolotów wyświetlającą informacje : "Id", "Marka", "Zasieg (km)", "Lokalizacja", "SredniaPredkosc", "MaxLiczbaMiejsc", "CenaPodstawowa (zł)", "CenaZaKm (zł)"
     * @param b
     * @return
     */
    JTable createBazaSamolotowTable(List<Samolot> b){
        String[] nazwyKolumn = new String[] {"Id", "Marka", "Zasieg (km)", "Lokalizacja", "SredniaPredkosc", "MaxLiczbaMiejsc", "CenaPodstawowa (zł)", "CenaZaKm (zł)"};
        DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0 );

        table2.setModel(model);
        table2.createDefaultColumnsFromModel();

        for(Samolot samolot : b){
            model.addRow(new Object[] {
                    samolot.getId(),
                    samolot.getMarka(),
                    samolot.getZasieg(),
                    samolot.getLokalizacja().getNazwa(),
                    samolot.getSredniaPredkosc(),
                    samolot.getMaxLiczbaMiejsc(),
                    samolot.getCenaPodstawowa(),
                    samolot.getCenaZaKm() });
        }
        return table2;

    }
    JTable createBazaSamolotowTable(){
        DefaultTableModel model = new DefaultTableModel(new String[] {"Id", "Marka", "Zasieg (km)", "Lokalizacja", "SredniaPredkosc", "MaxLiczbaMiejsc", "CenaPodstawowa (zł)", "CenaZaKm (zł)"}, 0);

        table2.setModel(model);
        table2.createDefaultColumnsFromModel();
        for(Samolot samolot : bazaSamolotow.getListaSamolotow()){
            model.addRow(new Object[] {
                    samolot.getId(),
                    samolot.getMarka(),
                    samolot.getZasieg(),
                    samolot.getLokalizacja().getNazwa(),
                    samolot.getSredniaPredkosc(),
                    samolot.getMaxLiczbaMiejsc(),
                    samolot.getCenaPodstawowa(),
                    samolot.getCenaZaKm() });
        }
        return table2;
    }

    /**
     * Tworzy liste lotnisk wyświetlającą informacje : "Nazwa", "Współrzędne N", "Współrzędne E", "Time Zone"
     * @return
     */
    JTable createSiecLotniskTable(){
        DefaultTableModel model = new DefaultTableModel(new String[] {"Nazwa", "Wsp N", "Wsp E", "Time Zone"}, 0);

        table3.setModel(model);
        for(Lotnisko lotnisko : siecLotnisk.getListaLotnisk()){
            model.addRow(new Object[] {
                    lotnisko.getNazwa(),
                    lotnisko.getWspN(),
                    lotnisko.getWspE(),
                    lotnisko.getZone().getDisplayName(Locale.ENGLISH)});
        }
        return table3;
    }
    JTable createSiecLotniskTable(List<Lotnisko> b){
        DefaultTableModel model = new DefaultTableModel(new String[] {"Nazwa", "Wsp N", "Wsp E", "Time Zone"}, 0);

        table3.setModel(model);
        for(Lotnisko lotnisko : b){
            model.addRow(new Object[] {
                    lotnisko.getNazwa(),
                    lotnisko.getWspN(),
                    lotnisko.getWspE(),
                    lotnisko.getZone().getDisplayName(Locale.ENGLISH)});
        }
        return table3;
    }

    /**
     * Tworzy tablicę odlotów lub przylotów wyświetlającą informacje , "Data odlotu/przylotu", "Lot do/lot z", "nr lotu", "czas lotu (h)", "samolot"
     * @param b
     * @param x
     * @return
     */
    JTable createRozkladTable(List<Lot> b, int x){
        if (x == 1) {
            String[] nazwyKolumn = new String[]{"<=>", "Data odlotu", "Lot do", "nr lotu", "czas lotu (h)", "samolot"};
            DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0);
            table4.setModel(model);
            table4.createDefaultColumnsFromModel();
                for(Lot lot : b){
                    model.addRow(new Object[] {
                            "odlot",
                            lot.getDataOdlotu().toString(),
                            lot.getTrasa().getLotniskoB(),
                            lot.getNumerLotu(),
                            Math.floor(lot.getCzasLotu()/60/60/1000*10)/10,
                            lot.getSamolot().getMarka()

                    });
            }
            return table4;
        }
        if( x == 2){
            System.out.println(b.get(0).getDataOdlotu());
            String[] nazwyKolumn = new String[]{"<=>", "Data przylotu", "Lot z ", "nr lotu", "czas lotu (h)", "samolot"};
            DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0);
            table4.setModel(model);
            table4.createDefaultColumnsFromModel();
                for(Lot lot : b){
                    model.addRow(new Object[] {
                            "przylot",
                            lot.getDataPrzylotu().toString(),
                            lot.getTrasa().getLotniskoA(),
                            lot.getNumerLotu(),
                            Math.floor(lot.getCzasLotu()/60/60/1000*10)/10,
                            lot.getSamolot().getMarka()

                    });
            }
            return table4;
        }
        return table4;

    }


    JTable createRozkladTable(int x) {


        if (x == 1) {
            String[] nazwyKolumn = new String[]{"<=>", "Data odlotu", "Lot do", "nr lotu", "czas lotu (h)", "samolot"};
            DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0);
            table4.setModel(model);
            table4.createDefaultColumnsFromModel();
            for(Lotnisko lotnisko : siecLotnisk.getListaLotnisk()){
                for(Lot lot : lotnisko.getRozklad().getListaodlotow()){
                     model.addRow(new Object[] {
                            "odlot",
                             lot.getDataOdlotu().toString(),
                             lot.getTrasa().getLotniskoB(),
                             lot.getNumerLotu(),
                             Math.floor(lot.getCzasLotu()/60/60/1000*10)/10,
                             lot.getSamolot().getMarka()
                     });
                }
            }
            return table4;
        }
        if( x == 2){
            String[] nazwyKolumn = new String[]{"<=>", "Data przylotu", "Lot z ", "nr lotu", "czas lotu (h)", "samolot"};
            DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0);
            table4.setModel(model);
            table4.createDefaultColumnsFromModel();
            for(Lotnisko lotnisko : siecLotnisk.getListaLotnisk()){
                for(Lot lot : lotnisko.getRozklad().getListaprzylotow()){
                    model.addRow(new Object[] {
                            "przylot",
                            lot.getDataPrzylotu().toString(),
                            lot.getTrasa().getLotniskoA(),
                            lot.getNumerLotu(),
                            Math.floor(lot.getCzasLotu()/60/60/1000*10)/10,
                            lot.getSamolot().getMarka()

                    });

                }
            }
            return table4;
        }
        return table4;
    }

    /**
     * Tworzy tablicę lotów wyświetlającą informacje potrzebne do kupienia biletu
     * @param lotniskoA
     * @param lotniskoB
     * @return
     */
    JTable createBiletyTable(Lotnisko lotniskoA, Lotnisko lotniskoB){
        String[] nazwyKolumn = {"Nr Lotu", "Data odlotu", "Liczba Wolnych", "Liczba miejsc", "Cena Ekonom (zł)", "Cena Biznes (zł)"};
        DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0);
        table5.createDefaultColumnsFromModel();
        table5.setModel(model);
        for(Lot lot : lotniskoA.getRozklad().getListaodlotow()){
            if (lot.getTrasa().getLotniskoB().getNazwa().equals(lotniskoB.getNazwa())) {
                model.addRow(new Object[] {
                        lot.getNumerLotu(),
                        lot.getDataOdlotu().toString(),
                        lot.getWolneMiejsca(),
                        lot.getSamolot().getMaxLiczbaMiejsc(),
                        Math.floor(lot.getCena()),
                        Math.floor(lot.getCena()*2.0)
                });
            }
        }
        return table5;

    }




    public SiecLotnisk getSiecLotnisk() {
        return siecLotnisk;
    }

    public BazaSamolotow getBazaSamolotow() {
        return bazaSamolotow;
    }

    public BazaKlientow getBazaKlientow() {
        return bazaKlientow;
    }

}

