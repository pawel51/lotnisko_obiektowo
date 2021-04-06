package com.gui;

import com.klienci.Firma;
import com.klienci.Osoba;
import com.klienci.exceptions.BrakBiletowBazaKlientowException;
import com.klienci.exceptions.KlientJuzIstniejeKlientException;
import com.klienci.exceptions.NazwaZajetaBazaKlientoException;
import com.loty.Lotnisko;
import com.loty.exceptions.BrakLotniskaSiecLotniskException;
import com.loty.exceptions.LotniskoException;
import com.samoloty.*;
import com.samoloty.exceptions.BrakSamolotowBazaException;

import javax.swing.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.TimeZone;

/**
 * @author  Paweł Szapiel
 * @version 1.0
 */

public class TestMenu {

    private JPanel mainPanel;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JCheckBox checkBoxFirmy;
    private JCheckBox checkBoxOsoby;
    private JCheckBox checkBoxWszyscy;
    private JTextField textFieldImie;
    private JTextField textFieldNazwisko;
    private JTextField textFieldPesel;
    private JLabel nameLabel;
    private JLabel nazwiskoLabel;
    private JLabel peselLabel;
    private JButton dodajOsobeButton;
    private JTextField textFieldNazwa;
    private JTextField textFieldKrs;
    private JLabel nazwaLabel;
    private JButton dodajFirmeButton;
    private JTabbedPane bazyPane;
    private JPanel bazaKlientowPanel;
    private JPanel checkBoxyKlienciPanel;
    private JPanel dodawaniePanel;
    private JLabel krsLabel;
    private JScrollPane scrollTable1;
    private JScrollPane scrollTable2;
    private JPanel bazaSamolotowPanel;
    private JPanel bazaLotniskPanel;
    private JTextField textFieldWyszukiwarka;
    private JButton nazwaButton;
    private JLabel wyszukajLabel;
    private JButton nrIdButton;
    private JButton usunButton;
    private JButton wyszukajSamolotButton;
    private JPanel wyszukiwarkaSamolotow;
    private JTextField textFieldMinZasieg;
    private JTextField textFieldLotnisko;
    private JTextField textFieldMinMiejsc;
    private JButton usunSamolotButton;
    private JPanel dodajSamolotPanel;
    private JComboBox comBoxSamolot;
    private JComboBox comBoxLotnisko;
    private JButton dodajButton;
    private JPanel biletyPanel;
    private JTextField textFieldLotniskaLotnisko;
    private JButton wyszukajButton;
    private JComboBox comboxSamoloty2;
    private JCheckBox szukajSamolotowCheckBox;
    private JPanel dodajLotniskoPanel;
    private JTextField textFieldNazwaLotniska;
    private JTextField textFieldWspN;
    private JComboBox comBoxTimeZones;
    private JTextField textFieldWspE;
    private JButton dodajLotnisko;
    private JButton pokazWszystkichButton;
    private JButton pokazWszystkieButton;
    private JTextField textFieldDodajLotnisko;
    private JComboBox comboBoxSamoloty3;
    private JTable table4;
    private JComboBox comboxGodzina;
    private JComboBox comboxMinuty;
    private JComboBox comboxDzien;
    private JComboBox comBoxMiesiac;
    private JComboBox comboBoxRok;
    private JPanel jPanelCzasPick;
    private JCheckBox odlotyCheckBox;
    private JCheckBox przylotyCheckBox;
    private JComboBox comboBoxLotnisko1;
    private JLabel hyperlinkwsp;
    private JComboBox comBoxDocelowe;
    private JComboBox comBoxRozklad;
    private JScrollPane scrollPane4;
    private JButton szukajLotyButton;
    private JCheckBox dataCheckBox;
    private JComboBox comboxNoweLotniskoStart;
    private JComboBox comboxNoweLotniskoCel;
    private JComboBox comboxGodzina2;
    private JComboBox comboxdzien2;
    private JComboBox comboxmiesiac2;
    private JComboBox comboxminuta2;
    private JPanel dodajLotPanel;
    private JPanel dodajLotDataPanel;
    private JButton dodajNowyLotButton;
    private JComboBox comboxrok2;
    private JSpinner spinnerSamolotyId;
    private JLabel dataLabel;
    private JLabel samolotImage;
    private JLabel clockLabel;
    private JPanel rokzkladPanel;
    private JPanel odlotyPanel;
    private JTable table5;
    private JScrollPane scrollPaneBilety;
    private JComboBox comBoxLotDoBilety;
    private JComboBox comBoxOdlotBilety;
    private JButton szukajButton;
    private JSpinner liczbaBiletowSpinner;
    private JButton rezerwujButton;
    private JSpinner spinnerIdKlienta;
    private JLabel cenaLabel;
    private JButton obliczButton;
    private JCheckBox biznesCheckBox;
    private JCheckBox ekonomicznaCheckBox;
    private JTextField stringIdKlient;
    private JComboBox comBoxSamolotIdLot;
    private JScrollPane scrollPane1;



