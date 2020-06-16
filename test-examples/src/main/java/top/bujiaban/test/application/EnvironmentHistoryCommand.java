package top.bujiaban.test.application;

import lombok.*;
import top.bujiaban.test.domain.EnvStatus;
import top.bujiaban.test.domain.EnvironmentHistory;
import top.bujiaban.test.domain.PipelineHistory;

import java.util.Date;
import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnvironmentHistoryCommand {

    private Long customerId;
    private Long projectId;
    private String sequence;
    private String envName;
    private String status;
    private Date triggeredTime;
    private Date endTime;
    private String pipelineName;
    private Long pipelineHistorySequence;
    private String originPipelineName;
    private Long originPipelineHistorySequence;

    Optional<PipelineHistory> mapPipelineHistory() {
        if (this.pipelineHistorySequence < 0) {
            return Optional.empty();
        }

        PipelineHistory pipelineHistory = PipelineHistory.builder()
                .pipelineName(this.pipelineName)
                .sequence(this.pipelineHistorySequence)
                .customerId(this.customerId)
                .projectId(this.projectId)
                .build();
        pipelineHistory.setId(pipelineHistory.generateId());
        return Optional.of(pipelineHistory);
    }

    Optional<EnvironmentHistory> mapEnvironmentHistory() {
        if (EnvironmentHistory.isInvalid(this.status)) {
            return Optional.empty();
        }

        EnvironmentHistory environmentHistory = EnvironmentHistory.builder()
                .customerId(this.customerId)
                .projectId(this.projectId)
                .environmentName(this.envName)
                .sequence(this.sequence)
                .status(EnvStatus.valueOf(this.getStatus().toUpperCase()))
                .triggeredTime(this.triggeredTime)
                .endTime(this.endTime)
                .originPipelineName(this.originPipelineName)
                .originPipelineHistorySequence(this.originPipelineHistorySequence)
                .build();
        environmentHistory.setId(environmentHistory.generateId());
        return Optional.of(environmentHistory);
    }
}
