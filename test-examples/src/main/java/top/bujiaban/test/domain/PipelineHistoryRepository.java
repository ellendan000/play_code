package top.bujiaban.test.domain;

import java.util.Collection;
import java.util.List;

public interface PipelineHistoryRepository {
    List<PipelineHistory> saveAll(Collection<PipelineHistory> entities);

    List<PipelineHistory> fetchAllExistsPipeline();
}
