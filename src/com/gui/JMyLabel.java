package com.gui;

import com.samoloty.Samolot;

import javax.swing.*;

/**
 * Zajmuje się image'ami
 */
public class JMyLabel extends JLabel{
    JMyLabel(){
        super();
    }
    public void wybierzImg(JComboBox comBoxSamolot){
        String path =null;
        if(((Samolot)comBoxSamolot.getModel().getSelectedItem()).getMarka().equals("Boeing 737")){
            path = "./images/Boeing_737.jpg";
        }

        else if(((Samolot)comBoxSamolot.getModel().getSelectedItem()).getMarka().equals("Boeing 747")){
            path = "./images/Boeing_747.jpg";
        }
        else if(((Samolot)comBoxSamolot.getModel().getSelectedItem()).getMarka().equals("Embraer 100")){
            path = "./images/Embraer_100.jpg";
        }
        else if(((Samolot)comBoxSamolot.getModel().getSelectedItem()).getMarka().equals("Embraer 300")){
            path = "./images/Embraer_300.jpeg";
        }

        ImageIcon iconSamolot = new ImageIcon(path);
        this.setIcon(iconSamolot);

    }

    public void setImage(String path){
        this.setIcon(new ImageIcon(path));
    }

}
