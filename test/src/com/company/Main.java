package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    //static String totalAttributes = "d8:c7:c8:94:0a:a1,d8:c7:c8:94:02:72,d8:c7:c8:94:02:71,d8:c7:c8:94:0a:a2,d8:c7:c8:94:38:a2,d8:c7:c8:94:38:a1,d8:c7:c8:94:23:d2,02:15:99:12:a1:a9,d8:c7:c8:94:23:d1,d8:c7:c8:94:13:d1,d8:c7:c8:94:27:82,d8:c7:c8:94:13:61,d8:c7:c8:94:27:81,d8:c7:c8:94:0a:a0,d8:c7:c8:94:02:70,d8:c7:c8:94:38:a0,d8:c7:c8:94:23:d0,d8:c7:c8:94:13:d0,d8:c7:c8:94:12:30,d8:c7:c8:94:16:c0,d8:c7:c8:94:12:31,d8:c7:c8:94:12:32,d8:c7:c8:1a:ce:f1,d8:c7:c8:94:04:41,d8:c7:c8:94:16:62,00:19:5b:be:5c:e9,d8:c7:c8:94:27:80,d8:c7:c8:1a:ce:f0,d8:c7:c8:94:13:60,d8:c7:c8:94:13:10,d8:c7:c8:94:04:40,d8:c7:c8:94:16:60,d8:c7:c8:94:09:d2,d8:c7:c8:1a:ce:b2,d8:c7:c8:1a:ce:f2,d8:c7:c8:94:08:62,32:cd:a7:37:ea:8a,d8:c7:c8:1a:ce:b1,d8:c7:c8:94:13:d2,d8:c7:c8:1a:cf:32,d8:c7:c8:94:09:d1,d8:c7:c8:94:13:62,d8:c7:c8:94:16:c1,d8:c7:c8:94:04:42,d8:c7:c8:94:13:11,d8:c7:c8:94:16:61,d8:c7:c8:1a:ce:b0,d8:c7:c8:1a:cf:30,d8:c7:c8:94:09:d0,d8:c7:c8:1a:cf:31,d8:c7:c8:94:16:c2,d8:c7:c8:94:08:60,d8:c7:c8:94:38:90,00:1e:e5:86:1a:4c,00:24:6c:58:3e:02,00:18:0a:34:eb:0c,00:24:6c:58:3e:01,d8:c7:c8:94:13:12,d8:c7:c8:94:08:61,32:cd:a7:30:4c:e5,e0:06:e6:56:0f:db,02:15:99:ec:dd:d5,d8:c7:c8:94:37:d1,00:24:6c:58:3e:00,d8:c7:c8:94:37:d2,d8:c7:c8:94:35:a2,d8:c7:c8:94:10:80,d8:c7:c8:1a:cf:e0,4c:60:de:3d:2d:30,a0:f3:c1:83:c3:9e,28:80:23:f5:f6:9a,d8:c7:c8:94:3c:62,d8:c7:c8:94:3c:61,d8:c7:c8:94:3c:60,d8:c7:c8:1a:cf:e2,d8:c7:c8:94:12:82,d8:c7:c8:94:12:81,d8:c7:c8:94:12:80,00:1e:58:06:3f:c3,d8:c7:c8:94:37:d0,d8:c7:c8:94:10:81,d8:c7:c8:94:38:92,d8:c7:c8:1a:cf:e1,d8:c7:c8:94:17:32,d8:c7:c8:94:17:31,d8:c7:c8:94:04:32,d8:c7:c8:94:15:d1,d8:c7:c8:94:04:31,d8:c7:c8:94:15:d0,d8:c7:c8:94:14:a2,d8:c7:c8:94:14:a0,d8:c7:c8:94:14:a1,d8:c7:c8:94:14:51,d8:c7:c8:94:04:30,d8:c7:c8:94:0c:20,d8:c7:c8:94:14:c0,d8:c7:c8:94:14:50,d8:c7:c8:94:10:82,d8:c7:c8:94:14:c1,d8:c7:c8:94:14:52,d8:c7:c8:94:38:91,d8:c7:c8:94:01:a0,d8:c7:c8:94:3c:32";
    public static void main(String[] args) {
	// write your code here
        makePivotSQLGenerateWekaHeader("res/Wifi (13).csv");
    }
    public static void makePivotSQLGenerateWekaHeader(String csvFile)
    {
        String bssidTable = readCSV(csvFile);
        String PIVOTSQL = createPIVOTSQL(bssidTable);
        generateWekaHeader(bssidTable);
        System.out.println(PIVOTSQL);
    }
    public static String createPIVOTSQL(String bssidTable)
    {
        String sqlFinal = "CREATE table RSSITable20151104 AS SELECT time,device,x,y,";
        String[] Bssid = bssidTable.split(",");
        for (int i = 0; i < Bssid.length - 1; i++)
        {
            String sqlCurrent = "AVG(CASE WHEN bssid = \"" + Bssid[i] + "\" THEN RSSI END) AS \"" + Bssid[i] + "\",";
            sqlFinal = sqlFinal + sqlCurrent;
        }
        sqlFinal = sqlFinal + "AVG(CASE WHEN bssid = \"" + Bssid[Bssid.length - 1] + "\" THEN RSSI END) AS \"" + Bssid[Bssid.length - 1] + "\" FROM `Wifi` where time >'2015-11-04 19:00:00'group by time;";
        return (sqlFinal);
    }
    public static String readCSV(String csvFile){
        BufferedReader br = null;
        String result = "";
        try {
            File file = new File(csvFile);
            br = new BufferedReader(new FileReader(file));
            result = br.readLine().toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }
    public static String generateWekaHeader(String totalAttributes){
        List<String> attributeList;
        attributeList = Arrays.asList(totalAttributes.split(","));
        String header = "";
        header = header+ "@relation 'TrainData"+ "'\n\n";

        for (int i = 0; i < attributeList.size(); i++) {
            header = header +  "@attribute " + attributeList.get(i) + " numeric\n";
        }
        header = header + "@attribute X numeric\n@attribute Y numeric\n@data\n";
        System.out.println(header);
        return header;
    }
}
