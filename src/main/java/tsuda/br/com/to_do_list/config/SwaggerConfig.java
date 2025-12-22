package tsuda.br.com.to_do_list.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("To do list API")
                .version("1.0.0")
                .description("Documentação da API");

        return new OpenAPI()
                .info(info);
    }
}
