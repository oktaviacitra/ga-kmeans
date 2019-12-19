/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;

/**
 *
 * @author oktaviacitra
 */
public class KMeans {
    private final int[][] dataset;
    private final int[][] centroids;

    public KMeans(int[][] dataset, int[][] centroids) {
        this.dataset = dataset;
        this.centroids = centroids;
    }
    
    public double getDistance(int[] data, int[] centroid) {
        return Math.sqrt((Math.pow(data[1] - centroid[1], 2)) + (Math.pow(data[0] - centroid[0], 2)));
    }
    
    public double getNearest(double[] distances) {
        Arrays.sort(distances);
        return distances[0];
    }
    
    public double getMinimumDistance() {
        int n = centroids.length;
        int m = dataset.length;
        double[] result = new double[n];
        for(int i = 0; i < n; i++) {
            double[] distances = new double[m];
            for(int j = 0; j < m; j++) {
                distances[j] = getDistance(dataset[j], centroids[i]);
            }
            result[i] = getNearest(distances);
        }
        return Arrays.stream(result).sum();
    }
}
