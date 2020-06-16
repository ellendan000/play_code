package top.bujiaban.test.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EnvironmentHistoryRequest {
    private String sequence;
    @JsonProperty("name")
    @NotNull
    private String envName;
    private String status;
    private Date triggeredTime;
    private Date endTime;
    private String pipelineName;
    private Long pipelineHistorySequence;
    private String originPipelineName;
    private Long originPipelineHistorySequence;
}
