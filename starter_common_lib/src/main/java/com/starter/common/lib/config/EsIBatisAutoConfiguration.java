package com.starter.common.lib.config;

import com.starter.common.lib.ibatis.SqlSessionFactory;
import com.starter.common.lib.ibatis.SqlSessionFactoryBuilder;
import com.starter.common.lib.spring.MapperFactoryBean;
import com.starter.common.lib.spring.MapperScannerConfigurer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.Resource;


/**
 * @author frankq
 * @date 2021/9/8
 */
@Configuration
@ConditionalOnClass({SqlSessionFactory.class})
@EnableConfigurationProperties(ESIBatisProperties.class)
@ComponentScan(basePackages = "com.starter.common.lib")
public class EsIBatisAutoConfiguration implements InitializingBean {

    @Resource
    private ESIBatisProperties esiBatisProperties;

    @Bean(name = "esSqlSessionFactory")
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return new SqlSessionFactoryBuilder().build(esiBatisProperties);
    }

    public static class AutoConfiguredMapperScannerRegistrar implements EnvironmentAware, ImportBeanDefinitionRegistrar {

        private String basePackage;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
            builder.addPropertyValue("basePackage", basePackage);
            registry.registerBeanDefinition(MapperScannerConfigurer.class.getName(), builder.getBeanDefinition());
        }

        @Override
        public void setEnvironment(Environment environment) {
            this.basePackage = environment.getProperty("es-ibatis.base-package");
        }

    }

    @Configuration
    @Import(AutoConfiguredMapperScannerRegistrar.class)
    @ConditionalOnMissingBean({MapperFactoryBean.class, MapperScannerConfigurer.class})
    public static class MapperScannerRegistrarNotFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() {
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

