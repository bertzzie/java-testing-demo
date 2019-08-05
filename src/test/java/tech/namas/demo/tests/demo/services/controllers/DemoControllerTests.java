package tech.namas.demo.tests.demo.services.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import tech.namas.demo.tests.demo.models.Demo;
import tech.namas.demo.tests.demo.services.DemoService;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerTests {

    @Value("${local.server.port}")
    private int port;

    @MockBean
    private DemoService service;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        RestAssured.port = port;

        mapper = new ObjectMapper();
    }

    @After
    public void cleanup() {
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testValue() throws Exception {
        given()
            .when()
            .get("/value")
            .then()
            .body(equalTo(mapper.writeValueAsString(new Demo(0, "OK"))));
    }

    @Test
    public void testMonoValue() throws Exception {
        given()
            .when()
            .get("/value/mono")
            .then()
            .body(equalTo(mapper.writeValueAsString(new Demo(1, "OK"))));
    }

    @Test
    public void testServiceValue() throws Exception {
        Demo expected = new Demo(1000, "DEMO");
        when(service.value()).thenReturn(expected);

        given()
            .when()
            .get("/service/value")
            .then()
            .body(equalTo(mapper.writeValueAsString(expected)));

        verify(service).value();
    }

    @Test
    public void testServiceValues() throws Exception {
        List<Demo> expected = Collections.singletonList(new Demo(50, "DEDEDEMO"));
        int count = 50;

        when(service.values(eq(count))).thenReturn(expected);

        given()
            .when()
            .post("/service/values/" + count)
            .then()
            .body(equalTo(mapper.writeValueAsString(expected)));

        verify(service).values(eq(count));
    }
}
