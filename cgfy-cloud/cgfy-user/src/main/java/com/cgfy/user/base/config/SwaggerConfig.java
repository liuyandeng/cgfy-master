package com.cgfy.user.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/actuator.*")))//健康信息
                .build();
    }
    //构建 api文档的详细信息函数
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                // 页面标题
                .title("user RESTful API")
                // 创建人信息
                .contact(new Contact("admin",  "http://www.cgfytop.cn",  "877507054@qq.com"))
                //Terms of service
                .termsOfServiceUrl("http://www.baidu.com")
                // 描述
                .description("用户管理的基础操作api.")
                // 版本号
                .version("1.0")
                .build();
    }

}