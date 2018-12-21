package com.lww.feature.dubbo.utils;

import com.lww.feature.common.FeatureConstants;
import com.lww.feature.common.FeatureContext;

/**
 * author: liweiwei
 * Date: 2018/12/19
 */
public class FeatureUtil {


    /**
     * 获取当前调用链路所属feature。
     *
     * 如果当前应用本身设置了-D_feature则当前调用链路属于feature,
     * 如果当前应用没有设置feature则从FeatureContext获取当前链路所属feature。
     *
     * 当FeatureContext和-D_feature都不为空时，理论上不会出现FeatureContext和-D_feature不一致的情况
     *
     * 该方法保证不会return Blank。
     */
    public static String getFeature() {
        //被某个feature调用
        String contextFeature = FeatureContext.getFeature();
        if (null != contextFeature && contextFeature.trim().length() > 0) {
            return contextFeature.trim();
        }
        String feature = System.getProperty(FeatureConstants.FEATURE);
        //消费者所在应用本身就属于某个feature则
        if (null != feature
                && feature.trim().length() > 0) {
            return feature.trim();
        }
        return null;
    }

    public static boolean isInFeature() {
        return getFeature()==null ? false:true;
    }
}
