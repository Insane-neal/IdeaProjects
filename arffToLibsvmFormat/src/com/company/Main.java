package com.company;

import weka.core.Instances;
import weka.core.converters.AbstractFileSaver;
import weka.core.converters.LibSVMSaver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("res/TrainData20150504FeaturesWithoutUwindsor.arff"));
            Instances data = new Instances(reader);
            AbstractFileSaver saver = new LibSVMSaver();
            saver.setInstances(data);
            saver.setFile(new File("res/libsvmData2"));
            saver.writeBatch();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
