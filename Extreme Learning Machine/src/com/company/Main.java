package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String line = loadFile("res/labels.txt");
        String[] tokens = line.split(",");
        int[] label = new int[tokens.length];
        int index = 0;

        for(int i=0;i<tokens.length-1;i++)
        {
            label[i]=index;
            if(!tokens[i].equals(tokens[i+1]))
            {
                index++;
            }
        }
        StringBuilder out = new StringBuilder();
        for(int i=0;i<label.length;i++)
        {
            out.append(label[i]+",");

        }
        stringToFile("res/labels.csv",out.toString());
    }
    private static String loadFile(String path){
        String result = null;
        try{
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            result =bufferedReader.readLine();
            bufferedReader.close();
        }
        catch (IOException e)
        {
            System.out.println("Error: "+e);
            e.printStackTrace();
        }
        return result;
    }

    private static void stringToFile(String output, String text) {
        try {
            File file = new File(output);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();

        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

}
