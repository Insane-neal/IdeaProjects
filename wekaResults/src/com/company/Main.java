package com.company;

import weka.classifiers.Classifier;
import weka.core.Instances;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Weka weka = new Weka();
        Instances trainData = weka.loadARFF("res/TrainData20150319FeasuresWithoutUwindsor.arff");
        double[] clusterResults = weka.buildCluster(null,trainData,trainData);

        double[] clsLable= new double[]{1};
        double[] actLable = new double[]{3};
        double[] errorArray = weka.distanceCal(clsLable,actLable);
        System.out.println(Arrays.toString(errorArray));
        // write your code here
    }

}
