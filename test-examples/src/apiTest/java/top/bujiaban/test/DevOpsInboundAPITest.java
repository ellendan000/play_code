package top.bujiaban.test;

import com.google.common.collect.Sets;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import top.bujiaban.test.interfaces.LatestPipelineInfoResponse;

public class DevOpsInboundAPITest extends APITestBase {

    @Test
    @Sql("/sql/prepare_data_for_fetch_latest_upload_info.sql")
    void shouldFetchLatestInfoSuccessful() {
        LatestPipelineInfoResponse[] result = given().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
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

        Assertions.assertThat(result).containsOnly(
                new LatestPipelineInfoResponse("test_pipeline_name", Sets.newHashSet(
                        new LatestPipelineInfoResponse.LatestEnvironmentInfo("ci", 123L)
                ))
        );

    }
}
