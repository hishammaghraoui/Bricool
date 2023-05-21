package com.example.brycool.chat;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class MemoryData {
    public static void savePhone(String data, Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(" phonee.txt ", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getPhone(Context context){
        String data = " ";
        try {
            FileInputStream fis = context.openFileInput("phonee.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static void saveLastMessageTS(String data,String chatid, Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(chatid+".txt ", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLastMessage(Context context, String chatid){
        String data = "0";
        try {
            FileInputStream fis = context.openFileInput(chatid+".txt ");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static void saveName(String data, Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(" namee.txt ", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static String getName(Context context){
        String data = "";
        try {
            FileInputStream fis = context.openFileInput("namee.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void saveUsername(String data, Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(" usernamee.txt ", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static String getUsername(Context context){
        String data = " ";
        try {
            FileInputStream fis = context.openFileInput("usernamee.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static void saveEmail(String data, Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(" Emaill.txt ", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static String getEmail(Context context){
        String data = " ";
        try {
            FileInputStream fis = context.openFileInput("Emaill.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static void savePassword(String data, Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(" Passwordd.txt ", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static String getPassword(Context context){
        String data = " ";
        try {
            FileInputStream fis = context.openFileInput("Passwordd.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}



