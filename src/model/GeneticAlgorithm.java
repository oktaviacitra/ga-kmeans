/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import java.util.Arrays;
import service.Action;
import service.RandomType;

/**
 *
 * @author oktaviacitra
 */
public class GeneticAlgorithm {

    public GeneticAlgorithm() {
    }
    
    public double getFitness(double minimumDistance) {
        return (1 / minimumDistance);
    }
    
    public double[][] getRoullete(double[] fitness) {
        int n = fitness.length;
        double[][] roullete = new double[n][2];
        double sum = 0;
        for(int i = 0; i < n; i++) {
            roullete[i][0] = sum;
            sum += fitness[i];
            roullete[i][1] = sum;
        }
        return roullete;
    }
    
    public int[] getMachine(double[][] roullete) {
        int n = roullete.length;
        RandomType random = new RandomType();
        int[] index = new int[n];
        double[] decimal = new double[n];
        for(int i = 0; i < n; i++) {
            do {
                decimal[i] = random.getDecimal(roullete[0][0], roullete[n-1][1]);
            } while(decimal[i] > roullete[n-1][1]);
//            System.out.println(decimal[i]);
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (decimal[i] >= roullete[j][0] && decimal[i] <= roullete[j][1]) {
                    index[i] = j;
                    break;
                }
            }
        }
        return index;
    }
    
    public int[][][] getOffspring(int[][][] individu, int[] index) {
        int x = individu.length;
        int y = individu[0].length;
        int z = individu[0][0].length;
        int[][][] offspring = new int[x][y][z];
//        System.out.println("**" + individu.length);
//        System.out.println("--" + index.length);
        for(int i = 0; i < x; i++) {
            offspring[i] = individu[index[i]];
//            System.out.println(index[i]);
        }
        return offspring;
    }
    
    public int[][] getCrossOver(int[][] offspring, int[][] range) {
        int n = offspring.length;
        int m = offspring[0].length;
        Action action = new Action();
        double probability = 0.80;
        int[][] crossOver = new int[n][m];
        int i = 0, j = 0, index = 0;
        double prediction;
        while (i < n) {
            int[][] couple = action.split(offspring, i);
            do {
                prediction = new RandomType().getDecimal(0.0, 1.0);
            } while(prediction > 1.0);
//            System.out.println(prediction);
            if (prediction < probability) {
                int[][] genSelection = action.partOf(offspring, range[j]);
//                System.out.println("\n"+Arrays.toString(genSelection[0]) + "\t" + Arrays.toString(genSelection[1]));
                genSelection = action.swap(genSelection[0], genSelection[1]);
//                System.out.println("\n--"+Arrays.toString(genSelection[0]) + "\t" + Arrays.toString(genSelection[1]));
                couple = action.merge(couple, genSelection, range[j]);
            }
            crossOver[index] = couple[0];
            index++;
            crossOver[index] = couple[1];
            index++;
            j++;
            i += 2;
        }
        return crossOver;
    }
    
    public int[][] getRange(int n, int gen) {
        int[][] result = new int[n][2];
        for(int i = 0; i < n; i++) {
            do{
                result[i][0] = new RandomType(0, n-2).getNumber();
                do {
                    result[i][1] = new RandomType(0, n-1).getNumber();
                } while(result[i][0] == result[i][1]);
            } while(result[i][0] > result[i][1]);
        }
        return result;
    }
    
    public int[][] getMutation(int[][] crossOver, int[][] xRange, int[][] yRange) {
        int[][] mutation = crossOver;
        double probability = 0.80;
        double prediction;
        for (int i = 0; i < mutation.length; i++) {
            do {
                prediction = new RandomType().getDecimal(0.0, 1.0);
            } while(prediction > 1.0);
            if (prediction < probability) {
                int randomIndex = new RandomType(0, (mutation[i].length - 1)).getNumber();
//                System.out.println("*" +  randomIndex);
                int rangeIndex = (int) randomIndex/2;
//                System.out.println("**" +  rangeIndex);
                int left = 0, right = 0;
                if(randomIndex % 2 == 0) {
                    left = xRange[rangeIndex][0];
                    right = xRange[rangeIndex][1];
                } else {
                    left = yRange[rangeIndex][0];
                    right = yRange[rangeIndex][1];
                }
//                System.out.println("***" +  left+ " " + right);
                mutation[i][randomIndex] = new RandomType(left, right).getNumber();
            }
        }
        return mutation;
    }
    
    public int[][] getElitism(int[][] data) {
        int row = data.length / 2;
        int column = data[0].length;
        int[][] result = new int[row][column];
        for (int i = 0; i < row; i++) {
            result[i] = data[i];
        }
        return result;
    }
}
