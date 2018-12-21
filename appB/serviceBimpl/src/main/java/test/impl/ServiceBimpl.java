package test.impl;

import com.lww.feature.dubbo.utils.FeatureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import test.ServiceB;
import test.ServiceC;

/**
 * author: liweiwei
 * Date: 2018/12/19
 */
public class ServiceBimpl implements ServiceB {

    @Autowired
    private ServiceC serviceC;
    @Override
    public String getFeature() {

        System.out.println("I'm in"+System.getProperty("_feature")+"feature" );
        return "appBfeature:"+FeatureUtil.getFeature()+"|"+"appBD_feature:" +System.getProperty("_feature")+"----------"
                +serviceC.getFeature();
    }
}
