package com.loty;

import com.loty.exceptions.BrakLotniskaSiecLotniskException;
import com.loty.exceptions.ZlaTrasaException;

import javax.swing.*;

/**
 * Klasa trasy pomiędzy dwoma punktami na Ziemi, nie przechowuje informacji o ewentualnych lotach na tej trasie
 */
public class Trasa {
    private Lotnisko lotniskoA;
    private Lotnisko lotniskoB;
    private double odeglosc;

    public Trasa(Lotnisko lotniskoA, Lotnisko lotniskoB, SiecLotnisk siecLotnisk) throws ZlaTrasaException {
        if(lotniskoA.getNazwa().equals(lotniskoB.getNazwa()))
            throw new ZlaTrasaException("Dwa takie same lotniska");

        try {
            siecLotnisk.contains(lotniskoA.getNazwa());
            siecLotnisk.contains(lotniskoB.getNazwa());
            this.odeglosc = obliczOdleglosc(lotniskoA, lotniskoB);
            this.lotniskoA = lotniskoA;
            this.lotniskoB = lotniskoB;
        } catch (BrakLotniskaSiecLotniskException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Oblicza odległość między dwoma punktami na sferzę jaką jest Ziemia
     * @param lotniskoA
     * @param lotniskoB
     * @return odległość
     */
    public double obliczOdleglosc(Lotnisko lotniskoA, Lotnisko lotniskoB){
        double AwspN;
        double AwspE;
        double BwspN;
        double BwspE;
        if(lotniskoA.getWspN() > 0){
            AwspN = lotniskoA.getWspN()*Math.PI/180;
        }
        else{
            AwspN = (Math.abs(lotniskoA.getWspN())+90)*Math.PI/180;
        }
        if(lotniskoA.getWspN() > 0){
            BwspN = lotniskoA.getWspN()*Math.PI/180;
        }
        else{
            BwspN = (Math.abs(lotniskoA.getWspN())+90)*Math.PI/180;
        }
        AwspE = lotniskoA.getWspE()*Math.PI/180;
        BwspE = lotniskoB.getWspE()*Math.PI/180;
        double odleglosc = Math.acos(Math.cos(AwspN)*Math.cos(BwspN)+Math.sin(AwspN)*Math.sin(BwspN)*Math.cos(Math.abs(AwspE-BwspE)))*180/Math.PI*111.1;
        return odleglosc;

    }

    public Lotnisko getLotniskoA() {
        return lotniskoA;
    }

    public Lotnisko getLotniskoB() {
        return lotniskoB;
    }

    public double getOdeglosc() {
        return odeglosc;
    }

}
