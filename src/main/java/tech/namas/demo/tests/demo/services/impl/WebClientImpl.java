package tech.namas.demo.tests.demo.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tech.namas.demo.tests.demo.models.Demo;
import tech.namas.demo.tests.demo.properties.WebClientProperties;
import tech.namas.demo.tests.demo.services.WebClientService;

@Service
public class WebClientImpl implements WebClientService {

    private WebClient webClient;

    private WebClientProperties properties;

    public WebClientImpl(WebClient webClient, WebClientProperties properties) {
        this.webClient = webClient;
        this.properties = properties;
    }

    @Override
    public Mono<Demo> consume() {
        return this.webClient.get()
            .uri(properties.getBaseUrl() + properties.getMonoValueUrl())
            .exchange()
            .flatMap(i -> i.bodyToMono(Demo.class));
    }
}
