package com.example.jeremy.shutupanddrive.Utensils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.jeremy.shutupanddrive.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmartinez on 9/24/15.
 */
public class SpeedSimulator extends MainActivity {
    private Context mContext;

    public SpeedSimulator(Context context){
        this.mContext = context;
    }

    // this function will read in a data file in the assests folder and out put each line by line
    public void readDataFile(){
        try{
            SpeedSimulator speedSimulator = new SpeedSimulator(this);

           InputStream is = mContext.getAssets().open("nmeaData.txt");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String text = new String(buffer);
            String[] lines = text.split(System.getProperty("line.separator"));
            for (String s: lines){
                // All items in the nmeaData Text file will be generated in this statement
                executeCommand(s);
            }


        }catch (Exception e){
            Log.d("APP", String.valueOf("[ReadDataFile] " + e.getMessage()));
        }
    }


    public void executeCommand(String cmd){
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            Thread.sleep(1000);
            socket = new Socket("localhost", 5554);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(cmd);
            System.out.println(in.readLine());
            out.close();
            in.close();
            socket.close();
        }
        catch (Exception e){
            Log.d("APP", String.valueOf("[executeCommand] " + e.getStackTrace()));
        }



    }



}

