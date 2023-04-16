package com.samgyeobsal.config;import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
/**
 * @filename SwaggerConfig
 * @author 최태승
 * @since 2023.04.06
 * Swagger API를 활용한 API 문서 작업
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.04.06	최태승        최초 생성

 * </pre>
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {

        Server dev = new Server();
        dev.setUrl("devDomain");
//
//        Server local = new Server();
//        local.setUrl("localDomain");
//
//        Server prd = new Server();
//        prd.setUrl("prdDomain");

        OpenAPI openAPI = new OpenAPI()
                .info(new Info().title("TheChef Api Document")
                        .description("Integration Production application")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Progressive Production Processing Documentation")
                        .url("https://springshop.wiki.github.org/docs"));

//        openAPI.setServers(Arrays.asList(dev, local, prd));
        openAPI.setServers(Arrays.asList(dev));
        return openAPI;
    }
}
