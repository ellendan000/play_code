package top.bujiaban.test.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import top.bujiaban.test.domain.EnvironmentHistory;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

class LatestPipelineInfoDTOTest {

    @Test
    void shouldParseEnvironmentHistoriesCorrectly() {
        List<EnvironmentHistory> givenList = newArrayList(
                EnvironmentHistory.builder()
                        .originPipelineName("go_service")
                        .environmentName("ci")
                        .originPipelineHistorySequence(123L)
                        .build(),
                EnvironmentHistory.builder()
                        .originPipelineName("go_service")
                        .environmentName("qa")
                        .originPipelineHistorySequence(122L)
                        .build(),
                EnvironmentHistory.builder()
                        .originPipelineName("test_service")
                        .environmentName("ci")
                        .originPipelineHistorySequence(79L)
                        .build());

        Set<LatestPipelineInfoDTO> result = LatestPipelineInfoDTO.fromEnvironmentHistory(givenList);
        Assertions.assertThat(result).containsOnly(
                LatestPipelineInfoDTO.builder()
                        .pipelineName("go_service")
                        .environments(newHashSet(
                                new LatestPipelineInfoDTO.LatestEnvironmentInfo("ci", 123L),
                                new LatestPipelineInfoDTO.LatestEnvironmentInfo("qa", 122L))
                        )
                        .build(),
                LatestPipelineInfoDTO.builder()
                        .pipelineName("test_service")
                        .environments(newHashSet(
                                new LatestPipelineInfoDTO.LatestEnvironmentInfo("ci", 79L)
                        ))
                        .build()
        );
    }
}