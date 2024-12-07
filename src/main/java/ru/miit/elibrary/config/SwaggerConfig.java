package ru.miit.elibrary.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(
        title = "E-Library API",
        description = "API электронной библиотеки",
        version = "0.0.1",
        contact = @io.swagger.v3.oas.annotations.info.Contact(
                name = "Schelkov Andrey",
                email = "bitoche@vk.ru"
        )
))
@EnableTransactionManagement
public class SwaggerConfig{
    @Value("${application.version}")
    private String buildVersion;
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("E-Library API")
                        .version(buildVersion)
                        .contact(new Contact()
                                .name("Schelkov Andrey")
                                .email("bitoche@vk.ru")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("DevEnv"),
                        new Server()
                                .url("http://localhost:8082")
                                .description("BetaEnv")));
    }
}
