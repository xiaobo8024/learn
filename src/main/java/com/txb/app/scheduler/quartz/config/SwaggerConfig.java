package com.txb.app.scheduler.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author shiva   2021/8/10 23:46
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("任务调度接口").apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("cn.shiva.quartz.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Quartz 任务调度框架")
                .description("任务调度模块接口")
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .build();
    }

}
