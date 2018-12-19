package com.lww.feature.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.*;
import com.lww.feature.common.FeatureConstants;
import com.lww.feature.common.FeatureContext;
import com.lww.feature.dubbo.utils.FeatureUtil;


/**
 * author: liweiwei
 * Date: 2018/12/18
 */
@Activate(group = Constants.CONSUMER)
public class ConsumerFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (invocation instanceof RpcInvocation){
            String feature = FeatureUtil.getFeature();
            if (null != feature) {
                ((RpcInvocation) invocation).setAttachment(FeatureConstants.FEATURE,feature);
            }
        }
        return invoker.invoke(invocation);
    }


}
