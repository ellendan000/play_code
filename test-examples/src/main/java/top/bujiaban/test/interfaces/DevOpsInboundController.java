package top.bujiaban.test.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.bujiaban.test.application.DevOpsInboundService;
import top.bujiaban.test.application.EnvironmentHistoryCommand;
import top.bujiaban.test.application.LatestPipelineInfoDTO;

import java.util.List;
import java.util.Set;

@RestController
@PreAuthorize("hasRole('USER')")
public class DevOpsInboundController {
    private DevOpsInboundService service;

    public DevOpsInboundController(DevOpsInboundService service) {
        this.service = service;
    }

    @PostMapping("/customers/{customerId}/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    void uploadFromCollector(@PathVariable Long customerId, @PathVariable Long projectId,
                             @RequestBody List<EnvironmentHistoryRequest> data){
        List<EnvironmentHistoryCommand> commandList = EnvironmentHistoryMapper.MAPPER.toCommandList(data, customerId, projectId);
        this.service.inboundData(commandList);
    }

    @GetMapping("/customers/{customerId}/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    Set<LatestPipelineInfoResponse> fetchLatestUploadInfo(@PathVariable Long customerId, @PathVariable Long projectId){
        Set<LatestPipelineInfoDTO> dtos = this.service.fetchLatestUploadInfo(customerId, projectId);
        return LatestPipelineInfoResponseMapper.MAPPER.fromDto(dtos);
    }
}
