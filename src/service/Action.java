/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author oktaviacitra
 */
public class Action {

    public Action() {
    }
    
    public int[][] split(int[][] data, int index) {
        int[][] result = new int[2][data[0].length];
        for (int i = 0; i < 2; i++) {
            result[i] = data[index];
            index++;
        }
        return result;
    }
    
    public int[][] partOf(int[][] data, int[] range) {
        int size = range[1] - range[0] + 1;
        int[][] result = new int[2][size];
        for (int i = 0; i < 2; i++) {
            int k = range[0];
            for (int j = 0; j < size; j++) {
                result[i][j] = data[i][k];
                k++;
            }
        }
        return result;
    }
    
    public int[][] swap(int[] a, int[] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int temp = a[i];
            a[i] = b[i];
            b[i] = temp;
        }
        int[][] result = new int[2][n];
        for (int i = 0; i < n; i++) {
            result[0][i] = a[i];
            result[1][i] = b[i];
        }
        return result;
    }
    
    public int[][] merge(int[][] data, int[][] part, int[] range) {
        for (int i = 0; i < data.length; i++) {
            int k = 0;
            for (int j = range[0]; j <= range[1]; j++) {
                data[i][j] = part[i][k];
                k++;
            }
        }
        return data;
    }
    
    public int[][] join(int[][] parent, int[][] child) {
        int n = parent.length * 2;
        int m = parent.length;
        int[][] result = new int[n][m];
        for (int i = 0; i < parent.length; i++) {
            result[i] = parent[i];
        }
        int j = 0;
        for (int i = parent.length; i < n; i++) {
            result[i] = child[j];
            j++;
        }
        return result;
    }
    
    public double[][] getIndex(double[] data) {
        int n = data.length;
        int m = 2;
        double[][] result = new double[n][m];
        for (int i = 0; i < n; i++) {
            result[i][0] = i;
            result[i][1] = data[i];
        }
        return result;
    }
    
    public double[][] sort(double[][] data) {
        HashMap<Double, Double> unSorted = new HashMap<>();
        LinkedHashMap<Double, Double> reverseSortedMap = new LinkedHashMap<>();
        for (int i = 0; i < data.length; i++) {
            unSorted.put(data[i][0], data[i][1]);
        }
        unSorted.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        double[][] result = new double[data.length][data[0].length];
        int index = 0;
        for (Map.Entry<Double, Double> sort : reverseSortedMap.entrySet()) {
            result[index][0] = sort.getKey();
            result[index][1] = sort.getValue();
            index++;
        }
        return result;
    }
    
    public int[][][] compare(int[][][] data, double[][] fitnessIndex) {
        int row = data.length;
        int column = data[0].length;
        int[][][] result = new int[row][column][data[0][0].length];
        for (int i = 0; i < row; i++) {
            result[i] = data[(int)fitnessIndex[i][0]];
        }
        return result;
    }
}
