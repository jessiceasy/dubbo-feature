import com.lww.feature.dubbo.utils.FeatureUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.ServiceB;

import java.util.Date;

/**
 * author: liweiwei
 * Date: 2018/12/19
 */
public class Test  {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo-appA.xml"});
        context.start();
        // obtain proxy object for remote invocation
        ServiceB serviceB = (ServiceB) context.getBean("serviceB");
        // execute remote invocation
        try {
            System.out.println("----------------------");
            String feature = serviceB.getFeature();
            System.out.println("appAfeature:"+ FeatureUtil.getFeature()+"|"+"appAD_feature" +System.getProperty("_feature")+"---------" +feature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
