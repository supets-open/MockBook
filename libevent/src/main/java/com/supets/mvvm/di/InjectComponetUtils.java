package com.supets.mvvm.di;

import android.app.Activity;
import android.support.annotation.Keep;
import android.support.annotation.LayoutRes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@Keep
public class InjectComponetUtils {


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
     * @param param  构造参数注入
     */
    public static void injectComponent(Object target, Class<?> type, Object param) {
        Field[] fields = target.getClass().getDeclaredFields();
        int var3 = fields.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Field field = fields[var4];
            Component annotation = field.getAnnotation(Component.class);
            if (annotation != null) {
                try {
                    Class<?> targetClass = annotation.getSubClass();
                    if (targetClass.equals(String.class)) {
                        targetClass = field.getType();
                    }
                    Constructor constructor = targetClass.getDeclaredConstructor(type);
                    constructor.setAccessible(true);
                    //构造函数参数列表的class类型
                    Object object = constructor.newInstance(param);
                    field.setAccessible(true);
                    field.set(target, object);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }
        }

    }

    public static void injectComponent(Object target, Class<?>[] classes, Object[] param) {
        Field[] fields = target.getClass().getDeclaredFields();
        int var3 = fields.length;
        for (int var4 = 0; var4 < var3; ++var4) {
            Field field = fields[var4];
            Component annotation = field.getAnnotation(Component.class);
            if (annotation != null) {
                try {
                    Class<?> targetClass = annotation.getSubClass();
                    if (targetClass.equals(String.class)) {
                        targetClass = field.getType();
                    }
                    Constructor constructor = targetClass.getDeclaredConstructor(classes);
                    constructor.setAccessible(true);
                    Object object = constructor.newInstance(param);
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