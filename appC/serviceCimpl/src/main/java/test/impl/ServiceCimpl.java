package test.impl;

import com.lww.feature.dubbo.utils.FeatureUtil;
import test.ServiceC;

/**
 * author: liweiwei
 * Date: 2018/12/19
 */
public class ServiceCimpl implements ServiceC {
    @Override
    public String getFeature() {
        System.out.println("I'm in"+System.getProperty("_feature")+"feature" );
        return "appCfeature:"+ FeatureUtil.getFeature()+"|"+"appCD_feature:" +System.getProperty("_feature");
    }
}
