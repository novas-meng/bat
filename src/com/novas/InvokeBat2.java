package com.novas;

import java.io.IOException;
import java.io.InputStream;

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
        InvokeBat2 test1 = new InvokeBat2();
        String batName = "C:\\demo\\run.bat";
        Runtime rt = Runtime.getRuntime();
        Process ps = null;
        try {
            ps = rt.exec("cmd.exe /C start /b "+batName);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ps.waitFor();
        int i = ps.exitValue();
        if (i == 0) {
            System.out.println("执行完成.") ;
        } else {
            System.out.println("执行失败.") ;
        }
    }
}