    public static void main(String[] args) {

        JFrame frame = new JFrame("System Linii Lotniczej");

        frame.setContentPane(new TestMenu().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(1600, 500);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }
    private void createUIComponents() {

        //Combobox Samoloty
        Samolot[] samolotArray = {new Boeing747(), new Boeing737(), new Embraer300(), new Embraer100()};
        comBoxSamolot = new JComboBox( samolotArray );
        comboxSamoloty2 = new JComboBox(samolotArray);
        comboBoxSamoloty3 = new JComboBox(samolotArray);
        spinnerSamolotyId = new JSpinner();
        SpinnerNumberModel spNrmodel = new SpinnerNumberModel(0, 0, 1000, 1);
        spinnerSamolotyId.setModel(spNrmodel);
        //combobox Strefy czasowe
        String[] strefy = TimeZone.getAvailableIDs();
        comBoxTimeZones = new JComboBox(strefy);
        samolotImage = new JMyLabel();
        clockLabel = new JMyLabel();

        liczbaBiletowSpinner = new JSpinner();
        SpinnerNumberModel model = new SpinnerNumberModel(0,0,500,1);
        liczbaBiletowSpinner.setModel(model);



    }

    private TestMenu() {

        /*
        Tabbed pane Klienci
        HERE -->
         */

        Init init = new Init(table1, table2, table3, table4, table5);

        Dodawanie dodawanie = new Dodawanie(init.getSiecLotnisk(), init.getBazaSamolotow(), init.getBazaKlientow(), init);


        checkBoxFirmy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkBoxFirmy.isSelected()) {
                    table1 = init.createBazaKlientowTable(1);
                    table1.tableChanged(null);
                }
            }
        });
        checkBoxOsoby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkBoxOsoby.isSelected()) {
                    table1 = init.createBazaKlientowTable(0);
                    table1.tableChanged(null);
                }
            }
        });
        checkBoxWszyscy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkBoxWszyscy.isSelected()) {
                    table1 = init.createBazaKlientowTable(2);
                    table1.tableChanged(null);
                }
            }
        });
        dodajOsobeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    init.getBazaKlientow().addKlient(new Osoba(textFieldImie.getText(), textFieldNazwisko.getText(), textFieldPesel.getText()));
                    JOptionPane.showMessageDialog(mainPanel, "Pomyślnie dodano klienta", "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
                } catch (NazwaZajetaBazaKlientoException | KlientJuzIstniejeKlientException e) {
                    JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "Nie można dodać klienta", JOptionPane.ERROR_MESSAGE);
                } finally {
                    textFieldPesel.setText("");
                    textFieldNazwisko.setText("");
                    textFieldImie.setText("");
                    table1 = init.createBazaKlientowTable(0);
                    table1.tableChanged(null);
                }
            }
        });
        dodajFirmeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    init.getBazaKlientow().addKlient(new Firma(textFieldNazwa.getText(), textFieldKrs.getText()));
                    JOptionPane.showMessageDialog(mainPanel, "Pomyślnie dodano klienta", "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
                } catch (KlientJuzIstniejeKlientException | NazwaZajetaBazaKlientoException e) {
                    JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "Nie można dodać klienta", JOptionPane.ERROR_MESSAGE);
                } finally {
                    textFieldKrs.setText("");
                    textFieldNazwa.setText("");
                    table1 = init.createBazaKlientowTable(1);
                    table1.tableChanged(null);
                }
            }
        });
        nazwaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Wyszukiwarki.wyszukajKlientNazwa(init, table1, textFieldWyszukiwarka);
            }
        });
        nrIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Wyszukiwarki.wyszukajKlientId(init, table1, textFieldWyszukiwarka);
            }
        });
        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                init.getBazaKlientow().usunKlient((String) table1.getModel().getValueAt(table1.getSelectedRow(), 0));
                table1 = init.createBazaKlientowTable(2);
                table1.tableChanged(null);
            }
        });
        /*
        Tabbed pane Samoloty
        HERE -->
         */
        wyszukajSamolotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //zasieg lotnisko miejsca
                table2 = Wyszukiwarki.wyszuajSamolot(textFieldMinMiejsc, textFieldMinZasieg, textFieldLotnisko, init);
                table2.tableChanged(null);
                }
        });
        usunSamolotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    init.getSiecLotnisk().getLotnisko((String)table2.getValueAt(table2.getSelectedRow(), 3)).usunSamolot(init.getBazaSamolotow().getSamolotPoStringID((String) table2.getValueAt(table2.getSelectedRow(), 0)));
                } catch (BrakLotniskaSiecLotniskException | BrakSamolotowBazaException e) {
                    e.printStackTrace();
                }
                init.getBazaSamolotow().usun((Double) table2.getModel().getValueAt(table2.getSelectedRow(), 0));
                table2 = init.createBazaSamolotowTable();
                table2.tableChanged(null);
            }
        });
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    init.getSiecLotnisk().contains(comboBoxLotnisko1.getSelectedItem().toString());
                    init.getBazaSamolotow().add((Samolot) comBoxSamolot.getModel().getSelectedItem(), init.getSiecLotnisk().getLotnisko(comboBoxLotnisko1.getSelectedItem().toString()));
                    table2 = init.createBazaSamolotowTable();
                    table2.tableChanged(null);
                    JOptionPane.showMessageDialog(null, "Pomyślnie dodano samolot marki " + comBoxSamolot.getModel().getSelectedItem().toString(), "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
                } catch (BrakLotniskaSiecLotniskException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        pokazWszystkichButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                table2 = init.createBazaSamolotowTable();
                table2.tableChanged(null);
            }
        });
        comboBoxSamoloty3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    table2 = init.createBazaSamolotowTable(init.getBazaSamolotow().wyszukajMarka(comboBoxSamoloty3.getSelectedItem().toString()));
                    table2.tableChanged(null);
                } catch (BrakSamolotowBazaException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //Lotniska panel HERE
        wyszukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Wyszukiwarki.wyszukajLotnisko(textFieldLotniskaLotnisko, szukajSamolotowCheckBox, comboxSamoloty2, table3, init);
            }
        });
        dodajLotnisko.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    init.getSiecLotnisk().add(textFieldNazwaLotniska.getText(), Double.parseDouble(textFieldWspN.getText()), Double.parseDouble(textFieldWspE.getText()), (String) comBoxTimeZones.getModel().getSelectedItem());
                    comboBoxLotnisko1.addItem(textFieldNazwaLotniska.getText());
                    textFieldNazwaLotniska.setText("");
                    textFieldWspE.setText("");
                    textFieldWspN.setText("");
                    JOptionPane.showMessageDialog(mainPanel, "Pomyślnie dodano Lotnisko", "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
                    table3 = init.createSiecLotniskTable();
                    table3.tableChanged(null);
                } catch (LotniskoException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Nazwa jest zajeta", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        pokazWszystkieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                table3 = init.createSiecLotniskTable();
                table3.tableChanged(null);
            }
        });

        hyperlinkwsp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    URI link = new URI(hyperlinkwsp.getText());
                    Wyszukiwarki.open(link);
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }

            }
        });
        //Loty tabbed pane here ->>
        szukajLotyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    table4 = Wyszukiwarki.wyszukajLoty(table4, dataCheckBox, comBoxDocelowe, init, comboBoxRok, comBoxMiesiac, comboxDzien, comboxGodzina, comboxMinuty, comBoxRozklad, odlotyCheckBox, przylotyCheckBox);
                    table4.tableChanged(null);
                } catch (BrakLotniskaSiecLotniskException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Brak lotniska", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        odlotyCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        przylotyCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });


        dodajNowyLotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                table4 = dodawanie.dodajLot(table4, comboxGodzina2, comboxminuta2, comboxdzien2, comboxmiesiac2, comboxrok2, comboxNoweLotniskoStart, comboxNoweLotniskoCel, spinnerSamolotyId);
                table4.tableChanged(null);
            }
        });
        comBoxRozklad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Lotnisko lotnisko = init.getSiecLotnisk().getLotnisko((String) comBoxRozklad.getModel().getSelectedItem());
                    dataLabel.setText(lotnisko.getKalendarz().getTime().toString());
                    ((JMyLabel)clockLabel).setImage("./images/clock.jpg");
                } catch (BrakLotniskaSiecLotniskException e) {
                    e.printStackTrace();
                }

            }
        });
        dataCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!dataCheckBox.isSelected())
                    System.out.println("Nie jestem zaznaczony");
            }
        });

        comBoxSamolot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((JMyLabel)samolotImage).wybierzImg(comBoxSamolot);
            }
        });
        //5. Bilety rezerwacja
        szukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                table5 = Wyszukiwarki.wyszukajBilety(comBoxOdlotBilety, comBoxLotDoBilety, table5, init);
                table5.tableChanged(null);
            }
        });
        rezerwujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    dodawanie.dodajBilety(table5, liczbaBiletowSpinner, comBoxOdlotBilety, stringIdKlient);
                    table1.tableChanged(null);
                } catch (BrakBiletowBazaKlientowException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Brak biletow", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });


        obliczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                    cenaLabel.setText(String.valueOf((((double) table5.getModel().getValueAt(table5.getSelectedRow(), 5))* (double)(int)(liczbaBiletowSpinner.getModel().getValue())))+" zł");
                if(ekonomicznaCheckBox.isSelected())
                    cenaLabel.setText(String.valueOf((((double) table5.getModel().getValueAt(table5.getSelectedRow(), 4))* (double)(int)liczbaBiletowSpinner.getValue()))+" zł");
            }
        });
        biznesCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        ekonomicznaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }



}



