package com.example.adrian.anddd;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class polaczenie
{
    private final String dane ="jdbc:mysql://192.168.42.169:3306/and?useUnicode=yes&characterEncoding=utf-8";
    private final String uzytkownik ="sad";
    private final String haslo = "";

    @SuppressLint("NewApi")
    public Connection CONN()


    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(dane, uzytkownik, haslo);
            conn = DriverManager.getConnection(ConnURL);

        }catch (SQLException se)
        {
            Log.e("Erro", se.getMessage());

        }
        catch (ClassNotFoundException xe)
        {
            Log.e("Erro", xe.getMessage());
        }
        catch (Exception e)
        {
            Log.e("Erro", e.getMessage());
        }
        return conn;
    }


}
