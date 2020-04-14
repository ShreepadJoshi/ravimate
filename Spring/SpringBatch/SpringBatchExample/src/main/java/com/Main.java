package com;

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {

        File file = new File("report.csv");
        FileOutputStream fos = new FileOutputStream(file);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < 1000000; i++) {
            bw.write(getRecord(i));
            bw.newLine();
        }

        bw.close();


    }

    // 1002,"320,200",1080,"staff 1", 30/7/2013
    private static String getRecord(int i) {
        return  i +  ",213100" + "," + "980" + "," +  "Bob" + "," + "29/7/2013";
    }


}
