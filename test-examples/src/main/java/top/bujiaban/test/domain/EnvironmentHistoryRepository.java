package top.bujiaban.test.domain;

import java.util.Collection;
import java.util.List;

public interface EnvironmentHistoryRepository {
    List<EnvironmentHistory> saveAll(Collection<EnvironmentHistory> entities);

    List<EnvironmentHistory> fetchLatestAll(Long customerId, Long projectId);
}
