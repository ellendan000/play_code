package top.bujiaban.flink.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Event implements Serializable {
    private String eventId;
    private String eventType;
    private String eventData;
    private Integer eventStatus;
    private String createBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;
    private Integer lastUpdateVersion;
}
