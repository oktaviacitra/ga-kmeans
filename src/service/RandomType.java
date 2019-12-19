/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author oktaviacitra
 */
public class RandomType {
    private int min;
    private int max;
    Random random = new Random();

    public RandomType() {
    }

    public RandomType(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    public int getNumber() {
        return random.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }
    
    public double getDecimal(double lower, double upper) {
        return random.doubles(lower, (upper + 1)).limit(1).findFirst().getAsDouble();
    }
    
    public int[] getDistinctNumber() {
        int size = max - min;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = min; i < max; i++)
            list.add(i);
        Collections.shuffle(list);
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = list.get(i);
        return array;
    }
    
    public int[][] getRange(int n) {
        int[][] result = new int[n][2];
        int k = max / n;
        int tempMin = 0;
        int tempMax = k;
        for(int i = 0; i < n; i++) {
            result[i][0] = tempMin;
            tempMin += k;
            result[i][1] = tempMax;
            tempMax += k;
        }
        if (max % 2 == 1 && result[n-1][1] != max)
            result[n-1][1] += 1;
        return result;
    }
}
