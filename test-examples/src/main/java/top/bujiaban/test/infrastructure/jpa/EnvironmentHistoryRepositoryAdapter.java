package top.bujiaban.test.infrastructure.jpa;

import org.springframework.stereotype.Repository;
import top.bujiaban.test.domain.EnvironmentHistory;
import top.bujiaban.test.domain.EnvironmentHistoryRepository;

import java.util.Collection;
import java.util.List;

@Repository
public class EnvironmentHistoryRepositoryAdapter implements EnvironmentHistoryRepository {
    private EnvironmentHistoryJpaRepository repository;

    public EnvironmentHistoryRepositoryAdapter(EnvironmentHistoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EnvironmentHistory> saveAll(Collection<EnvironmentHistory> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public List<EnvironmentHistory> fetchLatestAll(Long customerId, Long projectId) {
        return repository.fetchLatestAll(customerId, projectId);
    }


}
