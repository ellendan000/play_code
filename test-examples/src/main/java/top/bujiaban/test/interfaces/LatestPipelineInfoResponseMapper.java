package top.bujiaban.test.interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.bujiaban.test.application.LatestPipelineInfoDTO;

import java.util.Set;

@Mapper
public interface LatestPipelineInfoResponseMapper {
    LatestPipelineInfoResponseMapper MAPPER = Mappers.getMapper(LatestPipelineInfoResponseMapper.class);

    @Mapping(target = "env", source = "environments")
    LatestPipelineInfoResponse fromDto(LatestPipelineInfoDTO dto);

    LatestPipelineInfoResponse.LatestEnvironmentInfo fromInnerDto(LatestPipelineInfoDTO.LatestEnvironmentInfo dto);

    Set<LatestPipelineInfoResponse> fromDto(Set<LatestPipelineInfoDTO> dtos);
}
