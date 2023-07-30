package com.kb.java.university.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${kb.openapi.dev-url}")
    private String devUrl;
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("krzysztofbledzki@gmail.com");
        contact.setName("Krzysztof Bledzki");
        contact.setUrl("https://github.com/kbledzki");

        Info info = new Info()
                .title("Univeristy RestAPI")
                .version("1.0")
                .contact(contact)
                .description("This API for check grades by students and teachers.");
        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}