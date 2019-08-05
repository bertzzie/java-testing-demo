package tech.namas.demo.tests.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.namas.demo.tests.demo.models.Demo;
import tech.namas.demo.tests.demo.services.DemoService;

import java.util.List;

@RestController
public class DemoController {

    private DemoService service;

    @Autowired
    public DemoController(DemoService service) {
        this.service = service;
    }

    @GetMapping("/value")
    public Demo value() {
        return new Demo(0, "OK");
    }

    @GetMapping("/value/mono")
    public Mono<Demo> monoValue() {
        return Mono.create(sink -> sink.success(new Demo(1, "OK")));
    }

    @GetMapping("/service/value")
    public Demo serviceValue() {
        return service.value();
    }

    @GetMapping("/service/value/mono")
    public Mono<Demo> serviceMonoValue() {
        return service.monoValue();
    }

    @PostMapping("/service/values/{count}")
    public List<Demo> serviceValues(@PathVariable("count") Integer count) {
        return service.values(count);
    }

    @GetMapping("/service/values/{count}/flux")
    public Flux<Demo> serviceFluxValues(@PathVariable("count") Integer count) {
        return service.fluxValues(count);
    }

}
