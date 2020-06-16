package top.bujiaban.test.infrastructure.jpa;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import top.bujiaban.test.domain.EnvironmentHistory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EnvironmentHistoryJpaRepositoryTest extends DataJpaTestBase {

    @Autowired
    private EnvironmentHistoryJpaRepository repository;

    @Test
    @Sql("/sql/prepare_data_for_fetch_latest_upload_info.sql")
    void shouldFetchLatestAllCorrectly() {
        List<EnvironmentHistory> environmentHistoryList = repository.fetchLatestAll(1L, 2L);

        assertThat(environmentHistoryList).extracting("originPipelineName",
                "environmentName", "originPipelineHistorySequence").containsOnly(
                Tuple.tuple("lead-management-normal-master", "ci", 847L),
                Tuple.tuple("lead-management-normal-master", "qa", 846L),
                Tuple.tuple("lead-management-normal-release", "uat", 843L)
        );
    }
}