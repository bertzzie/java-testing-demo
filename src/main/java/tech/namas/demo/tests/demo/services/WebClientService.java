package tech.namas.demo.tests.demo.services;

import reactor.core.publisher.Mono;
import tech.namas.demo.tests.demo.models.Demo;

public interface WebClientService {

    Mono<Demo> consume();
}
