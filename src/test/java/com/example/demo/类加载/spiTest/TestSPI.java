package com.example.demo.类加载.spiTest;

import com.example.demo.spi.QiyiCDN;
import com.example.demo.spi.UploadCDN;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @Author XZQ
 * @Date 2021/7/10 12:52
 **/
public class TestSPI {
    public static void main(String[] args) throws IntrospectionException {
        /**
         *   JDK SPI方法
         *   配置文件是用接口全限定名做文件名，文件里面写实现类的全限定名
         */
        ServiceLoader<UploadCDN> uploadCDN = ServiceLoader.load(UploadCDN.class);
        for (UploadCDN u : uploadCDN) {
            u.upload("filePath");
        }
        /**
         *   Spring SPI方法
         *   配置文件必须是 接口=实现类 的key-value形式，多个实现类用逗号隔开
         */
        //获取所有META-INF/spring.factories中的value值
        List<String> applicationContextInitializers = SpringFactoriesLoader.loadFactoryNames(UploadCDN.class, TestSPI.class.getClassLoader());
        for (String applicationContextInitializer : applicationContextInitializers) {
            System.out.println("value:"+applicationContextInitializer);
        }
        //实例化所有在META-INF/spring.factories配置的且实现UploadCDN接口的类
        List<UploadCDN> UploadCDNs = SpringFactoriesLoader.loadFactories(UploadCDN.class, TestSPI.class.getClassLoader());
        for (UploadCDN UploadCDN : UploadCDNs) {
            UploadCDN.upload("ssss");
        }
    }
}
