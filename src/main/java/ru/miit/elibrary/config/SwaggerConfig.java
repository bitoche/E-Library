package ru.miit.elibrary.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.List;

import static io.swagger.v3.core.util.AnnotationsUtils.getInfo;

@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(
        title = "E-Library API",
        description = "API электронной библиотеки",
        version = "0.0.1",
        contact = @io.swagger.v3.oas.annotations.info.Contact(
                name = "Schelkov Andrey",
                email = "bitoche@vk.ru"
        )
    ),
    security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "basicAuth")
)
public class SwaggerConfig{
    private static final String AUTH_NAME = "basicAuth";
    private static final String DESCRIPTION = "API электронной библиотеки";
    private static final String VERSION = "0.0.1";
    private static final String TITLE = "E-Library API";
    @Value("${application.version}")
    private String buildVersion;
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("E-Library API")
                            .version(buildVersion)
                            .contact(new Contact()
                                    .name("Schelkov Andrey")
                                    .email("bitoche@vk.ru")))
                    .servers(List.of(
                            new Server().url("http://localhost:8080").description("DevEnv"),
                            new Server().url("http://localhost:8082").description("BetaEnv")))
                    .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                    .components(new Components().addSecuritySchemes(AUTH_NAME, new SecurityScheme()
                            .name(AUTH_NAME)
                            .type(SecurityScheme.Type.APIKEY)
                            .in(SecurityScheme.In.HEADER)))
                    .addSecurityItem(new SecurityRequirement().addList(AUTH_NAME)
                    ).info(getInfo());
        }

    private Info getInfo() {
        return new Info()
                .title(TITLE)
                .version(VERSION)
                .description(DESCRIPTION);
    }
}
