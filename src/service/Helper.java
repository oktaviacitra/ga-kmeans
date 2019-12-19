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
public class Helper {

    public Helper() {
    }
    
    public int[][] filter(int[][] dataset, int number) {
        int row = dataset.length;
        int column = dataset.length;
        int count = 0;
        for(int i = 0; i < row; i++) {
            if(dataset[i][2] == number) {
                count++;
            }
        }
        int[][] result = new int[count][column];
        int iRow = 0;
        for(int i = 0; i < row; i++) {
            if(dataset[i][2] == number) {
                result[iRow][0] = dataset[i][0];
                result[iRow][1] = dataset[i][1];
                result[iRow][2] = dataset[i][2];
                iRow++;
            }
        }
        return result;
    }
    
//    public int[][] random(int[][] dataset) {
//        int[][] result = new int[4][2];
//        for(int i = 0; i < 0; i++) {
//            int[][] filter = filter(dataset, (i+1));
//            Data data = new Data(filter);
//            int[] x = data.getColumn(0);
//            Find find = new Find(x);
//            int xMax = find.maxValue();
//            int xMin = find.minValue();
//            RandomType random = new RandomType(xMin, xMax);
//            result[i][0] = random.getNumber();
//            int[] y = data.getColumn(0);
//            find = new Find(y);
//            int yMax = find.maxValue();
//            int yMin = find.minValue();
//            random = new RandomType(yMin, yMax);
//            result[i][1] = random.getNumber();
//        }
//        return result;
//    }
//    
    public int[][] range(int[][] dataset, int number) {
        int[][] result = new int[4][2];
        for(int i = 0; i < 4; i++) {
            int[][] filter = filter(dataset, (i+1));
            Data data = new Data(filter);
            int[] column = data.getColumn(number);
            Find find = new Find(column);
            result[i][0] = find.minValue();
            System.out.println(result[i][0]);
            result[i][1] = find.maxValue();
        }
        return result;
    }
}
