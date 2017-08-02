package com.supets.pet.other;

import android.os.Build;

/**
 * BugBook
 *
 * @user lihongjiang
 * @description
 * @date 2017/2/15
 * @updatetime 2017/2/15
 */

public class PhoneInfo {
    public String build_product = Build.PRODUCT;
    public String build_cpu_abi = Build.CPU_ABI;
    public String build_tags = Build.TAGS;
    public String build_model = Build.MODEL;
    public int build_version_sdk_int = Build.VERSION.SDK_INT;
    public String version_release = Build.VERSION.RELEASE;
    public String build_device = Build.DEVICE;
    public String build_display = Build.DISPLAY;
    public String build_brand = Build.BRAND;
    public String build_board = Build.BOARD;
    public String build_fingerprint = Build.FINGERPRINT;
    public String build_id = Build.ID;
    public String build_manufacturer = Build.MANUFACTURER;
    public String build_user = Build.USER;
}