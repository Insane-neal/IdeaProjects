package com.company;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.clusterers.AbstractClusterer;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.pmml.jaxbbindings.Cluster;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/20.
 */
public class Weka {
    public Instances loadARFF(String dataSource){
        Instances data = null;
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(dataSource);
            data = source.getDataSet();
            //data.setClassIndex(data.numAttributes()-1);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return  data;
    }
    public double[] buildClassifier(String[] options, Instances trainingData, Instances testingData){
        AbstractClassifier randomForest = new RandomForest();
        double[] clsLabel = new double[testingData.size()];
        try {
            randomForest.setOptions(options);
            randomForest.buildClassifier(trainingData);
            for(int i =0;i<testingData.size();i++){
                clsLabel[i]=randomForest.classifyInstance(trainingData.instance(i));
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return clsLabel;
    }
    public double[] buildCluster(String[] options, Instances trainingData, Instances testingData){
        AbstractClusterer clusterer = new SimpleKMeans();
        double[] cluLabel = new double[testingData.size()];
        try {
            clusterer.setOptions(weka.core.Utils.splitOptions("weka.clusterers.SimpleKMeans -N 4 -A \"weka.core.EuclideanDistance -R first-last\" -I 500 -S 10"));
            clusterer.buildClusterer(trainingData);
            for(int i =0;i<testingData.size();i++){
                cluLabel[i]=clusterer.clusterInstance(trainingData.instance(i));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return cluLabel;
    }
    public double[] distanceCal(double[] classifiedLabel, double[] actualLabel){
        String csvFile = "res/GridInfo.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> totalGridInfo = new ArrayList<String[]>();
        double[] errorArray= new double[classifiedLabel.length];
        try{
            br=new BufferedReader(new InputStreamReader((new FileInputStream(csvFile)),"UTF-8"));
            while((line = br.readLine())!=null) {
                String[] grid = line.split(cvsSplitBy);
                System.out.println(grid[0]);
                System.out.println(grid[1]);
                System.out.println(grid[2]);
                totalGridInfo.add(grid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<classifiedLabel.length;i++){
            double clsX = Double.parseDouble(totalGridInfo.get((int)classifiedLabel[i])[0]);
            double clsY = Double.parseDouble(totalGridInfo.get((int)classifiedLabel[i])[1]);
            double actX = Double.parseDouble(totalGridInfo.get((int)actualLabel[i])[0]);
            double actY = Double.parseDouble(totalGridInfo.get((int)actualLabel[i])[1]);
            double error = Math.sqrt(Math.pow((clsX - actX), 2) / 2300 + Math.pow((clsY - actY), 2) / 2300);
            errorArray[i] = error;
        }
        return  errorArray;

    }




}
