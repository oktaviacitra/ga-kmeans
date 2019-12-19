/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Arrays;
/**
 *
 * @author oktaviacitra
 */
public class Find {
    private final int[] data;

    public Find(int[] data) {
        this.data = data;
    }
    
    public int maxValue() {
        Arrays.sort(data);
        return data[data.length - 1];
    }

    public int minValue() {
        Arrays.sort(data);
        return data[0];
    }
}
