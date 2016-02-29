package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

//    public static void main(String[] args) {
////        readCSV("res/Wifi (1).csv");
////        String bssidTable = null;
////        String PIVOTSQL = createPIVOTSQL(bssidTable);
////        System.out.println(PIVOTSQL);
//        System.out.println("123");
//        // write your code here
//    }




//    public static String createPIVOTSQL(String bssidTable)
//    {
//        String sqlFinal = "CREATE VIEW [RSSI] AS SELECT time,";
//        String[] Bssid = bssidTable.split(",");
//        for (int i = 0; i < Bssid.length - 1; i++)
//        {
//            String sqlCurrent = "AVG(CASE WHEN bssid = '" + Bssid[i] + "' THEN RSSI END) AS '" + Bssid[i] + "',";
//            sqlFinal = sqlFinal + sqlCurrent;
//        }
//        sqlFinal = sqlFinal + "AVG(CASE WHEN bssid = '" + Bssid[Bssid.length - 1] + "' THEN RSSI END) AS '" + Bssid[Bssid.length - 1] + "' FROM RSSI2 group by time;";
//        return (sqlFinal);
//    }
//    public static String readCSV(String csvFile){
//        BufferedReader br = null;
//        String result = null;
//        try {
//
//            br = new BufferedReader(new FileReader(csvFile));
//            result = br.toString();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return result;
//        }
//    }
}
