package com.supets.pet.robmoney;


import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

public class RobMoneyService extends AccessibilityService {

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
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                AccessibilityNodeInfo nodeInfo = event.getSource();
                if (nodeInfo != null) {
                    recycle(nodeInfo);
                    if (parents.size() > 0) {
                        // parents.get(parents.size() - 1).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        parents.clear();
                    }
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
                        Log.v("click", "" + info.getViewIdResourceName());
                    }
                    AccessibilityNodeInfo parent = info.getParent();
                    while (parent != null) {
                        if (parent.isClickable()) {
                            parents.add(parent);
                            Log.v("click", "" + parent.getViewIdResourceName());
                            break;
                        }
                        parent = parent.getParent();
                    }
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
}