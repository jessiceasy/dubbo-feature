package com.lww.feature.dubbo.registry;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.support.AbstractRegistryFactory;
import com.alibaba.dubbo.remoting.zookeeper.ZookeeperTransporter;


/**
 * author: liweiwei
 * Date: 2018/12/10
 */
public class ZookeeperRegistryExtFactory extends AbstractRegistryFactory {

    private ZookeeperTransporter zookeeperTransporter;

    public void setZookeeperTransporter(ZookeeperTransporter zookeeperTransporter) {
        this.zookeeperTransporter = zookeeperTransporter;
    }

    @Override
    protected Registry createRegistry(URL url) {
        return new ZookeeperRegistryExt(url, zookeeperTransporter);
    }
}
