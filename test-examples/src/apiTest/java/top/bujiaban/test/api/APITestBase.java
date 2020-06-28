package top.bujiaban.test.api;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("apiTest")
public class APITestBase {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
        TestSecurityContextHolder.clearContext();
    }

    protected MockMvcRequestSpecification given() {
        return RestAssuredMockMvc.given()
                .auth()
                .with(SecurityMockMvcRequestPostProcessors.testSecurityContext())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }
}
