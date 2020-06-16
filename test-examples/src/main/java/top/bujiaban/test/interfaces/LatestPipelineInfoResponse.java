package top.bujiaban.test.interfaces;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class LatestPipelineInfoResponse {
    private String pipelineName;
    private Set<LatestEnvironmentInfo> env;

    @Data
    @Builder
    @AllArgsConstructor
    static class LatestEnvironmentInfo {
        private String name;
        private Long sequence;
    }
}




