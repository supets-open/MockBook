package com.supets.pet.other;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.util.Log;

import com.supets.lib.json.JSonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

 class BugUtils {

    public static String getPhoneInfo() {
        return JSonUtil.toJson(new PhoneInfo());
    }

    public void readLogCat() {
        try {
            ArrayList<String> commandLine = new ArrayList<>();
            commandLine.add("logcat");
            commandLine.add("-d");
            commandLine.add("-v");
            commandLine.add("time");
            commandLine.add("-s");
            // commandLine.add("tag:W");
            commandLine.add("tag:W");
            Process process = Runtime.getRuntime().exec(
                    commandLine.toArray(new String[commandLine.size()]));
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()), 1024);
            String line = bufferedReader.readLine();
            while (line != null) {
                Log.v("log", line);
                Log.v("log", "===");
            }
        } catch (IOException e) {
        }
    }

    public void adbmonkey() {
        ArrayList<String> commandLine = new ArrayList<String>();
        commandLine.add("monkey");
        commandLine.add("-p");
        commandLine.add("com.example.logtoolapk");
        commandLine.add("-v");
        commandLine.add("1000");
        try {
            Runtime.getRuntime().exec(
                    commandLine.toArray(new String[commandLine.size()]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearLog() {
        try {
            Runtime.getRuntime().exec("logcat -c");
        } catch (Exception e) {
        }
    }

    public static String getTaskStackTopActivity(Context app) {
        try {
            ActivityManager am = (ActivityManager) app
                    .getSystemService(Activity.ACTIVITY_SERVICE);
            List<RunningTaskInfo> tasks = am.getRunningTasks(1);
            if (tasks == null || tasks.isEmpty()) {
                return "";
            }
            RunningTaskInfo info = tasks.get(0);
            StringBuffer sb = new StringBuffer();
            sb.append(info.topActivity.getClassName()).append(";")
                    .append(info.numActivities + "").append(";")
                    .append(info.baseActivity.getClassName());
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

}
