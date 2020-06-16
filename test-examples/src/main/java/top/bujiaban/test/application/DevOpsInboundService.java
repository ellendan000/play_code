package top.bujiaban.test.application;

import org.springframework.stereotype.Service;
import top.bujiaban.test.domain.EnvironmentHistory;
import top.bujiaban.test.domain.EnvironmentHistoryRepository;
import top.bujiaban.test.domain.PipelineHistory;
import top.bujiaban.test.domain.PipelineHistoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class DevOpsInboundService {

    private PipelineHistoryRepository pipelineHistoryRepository;
    private EnvironmentHistoryRepository environmentHistoryRepository;

    public DevOpsInboundService(PipelineHistoryRepository pipelineHistoryRepository, EnvironmentHistoryRepository environmentHistoryRepository) {
        this.pipelineHistoryRepository = pipelineHistoryRepository;
        this.environmentHistoryRepository = environmentHistoryRepository;
    }

    @Transactional
    public void inboundData(List<EnvironmentHistoryCommand> commands) {
        List<PipelineHistory> pipelineHistories = newArrayList();
        List<EnvironmentHistory> environmentHistories = newArrayList();

        for (EnvironmentHistoryCommand command : commands) {
            Optional<EnvironmentHistory> environmentHistoryOptional = command.mapEnvironmentHistory();
            if (!environmentHistoryOptional.isPresent()) {
                continue;
            }
            environmentHistories.add(environmentHistoryOptional.get());

            Optional<PipelineHistory> pipelineHistoryOptional = command.mapPipelineHistory();
            if (pipelineHistoryOptional.isPresent()) {
                PipelineHistory p = pipelineHistoryOptional.get();
                environmentHistoryOptional.get().setPipelineHistoryId(p.getId());
                pipelineHistories.add(p);
            }
        }

        if(pipelineHistories.size() > 0){
            pipelineHistoryRepository.saveAll(pipelineHistories);
        }
        if(environmentHistories.size() > 0){
            environmentHistoryRepository.saveAll(environmentHistories);

        }
    }

    public Set<LatestPipelineInfoDTO> fetchLatestUploadInfo(Long customerId, Long projectId) {
        List<EnvironmentHistory> environmentHistories = environmentHistoryRepository.fetchLatestAll(customerId, projectId);
        return LatestPipelineInfoDTO.fromEnvironmentHistory(environmentHistories);
    }
}
