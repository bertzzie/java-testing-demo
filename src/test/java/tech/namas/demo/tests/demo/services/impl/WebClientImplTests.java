package tech.namas.demo.tests.demo.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;
import tech.namas.demo.tests.demo.models.Demo;
import tech.namas.demo.tests.demo.properties.WebClientProperties;
import tech.namas.demo.tests.demo.services.WebClientService;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebClientImplTests {

    private final Integer PORT = 8080;
    private final String BASE_URL = "http://localhost:" + PORT;
    private final String MONO_VALUE_URL = "/test/values/mono";

    @MockBean
    private WebClientProperties properties;

    @Autowired
    private WebClientService clientService;

    @Rule
    public WireMockRule rule = new WireMockRule(options().port(PORT));

    @Value("${local.server.port}")
    private int port;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        when(properties.getBaseUrl()).thenReturn(BASE_URL);
        when(properties.getMonoValueUrl()).thenReturn(MONO_VALUE_URL);
    }

    @After
    public void cleanup() {
        verifyNoMoreInteractions(properties);
    }

    @Test
    public void test() throws Exception {
        rule.stubFor(
            get(urlPathEqualTo(MONO_VALUE_URL))
                .withHeader(HttpHeaders.ACCEPT, equalTo(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .willReturn(
                    aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBody(mapper.writeValueAsString(new Demo(1, "TEST")))
                )
        );

        StepVerifier.create(clientService.consume())
            .expectNext(new Demo(1, "TEST"))
            .verifyComplete();

        Mockito.verify(properties, times(2)).getBaseUrl();
        Mockito.verify(properties).getMonoValueUrl();
    }

}
