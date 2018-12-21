package com.lww.feature.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.lww.feature.common.FeatureConstants;
import com.lww.feature.common.FeatureContext;



/**
 * author: liweiwei
 * Date: 2018/12/18
 */
@Activate(group = Constants.PROVIDER)
public class ProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (invocation instanceof RpcInvocation){
            String attachment = invocation.getAttachment(FeatureConstants.FEATURE);
            if (null != attachment && attachment.trim().length() > 0) {
                FeatureContext.removeFeature();
                FeatureContext.setFeature(attachment);
            }
        }
        return invoker.invoke(invocation);
    }
}
