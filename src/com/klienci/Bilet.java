package com.klienci;

import com.loty.Lot;

public class Bilet {
        private Lot lot;
        private Klient klient;
        private int nrSiedzenia;
        public Bilet(Lot lot, Klient klient){
            this.lot = lot;
            this.klient = klient;
            this.nrSiedzenia = lot.getSamolot().getMaxLiczbaMiejsc() - lot.getKupione().size();
        }
    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
}
