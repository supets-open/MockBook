package com.supets.pet.other;

import android.content.Context;
import android.content.Intent;

import com.supets.lib.supetscontext.App;
import com.supets.lib.supetsrouter.uinav.UINav;
import com.supets.pet.crash.CrashTimePref;
import com.supets.pet.mock.ui.MockCrashUiActivity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;


 class BugHandler2 implements UncaughtExceptionHandler {

    //	private static final String INSERT = "content://com.bslee.logtoolapk.db.BugProvider/bug/insert";
//	private static String DebugPackage = "com.supets.shop";
    private UncaughtExceptionHandler mDefaultHandler;
    private static BugHandler2 INSTANCE;
    private Context mContext;

    private BugHandler2() {
    }

    public static BugHandler2 getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new BugHandler2();
        }
        return INSTANCE;
    }

    public void init(Context ctx) {
        mContext = ctx;
        //DebugPackage = ctx.getPackageName();
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
            sendEmail(file);
            sendBroadCast(ex, thread, file);
        }
        return true;
    }

    private void sendBroadCast(Throwable e, Thread thread, String file) {
        //		List<ContentValues> list = getTraceInfo(e,  thread,file);
//		ContentResolver contentResolver = mContext.getContentResolver();
//		Uri insertUri = Uri.parse(BugHandler.INSERT);
//		for (ContentValues contentValues : list) {
//			contentResolver.insert(insertUri, contentValues);
//		}
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

    private void sendEmail(String sb) {
        Intent intent = new Intent(App.INSTANCE, MockCrashUiActivity.class);
        intent.putExtra("crashlog", sb);
        UINav.pushStandard(App.INSTANCE, intent);
    }

//    public List<ContentValues> getTraceInfo(Throwable e,
//                                            Thread thread, String file) {
//
//
//        List<ContentValues> lists = new ArrayList<>();
//        long time = System.currentTimeMillis();
//
//        StackTraceElement[] stacks = e.getCause().getStackTrace();
//        for (int i = 0; i < stacks.length; i++) {
//            if (stacks[i].getClassName().contains(BugHandler.DebugPackage)) {
//                ContentValues cv = new ContentValues();
//                cv.put("fileName", stacks[i].getFileName());
//                cv.put("message", getExceptionMessage(e));
//                cv.put("methedName", stacks[i].getMethodName());
//                cv.put("className", stacks[i].getClassName());
//                cv.put("createTime", time);
//                cv.put("lineNumber", stacks[i].getLineNumber());
//                cv.put("messageInfoPath", file);
//                cv.put("appName", getApplicationName());
//                cv.put("appPackage", mContext.getPackageName());
//                cv.put("phoneInfo", BugUtils.getPhoneInfo());
//                cv.put("taskinfo", BugUtils.getTaskStackTopActivity(mContext));
//                cv.put("thread", thread.getName());
//
//                lists.add(cv);
//                break;
//            }
//        }
//        return lists;
//    }

//    public static String getExceptionMessage(Throwable e) {
//        String message = e.toString();
//        if (message.lastIndexOf(":") != -1)
//            message = message.substring(0, message.lastIndexOf(":"));
//        return message;
//    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex, thread) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

//    public String getApplicationName() {
//        PackageManager packageManager = null;
//        ApplicationInfo applicationInfo = null;
//        try {
//            packageManager = mContext.getPackageManager();
//            applicationInfo = packageManager.getApplicationInfo(
//                    mContext.getPackageName(), 0);
//        } catch (PackageManager.NameNotFoundException e) {
//            applicationInfo = null;
//        }
//        String applicationName = (String) packageManager
//                .getApplicationLabel(applicationInfo);
//        return applicationName;
//    }
}