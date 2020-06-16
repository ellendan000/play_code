package top.bujiaban.test.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.bujiaban.test.domain.PipelineHistory;

import java.util.List;

public interface PipelineHistoryJpaRepository extends
        JpaRepository<PipelineHistory, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM pipeline_histories p, " +
            "(SELECT max(id) AS id FROM pipeline_histories " +
            "GROUP BY customer_id, project_id, pipeline_name) tmp " +
            "WHERE p.id = tmp.id " +
            "ORDER BY customer_id, project_id, pipeline_name")
    public List<PipelineHistory> fetchAllExistsPipeline();
}
