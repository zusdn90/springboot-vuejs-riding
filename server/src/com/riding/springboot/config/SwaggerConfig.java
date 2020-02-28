package com.riding.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
//@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/dist/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/dist/webjars/");
    }


    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .useDefaultResponseMessages(false)  // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("Internal Server Error")
                                        .responseModel(new ModelRef("Error"))
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(400)
                                        .message("Bad Request")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("Not Found")
                                        .build()));
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Spring API Documentation")
                .description("따릉이 프로젝트 REST API 문서입니다.")
                .license("riding").licenseUrl("https://github.com/zusdn90/springboot-vue-riding").version("1").build();
    }
}
