package pe.programadoredeslavadoras.ficuniverse.shared.openapi;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI chaptersOpenApi (
            @Value("${documentation.application.description}") String applicationDescription,
            @Value("${documentation.application.version}") String applicationVersion){
        return new OpenAPI().info(new Info()
                .title("FicUniverse API v1")
                .version(applicationVersion)
                .description(applicationDescription)
                .termsOfService("https://www.ficuniverse.pe/tos")
                .license(new License().name("Apache 2.0 License").url("https://www.ficuniverse.pe/license"))
                .contact(new Contact()
                        .url("https://github.com/Programadores-de-lavadoras/back-ficuniverse")
                        .name("back-ficuniverse"))
        );
    }
}
