/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author root
 */
public class MyUtils {
    public static void print(InputStream in) throws IOException{
        BufferedReader rd = new BufferedReader(new InputStreamReader(in));
        while (true){
            String line = rd.readLine();
            if(line==null) break;
            System.out.println(line);
        }
    }
}
