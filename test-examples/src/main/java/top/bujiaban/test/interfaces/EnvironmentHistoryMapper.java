package top.bujiaban.test.interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.bujiaban.test.application.EnvironmentHistoryCommand;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface EnvironmentHistoryMapper {
    EnvironmentHistoryMapper MAPPER = Mappers.getMapper(EnvironmentHistoryMapper.class);

    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "projectId", source = "projectId")
    EnvironmentHistoryCommand toCommand(EnvironmentHistoryRequest request, Long customerId, Long projectId);

    default List<EnvironmentHistoryCommand> toCommandList(List<EnvironmentHistoryRequest> requests, Long customerId, Long projectId){
        return requests.stream().map(c -> MAPPER.toCommand(c, customerId, projectId)).collect(Collectors.toList());
    }
}
