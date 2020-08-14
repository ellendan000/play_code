package top.bujiaban.netflixcloud.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static org.hamcrest.Matchers.equalTo;

class InvokeControllerTest extends TestBase {
    @Autowired
    private WireMockServer wireMockServer;

    @BeforeEach
    protected void setUp() {
        wireMockServer.stubFor(WireMock.get(urlPathMatching("/users/\\d+/username"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("goodFriend")
                )
        );

        wireMockServer.stubFor(WireMock.get(urlPathMatching("/users/\\w+/username"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withFixedDelay(10000)
                )
        );

        wireMockServer.stubFor(WireMock.get(urlPathMatching("/users/_1/username"))
                .willReturn(aResponse()
                        .withStatus(404)
                )
        );
    }

    @AfterEach
    protected void tearDown() {
        wireMockServer.resetAll();
    }

    protected MockMvcRequestSpecification given() {
        return RestAssuredMockMvc.given();
    }

    @Test
    public void success() {
        given().when()
                .get("/users/{id}", 123)
                .then()
                .statusCode(200)
                .body(equalTo("goodFriend"));
    }

    @Test
    public void shouldInterruptedWhenTimeout() {
        given().when()
                .get("/users/{id}", "abc")
                .then()
                .statusCode(409);
    }
}