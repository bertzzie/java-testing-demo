package tech.namas.demo.tests.demo.services.impl;

import org.junit.Before;
import org.junit.Test;
import reactor.test.StepVerifier;
import tech.namas.demo.tests.demo.models.Demo;
import tech.namas.demo.tests.demo.services.DemoService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DemoServiceImplTests {

    private DemoService service;

    @Before
    public void setup() {
        this.service = new DemoServiceImpl();
    }

    @Test
    public void testValue() {
        Demo result = this.service.value();

        assertEquals(1, result.getValue().intValue());
        assertEquals("FROM SERVICE", result.getStatus());
    }

    @Test
    public void testValues() {
        List<Demo> results = this.service.values(10);

        assertEquals(10, results.size());
        for (int i = 0; i < 10; i++) {
            Demo result = results.get(i);
            assertNotNull(result);
            assertEquals(i, result.getValue().intValue());
            assertEquals("FROM SERVICE VALUES", result.getStatus());
        }
    }

    @Test
    public void testMonoValue() {
        StepVerifier.create(this.service.monoValue())
            .expectNext(new Demo(2, "FROM SERVICE MONO"))
            .verifyComplete();
    }

    @Test
    public void testFluxValues() {
        Integer count = 10;

        List<Demo> expectedResult = IntStream.range(0, count)
            .mapToObj(i -> new Demo(i, "FROM SERVICE FLUX VALUES"))
            .collect(Collectors.toList());

        StepVerifier.create(this.service.fluxValues(count))
            .expectNext(expectedResult.toArray(new Demo[]{}))
            .verifyComplete();

        StepVerifier.create(this.service.fluxValues(count))
            .expectNextCount(count)
            .verifyComplete();
    }
}
