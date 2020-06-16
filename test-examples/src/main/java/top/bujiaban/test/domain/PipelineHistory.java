package top.bujiaban.test.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pipeline_histories")
public class PipelineHistory {
    @Id
    private String id;
    @Column(name = "pipeline_name")
    private String pipelineName;
    private Long sequence;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "project_id")
    private Long projectId;

    public String generateId(){
        return UUID.randomUUID().toString();
    }
}
