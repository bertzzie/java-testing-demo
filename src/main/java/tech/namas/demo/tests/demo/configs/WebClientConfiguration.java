package tech.namas.demo.tests.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import tech.namas.demo.tests.demo.properties.WebClientProperties;

@Configuration
public class WebClientConfiguration {

    private WebClientProperties properties;

    public WebClientConfiguration(WebClientProperties properties) {
        this.properties = properties;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .baseUrl(properties.getBaseUrl())
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE)
            .build();
    }
}
