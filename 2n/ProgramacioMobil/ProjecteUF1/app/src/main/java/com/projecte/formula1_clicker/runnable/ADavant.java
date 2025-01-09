package com.projecte.formula1_clicker.runnable;

import android.util.Log;

import com.projecte.formula1_clicker.Main;

import java.math.BigDecimal;

public class ADavant implements Runnable {

    BigDecimal voltesPerSegon;

    public ADavant(BigDecimal num) {
        this.voltesPerSegon = num;
    }


    @Override
    public void run() {
        try{
            while(true) {
                Main.SumarVoltes(voltesPerSegon);
                Thread.sleep(1000);
            }
        } catch (Exception e) {

        }
    }
}
