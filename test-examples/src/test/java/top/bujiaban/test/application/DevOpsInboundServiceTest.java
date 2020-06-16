package top.bujiaban.test.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import top.bujiaban.test.domain.EnvironmentHistory;
import top.bujiaban.test.domain.EnvironmentHistoryRepository;
import top.bujiaban.test.domain.PipelineHistoryRepository;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DevOpsInboundServiceTest {

    @InjectMocks
    private DevOpsInboundService service;
    @Mock
    private EnvironmentHistoryRepository environmentHistoryRepository;
    @Mock
    private PipelineHistoryRepository pipelineHistoryRepository;

    @Test
    void shouldSaveDataCorrectlyWhenCommandIsInvalid() {
        this.service.inboundData(this.givenInvalidCommands());
    }

    @Test
    void shouldSaveDataCorrectlyWhenCommandMissingPipeline() {
        when(environmentHistoryRepository.saveAll(anyCollection())).thenReturn(null);
        this.service.inboundData(this.givenMissingPipelineCommands());

        verify(environmentHistoryRepository, times(1)).saveAll(argThat(
                l -> l.size() == 1
        ));
    }

    @Test
    void shouldSaveDataCorrectlyWhenCommandIsGivenEntireCommands() {
        when(pipelineHistoryRepository.saveAll(anyCollection())).thenReturn(null);
        when(environmentHistoryRepository.saveAll(anyCollection())).thenReturn(null);

        this.service.inboundData(this.givenEntireCommands());

        verify(pipelineHistoryRepository, times(1)).saveAll(argThat(
                l -> l.size() == 1
        ));

        ArgumentCaptor<List<EnvironmentHistory>> captor = ArgumentCaptor.forClass(List.class);
        verify(environmentHistoryRepository, times(1)).saveAll(captor.capture());
        assertThat(captor.getValue())
                .hasSize(1)
                .extracting("pipelineHistoryId")
                .isNotEmpty();
    }

    List<EnvironmentHistoryCommand> givenInvalidCommands() {
        return newArrayList(
                EnvironmentHistoryCommand.builder()
                        .customerId(1L)
                        .projectId(2L)
                        .envName("qa")
                        .sequence("1-1")
                        .status("Unknown")
                        .build()
        );
    }

    List<EnvironmentHistoryCommand> givenEntireCommands() {
        return newArrayList(
                EnvironmentHistoryCommand.builder()
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
                        .build()
        );
    }

    List<EnvironmentHistoryCommand> givenMissingPipelineCommands() {
        return newArrayList(
                EnvironmentHistoryCommand.builder()
                        .customerId(1L)
                        .projectId(2L)
                        .envName("uat")
                        .sequence("1-1")
                        .status("Failed")
                        .triggeredTime(new Date())
                        .endTime(new Date())
                        .pipelineHistorySequence(-1L)
                        .originPipelineName("test-service-release")
                        .originPipelineHistorySequence(3L)
                        .build()
        );
    }

}