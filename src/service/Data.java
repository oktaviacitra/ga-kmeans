/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author oktaviacitra
 */
public class Data {
    private final int[][] set;

    public Data(int[][] set) {
        this.set = set;
    }
    
    public int[] getColumn(int index) {
        int size = set.length;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = set[i][index];
        }
        return result;
    }
}
