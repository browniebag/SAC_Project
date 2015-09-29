/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedsimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author fmartinez
 */
public class SpeedSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "nmeaData.txt";
        if(fileFound(fileName)){
                readFile(fileName);
        }
    }
    private static void executeCommand(String cmd){
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String androidCmd = "geo nmea " + cmd;
        try {
            Thread.sleep(100);
            socket = new Socket("localhost", 5554);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(androidCmd);
            System.out.println(in.readLine());
            out.close();
            in.close();
            socket.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    // executed when the file is found and ready line by line.
    private static void readFile(String fileName){

        try{
           
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            String[] data = null;
            while((line = br.readLine()) != null){
                data = line.split(";");
            }
            for (int i = 0; i < data.length; i++) {
                executeCommand(data[i]);
            }
                    
        }catch(Exception ex){
            System.out.println("[readFile] " + ex.getMessage());
        }
    }
    // Method to check if the file exists read in by the main program 
    private static boolean fileFound(String _fileName) {
       Boolean results = false;
       try{
            File f = new File(_fileName);
            if(f.exists() && !f.isDirectory()){
                results = true;
            }
       }catch(Exception ex){
           System.out.println("[fileFound] " + ex.getMessage());
           results = false; 
       }
       
       return results;
    }
    
}
