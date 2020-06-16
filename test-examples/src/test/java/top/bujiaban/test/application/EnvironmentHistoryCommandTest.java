package top.bujiaban.test.application;

import org.junit.jupiter.api.Test;
import top.bujiaban.test.domain.EnvStatus;
import top.bujiaban.test.domain.EnvironmentHistory;
import top.bujiaban.test.domain.PipelineHistory;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class EnvironmentHistoryCommandTest {

    private EnvironmentHistoryCommand command = EnvironmentHistoryCommand.builder()
            .customerId(1L)
            .projectId(2L)
            .envName("ci")
            .sequence("1-1")
            .status("Failed")
            .triggeredTime(new Date())
            .endTime(new Date())
            .pipelineName("test-service")
            .pipelineHistorySequence(33L)
            .originPipelineName("test-service-master")
            .originPipelineHistorySequence(33L)
            .build();

    @Test
    void shouldReturnPipelineHistoryWhenPipelineSequenceGTZero() {
        assertThat(this.command.mapPipelineHistory()).isPresent()
                .get()
                .isEqualToIgnoringGivenFields(
                        PipelineHistory.builder()
                                .pipelineName("test-service")
                                .sequence(33L)
                                .customerId(1L)
                                .projectId(2L)
                                .build()
                        , "id"
                );
    }

    @Test
    void shouldReturnEmptyWhenPipelineSequenceLTZero() {
        this.command.setPipelineHistorySequence(-1L);
        assertThat(this.command.mapPipelineHistory()).isNotPresent();
    }

    @Test
    void shouldMapEnvironmentHistoryCorrectly() {
        assertThat(this.command.mapEnvironmentHistory()).isPresent()
                .get()
                .isEqualToIgnoringGivenFields(
                        EnvironmentHistory.builder()
                                .customerId(1L)
                                .projectId(2L)
                                .environmentName("ci")
                                .sequence("1-1")
                                .status(EnvStatus.FAILED)
                                .triggeredTime(command.getTriggeredTime())
                                .endTime(command.getEndTime())
                                .originPipelineName("test-service-master")
                                .originPipelineHistorySequence(33L)
                                .id("1-2-test-service-master-33-ci")
                                .build()
                );
    }

    @Test
    void shouldReturnEmptyWhenStatusIsUnknown() {
        this.command.setStatus("Unknown");
        assertThat(this.command.mapEnvironmentHistory()).isNotPresent();
    }
}