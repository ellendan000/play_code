package top.bujiaban.netflixcloud.controller;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(initializers = WireMockContextInitializer.class)
public class TestBase {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void superSetup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    protected MockMvcRequestSpecification given() {
        return RestAssuredMockMvc.given();
    }
}
