package top.bujiaban.test.domain;

import com.google.common.base.Strings;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static com.google.common.collect.Lists.newArrayList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "environment_histories")
public class EnvironmentHistory {

    @Id
    private String id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "pipeline_history_id")
    private String pipelineHistoryId;

    @Column(name = "env_name")
    private String environmentName;
    private String sequence;

    @Enumerated(EnumType.STRING)
    private EnvStatus status;

    @Column(name = "triggered_time")
    private Date triggeredTime;
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "origin_pipeline_name")
    private String originPipelineName;
    @Column(name = "origin_pipeline_history_sequence")
    private Long originPipelineHistorySequence;

    public String generateId() {
        return String.join("-",
                this.customerId.toString(),
                this.projectId.toString(),
                this.originPipelineName,
                this.originPipelineHistorySequence.toString(),
                this.environmentName);
    }

    public static boolean isInvalid(String status) {
        return Strings.isNullOrEmpty(status)
                || status.equalsIgnoreCase(EnvStatus.UNKNOWN.name());
    }

    public static boolean isResultStatus(EnvStatus status) {
        return newArrayList(EnvStatus.PASSED, EnvStatus.FAILED).contains(status);
    }
}
