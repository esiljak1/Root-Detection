package com.example.root_detection;

import android.os.Build;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class RootDetectionUnit {
    public static boolean isDeviceRooted(){
        return checkMethod1() || checkMethod2() || checkMethod3();
    }

    private static boolean checkMethod1(){
        String tags = Build.TAGS;
        return tags != null && tags.contains("test-keys");
    }

    private static boolean checkMethod2(){
        String[] paths = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for(String path : paths){
            if(new File(path).exists()){
                return true;
            }
        }
        return false;
    }

    private static boolean checkMethod3(){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] {"/system/xbin/which", "su"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if(reader.readLine() != null) return true;

            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(process != null) process.destroy();
        }
    }
}
