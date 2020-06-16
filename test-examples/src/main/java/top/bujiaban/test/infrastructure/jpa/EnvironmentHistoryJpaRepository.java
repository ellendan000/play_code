package top.bujiaban.test.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.bujiaban.test.domain.EnvironmentHistory;

import java.util.List;

public interface EnvironmentHistoryJpaRepository extends
        JpaRepository<EnvironmentHistory, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM environment_histories e, (" +
            "SELECT origin_pipeline_name, env_name, max(origin_pipeline_history_sequence) max_sequence " +
            "FROM environment_histories " +
            "WHERE customer_id = :customerId AND project_id = :projectId " +
            "GROUP BY origin_pipeline_name, env_name " +
            ") tmp " +
            "WHERE e.origin_pipeline_name = tmp.origin_pipeline_name " +
            "AND e.env_name = tmp.env_name " +
            "AND e.origin_pipeline_history_sequence = tmp.max_sequence")
    List<EnvironmentHistory> fetchLatestAll(Long customerId, Long projectId);
}
