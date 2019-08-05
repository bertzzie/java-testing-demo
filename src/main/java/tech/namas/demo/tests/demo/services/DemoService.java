package tech.namas.demo.tests.demo.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.namas.demo.tests.demo.models.Demo;

import java.util.List;

public interface DemoService {

    Demo value();

    Mono<Demo> monoValue();

    List<Demo> values(Integer count);

    Flux<Demo> fluxValues(Integer count);
}
