package com.novas;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 讨鬼 on 2017/3/19.
 */
public class Kettle {
    private static Kettle kettle;
    public static String home;

    public String getHome() {
        return System.getenv("NOVAS_HOME");
    }

    public static Kettle getKettleInstance() throws IOException {
        if (kettle == null) {
            kettle = new Kettle();
        }
        return kettle;
    }

    private Kettle() throws IOException {
        home = getHome();
        File batdir = new File(home + "/bat");
        if (!batdir.exists()) {
            batdir.mkdirs();
        }

        File logdir = new File(home + "/log");
        if (!logdir.exists()) {
            logdir.mkdirs();
        }
        File taskdir = new File(home + "/task");
        if (!taskdir.exists()) {
            taskdir.mkdirs();
        }
    }
    public String rewriteJOB (long timestamp, String filename) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line = bufferedReader.readLine();
        String lastline = "";
        String[] var = filename.split("[.]");
        int index = filename.lastIndexOf("\\");
        String kettle_file_name = var[0].substring(index + 1);
        String newFileName = home + "/task/" + timestamp + "_" + kettle_file_name + "." + var[1];
        FileWriter fileWriter = new FileWriter(newFileName);
        StringBuilder stringBuilder = new StringBuilder();
        while (line != null) {
            if (lastline.contains("<job>") && line.contains("<name>")) {
                stringBuilder.append("<name>" + timestamp + "_" + kettle_file_name + "</name>\r\n");
            } else if (line.contains("<job-log-table>")) {
                stringBuilder.append("<job-log-table><connection>kettle-connection</connection>\r\n");
                stringBuilder.append("<schema>kettledb</schema>\r\n");
                stringBuilder.append("<table>kettle_job_log_table</table>\r\n");
                bufferedReader.readLine();
                bufferedReader.readLine();
            } else if (line.contains("</modified_date>")) {
                stringBuilder.append(line+"\r\n");
                stringBuilder.append("<connection>\r\n");
                stringBuilder.append("<name>kettle-connection</name>\r\n");
                stringBuilder.append("<server>localhost</server>\r\n");
                stringBuilder.append("<type>MYSQL</type>\r\n");
                stringBuilder.append("<access>Native</access>\r\n");
                stringBuilder.append("<database>kettledb</database>\r\n");
                stringBuilder.append("<port>3306</port>\r\n");
                stringBuilder.append("<username>root</username>\r\n");
                stringBuilder.append("<password>Encrypted 2be98afc86aa7f2e4cb79ce10cc9da0ce</password>\r\n");
                stringBuilder.append("<servername/>\r\n");
                stringBuilder.append("<data_tablespace/>\r\n");
                stringBuilder.append("<index_tablespace/>\r\n");
                stringBuilder.append("<attributes>\r\n");
                stringBuilder.append("<attribute><code>EXTRA_OPTION_MYSQL.defaultFetchSize</code><attribute>500</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>EXTRA_OPTION_MYSQL.useCursorFetch</code><attribute>true</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>FORCE_IDENTIFIERS_TO_LOWERCASE</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>FORCE_IDENTIFIERS_TO_UPPERCASE</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>IS_CLUSTERED</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>PORT_NUMBER</code><attribute>3306</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>PRESERVE_RESERVED_WORD_CASE</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>QUOTE_ALL_FIELDS</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>STREAM_RESULTS</code><attribute>Y</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>SUPPORTS_BOOLEAN_DATA_TYPE</code><attribute>Y</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>SUPPORTS_TIMESTAMP_DATA_TYPE</code><attribute>Y</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>USE_POOLING</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("</attributes>\r\n");
                stringBuilder.append("</connection>\r\n");
            } else {
                stringBuilder.append(line + "\r\n");
            }
            lastline = line;
            line = bufferedReader.readLine();
            fileWriter.write(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }
        bufferedReader.close();
        fileWriter.close();
        return newFileName;
    }
    public String rewriteKTR(long timestamp, String filename) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line = bufferedReader.readLine();
        String lastline = "";
        String[] var = filename.split("[.]");
        int index = filename.lastIndexOf("\\");
        String kettle_file_name = var[0].substring(index + 1);
        String newFileName = home + "/task/" + timestamp + "_" + kettle_file_name + "." + var[1];
        FileWriter fileWriter = new FileWriter(newFileName);
        StringBuilder stringBuilder = new StringBuilder();
        while (line != null) {
            if (lastline.contains("<info>") && line.contains("<name>")) {
                stringBuilder.append("<name>" + timestamp + "_" + kettle_file_name + "</name>\r\n");
            } else if (line.contains("<trans-log-table>")) {
                stringBuilder.append("<trans-log-table><connection>kettle-connection</connection>\r\n");
                stringBuilder.append("<schema>kettledb</schema>\r\n");
                stringBuilder.append("<table>kettle_trans_log_table</table>\r\n");
                bufferedReader.readLine();
                bufferedReader.readLine();
            } else if (line.contains("</notepads>")) {
                stringBuilder.append("</notepads>\r\n");
                stringBuilder.append("<connection>\r\n");
                stringBuilder.append("<name>kettle-connection</name>\r\n");
                stringBuilder.append("<server>localhost</server>\r\n");
                stringBuilder.append("<type>MYSQL</type>\r\n");
                stringBuilder.append("<access>Native</access>\r\n");
                stringBuilder.append("<database>kettledb</database>\r\n");
                stringBuilder.append("<port>3306</port>\r\n");
                stringBuilder.append("<username>root</username>\r\n");
                stringBuilder.append("<password>Encrypted 2be98afc86aa7f2e4cb79ce10cc9da0ce</password>\r\n");
                stringBuilder.append("<servername/>\r\n");
                stringBuilder.append("<data_tablespace/>\r\n");
                stringBuilder.append("<index_tablespace/>\r\n");
                stringBuilder.append("<attributes>\r\n");
                stringBuilder.append("<attribute><code>EXTRA_OPTION_MYSQL.defaultFetchSize</code><attribute>500</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>EXTRA_OPTION_MYSQL.useCursorFetch</code><attribute>true</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>FORCE_IDENTIFIERS_TO_LOWERCASE</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>FORCE_IDENTIFIERS_TO_UPPERCASE</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>IS_CLUSTERED</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>PORT_NUMBER</code><attribute>3306</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>PRESERVE_RESERVED_WORD_CASE</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>QUOTE_ALL_FIELDS</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>STREAM_RESULTS</code><attribute>Y</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>SUPPORTS_BOOLEAN_DATA_TYPE</code><attribute>Y</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>SUPPORTS_TIMESTAMP_DATA_TYPE</code><attribute>Y</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>USE_POOLING</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("</attributes>\r\n");
                stringBuilder.append("</connection>\r\n");
            } else {
                stringBuilder.append(line + "\r\n");
            }
            lastline = line;
            line = bufferedReader.readLine();
            fileWriter.write(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }
        bufferedReader.close();
        fileWriter.close();
        return newFileName;
    }
/*
    public String rewriteKTR(long timestamp, String filename) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line = bufferedReader.readLine();
        String lastline = "";
        String[] var = filename.split("[.]");
        int index = filename.lastIndexOf("\\");
        String kettle_file_name = var[0].substring(index + 1);
        String newFileName = home + "/task/" + timestamp + "_" + kettle_file_name + "." + var[1];
        FileWriter fileWriter = new FileWriter(newFileName);
        StringBuilder stringBuilder = new StringBuilder();
        while (line != null) {
            if (lastline.contains("<info>") && line.contains("<name>")) {
                stringBuilder.append("<name>" + timestamp + "_" + kettle_file_name + "</name>\r\n");
            } else if (line.contains("<trans-log-table>")) {
                stringBuilder.append("<trans-log-table><connection>kettle-aliyun</connection>\r\n");
                stringBuilder.append("<schema>kettleDB</schema>\r\n");
                stringBuilder.append("<table>KETTLE_TRANS_LOG_TABLE</table>\r\n");
                bufferedReader.readLine();
                bufferedReader.readLine();
            } else if (line.contains("</notepads>")) {
                stringBuilder.append("</notepads>\r\n");
                stringBuilder.append("<connection>\r\n");
                stringBuilder.append("<name>kettle-aliyun</name>\r\n");
                stringBuilder.append("<server>120.24.42.28</server>\r\n");
                stringBuilder.append("<type>MYSQL</type>\r\n");
                stringBuilder.append("<access>Native</access>\r\n");
                stringBuilder.append("<database>kettleDB</database>\r\n");
                stringBuilder.append("<port>3306</port>\r\n");
                stringBuilder.append("<username>kettle</username>\r\n");
                stringBuilder.append("<password>Encrypted 2be98afc86aa7f2e4cb79ce10bec3fd89</password>\r\n");
                stringBuilder.append("<servername/>\r\n");
                stringBuilder.append("<data_tablespace/>\r\n");
                stringBuilder.append("<index_tablespace/>\r\n");
                stringBuilder.append("<attributes>\r\n");
                stringBuilder.append("<attribute><code>EXTRA_OPTION_MYSQL.defaultFetchSize</code><attribute>500</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>EXTRA_OPTION_MYSQL.useCursorFetch</code><attribute>true</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>FORCE_IDENTIFIERS_TO_LOWERCASE</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>FORCE_IDENTIFIERS_TO_UPPERCASE</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>IS_CLUSTERED</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>PORT_NUMBER</code><attribute>3306</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>PRESERVE_RESERVED_WORD_CASE</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>QUOTE_ALL_FIELDS</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>STREAM_RESULTS</code><attribute>Y</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>SUPPORTS_BOOLEAN_DATA_TYPE</code><attribute>Y</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>SUPPORTS_TIMESTAMP_DATA_TYPE</code><attribute>Y</attribute></attribute>\r\n");
                stringBuilder.append("<attribute><code>USE_POOLING</code><attribute>N</attribute></attribute>\r\n");
                stringBuilder.append("</attributes>\r\n");
                stringBuilder.append("</connection>\r\n");
            } else {
                stringBuilder.append(line + "\r\n");
            }
            lastline = line;
            line = bufferedReader.readLine();
            fileWriter.write(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }
        bufferedReader.close();
        fileWriter.close();
        return newFileName;
    }
    */
    //获取字符串的
    public static long getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            return l;
        } catch (ParseException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
    public State getState(long taskid) throws SQLException, ClassNotFoundException {
        //声明Connection对象
        String ip="120.24.42.28";
        String userName="kettle";
        String password="123";
        String db="kettleDB";
        Connection connection=DBConnection.getDBConnectionInstance(ip,userName,password,db);
        State trans=null;
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from KETTLE_TRANS_LOG_TABLE WHERE TRANSNAME like '%"+taskid+"%'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                //获取stuname这列数据
                String transname = rs.getString("TRANSNAME");
                transname=transname.split("_")[1];
                String Status=rs.getString("STATUS");
                String endTime=rs.getString("ENDDATE");
                long endTime_L=getTime(endTime);
                 trans=new State(taskid,0,taskid,transname,Status,endTime_L);
                //输出结果
            }
            rs.close();
          //  connection.close();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");

        }

      return trans;
}
    //运行,0表示转换，1表示job
    public long run(int type,String filename) throws IOException, InterruptedException {
        String command="pan";
        long timestamp=System.currentTimeMillis();
        String KTRFileName="";
        if(type==0)
            KTRFileName=rewriteKTR(timestamp,filename);
        else
            KTRFileName=rewriteJOB(timestamp,filename);
        if(type==1)
        {
            command="kitchen";
        }
        System.out.println(KTRFileName);
        String batName = home+"/bat/"+"run.bat";
        Runtime rt = Runtime.getRuntime();
        Process ps = null;
        //第一个参数是命令类型 pan或者kitchen，第二个参数是log文件位置，第三个参数是脚本位置
        //log 文件命名为时间戳
        String logFileName=home+"/log/"+timestamp+".log";
        try {
            ps = rt.exec("cmd.exe /C start /b "+batName+" "+command+" "+logFileName+" "+KTRFileName);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ps.waitFor();
        int i = ps.exitValue();
        if (i == 0) {
            System.out.println("执行完成.") ;
            return timestamp;
        } else {
            System.out.println("执行失败.") ;
            return -1;
        }
    }
}
