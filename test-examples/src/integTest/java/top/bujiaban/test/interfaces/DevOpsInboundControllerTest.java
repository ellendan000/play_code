package top.bujiaban.test.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import top.bujiaban.test.application.DevOpsInboundService;
import top.bujiaban.test.application.LatestPipelineInfoDTO;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@WebMvcTest(controllers = DevOpsInboundController.class)
class DevOpsInboundControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DevOpsInboundService service;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    protected void setUp() {
        RestAssuredMockMvc.webAppContextSetup(context);
        io.restassured.module.mockmvc.RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldUploadFromCollectorCorrectly() throws JsonProcessingException {
        List<EnvironmentHistoryRequest> requests = newArrayList(
                EnvironmentHistoryRequest.builder()
                        .envName("ci")
                        .sequence("1-1")
                        .originPipelineName("test_service")
                        .originPipelineHistorySequence(123L)
                .build()
        );
        doNothing().when(service).inboundData(any());

        given().header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .auth()
                .principalWithCredentials("", "", "ROLE_USER")
                .body(objectMapper.writeValueAsString(requests))
                .when()
                .post("/customers/{customerId}/projects/{projectId}", 1, 2)
                .then()
                .statusCode(200);

    }

    @Test
    void shouldFetchLatestInfoCorrectly() throws Exception {
        Set<LatestPipelineInfoDTO> givenDto = newHashSet(
                LatestPipelineInfoDTO.builder()
                        .pipelineName("test_pipeline_name")
                        .environments(newHashSet(
                                new LatestPipelineInfoDTO.LatestEnvironmentInfo("ci", 123L)
                        ))
                        .build()
        );
        when(service.fetchLatestUploadInfo(any(), any())).thenReturn(givenDto);

        LatestPipelineInfoResponse[] result = given().header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .auth()
                .principalWithCredentials("", "", "ROLE_USER")
                .when()
                .get("/customers/{customerId}/projects/{projectId}", 1, 2)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(LatestPipelineInfoResponse[].class);

        assertThat(result).containsOnly(
                new LatestPipelineInfoResponse("test_pipeline_name", newHashSet(
                        new LatestPipelineInfoResponse.LatestEnvironmentInfo("ci", 123L)
                ))
        );
    }
}