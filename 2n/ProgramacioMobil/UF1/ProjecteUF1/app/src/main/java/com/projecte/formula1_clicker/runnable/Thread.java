package com.projecte.formula1_clicker.runnable;

import com.projecte.formula1_clicker.Main;

import java.math.BigDecimal;

public class Thread implements Runnable {

    BigDecimal voltesPerSegon;

    public Thread(BigDecimal num) {
        this.voltesPerSegon = num;
    }


    @Override
    public void run() {
        try{
            while(true) {
                Main.SumarVoltes(voltesPerSegon);
                java.lang.Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
