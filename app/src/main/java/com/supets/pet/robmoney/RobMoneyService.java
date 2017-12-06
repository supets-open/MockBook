package com.supets.pet.robmoney;


import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RobMoneyService extends AccessibilityService {
    private TextToSpeech mTts;

    @Override
    public void onCreate() {
        super.onCreate();
        mTts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    mTts.setLanguage(Locale.CHINA);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        parents = new ArrayList<>();

    }

    @RequiresApi(api = 21)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();

        AccessibilityNodeInfo source = event.getSource();

        if (source != null) {
            Log.v("click-source", "" + source.getClassName()
                    + "-" + source.getText() + "-"
                    + source.getViewIdResourceName());
        }


        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
                if (nodeInfo != null) {
                    recycle(nodeInfo);
                    if (parents.size() > 0) {
                        // parents.get(parents.size() - 1).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        parents.clear();
                    }
                }
                break;
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                AccessibilityNodeInfo source2 = event.getSource();
                if (source2 != null) {
                    Log.v("click-view", "" + source2.getClassName()
                            + "-" + source2.getText() + "-"
                            + source2.getViewIdResourceName());
                }
                break;

        }
    }


    private List<AccessibilityNodeInfo> parents;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void recycle(AccessibilityNodeInfo info) {
        try {
            if (info.getChildCount() == 0) {
                if (info.getText() != null) {
                    if (info.isClickable()) {
                        //info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        Log.v("click", "" + info.getClassName() + "-" + info.getText() + "-" + info.getViewIdResourceName());
                    }
//                    AccessibilityNodeInfo parent = info.getParent();
//                    while (parent != null) {
//                        if (parent.isClickable()) {
//                            parents.add(parent);
//                            Log.v("click", "" + parent.getClassName() + "-" + parent.getText()+ "-" + parent.getViewIdResourceName());
//                            break;
//                        }
//                        parent = parent.getParent();
//                    }
                }
            } else {
                for (int i = 0; i < info.getChildCount(); i++) {
                    if (info.getChild(i) != null) {
                        recycle(info.getChild(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTts.shutdown();
    }
}