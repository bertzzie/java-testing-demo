package tech.namas.demo.tests.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "webclient")
public class WebClientProperties {

    private String baseUrl;

    private String monoValueUrl;
}
