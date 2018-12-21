package com.lww.feature.dubbo.loadbalance;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.lww.feature.common.FeatureConstants;
import com.lww.feature.dubbo.utils.FeatureUtil;

import java.util.List;

/**
 * author: liweiwei
 * Date: 2018/12/18
 */
public class FeatureLoadBalance implements LoadBalance {

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        return doSelect(invokers);
    }

    /**
     *     jdk8以下不支持lamda表达式和stream
     */
    private <T> Invoker<T> doSelect(List<Invoker<T>> invokers) {
        //当前调用链路所属feature
        String chainFeature = FeatureUtil.getFeature();
        //属于某个feature
        if (null != chainFeature) {
            for (Invoker invoker : invokers) {
                if (chainFeature.equals(invoker.getUrl().getParameter(FeatureConstants.FEATURE, ""))) {
                    return invoker;
                }
            }
        }

        Invoker masterInvoker = null;

        for (Invoker invoker : invokers) {
            if (StringUtils.isBlank(invoker.getUrl().getParameter(FeatureConstants.FEATURE, ""))) {
                masterInvoker = invoker;
                break;
            }
        }

        return masterInvoker;

    }
}
