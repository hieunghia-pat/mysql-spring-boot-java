package uit.java.mysql.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "uit.java.mysql")
@PropertySource("classpath:application.properties")
@Slf4j
public class AppConfig {

    @Value("${json-directory}")
    public String jsonDirectory;

    @Bean
    public String getJsonDirectory() {
        return jsonDirectory;
    }
}
