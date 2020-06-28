package top.bujiaban.test.contracts;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("contractTest")
public class TestBase {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
        TestSecurityContextHolder.clearContext();

    }

    protected void mockCurrentUser() {
        TestSecurityContextHolder.setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "", "",
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));
    }
}
