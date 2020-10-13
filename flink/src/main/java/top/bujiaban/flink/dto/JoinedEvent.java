package top.bujiaban.flink.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class JoinedEvent {
    private String eventId;
    private String eventType;
    private String eventData;
    private Date creationDate;

    private Boolean isConsumed;
    private Date consumedDate;
}
