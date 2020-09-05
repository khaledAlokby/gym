package com.wix.gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class GymApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.wix.gym")).build().apiInfo(new ApiInfo("Gym API", "System for a fitness buisness"
                        , "1.0.0", "Free to use", new Contact("Khaled", "Alokby", "khaledalokby@gmail.com"), "API License", "https://www.wix.com/", Collections.emptyList()));
    }

}
