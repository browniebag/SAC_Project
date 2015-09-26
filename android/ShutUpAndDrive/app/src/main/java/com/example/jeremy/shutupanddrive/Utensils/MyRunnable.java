package com.example.jeremy.shutupanddrive.Utensils;

import android.content.Context;

/**
 * Created by fmartinez on 9/25/15.
 */
public class MyRunnable implements Runnable {
    private Context ctx;
    public MyRunnable(Context cxt){
        this.ctx = cxt;
    }
    public void run(){
        // Method to run the speed testing for speed simulation.
        SpeedSimulator ss = new SpeedSimulator(ctx);
        ss.readDataFile();

    }
}

