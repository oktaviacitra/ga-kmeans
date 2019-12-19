/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import graph.SimpleGraph;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JFrame;
//import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.WindowConstants;
import model.GeneticAlgorithm;
import model.KMeans;
import service.Action;
import service.Convert;
import service.Data;
import service.Find;
import service.ManageFile;
import service.RandomType;
import view.Chart;
//import java.util.Arrays;
import java.util.Scanner;
import service.Helper;

/**
 *
 * @author oktaviacitra
 */
public class Main extends JFrame{
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        String name = "/Users/oktaviacitra/NetBeansProjects/GA-KMEANS/src/assets/ruspini.csv";
        SimpleGraph graph = new SimpleGraph();
        graph.setGridSpreadX(50);
        graph.setGridSpreadY(50);
        graph.display();
        ManageFile file = new ManageFile();
        Convert convert = new Convert();
        Action action = new Action();
//        int total = 5000;
        System.out.print("Masukkan jumlah generasi: ");
        int total = scanner.nextInt();
        int row = 75;
        int col = 3;
        int[][] dataset = file.read(name, row, col);
        System.out.print("Masukkan jumlah centroid: ");
//        int k = 4;
        int k = scanner.nextInt();
        System.out.print("Masukkan jumlah individu: ");
//        int n = 10;
        int n = scanner.nextInt();
        int gen = k * 2;
        double[][] bestFitness = new double[total][n];
        int[][][] individu = new int[n][k][2];
        
        Helper helper = new Helper();
        int[][] xRange = helper.range(dataset, 0);
        int[][] yRange = helper.range(dataset, 1);
        for(int i = 0; i < n; i++) {
            int[] xRandom = new int[k];
            for(int j = 0; j < k; j++) {
                RandomType randomInRange = new RandomType(xRange[j][0], xRange[j][1]);
//                RandomType randomInRange = new RandomType(xMin, xMax);
                xRandom[j] = randomInRange.getNumber();
            }
            int[] yRandom = new int[k];
            for(int j = 0; j < k; j++) {
                RandomType randomInRange = new RandomType(yRange[j][0], yRange[j][1]);
//                RandomType randomInRange = new RandomType(yMin, yMax);
                yRandom[j] = randomInRange.getNumber();
            }
            int[][] xyRandom = new int[k][2];
            for(int j = 0; j < k; j++) {
                xyRandom[j][0] = xRandom[j];
                xyRandom[j][1] = yRandom[j];
            }
            individu[i] = xyRandom;
        }
//        for(int i = 0; i < 4; i++) {
//            for(int j = 0; j < 2; j++) {
//                System.out.print(xRange[i][j]);
//            }
//            System.out.print("\n");
//        }
        GeneticAlgorithm GA = new GeneticAlgorithm();
        for(int l = 0; l < total; l++) {
            System.out.println("Process-"+ l);
            double[] fitness = new double[n];
            for(int i = 0; i < n; i++) {
                KMeans kmeans = new KMeans(dataset, individu[i]);
                double minimumDistance = kmeans.getMinimumDistance();
                fitness[i] = GA.getFitness(minimumDistance);
            }
            double[][] roullete = GA.getRoullete(fitness);
            int[] index = GA.getMachine(roullete);
//            System.out.println(l + "==");
            int[][][] selection = GA.getOffspring(individu, index);
            int[][] offspring = new int[n][gen];
            for(int i = 0; i < n; i++) {
                offspring[i] = convert.to1D(selection[i]);
            }
            List<List<Integer>> temp = convert.toList(offspring);
            int[][] range = GA.getRange((n/2), gen);
            int[][] crossOver = GA.getCrossOver(offspring, range);
            int[][] child = GA.getMutation(crossOver, xRange, yRange);
            int[][] parent = convert.toArray(temp);
            int[][] parentNchild = action.join(parent, child);
            int[][][] join = new int[n*2][k][2];
            double[] etilismFitness = new double[n*2];
            for(int i = 0; i < (2*n); i++){
                join[i] = convert.to2D(parentNchild[i], k, 2);
            }
            for(int i = 0; i < (n*2); i++) {
                KMeans kmeans = new KMeans(dataset, join[i]);
                double minimumDistance = kmeans.getMinimumDistance();
                etilismFitness[i] = GA.getFitness(minimumDistance);
            }
            double[][] fitnessNindex = action.getIndex(etilismFitness);
            double[][] ranking = action.sort(fitnessNindex);
            int[][][] winner = action.compare(join, ranking);
            graph.clearPoints();
            for(int i = 0; i < dataset.length; i++) {
                graph.addPoint(dataset[i][0], dataset[i][1], Color.BLUE);
            }
            for(int i = 0; i < winner[0].length; i++) {
                graph.addPoint(winner[0][i][0], winner[0][i][1], Color.RED);
            }
            for(int i = 0; i < n; i++) {
                individu[i] = winner[i];
                bestFitness[l][i] = fitnessNindex[i][1];
            }
        }
        Chart chart = new Chart(total, n, bestFitness);
        chart.setSize(600, 400);
        chart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chart.setVisible(true);
    }
}
