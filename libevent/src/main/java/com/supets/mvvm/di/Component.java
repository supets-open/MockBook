package com.supets.mvvm.di;

import android.support.annotation.Keep;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Keep
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    Class<?> getSubClass() default String.class;
}