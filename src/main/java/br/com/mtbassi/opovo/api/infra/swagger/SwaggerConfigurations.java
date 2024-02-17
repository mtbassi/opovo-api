package br.com.mtbassi.opovo.api.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(info());
    }

    private Info info() {
        return new Info().title("API O Povo").description("Service for newspaper journalists").version("v0.0.1").license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")).contact(contact());
    }

    private Contact contact() {
        return new Contact().name("Matheus Bassi").email("bassi.matheus@outlook.com").url("https://www.linkedin.com/in/matheusbassi/");
    }
}