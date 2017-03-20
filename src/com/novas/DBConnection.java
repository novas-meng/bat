package com.novas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 讨鬼 on 2017/3/20.
 */
public class DBConnection {
    private static Connection con;
    //驱动程序名
    String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名mydata
    String url = "jdbc:mysql://";
    //MySQL配置时的用户名
    String user ;
    //MySQL配置时的密码
    String password ;
    private DBConnection(String ip,String username,String password,String dbName) throws ClassNotFoundException, SQLException {
        url=url+ip+":3306/"+dbName;
        this.user=username;
        this.password=password;
        Class.forName(driver);
        //1.getConnection()方法，连接MySQL数据库！！
        con = DriverManager.getConnection(url, user, password);
    }
    public static Connection getDBConnectionInstance(String ip,String username,String password,String dbName) throws SQLException, ClassNotFoundException {
        if(con==null)
        {
            new DBConnection(ip,username,password,dbName);
        }
        return con;
    }
}
