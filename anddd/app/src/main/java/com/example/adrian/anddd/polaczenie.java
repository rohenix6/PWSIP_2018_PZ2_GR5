package com.example.adrian.anddd;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class polaczenie
{
    private static final String dane_bazy ="jdbc:mysql://:3306/and?useUnicode=yes&characterEncoding=utf8-8";
    private static final String uzytkownik_bazy ="root";
    private static final String haslo_bazy = "";

    @SuppressLint("NewApi")
    public Connection CONN()


    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(dane_bazy, uzytkownik_bazy, haslo_bazy);

        }catch (SQLException se)
        {
            Log.e("Erro", se.getMessage());

        }
        catch (ClassNotFoundException e)
        {
            Log.e("Erro", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("Erro", e.getMessage());
        }
        return conn;
    }
}
