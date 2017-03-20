package com.novas;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class InvokeBat2 {
    public void runbat(String batName) {
        try {
            Process ps = Runtime.getRuntime().exec(batName);
            //InputStream in = ps.getInputStream();
           // int c;
           // System.out.println("=====");

           // while ((c = in.read()) != -1) {
            //    System.out.print(c);// 如果你不需要看输出，这行可以注销掉
           // }
            System.out.println("=====");
           // in.close();
            ps.waitFor();
            System.out.println("=====");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("child thread done");
    }

    public static void main(String[] args) throws Exception
    {
        Kettle kettle=Kettle.getKettleInstance();
        Long taskid=kettle.run(0, "C:\\demo\\test.ktr");
        System.out.println(taskid);
       // Trans trans=kettle.getTransState(1489993945904L);
       // System.out.println(trans);
    }
}