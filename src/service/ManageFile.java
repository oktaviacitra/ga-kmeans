/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author oktaviacitra
 */
public class ManageFile {

    public ManageFile() {
    }
    
    public int[][] read(String name, int row, int col) {
        int[][] intArray = new int[row][col];
        String strLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            int count = 0;
            while (strLine != null) {
                strLine = br.readLine();
                if (strLine == null)
                    break;
                String[] stringArray = strLine.split(",");
                for (int i = 0; i < col; i++)
                    intArray[count][i] = Integer.valueOf(stringArray[i]);
                count++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return intArray;
    }
}
