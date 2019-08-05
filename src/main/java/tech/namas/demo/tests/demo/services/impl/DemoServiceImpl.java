package tech.namas.demo.tests.demo.services.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.namas.demo.tests.demo.models.Demo;
import tech.namas.demo.tests.demo.services.DemoService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public Demo value() {
        return new Demo(1, "FROM SERVICE");
    }

    @Override
    public Mono<Demo> monoValue() {
        return Mono.create(sink -> sink.success(new Demo(2, "FROM SERVICE MONO")));
    }

    @Override
    public List<Demo> values(Integer count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Demo(i, "FROM SERVICE VALUES"))
                .collect(Collectors.toList());
    }

    @Override
    public Flux<Demo> fluxValues(Integer count) {
        return Flux.create(emitter -> {
            IntStream.range(0, count)
                     .forEach(i -> emitter.next(new Demo(i, "FROM SERVICE FLUX VALUES")));
            emitter.complete();
        });
    }
}
