package cn.telling.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by Liupd on 15-12-31.
 **/
@Configuration
@EnableSwagger2
public class ApplicationSwaggerConfig{

    public Docket configSpringfoxDocketForAll(){
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json", "application/xml"))
                .consumes(Sets.newHashSet("application/json", "application/xml"))
                .protocols(Sets.newHashSet("http"/*, "https"*/))
                .forCodeGeneration(true)
                .select().paths(regex(".*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "SpringMVC REST API文档",
                "Fancy Api文档",
                "1.0.0",
                "http://localhost:8188/Fancy/v2/api-docs",
                "http://github.com/zhgo1116",
                "Apache License",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
        return apiInfo;
    }

}
