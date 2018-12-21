package com.lww.feature.dubbo.registry;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;
import com.alibaba.dubbo.remoting.zookeeper.ZookeeperTransporter;
import com.lww.feature.common.FeatureConstants;

/**
 * author: liweiwei
 * Date: 2018/12/10
 */
public class ZookeeperRegistryExt  extends ZookeeperRegistry {

    public ZookeeperRegistryExt(URL url, ZookeeperTransporter zookeeperTransporter) {
        super(url, zookeeperTransporter);
    }

    @Override
    public void register(URL url) {
        // 判断当前服务器是否运行在
        String _feature = System.getProperty(FeatureConstants.FEATURE);
        if (StringUtils.isNotEmpty(_feature)) {
            String category = url.getParameter(Constants.CATEGORY_KEY, Constants.DEFAULT_CATEGORY);
            // 如果是提供者(providers)或者消费者（consumers）注册
            if (StringUtils.isEquals(Constants.PROVIDERS_CATEGORY, category)
                    || StringUtils.isEquals(Constants.CONSUMERS_CATEGORY, category)) {
                // 增加自定义属性：项目分区
                url = url.addParameter(FeatureConstants.FEATURE, _feature);
            }
        }
        super.register(url);
    }
}
