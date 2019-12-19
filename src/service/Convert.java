/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oktaviacitra
 */
public class Convert {

    public Convert() {
    }
    
    public int[] to1D(int[][] data) {
        int n = data.length;
        int m = data[0].length;
        int[] result = new int[n*m];
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                result[count] = data[i][j];
                count++;
            }
        }
        return result;
    }
    
    public int[][] to2D(int[] data, int n, int m) {
        int[][] result = new int[n][m];
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                result[i][j] = data[count];
                count++;
            }
        }
        return result;
    }
    
    public List<List<Integer>> toList(int[][] data) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int[] ints : data) {
            List<Integer> list = new ArrayList<>();
            for (int i : ints) {
                list.add(i);
            }
            lists.add(list);
        }
        return lists;
    }
    
    public int[][] toArray(List<List<Integer>> data) {
        int row = data.size();
        int column = data.get(0).size();
        int[][] result = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                result[i][j] = data.get(i).get(j);
            }
        }
        return result;
    }
}
