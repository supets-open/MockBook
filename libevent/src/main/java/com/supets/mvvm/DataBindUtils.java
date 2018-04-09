package com.supets.mvvm;

import android.app.Activity;
import android.support.annotation.Keep;
import android.support.annotation.LayoutRes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@Keep
public class DataBindUtils {


    /**
     * 注入xml布局给activity
     *
     * @param activity
     */
    public static void injectActivityUI(Activity activity) {
        Class<? extends Activity> classd = activity.getClass();
        ComponentLayout contentView = classd.getAnnotation(ComponentLayout.class);
        int res = contentView.value();
        activity.setContentView(res);
    }

    public static
    @LayoutRes
    int injectViewGroupUI(Object object) {
        ComponentLayout contentView = object.getClass().getAnnotation(ComponentLayout.class);
        return contentView.value();
    }

    /**
     * 注入有参实例
     *
     * @param target 目标对象
     * @param param1 构造参数注入
     */
    public static void injectComponent(Object target, Object param1) {
        Field[] fields = target.getClass().getDeclaredFields();//所有字段
        Field[] var2 = fields;
        int var3 = fields.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            Component annotation = field.getAnnotation(Component.class);
            if (annotation != null) {
                try {
                    Class<?> targetClass = annotation.getSubClass();
                    if (targetClass.equals(String.class)) {
                        targetClass = field.getType();
                    }
                    Constructor constructor = targetClass.getDeclaredConstructor(param1.getClass());
                    constructor.setAccessible(true);
                    //构造函数参数列表的class类型
                    Object object = constructor.newInstance(param1);
                    field.setAccessible(true);
                    field.set(target, object);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }
        }

    }

    /**
     * 注入无参实例
     *
     * @param target 注入目标对象
     */
    public static void injectComponentNo(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();//所有字段
        Field[] var2 = fields;
        int var3 = fields.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            ComponentNo annotation = field.getAnnotation(ComponentNo.class);
            if (annotation != null) {
                try {
                    Class<?> targetClass = annotation.getSubClass();
                    if (targetClass.equals(String.class)) {
                        targetClass = field.getType();
                    }
                    Constructor constructor = targetClass.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    Object object = constructor.newInstance();
                    field.setAccessible(true);
                    field.set(target, object);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }
        }

    }

}