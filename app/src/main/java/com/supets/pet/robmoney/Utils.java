package com.supets.pet.robmoney;

import android.content.Context;
import android.provider.Settings;

/**
 * MockBook
 *
 * @user lihongjiang
 * @description
 * @date 2017/12/6
 * @updatetime 2017/12/6
 */

public class Utils {

    public static void isSet(Context context) throws Settings.SettingNotFoundException {
        int accessibilityEnabled = Settings.Secure.getInt(context.getApplicationContext().getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        }
    }

    public static void isSet2() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
           // AccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
        }
    }
}
