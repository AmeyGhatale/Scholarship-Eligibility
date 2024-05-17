package com.natwest.scholarshipEligibility.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.criteria.CriteriaBuilder;


@OpenAPIDefinition(
        info = @Info(
            title = "Scholarship Eligibility",
            description = "Students eligibility for scholarship program" ,
                   contact = @Contact(
                    name = "Amey Ghatale",
                    email = "ameyghatale2016@gmail.com"
                   ) ,
                    version = "v1"
        )
)


public class OpenAPIConfig{
}





















//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.service.Contact;
//
//
//@Configuration
//@EnableSwagger2
//public class OpenAPIConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.natwest.scholarshipEligibility.Controller"))
//                .paths(PathSelectors.any())
//                .build().apiInfo(metaData())
//                .useDefaultResponseMessages(false);
//    }
//
//    private ApiInfo metaData() {
//        return new ApiInfoBuilder()
//                .title("Scholarship Eligibility")
//                .description("Students eligibility for scholarship program")
//                .version("version 1.0")
//                .contact(new Contact("Amey Ghatale", "","ameyghatale2016@gmail.com")).build();
//    }
//}
//
//
