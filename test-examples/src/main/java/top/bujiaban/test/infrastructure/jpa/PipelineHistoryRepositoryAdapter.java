package top.bujiaban.test.infrastructure.jpa;

import org.springframework.stereotype.Repository;
import top.bujiaban.test.domain.PipelineHistory;
import top.bujiaban.test.domain.PipelineHistoryRepository;

import java.util.Collection;
import java.util.List;


@Repository
public class PipelineHistoryRepositoryAdapter implements PipelineHistoryRepository {
    private PipelineHistoryJpaRepository repository;

    public PipelineHistoryRepositoryAdapter(PipelineHistoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PipelineHistory> saveAll(Collection<PipelineHistory> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public List<PipelineHistory> fetchAllExistsPipeline() {
        return repository.fetchAllExistsPipeline();
    }
}
