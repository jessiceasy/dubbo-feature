package com.lww.feature.common;


/**
 * 每个
 * author: liweiwei
 * Date: 2018/12/17
 */
public class FeatureContext {


    /**
     * feature
     */
    private static ThreadLocal<String> feature = new ThreadLocal<String>();


    public static void setFeature(String feature) {
        if (feature == null) {
            return;
        }
        FeatureContext.feature.set(feature);
    }

    public static String getFeature() {
        return feature.get();
    }

    public static void removeFeature() {
        feature.remove();
    }

}
