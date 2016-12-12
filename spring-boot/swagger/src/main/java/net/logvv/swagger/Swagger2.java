package net.logvv.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/* Swagger Configure class
 * 
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())  // 可选，设置api文档描述信息
                .select()            // 选择哪些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("net.logvv.swagger.web"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2构建RESTful APIS demo.")
                .description("更多使用方法参见官方文档")
                .termsOfServiceUrl("http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api")
                .license("The Apache License, Version 2.0")
                .licenseUrl("")
                .contact("http://swagger.io/")
                .version("2.0")
                .build();
    }
}
