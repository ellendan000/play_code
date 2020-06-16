package top.bujiaban.test.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import top.bujiaban.test.domain.EnvironmentHistory;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LatestPipelineInfoDTO {
    @NotNull
    private final String pipelineName;
    @Builder.Default
    private Set<LatestEnvironmentInfo> environments = newHashSet();

    public static Set<LatestPipelineInfoDTO> fromEnvironmentHistory(List<EnvironmentHistory> environmentHistories) {
        Map<String, LatestPipelineInfoDTO> resultMap = newHashMap();

        environmentHistories.forEach(eh -> {
                    LatestEnvironmentInfo env = new LatestEnvironmentInfo(eh.getEnvironmentName(), eh.getOriginPipelineHistorySequence());
                    if (!resultMap.containsKey(eh.getOriginPipelineName())) {
                        resultMap.put(eh.getOriginPipelineName(), new LatestPipelineInfoDTO(eh.getOriginPipelineName()));
                    }

                    resultMap.get(eh.getOriginPipelineName()).addEnvironmentInfo(env);
                }
        );
        return newHashSet(resultMap.values());
    }

    public void addEnvironmentInfo(LatestEnvironmentInfo envInfo) {
        this.environments.add(envInfo);
    }

    @Data
    @AllArgsConstructor
    public static class LatestEnvironmentInfo {
        private String name;
        private Long sequence;
    }
}


