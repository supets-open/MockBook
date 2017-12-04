package com.supets.pet.crash;

import android.content.Context;

import com.supets.pet.mock.bean.CrashData;
import com.supets.pet.mock.dao.CrashDataDB;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

public class DefaultBugHandler implements UncaughtExceptionHandler {

    public static final String MOCK_SERVICE_CRASH = "mock.crash.service";

    private UncaughtExceptionHandler mDefaultHandler;
    private static DefaultBugHandler INSTANCE;
    private Context mContext;

    private DefaultBugHandler() {
    }

    public static DefaultBugHandler getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new DefaultBugHandler();
        }
        return INSTANCE;
    }

    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private boolean handleException(final Throwable ex, Thread thread) {
        if (ex == null) {
            return false;
        }

        ex.printStackTrace();

        if (CrashTimePref.isCrash()) {
            String file = saveBugFileInfo(ex);
            sendBroadCast(file);
        }

        return true;
    }

    private void sendBroadCast(String file) {
        CrashData data = new CrashData();
        data.setCrash(file);
        CrashDataDB.insertCrashData(data);
    }

    private String saveBugFileInfo(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        return sb.toString();
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex, thread) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

}